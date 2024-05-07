package com.benign.drone.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Value("${api_secret}")
    private String apiSecret;

    @Value("${api_key}")
    private String apiKey;

    @Value("${cloud_name}")
    private String cloudName;

    @Bean
    public Cloudinary getCloudinaryInstance(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        return new Cloudinary(config);
    }
}
