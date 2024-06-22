package com.github.crypto;

public class RSAExample {
    public static void main(String[] args) {
        RSAEncryptor instance = new RSAEncryptor(61,53,7);
        String encrypted = instance.encrypt("Hello");
        System.out.println(encrypted);
        String decrypted = instance.decrypt(encrypted);
        System.out.println(decrypted);
    }
}
