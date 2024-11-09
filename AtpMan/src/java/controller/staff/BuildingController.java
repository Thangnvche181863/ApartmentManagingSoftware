/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.BuildingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Vector;
import model.Building;

/**
 *
 * @author Admin
 */
@WebServlet(name = "BuildingController", urlPatterns = {"/building"})
public class BuildingController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("999999999999999999999");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            BuildingDAO dao = new BuildingDAO();
            List<Building> list = dao.getAllBuildings();

            Vector<Integer> vector1 = dao.getApartmentAvailable();

            request.setAttribute("availableApartment", vector1);
            request.setAttribute("listBuilding", list);
            System.out.println("++++++++");
            request.getRequestDispatcher("building.jsp").forward(request, response);
            System.out.println("---------");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service");
        String message = ""; // Biến để lưu mọi thông báo

        if ("add".equals(service)) {
            // Lấy dữ liệu từ form
            String name = request.getParameter("name");
            String numFloorStr = request.getParameter("numFloor");
            String numApartmentStr = request.getParameter("numApartment");
            String address = request.getParameter("address");

            // Kiểm tra hợp lệ dữ liệu
            int numFloor = 0;
            int numApartment = 0;
            boolean isValid = true;

            try {
                numFloor = Integer.parseInt(numFloorStr);
                numApartment = Integer.parseInt(numApartmentStr);

                if (numFloor <= 0 || numApartment <= 0) {
                    isValid = false;
                    message = "Số tầng và số phòng phải là số tự nhiên lớn hơn 0.";
                }
            } catch (NumberFormatException e) {
                isValid = false;
                message = "Số tầng và số phòng phải là số tự nhiên.";
            }

            if (isValid) {
                // Thêm tòa nhà mới vào cơ sở dữ liệu
                BuildingDAO dao = new BuildingDAO();
                boolean isInserted = dao.insertNewBuilding(name, numFloor, numApartment, address);

                if (isInserted) {
                    message = "Thêm tòa nhà thành công!";
                } else {
                    message = "Đã xảy ra lỗi khi thêm tòa nhà.";
                }
            }

            // Load lại danh sách tòa nhà
            BuildingDAO dao = new BuildingDAO();
            List<Building> list = dao.getAllBuildings();
            request.setAttribute("listBuilding", list);
        }

        // Set message để hiển thị lên JSP
        // Trả về trang building.jsp với thông báo
        response.sendRedirect("addBuilding.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
