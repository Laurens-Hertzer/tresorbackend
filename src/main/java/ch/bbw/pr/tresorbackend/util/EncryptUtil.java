/**package ch.bbw.pr.tresorbackend.util;

import org.jasypt.util.text.AES256TextEncryptor;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKey;

/**
 * EncryptUtil
 * Used to encrypt content.
 * Not implemented yet.
 * @author Peter Rutschmann

public class EncryptUtil {

   public static final String FIXED_SALT = "D3f4ultS4ltV4lu3";

   private final SecretKey secretKey;

   public EncryptUtil(String secretKey) {
      //todo ergänzen!
   }

   public String encrypt(String data) {
      //todo anpassen!
      return data;
   }

   public String decrypt(String data) {
      //todo anpassen!
      return data;
   }
}*/

package ch.bbw.pr.tresorbackend.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class EncryptUtil {

   // Fester Salt als Platzhalter für die vereinfachte Implementierung
   private static final String FIXED_SALT = "D3f4ultS4ltV4lu3!";

   private final SecretKey secretKey;

   public EncryptUtil(String secretPassword) {
      try {
         this.secretKey = deriveKey(secretPassword, FIXED_SALT);
      } catch (Exception e) {
         throw new RuntimeException("Fehler beim Generieren des Schlüssels", e);
      }
   }

   public String encrypt(String data) {
      if (data == null) return null;
      try {
         Cipher cipher = Cipher.getInstance("AES");
         cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
         byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
         return Base64.getEncoder().encodeToString(encryptedBytes);
      } catch (Exception e) {
         throw new RuntimeException("Fehler bei der Verschlüsselung", e);
      }
   }

   public String decrypt(String data) {
      if (data == null) return null;
      try {
         Cipher cipher = Cipher.getInstance("AES");
         cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
         byte[] decodedBytes = Base64.getDecoder().decode(data);
         byte[] decryptedBytes = cipher.doFinal(decodedBytes);
         return new String(decryptedBytes, StandardCharsets.UTF_8);
      } catch (Exception e) {
         throw new RuntimeException("Fehler bei der Entschlüsselung", e);
      }
   }

   // Hilfsmethode zur Schlüsselableitung (PBKDF2)
   private SecretKey deriveKey(String password, String salt) throws Exception {
      byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);
      KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, 65536, 256);
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      byte[] keyBytes = factory.generateSecret(spec).getEncoded();
      return new SecretKeySpec(keyBytes, "AES");
   }
}
