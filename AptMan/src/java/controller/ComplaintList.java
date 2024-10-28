/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.RequestComplaintDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RequestComplaint;

/**
 *
 * @author WuanTun
 */
public class ComplaintList extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//       
//        RequestComplaintDAO requestComplaintDAO = null;
//        try {
//            requestComplaintDAO = WebManager.getInstance().requestComplaintDAO;
//        } catch (SQLException ex) {
//            Logger.getLogger(ComplaintList.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ComplaintList.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       
//
//        List<RequestComplaint> complaints = requestComplaintDAO.getAllComplaints();
////        for (RequestComplaint complaint : complaints) {
////            String customerName = requestComplaintDAO.getCustomerNameByID(complaint.getCustomerID());
////            
////            complaint.setCustomerName(customerName); 
////        }
//        request.setAttribute("complaints", complaints);
//        request.getRequestDispatcher("complaint_list-admin.jsp").forward(request, response);
//    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RequestComplaintDAO requestComplaintDAO = WebManager.getInstance().requestComplaintDAO;

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RequestComplaintDAO requestComplaintDAO = WebManager.getInstance().requestComplaintDAO;
            int requestID = Integer.parseInt(request.getParameter("requestID"));
            int status = Integer.parseInt(request.getParameter("status"));

            requestComplaintDAO.updateStatus(requestID, status);
            response.sendRedirect("complaintlist");
        } catch (SQLException ex) {
            Logger.getLogger(RequestServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RequestServlet.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
