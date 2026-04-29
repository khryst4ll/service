package org.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dadata")
public class DadataProperites {
    private static final String URL = "https://cleaner.dadata.ru/api/v1/clean/address";
    private static final String TOKEN = "de9abfacb9745f5b2acf41e3f411d4d758078ef9";
    private static final String SECRET = "a48658882d35d5df3db147eae90f8aceaf81626f";

    public String getApiToken() {
        return DadataProperites.TOKEN;
    }

    public String getSecret() {
        return DadataProperites.SECRET;
    }

    public String getUrl() {
        return DadataProperites.URL;
    }
}