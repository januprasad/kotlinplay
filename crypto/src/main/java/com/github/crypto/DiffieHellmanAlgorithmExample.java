package com.github.crypto;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;


public class DiffieHellmanAlgorithmExample {
    private static final BigInteger G = new BigInteger("15");
    private static final BigInteger P = new BigInteger("23");


    public static void main(String[] args) throws Exception {
        // 1. Alice starts the key exchange
        BigInteger aliceRandomKey = createRandomKey();
        System.out.println("aliceRandomKey: " + aliceRandomKey);


        BigInteger aliceSharedKey = P.modPow(aliceRandomKey, G);
        System.out.println("aliceSharedKey: " + aliceSharedKey);


        // 2. aliceSharedKey is sent to Bob, Bob processes the request and creates a secret key
        BigInteger bobRandomKey = createRandomKey();
        System.out.println("bobRandomKey: " + bobRandomKey);


        BigInteger bobSharedKey = P.modPow(bobRandomKey, G);
        System.out.println("bobSharedKey: " + bobSharedKey);


        BigInteger bobSecretKey = aliceSharedKey.modPow(bobRandomKey, G);
        System.out.println("Bob agreed to use secretKey: " + bobSecretKey);


        // 3. bobSharedKey is sent to Alice, Alice processes the request and creates a secret key
        BigInteger aliceSecretKey = bobSharedKey.modPow(aliceRandomKey, G);
        System.out.println("Alice agreed to use secretKey: " + aliceSecretKey);


        // 4. Alice encrypts data using aliceSecretKey and sends it to Bob
        String plainText = "Hello World!";
        byte[] encryptedData = encrypt(plainText, aliceSecretKey);


        // 5. Bob receives encrypted data and decrypt is with its own secret
        String decryptedData = decrypt(encryptedData, bobSecretKey);
        System.out.println("Decrypted data is: " + decryptedData);
    }


    private static BigInteger createRandomKey() {
        Random random = new Random();
        return new BigInteger(128, random);
    }


    private static byte[] encrypt(String plainText, BigInteger secretKey) throws Exception {
        SecretKeySpec secretKeySpec = createSecretKeySpec(secretKey);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
    }


    private static String decrypt(byte[] encryptedData, BigInteger secretKey) throws Exception {
        SecretKeySpec secretKeySpec = createSecretKeySpec(secretKey);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedData);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }


    private static SecretKeySpec createSecretKeySpec(BigInteger secretKey) {
        byte[] keyBytes = secretKey.toByteArray();
        byte[] validKeyBytes = new byte[16];
        System.arraycopy(keyBytes, 0, validKeyBytes, 0, Math.min(keyBytes.length, validKeyBytes.length));
        return new SecretKeySpec(validKeyBytes, "AES");
    }
}

