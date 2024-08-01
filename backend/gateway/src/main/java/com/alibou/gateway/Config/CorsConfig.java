package com.alibou.gateway.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200 ") // Autoriser l'origine de votre application Angular
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Autoriser les méthodes HTTP
                        .allowedHeaders("*") // Autoriser tous les en-têtes
                        .allowCredentials(true); // Autoriser les cookies (si nécessaire)
            }
        };
    }
}
