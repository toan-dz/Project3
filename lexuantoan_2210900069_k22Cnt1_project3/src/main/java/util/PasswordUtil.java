package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // Mã hóa mật khẩu bằng BCrypt
    public static String hashPassword(String password) {
        try {
            // Sử dụng work factor 12 (càng cao càng chậm, càng an toàn)
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
            System.out.println("Hashing process - Input: " + password + ", Hashed: " + hashedPassword);
            return hashedPassword;
        } catch (Exception e) {
            System.out.println("Error in hashPassword: " + e.getMessage());
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Kiểm tra mật khẩu
    public static boolean checkPassword(String password, String hashedPassword) {
        try {
            boolean matches = BCrypt.checkpw(password, hashedPassword);
            System.out.println("Password check - Input: " + password + ", Hashed: " + hashedPassword + ", Matches: " + matches);
            return matches;
        } catch (Exception e) {
            System.out.println("Error in checkPassword: " + e.getMessage());
            return false;
        }
    }
}