package com.crocusoft.driver;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypter {

    private MessageDigest messageDigest;
    private static Encrypter encrypter = null;

    private Encrypter() throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance("SHA-512");
    }

    public static String getEncrypted(String unencrypted) throws NoSuchAlgorithmException {
        if(encrypter == null){
            encrypter = new Encrypter();
        }
        return new String(encrypter.messageDigest.digest(unencrypted.getBytes(StandardCharsets.UTF_8))).replaceAll("\u0000", "");
    }
}
