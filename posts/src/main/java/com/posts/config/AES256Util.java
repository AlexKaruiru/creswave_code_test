package com.posts.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AES256Util {
  public static String algorithms = "AES/CBC/PKCS5Padding";

  private static String KEY;
  private static String IV;

  public AES256Util(@Value("${jwt.secretKey}") String key) {
    KEY = key;
    IV = KEY.substring(0, 16);
  }

  public static String encrypt(String text) {
    try {
      Cipher cipher = Cipher.getInstance(algorithms);
      SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
      IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
      cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
      byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

      return Base64.getEncoder().encodeToString(encrypted);

    } catch (Exception e) {
      return null;
    }
  }

  public static String decrypt(String cipherText) {
    try {
      Cipher cipher = Cipher.getInstance(algorithms);
      SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
      IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
      cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
      byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
      byte[] decrypted = cipher.doFinal(decodedBytes);

      return new String(decrypted, StandardCharsets.UTF_8);
    } catch (Exception e) {
      return null;
    }
  }
}
