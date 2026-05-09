package org.example.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dadata")
@Getter
@Setter
public class DadataProperties {
    private String url;
    private String token;
    private String secret;
}