package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.NguoiDungDAO;
import model.NguoiDung;
import util.PasswordUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private NguoiDungDAO nguoiDungDAO;

    @Override
    public void init() throws ServletException {
        nguoiDungDAO = new NguoiDungDAO();
        System.out.println("RegisterServlet initialized.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        System.out.println("Starting doPost in RegisterServlet...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        System.out.println("Received registration request at " + new java.util.Date());

        String hoTen = request.getParameter("hoTen");
        String taiKhoan = request.getParameter("taiKhoan");
        String email = request.getParameter("email");
        String matKhau = request.getParameter("matKhau");
        String confirmMatKhau = request.getParameter("confirmMatKhau");
        String loaiNguoiDung = request.getParameter("loaiNguoiDung");
        String soDienThoai = request.getParameter("soDienThoai");
        String diaChi = request.getParameter("diaChi");

        System.out.println("HoTen: " + hoTen);
        System.out.println("TaiKhoan: " + taiKhoan);
        System.out.println("Email: " + email);
        System.out.println("MatKhau: " + matKhau);
        System.out.println("ConfirmMatKhau: " + confirmMatKhau);
        System.out.println("LoaiNguoiDung: " + loaiNguoiDung);
        System.out.println("SoDienThoai: " + soDienThoai);
        System.out.println("DiaChi: " + diaChi);

        // Kiểm tra dữ liệu đầu vào
        if (hoTen == null || hoTen.trim().isEmpty() || 
            taiKhoan == null || taiKhoan.trim().isEmpty() || 
            email == null || email.trim().isEmpty() || 
            matKhau == null || matKhau.trim().isEmpty() || 
            confirmMatKhau == null || confirmMatKhau.trim().isEmpty() ||
            soDienThoai == null || soDienThoai.trim().isEmpty()) {
            System.out.println("Validation failed: Missing required fields");
            response.sendRedirect(request.getContextPath() + "/register/dang-ky.jsp?error=true&message=Thông+tin+bắt+buộc+không+được+để+trống!");
            System.out.println("Redirected to dang-ky.jsp due to missing fields.");
            return;
        }

        if (!matKhau.equals(confirmMatKhau)) {
            System.out.println("Validation failed: Passwords do not match");
            response.sendRedirect(request.getContextPath() + "/register/dang-ky.jsp?error=true&message=Mật+khẩu+không+khớp!");
            System.out.println("Redirected to dang-ky.jsp due to password mismatch.");
            return;
        }

        // Kiểm tra định dạng số điện thoại
        if (!soDienThoai.matches("\\d{10,11}")) {
            System.out.println("Validation failed: Invalid phone number format");
            response.sendRedirect(request.getContextPath() + "/register/dang-ky.jsp?error=true&message=Số+điện+thoại+không+hợp+lệ!");
            System.out.println("Redirected to dang-ky.jsp due to invalid phone number.");
            return;
        }

        try {
            System.out.println("Hashing password with BCrypt...");
            String hashedPassword = PasswordUtil.hashPassword(matKhau);
            System.out.println("Password hashed successfully: " + hashedPassword);

            System.out.println("Creating NguoiDung object...");
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setHoTen(hoTen.trim());
            nguoiDung.setTaiKhoan(taiKhoan.trim());
            nguoiDung.setMatKhau(hashedPassword);
            nguoiDung.setLoaiNguoiDung(loaiNguoiDung.trim());
            nguoiDung.setSoDienThoai(soDienThoai.trim());
            nguoiDung.setEmail(email.trim());
            nguoiDung.setDiaChi(diaChi.trim());
            System.out.println("NguoiDung object created: TaiKhoan=" + nguoiDung.getTaiKhoan());

            System.out.println("Adding user to database...");
            boolean isAdded = nguoiDungDAO.addNguoiDung(nguoiDung);
            if (isAdded) {
                System.out.println("Registration successful for user: " + taiKhoan);
                System.out.println("Redirecting to success.jsp...");
                response.sendRedirect(request.getContextPath() + "/success.jsp");
                System.out.println("Redirect to success.jsp sent.");
            } else {
                System.out.println("Registration failed for user: " + taiKhoan);
                System.out.println("Redirecting to dang-ky.jsp with error...");
                response.sendRedirect(request.getContextPath() + "/register/dang-ky.jsp?error=true&message=Đăng+ký+thất+bại!+Vui+lòng+thử+lại.");
                System.out.println("Redirect to dang-ky.jsp sent.");
            }
        } catch (Exception e) {
            System.out.println("Exception in registration: " + e.getMessage());
            e.printStackTrace();
            System.out.println("Redirecting to dang-ky.jsp with error...");
            response.sendRedirect(request.getContextPath() + "/register/dang-ky.jsp?error=true&message=Có+lỗi+xảy+ra!+Vui+lòng+thử+lại.");
            System.out.println("Redirect to dang-ky.jsp sent due to exception.");
        }

        System.out.println("doPost in RegisterServlet completed.");
    }
}