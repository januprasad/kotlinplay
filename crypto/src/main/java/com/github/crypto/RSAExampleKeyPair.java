package com.github.crypto;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAExampleKeyPair {
    public static void main(String[] args) {
        KeyPair keyPair = generateKeypair();
        {
            byte[] encrypted = encrypt("Hello", keyPair);
            byte[] decrypt = decrypt(encrypted, keyPair);
            System.out.println("encrypte" + new String(encrypted));
            System.out.println(new String(decrypt));
        }
    }

    private static KeyPair generateKeypair() {
        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if (generator != null) {
            generator.initialize(2048);
            return generator.generateKeyPair();
        }
        return null;
    }

    private static byte[] encrypt(String message, KeyPair pair) {
        PublicKey publicKey = pair.getPublic();
        showPublicKey(publicKey);

        Cipher encryptCipher = null;
        try {
            encryptCipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        try {
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        byte[] secretMessageBytes = message.getBytes(StandardCharsets.UTF_8);
        try {
            byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
            return encryptedMessageBytes;
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showPublicKey(PublicKey publicKey) {
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        System.out.println(publicKey);
        byte[] publicKeyBytes = publicKey.getEncoded();
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        try {
            PublicKey publicKeyString = keyFactory.generatePublic(publicKeySpec);
            System.out.println(publicKeyString);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

    }

    private static byte[] decrypt(byte[] message, KeyPair pair) {
        PrivateKey privateKey = pair.getPrivate();
        Cipher decryptCipher = null;
        try {
            decryptCipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        try {
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        try {
            return decryptCipher.doFinal(message);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

}
