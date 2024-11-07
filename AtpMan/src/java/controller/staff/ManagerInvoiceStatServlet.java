/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.InvoiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import model.Invoice;

/**
 *
 * @author ADMIN
 */
public class ManagerInvoiceStatServlet extends HttpServlet {

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
            out.println("<title>Servlet ManagerInvoiceStatServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerInvoiceStatServlet at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        InvoiceDAO invoiceDAO = new InvoiceDAO();

        int invoicePerPage = 5;
        int currentPage = 1;

        BigDecimal totalAmount = invoiceDAO.totalPaidInvoice();
        BigDecimal totalAmountCurrentMonth = invoiceDAO.totalPaidInvoiceCurrentMonth(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        int totalPaidInvoice = invoiceDAO.totalInvoiceByStatus(1);
        int totalPaidInvoiceCurrentMonth = invoiceDAO.countPaidInvoiceCurrentMonth(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        Date minDate = invoiceDAO.getEarliestDateInvoice();
        LocalDate maxDate = LocalDate.now();

        List<Invoice> invoiceList = invoiceDAO.getInvoiceForStaff(1, 1, invoicePerPage, null, null, null, null, null);

//        int totalPaidInvoice = invoiceDAO.countInvoiceForStaff(null, null, null, null, null);
        int totalPaidInvoicePage = (int) Math.ceil((double) totalPaidInvoice / invoicePerPage);

        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("totalAmountCurrentMonth", totalAmountCurrentMonth);
        request.setAttribute("totalPaidInvoice", totalPaidInvoice);
        request.setAttribute("totalPaidInvoiceCurrentMonth", totalPaidInvoiceCurrentMonth);
        request.setAttribute("invoiceList", invoiceList);
        request.setAttribute("minDate", minDate);
        request.setAttribute("maxDate", maxDate);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPaidInvoicePage", totalPaidInvoicePage);
        request.getRequestDispatcher("/staff/managerinvoicestatistic.jsp").forward(request, response);
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
        processRequest(request, response);
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
