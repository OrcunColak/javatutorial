package com.colak.crypto.symmetric;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * See <a href="https://github.com/bmvermeer/java-encryption/blob/master/src/main/java/EncryptionServiceAESGCM.java">...</a>
 */
@Slf4j
public class AESGCMTest {

    public static void main(String[] args) throws GeneralSecurityException {
        // Original message
        String originalMessage = "Hello, this is a secret message!";

        String passphrase = "YourPassphraseHere";
        SecretKey secretKey = generateAesKey(passphrase);

        // Encrypt using the public key
        String encryptedMessage = encrypt(originalMessage, secretKey);

        log.info("Encrypted message: " + encryptedMessage);

        // Decrypt using the private key
        String decryptedMessage = decrypt(encryptedMessage, secretKey);

        log.info("Decrypted message: " + decryptedMessage);
    }

    private static SecretKey generateAesKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        // Using a 256-bit key for AES
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    private static SecretKey generateAesKey(String passphrase) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = getKeyBytesWithPBKDF2(passphrase);

        // Create a SecretKey from the encoded key
        return new SecretKeySpec(keyBytes, "AES");
    }

    private static byte[] getKeyBytesWithPBKDF2(String passphrase) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Using a 256-bit key for AES
        int keyLength = 256;
        // Number of iterations, you can adjust this based on your security requirements
        int iterations = 10000;

        // Generate a salt (random bytes)
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        // Use PBKDF2 with the given passphrase, salt, key length, and iterations
        KeySpec keySpec = new PBEKeySpec(passphrase.toCharArray(), salt, iterations, keyLength);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return secretKeyFactory.generateSecret(keySpec).getEncoded();
    }

    private static String encrypt(String message, SecretKey secretKey) throws GeneralSecurityException {

        // Generate a random IV (Initialization Vector)
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv); // Generate a random IV

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

        byte[] encryptedData = cipher.doFinal(message.getBytes());

        // Combine IV and ciphertext for later decryption
        var encoder = Base64.getEncoder();
        var encrypt64 = encoder.encode(encryptedData);
        var iv64 = encoder.encode(iv);
        return new String(encrypt64) + "#" + new String(iv64);
    }

    private static String decrypt(String encryptedMessage, SecretKey secretKey) throws GeneralSecurityException {
        // Extract IV from the combined data
        var split = encryptedMessage.split("#");
        var decoder = Base64.getDecoder();
        var cypherText = decoder.decode(split[0]);
        var iv = decoder.decode(split[1]);

        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
        byte[] decryptedData = cipher.doFinal(cypherText);
        return new String(decryptedData);
    }
}
