/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ApartmentDAO;
import DAO.BuildingDAO;
import DAO.CustomerDAO;
import DAO.LivingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.sql.Date;
import java.util.List;
import model.Apartment;
import model.Building;

/**
 *
 * @author Admin
 */
public class addResidentController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */

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
        String buildingIDStr = request.getParameter("buildingId"); // Get from AJAX request

        if (buildingIDStr != null && !buildingIDStr.isEmpty()) {
            int buildingID = Integer.parseInt(buildingIDStr);

            // Fetch the apartments for the selected building
            ApartmentDAO apartmentDAO = new ApartmentDAO();
            List<Apartment> apartments = apartmentDAO.getAllApartmentByID(buildingID);

            StringBuilder apartmentOptions = new StringBuilder();
            for (Apartment apartment : apartments) {
                apartmentOptions
                        .append("<option value='")
                        .append(apartment.getApartmentID())
                        .append("'>")
                        .append(apartment.getApartmentType())
                        .append(" - ") // thêm dấu gạch ngang giữa type và number
                        .append(apartment.getApartmentNumber())
                        .append(" - Floor: ")
                        .append(apartment.getFloor())
                        .append("</option>");
            }

            response.setContentType("text/html");
            response.getWriter().write(apartmentOptions.toString());
        } else {
            // Fetch buildings for initial page load
            BuildingDAO buildingDAO = new BuildingDAO();
            List<Building> listBuilding = buildingDAO.getAllBuildings();
            request.setAttribute("listBuildings", listBuilding);

            request.getRequestDispatcher("addResident.jsp").forward(request, response);
        }

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
        response.setCharacterEncoding("UTF-8");
        CustomerDAO dao = new CustomerDAO();
        LivingDAO ldao = new LivingDAO();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String dob = request.getParameter("dob");
        LocalDate currentDate = LocalDate.now();
        Date registrationDate = Date.valueOf(currentDate);

        String isOwner = request.getParameter("isOwner");

        int apartmentID = Integer.parseInt(request.getParameter("apartment"));
        String message;
        try {
            // Thêm người ở
            dao.addResident(name, email, phoneNumber, dob, registrationDate, isOwner);
            int customerID = dao.getLatestCustomerID();
            ldao.insertLiving(customerID, apartmentID, currentDate);
            message = "Thêm người ở thành công!";
        } catch (Exception e) {
            message = "Đã xảy ra lỗi khi thêm người ở. Vui lòng thử lại.";
        }

// Sử dụng sendRedirect và truyền message qua query string
        response.sendRedirect("addResident?message=" + URLEncoder.encode(message, "UTF-8"));
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
