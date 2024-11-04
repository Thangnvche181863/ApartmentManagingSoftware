/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAO.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

import java.util.Calendar;
import model.Customer;

/**
 *
 * @author PC
 */
public class RegisterResidentToApartment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerDAO cusDAO = new CustomerDAO();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String dobStr = request.getParameter("dob");
        String apartmentIDStr = request.getParameter("apartmentID");
        int apartmentID;
        try {
            if (apartmentIDStr != null && !apartmentIDStr.isEmpty()) {
                apartmentID = Integer.parseInt(apartmentIDStr);
            } else {
                throw new NumberFormatException("Apartment ID is null or empty");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("key", "Failed to add resident. Invalid apartment ID.");
            request.getRequestDispatcher("forbiddenpage.jsp").forward(request, response);
            return;
        }

        java.util.Date dob = null;
        if (dobStr != null && !dobStr.isEmpty()) {
            dob = Date.valueOf(dobStr); // Convert String to java.sql.Date
        }

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phone);
        customer.setDob(dob);
        customer.setRegistrationDate(new java.util.Date());
        customer.setIsOwner(0);

        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

        boolean isAdded = cusDAO.addCustomerToApartment(customer, apartmentID, currentDate);
        if (isAdded) {
            String referer = request.getHeader("referer");

            if (referer != null) {
                response.sendRedirect(referer); // Preserves query parameters
            }
        } else {
            request.setAttribute("key", "Failed to add resident.");
            request.getRequestDispatcher("forbiddenpage.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
