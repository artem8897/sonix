package com.example.sonix.controller;

import static com.example.sonix.constant.PathConstants.API_PATH;

import com.example.sonix.model.SignatureResponse;
import com.example.sonix.service.HashingService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_PATH)
public class SignatureController {

  @Autowired
  private HashingService hashingService;

  @PostMapping("/{operationId}/signature")
  public SignatureResponse createSignature(@RequestParam Map<String, String> requestParams,
      @PathVariable String operationId) {

    return hashingService.getFormat(requestParams);
  }
}
