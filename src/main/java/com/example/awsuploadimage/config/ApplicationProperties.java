package com.example.awsuploadimage.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationProperties {

    private String secretKey;

    private String secretId;
}
