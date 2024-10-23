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
            out.println("<title>Servlet ComplaintList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComplaintList at " + request.getContextPath() + "</h1>");
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
        // Lấy danh sách complaint từ đối tượng DAO
        String search = request.getParameter("search");
        String sort = request.getParameter("sort");
        RequestComplaintDAO requestComplaintDAO = null;
        try {
            requestComplaintDAO = WebManager.getInstance().requestComplaintDAO;
        } catch (SQLException ex) {
            Logger.getLogger(ComplaintList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComplaintList.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (sort == null || sort.isEmpty()) {
            sort = "requestID"; // Giá trị mặc định
        }
        List<RequestComplaint> complaints = requestComplaintDAO.getAllComplaints(search, sort);
//        for (RequestComplaint complaint : complaints) {
//            String customerName = requestComplaintDAO.getCustomerNameByID(complaint.getCustomerID());
//            
//            complaint.setCustomerName(customerName); 
//        }
        request.setAttribute("complaints", complaints);
        request.getRequestDispatcher("complaint_list-admin.jsp").forward(request, response);
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
