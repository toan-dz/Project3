package util;

import util.PasswordUtil;

import util.PasswordUtil;

public class TestPassword {
    public static void main(String[] args) {
        String plainPassword = "pass123"; // Thay bằng mật khẩu bạn dùng khi đăng ký
        String hashedPasswordFromDB = "$2a$12$wREKe.JDhqwb.TbBiop/uuGDybe7es.pWLZUiA0We2W1Z5W1Qvo/i>"; // Thay bằng giá trị từ database
        System.out.println("Password matches: " + PasswordUtil.checkPassword(plainPassword, hashedPasswordFromDB));
    }
}