/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.UtilHashPass;
import utils.Validation;

/**
 *
 * @author WuanTun
 */
public class UserRegister extends HttpServlet {

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            CustomerDAO customerDAO = WebManager.getInstance().customerDAO;
//            String username = request.getParameter("username");
//            String password = request.getParameter("password");
//            String cfpassword = request.getParameter("cfpassword");
//            String email = request.getParameter("email");
//            String phone = request.getParameter("phone");
//
//            boolean hasError = false; // Biến để kiểm tra xem có lỗi xảy ra hay không
//
//            if (!Validation.isValidUsername(username)) {
//                request.setAttribute("messUsername", "Invalid username");
//                hasError = true;
//            } else if (!Validation.isValidGmail(email)) {
//                request.setAttribute("messGmail", "Invalid gmail");
//                hasError = true;
//            } else if (!Validation.isValidPhone(phone)) {
//                request.setAttribute("messPhone", "Invalid phone");
//                hasError = true;
//            } else {
//                String passwordError = Validation.checkPassword(password);
//                if (passwordError != null) {
//                    request.setAttribute("messPassword", passwordError);
//                    hasError = true;
//                } else if (!password.equals(cfpassword)) {
//                    request.setAttribute("messCfPass", "Confirm password does not match");
//                    hasError = true;
//                }
//            }
//            if (hasError) {
//                request.getRequestDispatcher("register.jsp").forward(request, response);
//                return;
//            }
//            String hassPassword = UtilHashPass.EncodePassword(password);
//            customerDAO.createNewCustomer(username, phone, email, hassPassword);
//            response.sendRedirect("login.jsp");
//        } catch (SQLException ex) {
//            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
