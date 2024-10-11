package com.example.sonix.config;

import static com.example.sonix.constant.PathConstants.APIS_PATH;

import com.example.sonix.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  private TokenInterceptor tokenInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(tokenInterceptor).addPathPatterns(APIS_PATH);
  }
}
