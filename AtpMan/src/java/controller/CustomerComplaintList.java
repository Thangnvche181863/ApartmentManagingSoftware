/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CustomerDAO;
import DAO.RequestComplaintDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.RequestComplaint;

/**
 *
 * @author WuanTun
 */
public class CustomerComplaintList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CustomerDAO customerDAO = WebManager.getInstance().customerDAO;

            HttpSession session = request.getSession(false);
            Customer loggedInCustomer = (Customer) session.getAttribute("user");

            if (loggedInCustomer == null) {
                request.setAttribute("errSession", "Bạn cần đăng nhập để thay đổi email.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            int customerID = loggedInCustomer.getCustomerID();
            System.out.println("Customer ID from session: " + customerID);

            try {
                RequestComplaintDAO requestComplaintDAO = WebManager.getInstance().requestComplaintDAO;
                List<RequestComplaint> customerComplaints = requestComplaintDAO.getComplaintsByCustomer(customerID);

                request.setAttribute("customerComplaints", customerComplaints);
                request.getRequestDispatcher("complaint_list-customer.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CustomerComplaintList.class.getName()).log(Level.SEVERE, null, ex);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerComplaintList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerComplaintList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
