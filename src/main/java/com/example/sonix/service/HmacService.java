package com.example.sonix.service;

import org.apache.commons.codec.digest.HmacAlgorithms;

/**
 * Interface for the HMAC generation service.
 */
public interface HmacService {

  /**
   * Generates an HMAC for the given data and key.
   *
   * @param algorithm the HMAC algorithm to be used.
   * @param data      the data for which the HMAC will be generated.
   * @param key       the key for generating the HMAC.
   * @return a string containing the generated HMAC in hexadecimal format.
   */
  String generateHmac(HmacAlgorithms algorithm, String data, byte[] key);
}
