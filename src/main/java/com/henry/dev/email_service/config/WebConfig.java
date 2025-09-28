package com.henry.dev.email_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Aplica a todos os endpoints sob /api/
                        .allowedOrigins("*") // Permite de qualquer origem
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // EXPLICITAMENTE permite os métodos
                        .allowedHeaders("*"); // Permite todos os cabeçalhos
            }
        };
    }
}