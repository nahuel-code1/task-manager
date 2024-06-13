package com.example.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {

	public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

	public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            // Convertir el hash y el salt a una representación en base64
            String hashedPasswordBase64 = Base64.getEncoder().encodeToString(hashedPassword);
            String saltBase64 = Base64.getEncoder().encodeToString(salt);

            // Concatenar el salt y el hash para almacenarlos juntos
            return saltBase64 + ":" + hashedPasswordBase64;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
