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
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author WuanTun
 */
public class ChangePassword extends HttpServlet {

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
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            CustomerDAO customerDAO = WebManager.getInstance().customerDAO;

            HttpSession session = request.getSession(false);
            Customer loggedInCustomer = (Customer) session.getAttribute("user");

            if (loggedInCustomer == null) {
                request.setAttribute("errSession", "Bạn cần đăng nhập để thay đổi mật khẩu.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            int customerID = loggedInCustomer.getCustomerID();
            System.out.println("Customer ID from session: " + customerID);

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
            
            boolean passwordChange = customerDAO.updatePassword(customerID, newPassword);
            if (passwordChange) {               
                request.setAttribute("changepwdsuccess", "Đổi mật khẩu thành công.");
            } else {              
                request.setAttribute("changepwderr", "Đổi mật khẩu không thành công.");
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