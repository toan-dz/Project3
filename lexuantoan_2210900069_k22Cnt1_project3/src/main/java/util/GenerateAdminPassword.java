package util;

import util.PasswordUtil;

public class GenerateAdminPassword {
    public static void main(String[] args) {
        String hashedPassword = PasswordUtil.hashPassword("pass789");
        System.out.println("Hashed password for pass789: " + hashedPassword);
    }
}