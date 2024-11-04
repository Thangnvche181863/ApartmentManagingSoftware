package controller.staff;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
public class UnpaidInvoiceStatServlet extends HttpServlet {

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
            out.println("<title>Servlet UnpaidInvoiceStatServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UnpaidInvoiceStatServlet at " + request.getContextPath() + "</h1>");
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

        double totalAmount = invoiceDAO.totalAmountPaidInvoice(0, null, null, null, null, null);
        int totalUnPaidInvoice = invoiceDAO.totalInvoiceByStatus(0);
        Date minDate = invoiceDAO.getEarliestIssueDateInvoice();
        LocalDate maxDate = LocalDate.now();

        List<Invoice> invoiceList = invoiceDAO.getInvoiceForStaff(0, 1, invoicePerPage, null, null, null, null, null);

//        int totalUnPaidInvoice2 = invoiceDAO.countInvoiceForStaff(0, null, null, null, null, null);
        int totalUnPaidInvoicePage = (int) Math.ceil((double) totalUnPaidInvoice / invoicePerPage);

        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("totalUnPaidInvoice", totalUnPaidInvoice);
        request.setAttribute("invoiceList", invoiceList);
        request.setAttribute("minDate", minDate);
        request.setAttribute("maxDate", maxDate);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalUnPaidInvoicePage", totalUnPaidInvoicePage);
        request.getRequestDispatcher("/staff/unpaidinvoicestat.jsp").forward(request, response);
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
