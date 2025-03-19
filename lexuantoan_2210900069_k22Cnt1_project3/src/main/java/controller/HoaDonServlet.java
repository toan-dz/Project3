package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.HoaDonDAO;

@WebServlet("/hoadon")
public class HoaDonServlet extends HttpServlet {
    private HoaDonDAO hoaDonDAO;

    @Override
    public void init() throws ServletException {
        hoaDonDAO = new HoaDonDAO();
        System.out.println("HoaDonServlet initialized.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int maBenhNhan = Integer.parseInt(request.getParameter("maBenhNhan"));
        String[] dichVuIds = request.getParameterValues("dichVuIds");

        try {
            // Kiểm tra xem bệnh nhân có hóa đơn chưa thanh toán không
            int maHoaDon = hoaDonDAO.getUnpaidHoaDonId(maBenhNhan);
            if (maHoaDon == -1) {
                // Nếu không có, tạo hóa đơn mới
                maHoaDon = hoaDonDAO.createHoaDon(maBenhNhan);
                if (maHoaDon == -1) {
                    request.setAttribute("errorMessage", "Không thể tạo hóa đơn mới!");
                    request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
                    return;
                }
            }

            // Thêm các dịch vụ vào chi tiết hóa đơn
            for (String dichVuId : dichVuIds) {
                int maDichVu = Integer.parseInt(dichVuId);
                double giaTien = hoaDonDAO.getGiaTienDichVu(maDichVu);
                int soLuong = 1; // Mặc định số lượng là 1, có thể thêm trường để người dùng nhập
                double thanhTien = giaTien * soLuong;

                boolean success = hoaDonDAO.addChiTietHoaDon(maHoaDon, maDichVu, soLuong, thanhTien);
                if (!success) {
                    request.setAttribute("errorMessage", "Không thể thêm dịch vụ vào hóa đơn!");
                    request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
                    return;
                }
            }

            // Cập nhật tổng tiền hóa đơn
            boolean updated = hoaDonDAO.updateTongTien(maHoaDon);
            if (updated) {
                request.setAttribute("successMessage", "Thêm dịch vụ và cập nhật hóa đơn thành công!");
            } else {
                request.setAttribute("errorMessage", "Không thể cập nhật tổng tiền hóa đơn!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi xử lý hóa đơn: " + e.getMessage());
        }

        // Chuyển tiếp lại admin/dashboard.jsp
        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
    }
}