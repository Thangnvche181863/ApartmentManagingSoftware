/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.RoleDAO;
import DAO.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Role;
import utils.EmailHandle;
import utils.GeneratePassword;

/**
 *
 * @author WuanTun
 */
public class CreateAccountStaff extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoleDAO roleDAO = new RoleDAO();
        List<Role> listRole = roleDAO.getRoles();
        request.setAttribute("roleList", listRole); 
        request.getRequestDispatcher("createAccountStaff.jsp").forward(request, response); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            StaffDAO staffDAO = WebManager.getInstance().staffDAO;

            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String hireDateStr = request.getParameter("hireDate");
            String roleStaffStr = request.getParameter("roleStaff");

            int roleStaff = 0; // Default value
            if (roleStaffStr != null && !roleStaffStr.isEmpty()) {
                roleStaff = Integer.parseInt(roleStaffStr); // Parse the roleID
            } else {
                // Handle the case where roleID is not provided
                request.setAttribute("messExist", "Role must be selected");
                request.getRequestDispatcher("createAccountStaff.jsp").forward(request, response);
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date hireDate = sdf.parse(hireDateStr);

            if (staffDAO.existsByUsernameOrGmail(username, email)) {
                request.setAttribute("messExist", "Username or email already exists");
                request.getRequestDispatcher("createAccountStaff.jsp").forward(request, response);
                return;
            }

            String password = GeneratePassword.generatePass();
            staffDAO.createNewStaff(roleStaff, username, password, name, email, phoneNumber, hireDate, 0);
            int staffID = staffDAO.getStaffIDByUsername(username);

            String subject = "Thong tin tai khoan va mat khau cua ban";
            String body = "<html>"
                    + "<body>"
                    + "<p>Dear " + name + ",</p>"
                    + "<p>Tai khoan và mat khau cua ban:</p>"
                    + "<table style='border-collapse: collapse;'>"
                    + "<tr><td><strong>Tai khoan:</strong></td><td>" + username + "</td></tr>"
                    + "<tr><td><strong>Mat khau:</strong></td><td>" + password + "</td></tr>"
                    + "</table>"
                    + "<br>"
                    + "<p>Xin vui long dang nhap va doi mat khau sau khi truy cap lan đau.</p>"
                    + "<p>Tran trong,</p>"
                    + "<p>Doi ngu ho tro : NHOM 6 </p>"
                    + "</body>"
                    + "</html>";

            EmailHandle.sendEmail(email, subject, body);

            request.setAttribute("successCreate", "Create account successfully");
            request.getRequestDispatcher("createAccountStaff.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
