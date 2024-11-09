/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.ApartmentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;

/**
 *
 * @author Admin
 */
public class addApartment extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int buildingID = Integer.parseInt(request.getParameter("buildingID"));

            request.setAttribute("buildingID", buildingID);

            request.getRequestDispatcher("addApartment.jsp").forward(request, response);
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
        int buildingID = Integer.parseInt(request.getParameter("buildingID"));
        System.out.println("" + buildingID);// ID tòa nhà
        if ("add".equals(service)) {
            // Xử lý thêm căn hộ mới từ form
            try {
                // Lấy dữ liệu từ form
                String apartmentNumber = request.getParameter("apartmentNumber");
                String apartmentType = request.getParameter("apartmentType");
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
                BigDecimal maintenanceFee = BigDecimal.valueOf(Double.parseDouble(request.getParameter("maintenanceFee")));
                int floor = Integer.parseInt(request.getParameter("floor"));
                int area = Integer.parseInt(request.getParameter("area"));

                // Tạo đối tượng Apartment mới
                // Gọi DAO để thêm căn hộ vào cơ sở dữ liệu
                ApartmentDAO apartmentDAO = new ApartmentDAO();
                boolean isInserted = apartmentDAO.insertNewApartment(buildingID, apartmentNumber, apartmentType, price, maintenanceFee, floor, area); // Thêm căn hộ

                // Xử lý kết quả
                if (isInserted) {
                    message = "Thêm căn hộ thành công!";
                } else {
                    message = "Đã xảy ra lỗi khi thêm căn hộ.";
                }

            } catch (Exception e) {
                // Xử lý lỗi nếu có
                message = "Thông tin nhập không hợp lệ hoặc có lỗi trong quá trình xử lý.";
            }

            // Chuyển hướng về trang addBuilding.jsp và gửi thông báo
            response.sendRedirect("addApartment.jsp?buildingID=" + buildingID + "&message=" + URLEncoder.encode(message, "UTF-8"));
            System.out.println("" + buildingID);
        } else {
            request.setAttribute("buildingID", buildingID);
            request.getRequestDispatcher("addApartment.jsp").forward(request, response);
        }
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
