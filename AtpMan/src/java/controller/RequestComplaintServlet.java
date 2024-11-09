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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author WuanTun
 */
public class RequestComplaintServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("requestcomplaint.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            CustomerDAO customerDAO = WebManager.getInstance().customerDAO;

            HttpSession session = request.getSession(false);
            Customer loggedInCustomer = (Customer) session.getAttribute("user");

            if (loggedInCustomer == null) {
                request.setAttribute("errSession", "Bạn cần đăng nhập.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            int customerID = loggedInCustomer.getCustomerID();
            System.out.println("Customer ID from session: " + customerID);
            RequestComplaintDAO requestcomplaintDAO = WebManager.getInstance().requestComplaintDAO;

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String type = request.getParameter("type");

            requestcomplaintDAO.submitComplaint(customerID, title, description, type);
            response.sendRedirect("userhome");

        } catch (SQLException ex) {
            Logger.getLogger(RequestComplaintServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RequestComplaintServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
