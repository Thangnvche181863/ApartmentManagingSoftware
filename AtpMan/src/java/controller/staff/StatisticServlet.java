/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.BuildingDAO;
import DAO.FinanceDAO;
import DAO.InvoiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import model.Building;
import model.Finance;
import model.Statistic;

/**
 *
 * @author thang
 */
public class StatisticServlet extends HttpServlet {

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
            out.println("<title>Servlet StatisticServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticServlet at " + request.getContextPath() + "</h1>");
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
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        
        BuildingDAO buildingDAO = new BuildingDAO();
        List<Building> buildings = buildingDAO.getAllBuilding();

        FinanceDAO fdao = new FinanceDAO();
        List<Finance> finances = fdao.getAllByTime(year, month, 1);
        request.setAttribute("buildings", buildings);
        request.setAttribute("finances", finances);
        
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("buildingId", 1);

        request.getRequestDispatcher("charts.jsp").forward(request, response);

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

        
        FinanceDAO fdao = new FinanceDAO();

        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String buildingId = request.getParameter("buildingId");

        BuildingDAO buildingDAO = new BuildingDAO();
        List<Building> buildings = buildingDAO.getAllBuilding();
        request.setAttribute("buildings", buildings);

        List<Finance> finances = fdao.getAllByTime(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(buildingId));
        request.setAttribute("finances", finances);

        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("buildingId", buildingId);

        request.getRequestDispatcher("charts.jsp").forward(request, response);
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
