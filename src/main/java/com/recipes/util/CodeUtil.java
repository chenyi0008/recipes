package com.recipes.util;


import java.util.Base64;

public class CodeUtil {
    public static String encodeToString(String password){
        return Base64.getUrlEncoder().encodeToString(password.getBytes());
    }

    public static String decode(String password){
        byte[] decodedBytes = Base64.getUrlDecoder().decode(password);
        return new String(decodedBytes);
    }
}
