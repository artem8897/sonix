package com.example.sonix.service.impl;

import static org.apache.commons.codec.digest.HmacAlgorithms.HMAC_SHA_256;

import com.example.sonix.constant.ResponseStatus;
import com.example.sonix.constant.Symbols;
import com.example.sonix.model.Signature;
import com.example.sonix.model.SignatureResponse;
import com.example.sonix.service.HashingService;
import com.example.sonix.service.HmacService;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HashingServiceImpl implements HashingService {

  @Value("${signature.secret-key}")
  private byte[] secretKey;

  @Autowired
  private HmacService hmacService;

  @Override
  public SignatureResponse getFormat(Map<String, String> requestParams) {
    return Optional.ofNullable(requestParams)
        .filter(MapUtils::isNotEmpty)
        .map(this::formatParamsToString)
        .filter(StringUtils::isNotEmpty)
        .map(this::generateHmac)
        .map(this::buildSuccessResponse)
        .orElse(buildErrorResponse());
  }

  private String formatParamsToString(Map<String, String> params) {
    return params.entrySet().stream()
        .filter(this::isEntryValid)
        .sorted(Map.Entry.comparingByKey())
        .map(entry -> entry.getKey() + Symbols.EQUALS + entry.getValue())
        .collect(Collectors.joining(Symbols.AND));
  }

  private boolean isEntryValid(Map.Entry<String, String> entry) {
    return entry.getKey() != null && entry.getValue() != null;
  }

  private String generateHmac(String data) {
    return hmacService.generateHmac(HMAC_SHA_256, data, secretKey);
  }

  private SignatureResponse buildSuccessResponse(String hmac) {
    return new SignatureResponse(ResponseStatus.SUCCESS,
        Collections.singletonList(new Signature(hmac)));
  }

  private SignatureResponse buildErrorResponse() {
    return new SignatureResponse(ResponseStatus.BAD_ENTITY_FOR_HASH, Collections.emptyList());
  }
}
