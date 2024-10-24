/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.security.MessageDigest;
import java.util.Base64;

/**
 *
 * @author WuanTun
 */
public class UtilHashPass {
    public static String EncodePassword(String password) {
        String salt = "abc@@@defgh;12345"; // Static salt for complexity
        String result = null;
        password = password + salt;
        try {
            byte[] dataBytes = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest(dataBytes);
            result = Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Password: " + password);
        System.out.println();
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(EncodePassword("123"));
    }
}
