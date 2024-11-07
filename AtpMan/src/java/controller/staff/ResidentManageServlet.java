/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.staff;

import DAO.ApartmentDAO;
import DAO.BuildingDAO;
import DAO.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.*;

/**
 *
 * @author ADMIN
 */
public class ResidentManageServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet ResidentManageServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResidentManageServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        
        String selectBuilding = request.getParameter("selectBuilding");
        String apartmentNumber = request.getParameter("apartmentNumber");
        String residentName = request.getParameter("residentName");
        String status = request.getParameter("status");
        String owner = request.getParameter("owner");
        String tenant = request.getParameter("tenant");
        
        BuildingDAO buildingDAO = new BuildingDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        ApartmentDAO apartmentDAO = new ApartmentDAO();
        
        List<Building> buildingList = buildingDAO.getAllBuilding();
        
        int currentPage = 1;
        int rowsPerPage = 5;
        
        List<Customer> customerList = customerDAO.getActiveResidentForManage(currentPage, rowsPerPage, 0, null, 2, 2, null);
        int totalResidentSearch = customerDAO.countResidentSearch(0, null, 2, 2, null);
        
        int totalPage = (int) Math.ceil((double) totalResidentSearch/rowsPerPage);
        
        int totalResident = customerDAO.countResident();
        int totalBuilding = buildingDAO.countBuilding();
        int totalApartment = apartmentDAO.countApartment();
        int totalResidentWaitForRegis = customerDAO.countResidentByStatus(3);
        
        List<Customer> registResidentList = customerDAO.getRegistResidentForManage(currentPage, rowsPerPage, 0, null, null);
        
        request.setAttribute("buildingList", buildingList);
        request.setAttribute("customerList", customerList);
        request.setAttribute("totalResident", totalResident);
        request.setAttribute("totalBuilding", totalBuilding);
        request.setAttribute("totalApartment", totalApartment);
        request.setAttribute("totalResidentWaitForRegis", totalResidentWaitForRegis);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("rowsPerPage", rowsPerPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("registResidentList", registResidentList);
        request.getRequestDispatcher("/staff/residentmanage.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        CustomerDAO customerDAO = new CustomerDAO();

        String action = request.getParameter("action");
        String customerId_raw = request.getParameter("customerId");
        
        int customerId = 0;
        try {
            customerId = Integer.parseInt(customerId_raw);
        } catch (NumberFormatException e) {
        }
        
        int status = 0;
        if(action.equals("accept")){
            status = 1;
        }else if(action.equals("decline")){
            status = 0;
        }else if(action.equals("remove")){
            status = 0;
            customerDAO.updateLivingResident(customerId);
            customerDAO.removeAccount(customerId);
        }
        
        customerDAO.updateStatusResident(status, customerId);
        response.sendRedirect("/AtpMan/residentmanage");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
