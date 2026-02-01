package com.oneb.common.ai.integration;

import com.oneb.common.ai.AiContentGenerator;
import com.oneb.common.config.CommonClientConfig;
import com.oneb.common.config.properties.ClientProperties;
import com.oneb.common.config.properties.OpenRouterProperties;
import com.oneb.common.ai.openrouter.OpenRouterAiContentGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test to verify that OpenRouterAiContentGenerator works correctly
 * with the WebClient.Builder bean from ClientConfig.
 */
@SpringBootTest(classes = WebClientBuilderIntegrationTest.TestConfig.class)
@TestPropertySource(properties = {
        "app.common.client.enabled=true",
        "app.common.ai.openrouter.enabled=true",
        "app.common.ai.openrouter.api-key=test-key",
        "app.common.ai.openrouter.base-url=https://test.example.com"
})
class WebClientBuilderIntegrationTest {

    @Autowired
    private AiContentGenerator aiContentGenerator;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    void contextLoads() {
        // Verify that all beans are properly wired
        assertNotNull(aiContentGenerator);
        assertNotNull(webClientBuilder);
        assertTrue(aiContentGenerator instanceof OpenRouterAiContentGenerator);
    }

    @Test
    void webClientBuilder_shouldBeConfiguredCorrectly() {
        // Verify that the WebClient.Builder bean is available and configured
        assertNotNull(webClientBuilder);
        
        // Build a WebClient to verify it works
        WebClient webClient = webClientBuilder.build();
        assertNotNull(webClient);
    }

    @Configuration
    @EnableConfigurationProperties({ClientProperties.class, OpenRouterProperties.class})
    @Import({CommonClientConfig.class})
    static class TestConfig {

        // Manually create the OpenRouterAiContentGenerator bean since @Component scanning is not enabled
        @Bean
        public OpenRouterAiContentGenerator openRouterAiContentGenerator(
                WebClient.Builder webClientBuilder,
                OpenRouterProperties openRouterProperties) {
            return new OpenRouterAiContentGenerator(webClientBuilder, openRouterProperties);
        }
    }
}
