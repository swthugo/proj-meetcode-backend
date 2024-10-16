package dev.hugosiu.meetCode.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Autowired
  private CorsProperties corsProperties;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    System.out.println("========== WebConfig.addCorsMappings invoked! ==========");

    registry.addMapping("/**")
            .allowedOrigins(corsProperties.getAllowedOrigins())
            .allowedMethods("*")
            .allowCredentials(true)
            .allowedHeaders("*");
  }
}
