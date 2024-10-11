package com.example.sonix.controller;

import com.example.sonix.config.AccessConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {

  @Autowired
  private AccessConfig accessConfig;

  @GetMapping("/token")
  public String getToken() {
    return accessConfig.getSecretKey();
  }
}