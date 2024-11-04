/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.BuildingDAO;
import DAO.FinanceDAO;
import DAO.FinanceTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import model.Building;
import model.FinanceType;

/**
 *
 * @author thang
 */
@MultipartConfig
public class CostStatisticServlet extends HttpServlet {

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
            out.println("<title>Servlet CostStatisticServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CostStatisticServlet at " + request.getContextPath() + "</h1>");
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
        String status = request.getParameter("status");

        // Kiểm tra tham số status và hiển thị thông báo nếu thành công
        if ("success".equals(status)) {
            request.setAttribute("message", "Tạo thành công!");
        }

        FinanceTypeDAO ftdao = new FinanceTypeDAO();
        BuildingDAO bdao = new BuildingDAO();

        List<Building> buildings = bdao.getAllBuilding();
        List<FinanceType> financeTypes = ftdao.getAll();

        request.setAttribute("buildings", buildings);
        request.setAttribute("financeTypes", financeTypes);
        request.getRequestDispatcher("costStatistics.jsp").forward(request, response);
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
        String typeBuilding = request.getParameter("typeBuilding");
        String typeFee = request.getParameter("typeFee");
        String fee = request.getParameter("fee");
        fee = fee.replace(",", "");
        String description = request.getParameter("description");

//        out.print(typeBuilding);
//        out.print(typeFee);
//        out.print(fee);
//        out.print(description);

        FinanceDAO fdao = new FinanceDAO();
        fdao.insertFinance(Integer.parseInt(typeBuilding), Integer.parseInt(typeFee), BigDecimal.valueOf(Double.parseDouble(fee)), Date.valueOf(LocalDate.now()), description);
        
        response.sendRedirect("costStatistic?status=success");
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