/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CustomerDAO;
import DAO.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Staff;
import utils.UtilHashPass;

/**
 *
 * @author WuanTun
 */
public class UserLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CustomerDAO customerDAO = WebManager.getInstance().customerDAO;
            StaffDAO staffDAO = WebManager.getInstance().staffDAO;

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String userType = request.getParameter("userType");

            if ("".equals(username) || "".equals(password)) {
                request.setAttribute("loginerr", "Username or password is empty");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            HttpSession session = request.getSession();

            if ("2".equals(userType)) { // Resident
                Customer customer = customerDAO.getAllInformationCustomer(username, password);

                if (customer == null) {
                    request.setAttribute("loginerr", "Username or password is incorrect for Resident.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }

                session.setAttribute("user", customer);
                response.sendRedirect("user/userhome");

            } else if ("3".equals(userType)) { // Manage
                Staff staff = staffDAO.getAllInformationstaff(username, password);
                if (staff == null) {
                    request.setAttribute("loginerr", "Username or password is incorrect for Manage.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }

                session.setAttribute("user", staff);
                response.sendRedirect("managerHomePage.jsp");

            } else {

                request.setAttribute("loginerr", "Please choose a valid option.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
