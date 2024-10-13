package com.example.sonix.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info().title("Sonix Project API")
        .description("API for generating HMAC SHA256 signatures")
        .contact(new Contact().name("Artsiom Kuzmik"))
        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
  }
}
