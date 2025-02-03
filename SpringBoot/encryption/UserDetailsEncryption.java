package com.boot.encryption;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import org.springframework.stereotype.Component;

@Component
public class UserDetailsEncryption {

    private static final String SECRET_KEY_BASE64 = "k8vN4rGQDdQ1tjZpRVspFq93vmpJREJst4DFtGPOxOo=";
    private static final String IV_BASE64 = "LearningIV12"; 

    private static final int TAG_LENGTH = 128;

    private static SecretKey key;
    private static byte[] iv;

    public UserDetailsEncryption() {
        try {
            // Decode the secret key
            byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY_BASE64);
            key = new javax.crypto.spec.SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

            // Decode the IV (12 bytes required for AES-GCM)
            iv = IV_BASE64.getBytes();
            if (iv.length != 12) {
                throw new IllegalArgumentException("IV must be 12 bytes long.");
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid Base64 key or IV format!", e);
        }
    }

    public String encrypt(String password) throws Exception {
        Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH, iv));
        byte[] encryptedBytes = encryptionCipher.doFinal(password.getBytes());
        return encode(encryptedBytes);
    }

    public String decrypt(String encryptedPassword) throws Exception {
        byte[] encryptedBytes = decode(encryptedPassword);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv);
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
