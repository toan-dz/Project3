package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BenhNhanDAO;
import dao.NguoiDungDAO;
import model.BenhNhan;
import model.NguoiDung;

@WebServlet("/update-benhnhan")
public class UpdateBenhNhanServlet extends HttpServlet {
    private BenhNhanDAO benhNhanDAO;
    private NguoiDungDAO nguoiDungDAO;

    @Override
    public void init() throws ServletException {
        nguoiDungDAO = new NguoiDungDAO();
        System.out.println("UpdateBenhNhanInfoServlet initialized.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int maBenhNhan = Integer.parseInt(request.getParameter("maBenhNhan"));
        String ngaySinh = request.getParameter("ngaySinh");
        String gioiTinh = request.getParameter("gioiTinh");
        String soCMND = request.getParameter("soCMND");
        String baoHiemYTe = request.getParameter("baoHiemYTe");
        String ghiChu = request.getParameter("ghiChu");
        String soDienThoai = request.getParameter("soDienThoai");

        boolean success = nguoiDungDAO.updateBenhNhanInfo(maBenhNhan, ngaySinh, gioiTinh, soCMND, baoHiemYTe, ghiChu);
        if (success) {
            request.setAttribute("successMessage", "Cập nhật thông tin bệnh nhân thành công!");
        } else {
            request.setAttribute("errorMessage", "Cập nhật thông tin bệnh nhân thất bại!");
        }

        // Sau khi cập nhật, tìm lại bệnh nhân để hiển thị thông tin
        request.setAttribute("soDienThoai", soDienThoai);
        request.getRequestDispatcher("/search-patient").forward(request, response);
    }
}