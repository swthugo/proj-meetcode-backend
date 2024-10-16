package dev.hugosiu.meetCode.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Configuration
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
  private String allowedOrigins;

  public String[] getAllowedOrigins() {
    System.out.println("========== CorsProperties invoked! ==========");
    return allowedOrigins.split(",");
  }
}
