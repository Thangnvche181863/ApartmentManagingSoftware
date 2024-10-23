/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.security.SecureRandom;

/**
 *
 * @author WuanTun
 */
public class GeneratePassword {
    public static String generatePass(){
        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();
        int length = 8;
        for (int i = 0; i < length; i++) {
                char genpass;
    
                if (random.nextBoolean()) {
                    genpass = (char) (random.nextInt(26) + 'a'); // a : 97
    
                } else {
                    if (random.nextBoolean()) {
                        genpass = (char) (random.nextInt(26) + 'A'); // A : 65
    
                    } else {
                        genpass = (char) (random.nextInt(10) + '0'); // 0 : 48
                    }
                }
                codeBuilder.append(genpass);
            }
    
        return codeBuilder.toString();
    }
    
    public static void main(String[] args) {
        String code = generatePass();
        System.out.println(code);
    }
}
