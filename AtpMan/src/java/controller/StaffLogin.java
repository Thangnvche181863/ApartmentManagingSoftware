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

/**
 *
 * @author WuanTun
 */
public class StaffLogin extends HttpServlet {

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            StaffDAO staffDAO = WebManager.getInstance().staffDAO;
//
//            String username = request.getParameter("username");
//            String password = request.getParameter("password");
//
////            if ("".equals(username) || "".equals(password)) {
////                request.setAttribute("loginerr", "Username or password is empty");
////                request.getRequestDispatcher("login.jsp").forward(request, response);
////                return;
////            }
////
////            if (!customerDAO.checkUsername(username)) {
////                request.setAttribute("loginerr", "Username is incorrect.");
////                request.getRequestDispatcher("login.jsp").forward(request, response);
////                return;
////            }
//            if (!staffDAO.checkAuthenticationUser(username, password)) {
//                request.setAttribute("loginerr", "Username or password is incorrect.");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
//                return;
//            }
//
//            if (!staffDAO.checkAuthenticationUser(username, password)) {
//
//            }
//            HttpSession session = request.getSession();
//            session.setAttribute("user", username);
//            session.setAttribute("role", 1); // admin : 0; cus : 1;
//
//            response.sendRedirect("createAccount.jsp");
//        } catch (SQLException ex) {
//            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}