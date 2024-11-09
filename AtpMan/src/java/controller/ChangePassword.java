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

/**
 *
 * @author WuanTun
 */
public class ChangePassword extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
//        response.getWriter().print("a");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            CustomerDAO customerDAO = WebManager.getInstance().customerDAO;
            StaffDAO staffDAO = WebManager.getInstance().staffDAO;

            HttpSession session = request.getSession(false);
            Object user = session.getAttribute("user");

            Customer loggedInCustomer = null;
            Staff loggedInStaff = null;

            if (user instanceof Customer) {
                loggedInCustomer = (Customer) user;
            } else if (user instanceof Staff) {
                loggedInStaff = (Staff) user;
            }

            if (loggedInCustomer == null && loggedInStaff == null) {
                request.setAttribute("errSession", "Bạn cần đăng nhập để thay đổi mật khẩu.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            String email = request.getParameter("email");
            String newPassword = request.getParameter("newPassword");
            String cfPassword = request.getParameter("cfPassword");

            if (newPassword == null || newPassword.isEmpty()) {
                request.setAttribute("errNewpass", "Mật khẩu mới không được để trống.");
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                return;
            }

            if (!newPassword.equals(cfPassword)) {
                request.setAttribute("errNewpass", "Mật khẩu mới không khớp.");
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                return;
            }

            if (loggedInCustomer != null) {

                int customerID = loggedInCustomer.getCustomerID();
                boolean passwordChange = customerDAO.updatePassword(customerID, newPassword);
                if (passwordChange) {
                    request.setAttribute("changepwdsuccess", "Đổi mật khẩu thành công cho khách hàng.");
                } else {
                    request.setAttribute("changepwderr", "Đổi mật khẩu không thành công cho khách hàng.");
                }
            } else if (loggedInStaff != null) {

                int staffID = loggedInStaff.getStaffID();
                boolean passwordChange = staffDAO.updatePassword(staffID, newPassword);
                if (passwordChange) {
                    request.setAttribute("changepwdsuccess", "Đổi mật khẩu thành công cho nhân viên.");
                } else {
                    request.setAttribute("changepwderr", "Đổi mật khẩu không thành công cho nhân viên.");
                }
            }

            request.getRequestDispatcher("changepassword.jsp").forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
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
