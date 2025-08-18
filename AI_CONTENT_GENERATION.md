# AI Content Generation

This module provides a reactive AI content generation interface with OpenRouter.ai integration for the OneB Common Library.

## Features

- **Reactive Programming**: Built with Spring WebFlux for non-blocking, reactive operations
- **OpenRouter.ai Integration**: Access to multiple AI models through a single API
- **Configurable**: Externalized configuration for API keys, models, and parameters
- **Error Handling**: Comprehensive error handling with specific exceptions
- **Retry Logic**: Built-in retry mechanism for transient failures
- **Health Checks**: Service health monitoring capabilities
- **Auto-Configuration**: Spring Boot auto-configuration for easy setup

## Quick Start

### 1. Add Dependency

Add the OneB Common Library to your project:

```xml
<dependency>
    <groupId>com.oneb</groupId>
    <artifactId>oneb-common</artifactId>
    <version>1.0.8-SNAPSHOT</version>
</dependency>
```

### 2. Configuration

Add the following configuration to your `application.yml`:

```yaml
app:
  common:
    # Enable the WebClient configuration (required for OpenRouter)
    client:
      enabled: true
      timeout: 60000
      read-timeout: 60000
      write-timeout: 60000
    ai:
      openrouter:
        enabled: true
        api-key: ${OPENROUTER_API_KEY}
        default-model: openai/gpt-4
        default-temperature: 0.7
        default-max-tokens: 1000
```

### 3. Usage

Inject the `AiContentGenerator` bean and use it:

```java
@Service
public class MyService {
    
    private final AiContentGenerator aiContentGenerator;
    
    public MyService(AiContentGenerator aiContentGenerator) {
        this.aiContentGenerator = aiContentGenerator;
    }
    
    public Mono<String> generateContent(String prompt) {
        return aiContentGenerator.generateContent(prompt);
    }
    
    public Mono<String> generateWithCustomModel(String prompt) {
        return aiContentGenerator.generateContent(prompt, "anthropic/claude-3-opus");
    }
    
    public Mono<String> generateWithFullControl(String prompt) {
        AiContentRequest request = AiContentRequest.builder()
            .prompt(prompt)
            .model("openai/gpt-4")
            .temperature(0.5)
            .maxTokens(500)
            .systemMessage("You are a helpful assistant")
            .build();
            
        return aiContentGenerator.generateContent(request);
    }
}
```

## Configuration Options

| Property | Description | Default | Required |
|----------|-------------|---------|----------|
| `app.common.ai.openrouter.enabled` | Enable/disable AI integration | `false` | No |
| `app.common.ai.openrouter.api-key` | OpenRouter.ai API key | - | Yes |
| `app.common.ai.openrouter.base-url` | API base URL | `https://openrouter.ai/api/v1` | No |
| `app.common.ai.openrouter.default-model` | Default AI model | `openai/gpt-3.5-turbo` | No |
| `app.common.ai.openrouter.default-temperature` | Default temperature | `1.0` | No |
| `app.common.ai.openrouter.default-max-tokens` | Default max tokens | `1000` | No |
| `app.common.ai.openrouter.connection-timeout` | HTTP connection timeout | `PT30S` | No |
| `app.common.ai.openrouter.read-timeout` | HTTP read timeout | `PT60S` | No |
| `app.common.ai.openrouter.max-retries` | Max retry attempts | `3` | No |

## Available Models

OpenRouter.ai provides access to various AI models:

- **OpenAI**: `openai/gpt-4`, `openai/gpt-3.5-turbo`
- **Anthropic**: `anthropic/claude-3-opus`, `anthropic/claude-3-sonnet`
- **Google**: `google/gemini-pro`
- **Meta**: `meta-llama/llama-2-70b-chat`
- And many more...

Check [OpenRouter.ai documentation](https://openrouter.ai/docs) for the complete list.

## Error Handling

The library provides specific exceptions for different error scenarios:

- `AiContentGenerationException`: General content generation errors
- `AiServiceUnavailableException`: Service unavailable (5xx errors)
- `AiRateLimitException`: Rate limit exceeded (429 errors)

```java
public Mono<String> handleErrors(String prompt) {
    return aiContentGenerator.generateContent(prompt)
        .onErrorResume(AiRateLimitException.class, ex -> {
            log.warn("Rate limit exceeded, retry after {} seconds", ex.getRetryAfterSeconds());
            return Mono.delay(Duration.ofSeconds(ex.getRetryAfterSeconds()))
                .then(aiContentGenerator.generateContent(prompt));
        })
        .onErrorResume(AiServiceUnavailableException.class, ex -> {
            log.error("AI service unavailable", ex);
            return Mono.just("Service temporarily unavailable");
        });
}
```

## Health Checks

Check if the AI service is healthy:

```java
public Mono<Boolean> checkHealth() {
    return aiContentGenerator.isHealthy();
}
```

## Testing

The library includes comprehensive test utilities. For testing your services:

```java
@ExtendWith(MockitoExtension.class)
class MyServiceTest {
    
    @Mock
    private AiContentGenerator aiContentGenerator;
    
    @InjectMocks
    private MyService myService;
    
    @Test
    void shouldGenerateContent() {
        // Given
        when(aiContentGenerator.generateContent("test"))
            .thenReturn(Mono.just("generated content"));
        
        // When
        Mono<String> result = myService.generateContent("test");
        
        // Then
        StepVerifier.create(result)
            .expectNext("generated content")
            .verifyComplete();
    }
}
```

## Best Practices

1. **Use appropriate timeouts**: Configure timeouts based on your use case
2. **Handle rate limits**: Implement proper backoff strategies for rate limits
3. **Monitor usage**: Use health checks and logging to monitor AI service usage
4. **Secure API keys**: Store API keys securely using environment variables
5. **Choose appropriate models**: Select models based on your specific needs and budget
6. **Implement fallbacks**: Have fallback strategies for when AI services are unavailable

## Security Considerations

- Never commit API keys to version control
- Use environment variables or secure configuration management
- Implement proper authentication and authorization in your application
- Monitor API usage and costs
- Be aware of data privacy implications when sending content to external AI services
