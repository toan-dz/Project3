package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.NguoiDungDAO;
import dao.BenhNhanDAO;
import model.NguoiDung;
import model.BenhNhan;

@WebServlet("/search-patient")
public class SearchPatientServlet extends HttpServlet {
    private NguoiDungDAO nguoiDungDAO;
    private BenhNhanDAO benhNhanDAO;

    @Override
    public void init() throws ServletException {
        nguoiDungDAO = new NguoiDungDAO();
        benhNhanDAO = new BenhNhanDAO();
        System.out.println("SearchPatientServlet initialized.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String soDienThoai = request.getParameter("soDienThoai");
        System.out.println("SearchPatientServlet: Received soDienThoai = " + soDienThoai);

        try {
            // Kiểm tra thông tin người dùng trước
            NguoiDung nguoiDung = nguoiDungDAO.getNguoiDungBySoDienThoai(soDienThoai);
            if (nguoiDung == null) {
                System.out.println("SearchPatientServlet: NguoiDung not found for soDienThoai = " + soDienThoai);
                request.setAttribute("errorMessage", "Không tìm thấy người dùng với số điện thoại này!");
                request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
                return;
            }

            // Kiểm tra xem người dùng có phải là bệnh nhân không
            int maBenhNhan = nguoiDungDAO.getMaBenhNhanBySoDienThoai(soDienThoai);
            System.out.println("SearchPatientServlet: MaBenhNhan = " + maBenhNhan);

            if (maBenhNhan != -1) {
                System.out.println("SearchPatientServlet: Found NguoiDung with TaiKhoan = " + nguoiDung.getTaiKhoan());
                BenhNhan benhNhan = benhNhanDAO.getBenhNhanByMaBenhNhan(maBenhNhan);
                request.setAttribute("nguoiDung", nguoiDung);
                request.setAttribute("maBenhNhan", maBenhNhan);
                request.setAttribute("benhNhan", benhNhan);
            } else {
                System.out.println("SearchPatientServlet: MaBenhNhan not found for soDienThoai = " + soDienThoai);
                request.setAttribute("errorMessage", "Người dùng này không phải là bệnh nhân!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in SearchPatientServlet: " + e.getMessage());
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi tìm kiếm bệnh nhân: " + e.getMessage());
        }

        // Chuyển tiếp lại admin/dashboard.jsp
        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
    }
}