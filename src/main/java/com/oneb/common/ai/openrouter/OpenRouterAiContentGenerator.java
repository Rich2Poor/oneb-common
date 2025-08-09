package com.oneb.common.ai.openrouter;

import com.oneb.common.ai.AiContentGenerator;
import com.oneb.common.ai.AiContentRequest;
import com.oneb.common.ai.openrouter.dto.ChatCompletionRequest;
import com.oneb.common.ai.openrouter.dto.ChatCompletionResponse;
import com.oneb.common.ai.openrouter.dto.Message;
import com.oneb.common.config.properties.OpenRouterProperties;
import com.oneb.common.exception.AiContentGenerationException;
import com.oneb.common.exception.AiRateLimitException;
import com.oneb.common.exception.AiServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * OpenRouter.ai implementation of the AiContentGenerator interface.
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "app.common.ai.openrouter.enabled", havingValue = "true")
public class OpenRouterAiContentGenerator implements AiContentGenerator {

    private final WebClient webClient;
    private final OpenRouterProperties properties;

    public OpenRouterAiContentGenerator(WebClient.Builder webClientBuilder, OpenRouterProperties properties) {
        this.properties = properties;
        this.webClient = webClientBuilder
                .baseUrl(properties.getBaseUrl())
                .defaultHeader("Authorization", "Bearer " + properties.getApiKey())
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("HTTP-Referer", properties.getAppUrl() != null ? properties.getAppUrl() : "")
                .defaultHeader("X-Title", properties.getAppName() != null ? properties.getAppName() : "")
                .build();
    }

    @Override
    public Mono<String> generateContent(String prompt) {
        return generateContent(AiContentRequest.simple(prompt));
    }

    @Override
    public Mono<String> generateContent(String prompt, String model) {
        return generateContent(AiContentRequest.withModel(prompt, model));
    }

    @Override
    public Mono<String> generateContent(AiContentRequest request) {
        log.debug("Generating content with model: {}", 
                  request.getModel() != null ? request.getModel() : properties.getDefaultModel());

        ChatCompletionRequest apiRequest = buildApiRequest(request);
        
        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(apiRequest)
                .retrieve()
                .onStatus(this::isClientError, this::handleClientError)
                .onStatus(this::isServerError, this::handleServerError)
                .bodyToMono(ChatCompletionResponse.class)
                .map(response -> {
                    String content = response.getFirstChoiceContent();
                    if (content == null) {
                        throw new AiContentGenerationException("No content generated in response");
                    }
                    log.debug("Successfully generated content with {} tokens", 
                              response.getUsage() != null ? response.getUsage().getTotalTokens() : "unknown");
                    return content;
                })
                .retryWhen(Retry.backoff(properties.getMaxRetries(), properties.getRetryDelay())
                        .filter(this::isRetryableException))
                .onErrorMap(this::mapException);
    }

    @Override
    public Mono<Boolean> isHealthy() {
        return generateContent("Hello")
                .map(response -> true)
                .onErrorReturn(false)
                .doOnNext(healthy -> log.debug("Health check result: {}", healthy));
    }

    private ChatCompletionRequest buildApiRequest(AiContentRequest request) {
        List<Message> messages = new ArrayList<>();
        
        // Add system message if provided
        if (StringUtils.hasText(request.getSystemMessage())) {
            messages.add(Message.system(request.getSystemMessage()));
        }
        
        // Add user prompt
        messages.add(Message.user(request.getPrompt()));

        return ChatCompletionRequest.builder()
                .model(request.getModel() != null ? request.getModel() : properties.getDefaultModel())
                .messages(messages)
                .temperature(request.getTemperature() != null ? request.getTemperature() : properties.getDefaultTemperature())
                .maxTokens(request.getMaxTokens() != null ? request.getMaxTokens() : properties.getDefaultMaxTokens())
                .topP(request.getTopP() != null ? request.getTopP() : properties.getDefaultTopP())
                .frequencyPenalty(request.getFrequencyPenalty() != null ? request.getFrequencyPenalty() : properties.getDefaultFrequencyPenalty())
                .presencePenalty(request.getPresencePenalty() != null ? request.getPresencePenalty() : properties.getDefaultPresencePenalty())
                .stream(false)
                .build();
    }

    private boolean isClientError(HttpStatusCode status) {
        return status.is4xxClientError();
    }

    private boolean isServerError(HttpStatusCode status) {
        return status.is5xxServerError();
    }

    private Mono<? extends Throwable> handleClientError(ClientResponse response) {
        if (response.statusCode() == HttpStatus.TOO_MANY_REQUESTS) {
            long retryAfter = response.headers().header("Retry-After")
                    .stream()
                    .findFirst()
                    .map(Long::parseLong)
                    .orElse(60L);
            return Mono.error(new AiRateLimitException("Rate limit exceeded", retryAfter));
        }
        return response.bodyToMono(String.class)
                .map(body -> new AiContentGenerationException("Client error: " + response.statusCode() + " - " + body));
    }

    private Mono<? extends Throwable> handleServerError(ClientResponse response) {
        return response.bodyToMono(String.class)
                .map(body -> new AiServiceUnavailableException("Server error: " + response.statusCode() + " - " + body));
    }

    private boolean isRetryableException(Throwable throwable) {
        return throwable instanceof AiServiceUnavailableException ||
               (throwable instanceof WebClientResponseException && 
                ((WebClientResponseException) throwable).getStatusCode().is5xxServerError());
    }

    private Throwable mapException(Throwable throwable) {
        if (throwable instanceof AiContentGenerationException) {
            return throwable;
        }
        log.error("Unexpected error during content generation", throwable);
        return new AiContentGenerationException("Unexpected error during content generation", throwable);
    }
}
