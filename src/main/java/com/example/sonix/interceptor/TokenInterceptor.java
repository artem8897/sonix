package com.example.sonix.interceptor;

import static com.example.sonix.constant.HttpHeaderConstants.TOKEN_HEADER;

import com.example.sonix.config.AccessConfig;
import java.util.Arrays;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {

  @Autowired
  private AccessConfig accessConfig;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    return isTokenValid(request.getHeader(TOKEN_HEADER)) || denyAccess(response);
  }

  private boolean isTokenValid(String tokenHeader) {
    return Optional.ofNullable(tokenHeader)
        .map(String::getBytes)
        .filter(this::isMatchingSecretKey)
        .isPresent();
  }

  private boolean isMatchingSecretKey(byte[] token) {
    return Arrays.equals(token, accessConfig.getSecretKey());
  }

  private boolean denyAccess(HttpServletResponse response) {
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    return false;
  }
}
