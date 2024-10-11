package com.example.sonix.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccessConfig {

  @Value("${token.key}")
  private String secretKey;

  public String getSecretKey() {
    return secretKey;
  }
}
