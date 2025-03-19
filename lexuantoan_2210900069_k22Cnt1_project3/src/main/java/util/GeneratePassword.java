package util;

import util.PasswordUtil;

public class GeneratePassword {
    public static void main(String[] args) {
        String hashedPassword = PasswordUtil.hashPassword("pass123");
        System.out.println("Hashed password for pass123: " + hashedPassword);
    }
}