package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DichVuDAO;
import model.DichVu;

@WebServlet("/dichvu")
public class DichVuServlet extends HttpServlet {
    private DichVuDAO dichVuDAO;

    @Override
    public void init() throws ServletException {
        dichVuDAO = new DichVuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {
            int maDichVu = Integer.parseInt(request.getParameter("id"));
            dichVuDAO.deleteDichVu(maDichVu);
        }
        response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String tenDichVu = request.getParameter("tenDichVu");
            String moTa = request.getParameter("moTa");
            double giaTien = Double.parseDouble(request.getParameter("giaTien"));

            DichVu dichVu = new DichVu();
            dichVu.setTenDichVu(tenDichVu);
            dichVu.setMoTa(moTa.isEmpty() ? null : moTa);
            dichVu.setGiaTien(giaTien);

            dichVuDAO.addDichVu(dichVu);
        } else if ("edit".equals(action)) {
            int maDichVu = Integer.parseInt(request.getParameter("maDichVu"));
            String tenDichVu = request.getParameter("tenDichVu");
            String moTa = request.getParameter("moTa");
            double giaTien = Double.parseDouble(request.getParameter("giaTien"));

            DichVu dichVu = new DichVu();
            dichVu.setMaDichVu(maDichVu);
            dichVu.setTenDichVu(tenDichVu);
            dichVu.setMoTa(moTa.isEmpty() ? null : moTa);
            dichVu.setGiaTien(giaTien);

            dichVuDAO.updateDichVu(dichVu);
        }

        response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
    }
}