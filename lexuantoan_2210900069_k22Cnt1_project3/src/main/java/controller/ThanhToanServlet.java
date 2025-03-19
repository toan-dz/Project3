package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.HoaDonDAO;
import dao.NguoiDungDAO;

@WebServlet("/thanh-toan")
public class ThanhToanServlet extends HttpServlet {
    private HoaDonDAO hoaDonDAO;
    private NguoiDungDAO nguoiDungDAO;

    @Override
    public void init() throws ServletException {
        hoaDonDAO = new HoaDonDAO();
        nguoiDungDAO = new NguoiDungDAO();
        System.out.println("ThanhToanServlet initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("taiKhoan") == null) {
            System.out.println("No session found in ThanhToanServlet, redirecting to login_khachhang.jsp");
            response.sendRedirect(request.getContextPath() + "/login/login_khachhang.jsp?error=true");
            return;
        }

        String taiKhoan = (String) session.getAttribute("taiKhoan");
        System.out.println("ThanhToanServlet doGet called for user: " + taiKhoan);

        int maBenhNhan = nguoiDungDAO.getMaBenhNhanByTaiKhoan(taiKhoan);
        if (maBenhNhan == -1) {
            request.setAttribute("errorMessage", "Không tìm thấy thông tin bệnh nhân!");
            request.getRequestDispatcher("/thanh-toan.jsp").forward(request, response);
            return;
        }

        int maHoaDon = hoaDonDAO.getUnpaidHoaDonId(maBenhNhan);
        if (maHoaDon == -1) {
            request.setAttribute("errorMessage", "Không có hóa đơn nào cần thanh toán!");
        } else {
            double tongTien = hoaDonDAO.getTongTien(maHoaDon);
            request.setAttribute("maHoaDon", maHoaDon);
            request.setAttribute("tongTien", tongTien);
        }

        System.out.println("Forwarding to /thanh-toan.jsp");
        request.getRequestDispatcher("/thanh-toan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("taiKhoan") == null) {
            response.sendRedirect(request.getContextPath() + "/login/login_khachhang.jsp?error=true");
            return;
        }

        String action = request.getParameter("action");
        if ("confirm".equals(action)) {
            String taiKhoan = (String) session.getAttribute("taiKhoan");
            int maBenhNhan = nguoiDungDAO.getMaBenhNhanByTaiKhoan(taiKhoan);
            if (maBenhNhan == -1) {
                request.setAttribute("errorMessage", "Không tìm thấy thông tin bệnh nhân!");
                request.getRequestDispatcher("/thanh-toan.jsp").forward(request, response);
                return;
            }

            int maHoaDon = hoaDonDAO.getUnpaidHoaDonId(maBenhNhan);
            if (maHoaDon == -1) {
                request.setAttribute("errorMessage", "Không có hóa đơn nào cần thanh toán!");
                request.getRequestDispatcher("/thanh-toan.jsp").forward(request, response);
                return;
            }

            boolean success = hoaDonDAO.updateTrangThaiThanhToan(maHoaDon);
            if (success) {
                System.out.println("Payment confirmed for MaHoaDon: " + maHoaDon);
                request.setAttribute("successMessage", "Thanh toán hóa đơn thành công!");
                response.sendRedirect(request.getContextPath() + "/benhnhan/dashboard.jsp");
            } else {
                request.setAttribute("errorMessage", "Thanh toán hóa đơn thất bại!");
                request.getRequestDispatcher("/thanh-toan.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Hành động không hợp lệ!");
            request.getRequestDispatcher("/thanh-toan.jsp").forward(request, response);
        }
    }
}