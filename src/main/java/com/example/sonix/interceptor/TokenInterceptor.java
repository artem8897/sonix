package com.example.sonix.interceptor;

import static com.example.sonix.constant.HttpHeaderConstants.TOKEN_HEADER;

import com.example.sonix.config.AccessConfig;
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
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {

    String token = request.getHeader(TOKEN_HEADER);

    if (token == null || !token.equals(accessConfig.getSecretKey())) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      return false;
    }
    return true;
  }
}
