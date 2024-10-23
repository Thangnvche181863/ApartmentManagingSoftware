/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.regex.Pattern;

/**
 *
 * @author WuanTun
 */
public class Validation {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{6,16}$");

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[\\s\\S]{8,31}$");
    
    //private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@gmail\\.com$");

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(?:\\.[a-zA-Z]{2,})?$");

    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");

    public static boolean isValidUsername(String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }

    public static boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidGmail(String gmail) {
        return EMAIL_PATTERN.matcher(gmail).matches();
    }

    public static boolean isValidPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    public static String checkPassword(String password) {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            return "Password must be between 8 and 31 characters long.";
        }

        if (!password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
            return "Password must contain at least one letter and one number.";
        }

        if (password.contains(" ")) {
            return "Password must not contain whitespace.";
        }

        return null;
    }
}
