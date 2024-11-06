/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AssignmentDAO;
import DAO.HandleRequestDAO;
import DAO.RequestComplaintDAO;
import DAO.StaffDAO;
import DAO.TaskDAO;
import java.io.IOException;
import java.io.PrintWriter;
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

            if (search == null) {
                search = "";
            }

            if (sort == null) {
                sort = "date";
            }

            List<RequestComplaint> complaints = requestComplaintDAO.getComplaints(search, searchField, sort);

            request.setAttribute("complaints", complaints);
            request.setAttribute("search", search);
            request.setAttribute("searchField", searchField);
            request.setAttribute("sort", sort);
            request.getRequestDispatcher("complaint_list-admin.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ComplaintList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            
//            RequestComplaintDAO requestComplaintDAO = WebManager.getInstance().requestComplaintDAO;
//            TaskDAO taskDAO = WebManager.getInstance().taskDAO;
//
//            int requestID = Integer.parseInt(request.getParameter("requestID"));
//            RequestComplaint complaint = requestComplaintDAO.getComplaintByID(requestID);
//
//            String description = complaint.getDescription();
//            String type = complaint.getType();
//
//            // Tạo task với type và description từ complaint
//            int taskID = taskDAO.createTask(
//                    complaint.getRequestID(),
//                    complaint.getDescription(),
//                    complaint.getType()
//            );
//
//            // Update trạng thái request thành đã xử lý
//            requestComplaintDAO.updateStatus(requestID, 1);
//
//            // Lưu taskID vào session để dùng cho trang assignstaff.jsp
//            HttpSession session = request.getSession();
//            session.setAttribute("taskID", taskID);
//
//            // Chuyển hướng đến trang assignstaff.jsp
//            response.sendRedirect("createtask.jsp");
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(ComplaintList.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            int requestID = Integer.parseInt(request.getParameter("requestID"));
//        int staffID = Integer.parseInt(request.getParameter("staffID"));  // Lấy staffID từ request

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

                handleRequestDAO.createHandleRequest(requestID, taskID);

                HttpSession session = request.getSession();
                session.setAttribute("taskID", taskID);
                List<RequestComplaint> complaints = requestComplaintDAO.getComplaints("", null, "date");
                request.setAttribute("complaints", complaints);
//            response.sendRedirect("complaint_list-admin.jsp");
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
