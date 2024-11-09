/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.HandleRequestDAO;
import DAO.RequestComplaintDAO;
import DAO.StaffDAO;
import DAO.TaskDAO;
import controller.WebManager;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RequestComplaint;
import model.Staff;

/**
 *
 * @author WuanTun
 */
public class ComplaintList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RequestComplaintDAO requestComplaintDAO = WebManager.getInstance().requestComplaintDAO;
            StaffDAO staffDAO = WebManager.getInstance().staffDAO;

            List<Staff> staffList = staffDAO.getAllStaff();
            request.setAttribute("staffList", staffList);

            String search = request.getParameter("search");
            String searchField = request.getParameter("searchField");
            String sort = request.getParameter("sort");

            // Get page parameters
            int page = 1;
            int pageSize = 10;
            String pageStr = request.getParameter("page");
            if (pageStr != null && !pageStr.isEmpty()) {
                page = Integer.parseInt(pageStr);
            }

            if (search == null) {
                search = "";
            }

            if (sort == null) {
                sort = "date";
            }

            // Get total complaints for pagination
            int totalComplaints = requestComplaintDAO.getTotalComplaints(search, searchField);
            int totalPages = (int) Math.ceil((double) totalComplaints / pageSize);

            // Get complaints for current page
            List<RequestComplaint> complaints = requestComplaintDAO.getComplaints(search, searchField, sort, page, pageSize);

            request.setAttribute("complaints", complaints);
            request.setAttribute("search", search);
            request.setAttribute("searchField", searchField);
            request.setAttribute("sort", sort);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("complaint_list-admin.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ComplaintList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            String action = request.getParameter("action");
//            int requestID = Integer.parseInt(request.getParameter("requestID"));
//
//            RequestComplaintDAO requestComplaintDAO = WebManager.getInstance().requestComplaintDAO;
//            TaskDAO taskDAO = WebManager.getInstance().taskDAO;
//            HandleRequestDAO handleRequestDAO = WebManager.getInstance().handleRequestDAO;
//
//            if ("accept".equals(action)) {
//                RequestComplaint complaint = requestComplaintDAO.getComplaintByID(requestID);
//                String taskName = complaint.getTitle();
//                String description = complaint.getDescription();
//                String taskType = complaint.getType();
//
//                int taskID = taskDAO.createTask(taskName, taskType, description);
//
//                requestComplaintDAO.updateStatus(requestID, 1);
//
//                handleRequestDAO.createHandleRequest(requestID, taskID);
//
//                HttpSession session = request.getSession();
//                session.setAttribute("taskID", taskID);
//                int page = 1;
//                int pageSize = 10;
//                List<RequestComplaint> complaints = requestComplaintDAO.getComplaints("", null, "date", page, pageSize);
//                request.setAttribute("complaints", complaints);
////            response.sendRedirect("complaint_list-admin.jsp");
//                request.getRequestDispatcher("complaint_list-admin.jsp").forward(request, response);
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(ComplaintList.class.getName()).log(Level.SEVERE, null, ex);
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            int requestID = Integer.parseInt(request.getParameter("requestID"));

            RequestComplaintDAO requestComplaintDAO = WebManager.getInstance().requestComplaintDAO;
            TaskDAO taskDAO = WebManager.getInstance().taskDAO;
            HandleRequestDAO handleRequestDAO = WebManager.getInstance().handleRequestDAO;

            if ("accept".equals(action)) {
                RequestComplaint complaint = requestComplaintDAO.getComplaintByID(requestID);
                String taskName = complaint.getTitle();
                String description = complaint.getDescription();
                String taskType = complaint.getType();

                int taskID = taskDAO.createTask(taskName, taskType, description);

                requestComplaintDAO.updateStatus(requestID, 1);

                handleRequestDAO.createHandleRequest2(requestID, taskID);

                // Lưu lại thông tin phân trang và tìm kiếm
                String search = request.getParameter("search");
                String searchField = request.getParameter("searchField");
                String sort = request.getParameter("sort");

                HttpSession session = request.getSession();
                session.setAttribute("taskID", taskID);

                // Truyền tham số phân trang và tìm kiếm vào request
                request.setAttribute("search", search);
                request.setAttribute("searchField", searchField);
                request.setAttribute("sort", sort);
                request.setAttribute("requestID1", requestID);
                // Truyền dữ liệu phân trang
                int page = 1;
                int pageSize = 10;
                List<RequestComplaint> complaints = requestComplaintDAO.getComplaints(search, searchField, sort, page, pageSize);
                request.setAttribute("complaints", complaints);

                // Chuyển tiếp đến trang JSP với tham số phân trang
                request.getRequestDispatcher("complaint_list-admin.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ComplaintList.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
