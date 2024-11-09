/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AssignmentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Assignment;

/**
 *
 * @author Admin
 */
public class TaskController extends HttpServlet {

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
        AssignmentDAO adao = new AssignmentDAO();

        // Thiết lập các tham số phân trang mặc định
        int page = 1;
        int recordsPerPage = 10;

        // Lấy tham số `recordsPerPage` từ request nếu có
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }

        // Lấy tham số `page` từ request nếu có
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        //Lấy ra số lượng công việc đã giao
        int amountOfAssignment = adao.getAmountOfAssignment();

        // Lấy danh sách các Assignment có phân trang
        List<Assignment> listAssignments = adao.AssignmentPaging(page, recordsPerPage);
        int totalAssignments = adao.count(recordsPerPage);
        int numberCompleteAssignment = adao.getNumberCompleteAssignment();

        request.setAttribute("numberCompleteAssignment", numberCompleteAssignment);
        request.setAttribute("numberUncompleteAssignment", amountOfAssignment - numberCompleteAssignment);
        // Thiết lập thuộc tính cho request để hiển thị trên JSP
        request.setAttribute("amountOfAssignment", amountOfAssignment);
        request.setAttribute("listAssignments", adao.AssignmentPaging(page, recordsPerPage));
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("totalPages", totalAssignments);

        // Lấy tất cả các task và loại công việc để hiển thị trên form lọc
        // Forward request và response đến assignment.jsp
        request.getRequestDispatcher("task.jsp").forward(request, response);
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
        AssignmentDAO adao = new AssignmentDAO();
        int page = 1;
        int recordsPerPage = 10;
        //get Staff by taskType
        AssignmentDAO dao = new AssignmentDAO();
        List<Assignment> list1 = dao.getAllAssignment();
        request.setAttribute("listAssignments", list1);

        String search = request.getParameter("search");
        System.out.println("" + search);
        String orderBy = request.getParameter("orderBy");
        String status = (request.getParameter("status") != null) ? (request.getParameter("status")) : "0";

        request.setAttribute("selectedStatus", status);
        // Lấy tham số phân trang
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Logic lọc công việc
        if ("0".equals(status) && (search == null || search.isEmpty())) {
            // Nếu chọn "Tất cả", lấy tất cả các task
            request.setAttribute("listAssignments", dao.AssignmentPaging(page, recordsPerPage));
        } else {
            // Logic lấy công việc theo trạng thái 
            request.setAttribute("listAssignments", dao.getAssignmentByType(status, search, orderBy, page, recordsPerPage));
        }

        int amountOfAssignment = adao.getAmountOfAssignment();
        int numberCompleteAssignment = adao.getNumberCompleteAssignment();
        request.setAttribute("numberCompleteAssignment", numberCompleteAssignment);
        request.setAttribute("numberUncompleteAssignment", amountOfAssignment - numberCompleteAssignment);
        // Thiết lập thuộc tính cho JSP
        request.setAttribute("search", search);
        request.setAttribute("orderBy", orderBy);
        request.setAttribute("amountOfAssignment", dao.getAmountOfAssignment());
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", dao.countActive(status, search, recordsPerPage));
        request.getRequestDispatcher("task.jsp").forward(request, response);

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
