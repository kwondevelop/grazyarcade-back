package com.grazyarcadeback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        // ⭐️ 터미널에 뜬 모든 주소를 다 추가합니다!
        .allowedOrigins(
            "http://localhost:5174",
            "http://172.29.0.1:5174",
            "http://172.19.224.1:5174",
            "http://192.168.0.120:5174"
        )
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowCredentials(true);
  }
}