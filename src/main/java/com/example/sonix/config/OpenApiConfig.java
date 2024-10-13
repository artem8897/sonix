package com.example.sonix.config;

import static com.example.sonix.constant.HttpHeaderConstants.TOKEN_HEADER;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(createApiInfo())
        .components(createComponents())
        .addSecurityItem(createSecurityRequirement());
  }

  private Info createApiInfo() {
    return new Info()
        .title("Sonix Project API")
        .description("API for generating HMAC SHA256 signatures")
        .contact(new Contact().name("Artsiom Kuzmik"))
        .license(new License().name("Apache 2.0").url("http://springdoc.org"));
  }

  private Components createComponents() {
    return new Components()
        .addSecuritySchemes(TOKEN_HEADER, createSecurityScheme());
  }

  private SecurityScheme createSecurityScheme() {
    return new SecurityScheme()
        .type(SecurityScheme.Type.APIKEY)
        .in(SecurityScheme.In.HEADER)
        .name(TOKEN_HEADER);
  }

  private SecurityRequirement createSecurityRequirement() {
    return new SecurityRequirement().addList(TOKEN_HEADER);
  }
}