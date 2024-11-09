/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AssignmentDAO;
import DAO.RoleDAO;
import DAO.StaffDAO;
import DAO.TaskDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Staff;
import model.Task;

/**
 * assignment
 *
 * @author Admin
 */
public class AssignmentController extends HttpServlet {

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
//            TaskDAO dao = new TaskDAO();
//            List<Task> list = dao.getAllTask();
//            int AmountOfTask = dao.getAmountOfTask();
//            
//            request.setAttribute("AmountOfTask", AmountOfTask);
//            request.setAttribute("listTask", list);
//            request.getRequestDispatcher("assignment.jsp").forward(request, response);

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
        TaskDAO dao = new TaskDAO();
        int page = 1;
        int recordsPerPage = 10;
        String taskType = request.getParameter("taskType");
        StaffDAO sdao = new StaffDAO();
        List<Staff> list = sdao.getStaffByTaskType(taskType);
        request.setAttribute("ListStaffByTaskType", list);

        List<Staff> list1 = sdao.getAllStaffHaveRole();
        request.setAttribute("listStaff", list1);
        System.out.println("" + list1.toString());

        // Kiểm tra tham số recordsPerPage
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }

        // Kiểm tra tham số page
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Lấy tổng số task và các task phân trang
        request.setAttribute("totalTask", dao.getAmountOfTask());
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("totalPages", dao.count(recordsPerPage));
        request.setAttribute("listTask", dao.taskPaging(page, recordsPerPage));

        // Lấy loại công việc để hiển thị trong form lọc
        request.setAttribute("taskType", dao.getAllTaskType());

        request.getRequestDispatcher("assignment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TaskDAO dao = new TaskDAO();
        String service = request.getParameter("service");
        // Mặc định dịch vụ là filter nếu không có dịch vụ nào khác
        System.out.println("" + service);
        if (service == null) {
            service = "filter";
        }

        // Sửa công việc
        if (service.equals("create")) {
            TaskDAO tdao = new TaskDAO();
            String taskName = request.getParameter("taskName");
            String description = request.getParameter("description");
            String taskType = request.getParameter("taskType");
            int n = tdao.createTask(taskName, taskType, description);
            String mess = (n > 0) ? "Tạo mới công việc thành công" : "Đã xảy ra lỗi, hãy thử lại!";
            request.getSession().setAttribute("mess", mess); // Sử dụng session
            response.sendRedirect("assignment");
        }

        if (service.equals("assign")) {
            AssignmentDAO adao = new AssignmentDAO();
            int staffID = Integer.parseInt(request.getParameter("staffID"));
            int taskID = Integer.parseInt(request.getParameter("taskID"));
            java.sql.Date startTime = new java.sql.Date(System.currentTimeMillis());

            // Lấy số ngày từ form (người dùng nhập vào)
            int endDays = Integer.parseInt(request.getParameter("endDays"));
            // Tính toán endTime bằng cách cộng số ngày vào startTime
            long endMillis = startTime.getTime() + (long) endDays * 24 * 60 * 60 * 1000;
            java.sql.Date endTime = new java.sql.Date(endMillis);
            String status = "Chua hoan thanh";
            int n = adao.creatAssignment(staffID, taskID, startTime, endTime,status);
            String mess = (n > 0) ? "Công việc đã được giao" : "Đã xảy ra lỗi, hãy thử lại!";
            request.getSession().setAttribute("mess", mess);
            response.sendRedirect("assignment");

        } // Xóa công việc
        //        if (service.equals("delete")) {
        //            int taskId = Integer.parseInt(request.getParameter("taskId"));
        //            //dao.deleteTask(taskId);
        //            response.sendRedirect("assignment");
        else if (service.equals("filter")) {
            int page = 1;
            int recordsPerPage = 10;
            //get Staff by taskType
            StaffDAO sdao = new StaffDAO();
            List<Staff> list1 = sdao.getAllStaffHaveRole();
            request.setAttribute("listStaff", list1);

            String search = request.getParameter("search");
            System.out.println("" + search);
            String orderBy = request.getParameter("orderBy");
            String taskType = (request.getParameter("taskType") != null) ? (request.getParameter("taskType")) : "0";
            request.setAttribute("selectedTaskType", taskType);
            // Lấy tham số phân trang
            if (request.getParameter("recordsPerPage") != null) {
                recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
            }
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            // Logic lọc công việc
            if ("0".equals(taskType) && (search == null || search.isEmpty())) {
                // Nếu chọn "Tất cả", lấy tất cả các task
                request.setAttribute("listTask", dao.taskPaging(page, recordsPerPage));
            } else {
                // Logic lấy task theo loại
                request.setAttribute("listTask", dao.getTaskByType(taskType, search, orderBy, page, recordsPerPage));
            }

            // Thiết lập thuộc tính cho JSP
            request.setAttribute("search", search);
            request.setAttribute("orderBy", orderBy);
            request.setAttribute("totalTask", dao.getAmountOfTask());
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", dao.countActive(taskType, search, recordsPerPage));
            request.setAttribute("taskType", dao.getAllTaskType());
            request.getRequestDispatcher("assignment.jsp").forward(request, response);
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
