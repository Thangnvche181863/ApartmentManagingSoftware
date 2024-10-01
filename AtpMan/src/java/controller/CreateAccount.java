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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Apartment;
import model.Building;
import utils.EmailHandle;
import utils.GeneratePassword;

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
        // Check if buildingID parameter is present for loading apartments via AJAX
        String buildingIDStr = request.getParameter("buildingId"); // Get from AJAX request

        if (buildingIDStr != null && !buildingIDStr.isEmpty()) {
            int buildingID = Integer.parseInt(buildingIDStr);

            // Fetch the apartments for the selected building
            ApartmentDAO apartmentDAO = new ApartmentDAO();
            List<Apartment> apartments = apartmentDAO.getApartmentsByBuilding(buildingID);

            StringBuilder apartmentOptions = new StringBuilder();
            for (Apartment apartment : apartments) {
                apartmentOptions.append("<option value='")
                        .append(apartment.getApartmentID())
                        .append("'>")
                        .append(apartment.getDepartmentType())
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

            request.getRequestDispatcher("createAccount.jsp").forward(request, response);
        }
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    try {
        CustomerDAO customerDAO = WebManager.getInstance().customerDAO;
        LivingDAO livingDAO = WebManager.getInstance().livingDAO;

        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
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
        String password = GeneratePassword.generatePass();
        customerDAO.createNewCustomer(username, password, name, email, phoneNumber, isOwner);         
        int customerID = customerDAO.getCustomerIDByUsername(username);
        
        System.out.println("Customer ID: " + customerID);

        if (customerID > 0) {
            int apartmentID = Integer.parseInt(apartmentId);
            livingDAO.insertResident(customerID, apartmentID);
        }

        
        String subject = "Tai khoan va mat khau";
        String body = "Dear " + name + ",\nTai khoan va mat khau cua ban: " +"\nTai khoan: "+ username + "\nMat khau: " + password + "\n";
        EmailHandle.sendEmail(email, subject, body);

        request.setAttribute("successCreate", "Create account successfully");
        request.getRequestDispatcher("createAccount.jsp").forward(request, response);

    } catch (Exception e) {
        e.printStackTrace(); 
    }
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
