package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.NguoiDungDAO;
import model.NguoiDung;
import util.PasswordUtil;

@WebServlet({"/login", "/dang-nhap-admin"})
public class LoginServlet extends HttpServlet {
    private NguoiDungDAO nguoiDungDAO;

    @Override
    public void init() throws ServletException {
        nguoiDungDAO = new NguoiDungDAO();
        System.out.println("LoginServlet initialized.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Starting doPost in LoginServlet at: " + new java.util.Date());
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String loginPath = request.getServletPath();
        System.out.println("Login path: " + loginPath);

        if ("/dang-nhap-admin".equals(loginPath)) {
            handleAdminLogin(request, response);
        } else {
            handleUserLogin(request, response);
        }
    }

    private void handleAdminLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String taiKhoan = request.getParameter("taiKhoan");
        String matKhau = request.getParameter("matKhau");

        System.out.println("Admin login attempt - TaiKhoan: " + taiKhoan);

        if (taiKhoan == null || matKhau == null || taiKhoan.trim().isEmpty() || matKhau.trim().isEmpty()) {
            System.out.println("Missing or empty admin credentials.");
            response.sendRedirect(request.getContextPath() + "/login/login_admin.jsp?error=true");
            return;
        }

        try {
            NguoiDung nguoiDung = nguoiDungDAO.getNguoiDungByTaiKhoan(taiKhoan);

            if (nguoiDung != null && "Admin".equals(nguoiDung.getLoaiNguoiDung())) {
                if (PasswordUtil.checkPassword(matKhau, nguoiDung.getMatKhau())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("taiKhoan", taiKhoan);
                    session.setAttribute("loaiNguoiDung", "Admin");
                    System.out.println("Admin " + taiKhoan + " logged in successfully, redirecting to /admin/dashboard.jsp");
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
                } else {
                    System.out.println("Password mismatch for Admin: " + taiKhoan);
                    response.sendRedirect(request.getContextPath() + "/login/login_admin.jsp?error=true&message=Sai+mật+khẩu!");
                }
            } else {
                System.out.println("No Admin found for TaiKhoan: " + taiKhoan);
                response.sendRedirect(request.getContextPath() + "/login/login_admin.jsp?error=true&message=Tài+khoản+không+tồn+tại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in Admin login: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/login/login_admin.jsp?error=true&message=Lỗi+hệ+thống!");
        }
    }

    private void handleUserLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginValue = request.getParameter("loginValue");
        String matKhau = request.getParameter("matKhau");

        System.out.println("User login attempt - SoDienThoai: " + loginValue);

        if (loginValue == null || matKhau == null || loginValue.trim().isEmpty() || matKhau.trim().isEmpty()) {
            System.out.println("Missing or empty user credentials.");
            response.sendRedirect(request.getContextPath() + "/login/login_khachhang.jsp?error=true&message=Thiếu+thông+tin+đăng+nhập!");
            return;
        }

        if (!loginValue.matches("^0\\d{9,10}$")) {
            System.out.println("Invalid phone number format: " + loginValue);
            response.sendRedirect(request.getContextPath() + "/login/login_khachhang.jsp?error=true&message=Số+điện+thoại+không+hợp+lệ!");
            return;
        }

        try {
            NguoiDung nguoiDung = nguoiDungDAO.getNguoiDungBySoDienThoai(loginValue);

            if (nguoiDung != null && "BenhNhan".equals(nguoiDung.getLoaiNguoiDung())) {
                if (PasswordUtil.checkPassword(matKhau, nguoiDung.getMatKhau())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("taiKhoan", nguoiDung.getTaiKhoan());
                    session.setAttribute("loaiNguoiDung", nguoiDung.getLoaiNguoiDung());
                    System.out.println("BenhNhan " + nguoiDung.getTaiKhoan() + " logged in successfully, redirecting to /benhnhan/dashboard.jsp");
                    response.sendRedirect(request.getContextPath() + "/benhnhan/dashboard.jsp");
                    return; // Đảm bảo không có mã nào chạy sau redirect
                } else {
                    System.out.println("Password mismatch for SoDienThoai: " + loginValue);
                    response.sendRedirect(request.getContextPath() + "/login/login_khachhang.jsp?error=true&message=Sai+mật+khẩu!");
                }
            } else {
                System.out.println("No BenhNhan found for SoDienThoai: " + loginValue);
                response.sendRedirect(request.getContextPath() + "/login/login_khachhang.jsp?error=true&message=Số+điện+thoại+không+tồn+tại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in User login: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/login/login_khachhang.jsp?error=true&message=Lỗi+hệ+thống!");
        }
    }
}