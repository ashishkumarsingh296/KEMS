package com.kems.Kems.config;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class AESEncryption {
    private static SecretKey secretKey;

    public static void main(String[] args) throws Exception {
        // Generate AES key
        secretKey = KeyGenerator.getInstance("AES").generateKey();

        // Encrypt Password
        String originalPassword = "Indra@1989";
        String encryptedPassword = encrypt(originalPassword);
        System.out.println("Encrypted Password: " + encryptedPassword);

        // Decrypt Password
        String decryptedPassword = decrypt(encryptedPassword);
        System.out.println("Decrypted Password: " + decryptedPassword);
    }

    // Encrypt a password
    public static String encrypt(String password) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt a password
    public static String decrypt(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }
}
