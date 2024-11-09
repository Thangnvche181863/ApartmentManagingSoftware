/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CustomerDAO;
import DAO.StaffDAO;
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
import model.Staff;

/**
 *
 * @author WuanTun
 */
public class UserLogin extends HttpServlet {
    
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
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
                    request.setAttribute("loginerr", "Tên đăng nhập hoặc mật khẩu khách hàng sai.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }
                
                String userName = customer.getName();
                session.setAttribute("user", customer);
                session.setAttribute("name", userName);
                session.setAttribute("customer", customer);
                request.getSession().setAttribute("userRole", "customer");
                response.sendRedirect("user/userhome");

            } else if ("3".equals(userType)) { // Manage
                Staff staff = staffDAO.getAllInformationstaff(username, password);
                if (staff == null) {
                    request.setAttribute("loginerr", "Tên đăng nhập hoặc mật khẩu nhân viên sai ");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }
                if(staff.getStatus()!= 1){
                    request.setAttribute("loginerr", "Tài khoản của bạn đã bị vô hiệu hóa.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }
                String staffName = staff.getName();
                session.setAttribute("user", staff);
                session.setAttribute("name", staffName);
                //phan loai nguoi dung: staff
                session.setAttribute("staff", staff);
                request.getSession().setAttribute("userRole", "staff");
                System.out.println("Role ID: " + staff.getRoleID());
                if(staff.getRoleID() == 1){
                    response.sendRedirect("managerPage");
                }else{
                    response.sendRedirect("staffhome");
                }
                
                
                
//                response.sendRedirect("managerPage");

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
