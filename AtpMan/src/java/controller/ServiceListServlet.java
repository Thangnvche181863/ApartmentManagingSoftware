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
import model.Service;

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
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        ServiceDAO sdao = new ServiceDAO();
        request.setAttribute("totalservice", sdao.totalService());
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("totalPages", sdao.count(recordsPerPage));
        request.setAttribute("listservice", sdao.servicePaging(page, recordsPerPage));
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
        String search = request.getParameter("search");
        String orderBy = request.getParameter("orderBy");
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        String type = request.getParameter("type");

        if ((type == null || type.equals("All")) && (search == null || search.isEmpty()) && (orderBy == null || orderBy.isEmpty())) {
            // Không có loại dịch vụ cụ thể và không có từ khóa tìm kiếm, lấy tất cả dịch vụ theo phân trang
            request.setAttribute("listservice", sdao.servicePaging(page, recordsPerPage));
        } 
        else if (search != null && !search.isEmpty() && (type == null || type.equals("All"))) {
            // Có từ khóa tìm kiếm, tìm tất cả dịch vụ theo từ khóa (bất kể loại)
            request.setAttribute("listservice", sdao.getServiceByType("All", search, orderBy, page, recordsPerPage));

        } else {
            // Lọc theo loại dịch vụ cụ thể và có thể có sắp xếp
            request.setAttribute("listservice", sdao.getServiceByType(type, search, orderBy, page, recordsPerPage));
            // Đếm số lượng theo loại dịch vụ
        }
        if (recordsPerPage == 25) {
            request.setAttribute("recordsPerPage", 25);
        }
        if (recordsPerPage == 50) {
            request.setAttribute("recordsPerPage", 50);
        }
        if (recordsPerPage == 100) {
            request.setAttribute("recordsPerPage", 100);
        }
        request.setAttribute("type", type);
        request.setAttribute("search", search);
        request.setAttribute("orderBy", orderBy);
        request.setAttribute("totalservice", sdao.totalService());
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", sdao.countActive(type, search, recordsPerPage));
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
