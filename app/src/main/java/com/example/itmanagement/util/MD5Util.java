package com.example.itmanagement.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String encrypt(String input) {
        try {
            // Obtener una instancia de MessageDigest con el algoritmo MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Convertir la cadena de entrada a bytes y calcular el hash
            byte[] messageDigest = md.digest(input.getBytes());

            // Convertir el hash de bytes a una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejar la excepción de algoritmo no encontrado
            e.printStackTrace();
            return null;
        }
    }
}
