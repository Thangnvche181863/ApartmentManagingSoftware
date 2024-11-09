/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ApartmentDAO;
import DAO.AssignmentDAO;
import DAO.CustomerDAO;
import DAO.LivingDAO;
import DAO.StaffDAO;
import DAO.TaskDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.Customer;
import model.Staff;
import model.Task;

/**
 *
 * @author WuanTun
 */
public class StaffHome extends HttpServlet {

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
            out.println("<title>Servlet StaffHome</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StaffHome at " + request.getContextPath() + "</h1>");
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
    private static final Logger LOGGER = Logger.getLogger(StaffHome.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            StaffDAO staffDAO = WebManager.getInstance().staffDAO;
            TaskDAO taskDAO = WebManager.getInstance().taskDAO;
            AssignmentDAO assignmentDAO = WebManager.getInstance().assignmentDAO;
            CustomerDAO customerDAO = WebManager.getInstance().customerDAO;
            ApartmentDAO apartmentDAO = WebManager.getInstance().apartmentDAO;
            LivingDAO livingDAO = WebManager.getInstance().livingDAO;
            HttpSession session = request.getSession(false);
            Staff loggedInStaff = (Staff) session.getAttribute("user");

            if (loggedInStaff == null) {
                request.setAttribute("errSession", "Bạn cần đăng nhập để thay đổi email.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            int staffID = loggedInStaff.getStaffID();
            System.out.println("Staff ID from session: " + staffID); // In thông tin staffID

            List<Task> tasks = taskDAO.getTaskByStaffID(staffID);
            System.out.println("Number of tasks found: " + tasks.size()); // In số lượng tasks tìm được

            // Gán trạng thái và customerID của từng task vào danh sách
            for (Task task : tasks) {
                String status = assignmentDAO.getStatus(staffID, task.getTaskID());
//                int customerID = task.getCustomerID();
//                System.out.println("Assigning status for taskID: " + task.getTaskID() + ", customerID: " + customerID);
//
//                // Sử dụng phương thức getApartmentNumberByCustomerID để lấy apartmentNumber từ customerID
//                String apartmentNumber = customerDAO.getApartmentNumberByCustomerID(customerID);
//                task.setApartmentNumber(apartmentNumber);  // Gán tên căn hộ vào task
                task.setStatus(status);  // Gán trạng thái vào task

                // In thông tin của từng task đã được gán
//                System.out.println("TaskID: " + task.getTaskID() + ", Apartment: " + task.getApartmentNumber() + ", Status: " + task.getStatus());
            }

            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("listtask_staff.jsp").forward(request, response);

        } catch (SQLException ex) {
            System.out.println("SQLException occurred in doGet:");
            ex.printStackTrace(); // In lỗi SQLException chi tiết
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException occurred in doGet:");
            ex.printStackTrace(); // In lỗi ClassNotFoundException chi tiết
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Staff loggedInStaff = (Staff) session.getAttribute("user");

        if (loggedInStaff == null) {
            request.setAttribute("errSession", "Bạn cần đăng nhập để thay đổi trạng thái.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        int staffID = loggedInStaff.getStaffID();
        int taskID = Integer.parseInt(request.getParameter("taskID"));
        String status = request.getParameter("status");

        try {
            AssignmentDAO assignmentDAO = WebManager.getInstance().assignmentDAO;
            assignmentDAO.updateStatus(staffID, taskID, status);
            response.sendRedirect("/AtpMan/staffhome");
        } catch (SQLException ex) {
            Logger.getLogger(StaffHome.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffHome.class.getName()).log(Level.SEVERE, null, ex);
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
