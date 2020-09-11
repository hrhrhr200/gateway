package com.example.gateway.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author LiuJiaPeng
 * @version 1.0
 * @description TODO
 * @date 2019-09-09 13:40
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DataSourceProperties implements Serializable {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private Hikari hikari;

    @Data
    public static class Hikari implements Serializable {

        private String connectionTestQuery;
        private Integer minimumIdle;
        private Integer maximumPoolSize;
        private String catalog;
    }

}
