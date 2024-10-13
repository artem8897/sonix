package com.example.sonix;

import static org.apache.commons.codec.digest.HmacAlgorithms.HMAC_SHA_256;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.sonix.constant.ResponseStatus;
import com.example.sonix.model.SignatureResponse;
import com.example.sonix.service.HmacService;
import com.example.sonix.service.impl.HashingServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class HashingServiceImplTest {

  private static final String VALID_HMAC = "18a620114c3a5754929ca69920cc77d4eef6bc38f85b14ae42131d881f720ca4";

  @InjectMocks
  private HashingServiceImpl hashingService;

  @Mock
  private HmacService hmacService;

  @Value("${signature.secret-key}")
  private byte[] secretKey;

  @Test
  void shouldReturnSuccessResponseWhenRequestParamsAreValid() {
    Map<String, String> requestParams = new HashMap<>();
    requestParams.put("name1", "value1");
    requestParams.put("name2", "value2");

    when(hmacService.generateHmac(eq(HMAC_SHA_256), any(String.class), eq(secretKey)))
        .thenReturn(VALID_HMAC);

    SignatureResponse response = hashingService.getFormat(requestParams);

    assertEquals(ResponseStatus.SUCCESS, response.getStatus());
    assertEquals(1, response.getResult().size());
    assertEquals(VALID_HMAC, response.getResult().get(0).getSignature());
  }

  @Test
  void shouldReturnErrorResponseWhenRequestParamsAreEmpty() {
    Map<String, String> requestParams = new HashMap<>();

    SignatureResponse response = hashingService.getFormat(requestParams);

    assertEquals(ResponseStatus.BAD_ENTITY_FOR_HASH, response.getStatus());
    assertTrue(response.getResult().isEmpty());
  }

  @Test
  void shouldReturnErrorResponseWhenRequestParamsAreNull() {
    SignatureResponse response = hashingService.getFormat(null);

    assertEquals(ResponseStatus.BAD_ENTITY_FOR_HASH, response.getStatus());
    assertTrue(response.getResult().isEmpty());
  }

  @Test
  void shouldReturnErrorResponseWhenParamsContainInvalidEntries() {
    Map<String, String> requestParams = new HashMap<>();
    requestParams.put(null, "value1");
    requestParams.put("name2", null);

    SignatureResponse response = hashingService.getFormat(requestParams);

    assertEquals(ResponseStatus.BAD_ENTITY_FOR_HASH, response.getStatus());
    assertTrue(response.getResult().isEmpty());
  }

  @Test
  void shouldGenerateHmacWithCorrectParameters() {
    Map<String, String> requestParams = new HashMap<>();
    requestParams.put("name1", "value1");
    requestParams.put("name2", "value2");

    String formattedParams = "name1=value1&name2=value2";

    when(hmacService.generateHmac(HMAC_SHA_256, formattedParams, secretKey))
        .thenReturn(VALID_HMAC);

    hashingService.getFormat(requestParams);

    verify(hmacService).generateHmac(HMAC_SHA_256, formattedParams, secretKey);
  }
}