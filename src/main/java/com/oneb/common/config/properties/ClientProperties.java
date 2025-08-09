package com.oneb.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(value = "app.common.client")
public class ClientProperties {
    private Long timeout = 60000L;
    private Long readTimeout = 60000L;
    private Long writeTimeout = 60000L;
}
