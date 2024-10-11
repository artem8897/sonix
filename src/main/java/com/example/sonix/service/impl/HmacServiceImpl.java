package com.example.sonix.service.impl;

import com.example.sonix.service.HmacService;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.stereotype.Service;

@Service
public class HmacServiceImpl implements HmacService {

  @Override
  public String generateHmac(HmacAlgorithms algorithm, String data, byte[] key) {
    HmacUtils hmacUtils = new HmacUtils(algorithm, key);
    byte[] hmacBytes = hmacUtils.hmac(data);
    return Hex.encodeHexString(hmacBytes);
  }
}
