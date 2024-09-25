/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ApartmentDAO;
import DAO.BuildingDAO;
import DAO.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Apartment;
import model.Building;

/**
 *
 * @author WuanTun
 */
public class CreateAccount extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateAccount at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            ApartmentDAO apartmentDAO = WebManager.getInstance().apartmentDAO;
            BuildingDAO buildingDAO = WebManager.getInstance().buildingDAO;
            // Kiểm tra nếu có tham số buildingId, tức là yêu cầu AJAX để lấy danh sách Apartment
            String buildingId = request.getParameter("buildingId");
            if (buildingId != null && !buildingId.isEmpty()) {
                // Trả về danh sách Apartment dựa trên buildingId
                List<Apartment> apartments = apartmentDAO.getApartmentsByBuilding(Integer.parseInt(buildingId));
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();

                // Gửi danh sách apartment dưới dạng option cho select
                out.println("<option value=''>Choose apartment</option>");
                for (Apartment apartment : apartments) {
                    out.println("<option value='" + apartment.getApartmentID() + "'>" + "</option>");
                }
                out.close();
                return;
            }

            // Nếu không có buildingId, tải trang lần đầu và nạp danh sách Building
            List<Building> buildings = buildingDAO.getAllBuildings();
            request.setAttribute("buildings", buildings);

            // Forward dữ liệu sang JSP để hiển thị form
            request.getRequestDispatcher("createAccount.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            CustomerDAO customerDAO = WebManager.getInstance().customerDAO;
            String username = request.getParameter("username");
//            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
//            String age = request.getParameter("age");
//            String registrationDate = request.getParameter("registrationDate");   
            String buildingId = request.getParameter("building");
            String apartmentId = request.getParameter("apartment");
            String userType = request.getParameter("userType");

            String isOwner = null;
            if ("2".equals(userType)) {
                isOwner = "1"; // Resident -> isOwner = 1
            } else if ("3".equals(userType)) {
                isOwner = "0"; // Owner -> isOwner = 0
            }

            boolean hasError = false;

            if (customerDAO.existsByUsernameOrGmail(username, email)) {
                request.setAttribute("messExist", "Username or email already exists");
                request.getRequestDispatcher("createAccount.jsp").forward(request, response);
                return;
            }

//            customerDAO.createNewCustomer(username, password, name, email, phoneNumber, age, registrationDate, isOwner);
//            request.setAttribute("successCreate", "Create account successfully");
//            request.getRequestDispatcher("createAccount.jsp").forward(request, response);
            customerDAO.createNewCustomer(username, name, email, phoneNumber, isOwner);
            request.setAttribute("successCreate", "Create account successfully");
            request.getRequestDispatcher("createAccount.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
