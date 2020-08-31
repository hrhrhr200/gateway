package com.example.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hrhrh on 2020/4/26 10:28
 */
@Configuration
@ConfigurationProperties(prefix = "token")
@Data
public class UrlConfig {

    private String tokenUrl;
}
