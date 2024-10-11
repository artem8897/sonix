package com.example.sonix.service;

import com.example.sonix.model.SignatureResponse;
import java.util.Map;

/**
 * HashingService is responsible for generating an Hmac SHA256 hash.
 */
public interface HashingService {

  /**
   * Generates a signature based on the given parameters.
   *
   * @param requestParams a map containing parameters from the form.
   * @return {@link SignatureResponse} containing the status of the operation and the generated
   * signature result.
   */
  SignatureResponse getFormat(Map<String, String> requestParams);

}
