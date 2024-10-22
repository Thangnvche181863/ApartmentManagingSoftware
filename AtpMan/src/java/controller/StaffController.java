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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;
import model.Staff;

/**
 *
 * @author Admin
 */
@WebServlet(name = "StaffController", urlPatterns = {"/staff"})
public class StaffController extends HttpServlet {

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
//            StaffDAO dao = new StaffDAO();
//            List<Staff> list = dao.getAllStaff();
//            // get roleName by ID
//            List<String> listRoleName = dao.getRoleNameByID();
//            
//            request.setAttribute("listRoleName", listRoleName);
//            request.setAttribute("listStaff", list);
//            request.getRequestDispatcher("staff.jsp").forward(request, response);
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
        RoleDAO rdao = new RoleDAO();
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        StaffDAO sdao = new StaffDAO();
        request.setAttribute("totalStaff", sdao.getAmountOfStaff());
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("totalPages", sdao.count(recordsPerPage));
        request.setAttribute("listStaff", sdao.staffPaging(page, recordsPerPage));

        request.setAttribute("staffType", rdao.getAllRole());

        request.getRequestDispatcher("staff.jsp").forward(request, response);
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
//        processRequest(request, response);
        StaffDAO dao = new StaffDAO();
        RoleDAO rdao = new RoleDAO();
        String service = request.getParameter("service");
        if (service == null) {
            service = "filter";
        }
        if (service.equals("edit")) {
            List<Role> roleTypes = rdao.getAllRole(); // Lấy danh sách vai trò
            
            int staffID = Integer.parseInt(request.getParameter("id"));
            int roleID = Integer.parseInt(request.getParameter("roleID")); // Lấy roleID từ form

            boolean isUpdated = dao.updateStaffRole(staffID, roleID);

            if (isUpdated) {
                request.setAttribute("roleTypes", roleTypes);
                request.getRequestDispatcher("staff?service=filter"); // Chuyển hướng đến danh sách nhân viên
            } else {
                // Xử lý trường hợp cập nhật không thành công
                request.setAttribute("errorMessage", "Failed to update staff role.");
                request.getRequestDispatcher("staff.jsp").forward(request, response);
            }
        }
        if (service.equals("dismiss")) {
            int staffID = Integer.parseInt(request.getParameter("staffID"));
            int n = dao.dismissStaff(staffID);
            response.sendRedirect("staff");
        } else if (service.equals("filter")) {
            int page = 1;
            int recordsPerPage = 10;
            String search = request.getParameter("search");
            String orderBy = request.getParameter("orderBy");
            int roleID = (request.getParameter("roleID") != null) ? Integer.parseInt(request.getParameter("roleID")) : 0;

            if (request.getParameter("recordsPerPage") != null) {
                recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
            }
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            // Logic lọc nhân viên
            if (roleID == 0 && (search == null || search.isEmpty())) {
                // Lấy tất cả nhân viên
                request.setAttribute("listStaff", dao.staffPaging(page, recordsPerPage));
            } else {
                // Lọc theo vai trò và tìm kiếm
                request.setAttribute("listStaff", dao.getStaffByRole(roleID, search, orderBy, page, recordsPerPage));
            }

            // Thiết lập thuộc tính cho JSP
            request.setAttribute("roleID", roleID);
            request.setAttribute("search", search);
            request.setAttribute("orderBy", orderBy);
            request.setAttribute("totalStaff", dao.getAmountOfStaff());
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", dao.countActive(roleID, search, recordsPerPage));
            request.setAttribute("staffType", rdao.getAllRole());
            request.getRequestDispatcher("staff.jsp").forward(request, response);
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
