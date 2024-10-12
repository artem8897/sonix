package com.example.sonix.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccessConfig {

  @Value("${token.key}")
  private byte[] secretKey;

  public byte[] getSecretKey() {
    return secretKey;
  }
}
