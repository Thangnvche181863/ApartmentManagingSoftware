/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ApartmentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.Apartment;

/**
 *
 * @author thang
 */
public class RegistListServlet extends HttpServlet {

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
            out.println("<title>Servlet RegistList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistList at " + request.getContextPath() + "</h1>");
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
        int page = 1;
        int recordsPerPage = 25;
        String buildingtype = "";
        String apartmentType = "";
        String search = "";
        String orderBy = "";
        if (request.getParameter("apartmentType") != null) {
            apartmentType = request.getParameter("apartmentType");
        }
        if (request.getParameter("search") != null) {
            search = request.getParameter("search");
        }
        if (request.getParameter("orderBy") != null) {
            orderBy = request.getParameter("orderBy");
        }
        if (request.getParameter("buildingtype") != null) {
            buildingtype = request.getParameter("buildingtype");
        }
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        ApartmentDAO adao = new ApartmentDAO();
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("buildingtype", buildingtype);
        request.setAttribute("apartmentType", apartmentType);
        request.setAttribute("search", search);
        request.setAttribute("orderBy", orderBy);
        int totalRecords = adao.getTotalApartment(buildingtype, apartmentType, search, orderBy);
        request.setAttribute("totalPages", (int) Math.ceil((double) totalRecords / recordsPerPage));
        request.setAttribute("totalRoom", totalRecords);
        request.setAttribute("listbuilding", adao.getAllBuilding());
        request.setAttribute("listdepartment", adao.getAllDepartmentType());
        request.setAttribute("listapart", adao.allApartmentPaging(page, recordsPerPage, buildingtype, apartmentType, search, orderBy));
        request.getRequestDispatcher("registlist.jsp").forward(request, response);
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
        // PrintWriter out = response.getWriter();
        int page = 1;
        int recordsPerPage = 25;
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        ApartmentDAO adao = new ApartmentDAO();

        String buildingtype = request.getParameter("buildingtype");
        String apartmentType = request.getParameter("apartmentType");
        String search = request.getParameter("search").trim().replaceAll("\\s+", " ");
        String orderBy = request.getParameter("orderBy");

        request.setAttribute("listapart", adao.allApartmentPaging(page, recordsPerPage, buildingtype, apartmentType, search, orderBy));
        request.setAttribute("buildingtype", buildingtype);
        request.setAttribute("apartmentType", apartmentType);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("orderBy", orderBy);
        request.setAttribute("search", search);
        int totalRecords = adao.getTotalApartment(buildingtype, apartmentType, search, orderBy);
        request.setAttribute("totalPages", (int) Math.ceil((double) totalRecords / recordsPerPage));
         if(page > (int) Math.ceil((double) totalRecords / recordsPerPage)){
             request.setAttribute("currentPage", (int) Math.ceil((double) totalRecords / recordsPerPage)-1);
        }else{
            request.setAttribute("currentPage", page);
        }
        request.setAttribute("totalRoom", totalRecords);
        request.setAttribute("listbuilding", adao.getAllBuilding());
        request.setAttribute("listdepartment", adao.getAllDepartmentType());
        request.getRequestDispatcher("registlist.jsp").forward(request, response);

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
