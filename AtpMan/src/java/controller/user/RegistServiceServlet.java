/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAO.ServiceContractDAO;
import DAO.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import model.Customer;
import model.Service;

/**
 *
 * @author thang
 */
public class RegistServiceServlet extends HttpServlet {

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
            out.println("<title>Servlet RegistServiceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistServiceServlet at " + request.getContextPath() + "</h1>");
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
//        PrintWriter out = response.getWriter();
        String apartmentId = request.getParameter("apartmentId");
        String serviceId = request.getParameter("serviceId");
//        out.println(apartmentId);
//                out.println(serviceId);

        ServiceContractDAO scdao = new ServiceContractDAO();
        
        scdao.deleteServiceContract(Integer.parseInt(apartmentId), Integer.parseInt(serviceId));
        response.sendRedirect("registServiceTenant");
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

        PrintWriter out = response.getWriter();
        String apartmentID = request.getParameter("apartmentID");
        String serviceID = request.getParameter("serviceID");
        String fee = request.getParameter("fee");
        String subscriptionPlan = request.getParameter("subscriptionPlan");
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        // Kiểm tra giá trị của subscriptionPlan
        if ("1".equals(subscriptionPlan)) {
            endDate = startDate.plus(1, ChronoUnit.MONTHS);; // Nếu là gói 1 tháng,
        } else if ("2".equals(subscriptionPlan)) {
            endDate = startDate.plus(2, ChronoUnit.MONTHS); // Gói 2 tháng
        } else if ("3".equals(subscriptionPlan)) {
            endDate = startDate.plus(3, ChronoUnit.MONTHS); // Gói 3 tháng
        }
        fee = fee.replace(",", "").replace(" VND/tháng", "");
        out.println(startDate);
        out.println(endDate);
        out.println(apartmentID);
        out.println(serviceID);

        ServiceContractDAO scdao = new ServiceContractDAO();
//        
        scdao.insertServiceContract(Integer.parseInt(apartmentID), Integer.parseInt(serviceID), Date.valueOf(startDate), Date.valueOf(endDate), Double.parseDouble(fee));
        response.sendRedirect("registServiceTenant?status=success");
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
