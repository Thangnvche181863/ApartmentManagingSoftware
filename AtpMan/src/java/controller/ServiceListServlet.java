/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import model.ServiceType;

/**
 *
 * @author thang
 */
public class ServiceListServlet extends HttpServlet {

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
            out.println("<title>Servlet ServiceAddServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServiceAddServlet at " + request.getContextPath() + "</h1>");
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
        int recordsPerPage = 10;
        String type = "";
        String search = "";
        String orderBy = "";
        if (request.getParameter("type") != null) {
            type = request.getParameter("type");
        }
        if (request.getParameter("search") != null) {
            search = request.getParameter("search");
        }
        if (request.getParameter("orderBy") != null) {
            orderBy = request.getParameter("orderBy");
        }
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        request.setAttribute("type", type);
        request.setAttribute("search", search);
        request.setAttribute("orderBy", orderBy);
        ServiceDAO sdao = new ServiceDAO();
        int totalRecords = sdao.getTotalService(type, search, orderBy);
        request.setAttribute("totalservice", totalRecords);
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("totalPages", (int) Math.ceil((double) totalRecords / recordsPerPage));
        request.setAttribute("listservice", sdao.getServiceByType(type, search, orderBy, page, recordsPerPage));
        request.setAttribute("serviceType", sdao.getAllType());
        request.getRequestDispatcher("servicelist.jsp").forward(request, response);
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
        ServiceDAO sdao = new ServiceDAO();
        int page = 1;
        int recordsPerPage = 10;
        String type = request.getParameter("type");
        String search = request.getParameter("search").trim().replaceAll("\\s+", " ");
        String orderBy = request.getParameter("orderBy");
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        request.setAttribute("listservice", sdao.getServiceByType(type, search, orderBy, page, recordsPerPage));

        request.setAttribute("recordsPerPage", recordsPerPage);

        int totalRecords = sdao.getTotalService(type, search, orderBy);
        request.setAttribute("totalservice", totalRecords);
        request.setAttribute("type", type);
        request.setAttribute("search", search);
        request.setAttribute("orderBy", orderBy);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", (int) Math.ceil((double) totalRecords / recordsPerPage));
        request.setAttribute("serviceType", sdao.getAllType());

        request.getRequestDispatcher("servicelist.jsp").forward(request, response);
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
