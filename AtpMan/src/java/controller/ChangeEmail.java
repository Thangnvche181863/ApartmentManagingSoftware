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
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import utils.EmailHandle;

/**
 *
 * @author WuanTun
 */
public class ChangeEmail extends HttpServlet {

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
            out.println("<title>Servlet ChangeEmail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeEmail at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("changemail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

            String oldemail = request.getParameter("oldemail");
            String newemail = request.getParameter("newemail");

            String currentEmail = customerDAO.getEmailByCustomerID(customerID);

            if (!oldemail.equals(currentEmail)) {
                request.setAttribute("errOldEmail", "Email cũ không khớp với thông tin hiện tại.");
                request.getRequestDispatcher("changemail.jsp").forward(request, response);
                return;
            }
            String vericode = generateVerifyCode();
            session.setAttribute("vericode", vericode);
            session.setAttribute("newemail", newemail);

            // Send verification email
            String subject = "Xac nhan thay doi email";
            String body = "<html><body>"
                    + "<p>Dear,</p>"
                    + "<p>Ma xac thuc cua ban la</p>"
                    + "<h3>" + vericode + "</h3>"
                    + "<p>Tran trong,</p>"
                    + "<p>Doi ngu ho tro: Nhom 6</p>"
                    + "</body></html>";

            EmailHandle.sendEmail(oldemail, subject, body);
            response.sendRedirect("verify");

        } catch (SQLException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected static String generateVerifyCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();
        int length = 6;
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Generate a random digit between 0 and 9
            codeBuilder.append(digit);
        }
        return codeBuilder.toString();
    }
}
