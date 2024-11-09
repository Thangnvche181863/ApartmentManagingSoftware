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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import model.Invoice;

/**
 *
 * @author ADMIN
 */
public class ManagerInvoiceStatAjax extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        InvoiceDAO invoiceDAO = new InvoiceDAO();

        String fromDate_raw = request.getParameter("fromDate");
        String toDate_raw = request.getParameter("toDate");
        String invoiceCode = request.getParameter("invoiceCode");
        String transactionNo = request.getParameter("transactionNo");
        String orderInfo = request.getParameter("orderInfo");
        String currentPage_raw = request.getParameter("currentPage");
        String invoicePerPage_raw = request.getParameter("invoicePerPage");

        Date fromDate = null;
        if (fromDate_raw != null && !fromDate_raw.trim().isEmpty()) {
            fromDate = Date.valueOf(fromDate_raw);
        }
        Date toDate = null;
        if (toDate_raw != null && !toDate_raw.trim().isEmpty()) {
            toDate = Date.valueOf(toDate_raw);
        }

        int invoicePerPage = 5;
        try {
            invoicePerPage = Integer.parseInt(invoicePerPage_raw);
        } catch (NumberFormatException e) {
        }

        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(currentPage_raw);
        } catch (NumberFormatException e) {
        }

        int totalPaidInvoice = invoiceDAO.countInvoiceForStaff(1, fromDate, toDate, invoiceCode, transactionNo, orderInfo);
        int totalPaidInvoicePage = (int) Math.ceil((double) totalPaidInvoice / invoicePerPage);

        if (currentPage > totalPaidInvoicePage) {
            currentPage = 1;
        }

        double totalAmountPaidInvoice = invoiceDAO.totalAmountPaidInvoice(1, fromDate, toDate, invoiceCode, transactionNo, orderInfo);

        List<Invoice> invoiceList = invoiceDAO.getInvoiceForStaff(1, currentPage, invoicePerPage, fromDate, toDate, invoiceCode, transactionNo, orderInfo);

        Locale locale = Locale.US;
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        out.println("<div class=\"table-responsive\">\n"
                + "                                <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">\n"
                + "                                    <thead style=\"background-color: #4e73df; color: white\">\n"
                + "                                        <tr>\n"
                + "                                            <th>#</th>\n"
                + "                                            <th>Mã giao dịch</th>\n"
                + "                                            <th>Mã hóa đơn</th>\n"
                + "                                            <th>Đơn giá</th>\n"
                + "                                            <th>Ngân hàng</th>\n"
                + "                                            <th>Nội dung</th>\n"
                + "                                            <th>Căn hộ</th>\n"
                + "                                            <th>Ngày thanh toán</th>\n"
                + "                                            <th>Thông tin</th>\n"
                + "                                        </tr>\n"
                + "                                    </thead>\n"
                + "                                    <tbody>\n");
        int count = 0;
        for (Invoice invoice : invoiceList) {
            count++;
            out.println("                                            <tr>\n"
                    + "                                                <td>" + count + "</td>\n"
                    + "                                                <td>" + invoice.getTransactionNo() + "</td>\n"
                    + "                                                <td>" + invoice.getInvoiceCode() + "</td>\n"
                    + "                                                <td>" + decimalFormat.format(invoice.getAmount()) + " VNĐ</td>\n"
                    + "                                                <td>" + invoice.getBankCode() + "</td>\n"
                    + "                                                <td>" + invoice.getOrderInfo() + "</td>\n"
                    + "                                                <td>" + invoice.getApartmentName() + "</td>\n"
                    + "                                                <td>" + dateFormat.format(invoice.getTransactionDate()) + "</td>\n"
                    + "                                                <td><input class=\"btn btn-primary\" type=\"submit\" value=\"Chi tiết\" onclick=\"handleDetails(" + invoice.getInvoiceId() + " )\"></td>\n"
                    + "                                                </tr>\n");
        }

        out.println("                                    </tbody>\n"
                + "                                    <tfoot style=\"background-color: #4e73df; color: white\" class=\"h5\">\n"
                + "                                        <tr>\n"
                + "                                            <th colspan=\"9\">\n"
                + "                                                <div  class=\"d-flex justify-content-between\">\n"
                + "                                                    <span>\n"
                + "                                                        Tổng hóa đơn thanh toán: " + totalPaidInvoice + "\n"
                + "                                                    </span>\n"
                + "                                                    <span class=\"d-flex flex-row-reverse\">\n"
                + "                                                        Tổng tiền thanh toán: " + decimalFormat.format(totalAmountPaidInvoice) + " VNĐ\n"
                + "                                                        </span>\n"
                + "                                                    </div>\n"
                + "                                                </th>\n"
                + "                                            </tr>\n"
                + "                                        </tfoot>\n"
                + "                                    </table>\n"
                + "                                    <div class=\"d-flex flex-row-reverse\">\n"
                + "                                        <nav aria-label=\"Page navigation\">\n"
                + "                                            <ul class=\"pagination justify-content-start\">\n");
        if (currentPage > 1) {
            out.println("                                                <li class=\"page-item\">\n"
                    + "                                                    <button class=\"page-link\" value=\"" + (currentPage - 1) + "\" onclick=\"handleSearch(this.value)\">Previous</button>\n"
                    + "                                                </li>\n");
        }
        for (int i = 1; i <= totalPaidInvoicePage; i++) {
            out.println("                                                <li class=\"page-item " + (i == currentPage ? "active" : "") + "\">\n"
                    + "                                                    <button class=\"page-link\" value=\"" + i + "\" onclick=\"handleSearch(this.value)\">" + i + "</button>\n"
                    + "                                                </li>\n");
        }
        if (currentPage < totalPaidInvoicePage) {
            out.println("                                                <li class=\"page-item\">\n"
                    + "                                                    <button class=\"page-link\" value=\"" + (currentPage + 1) + "\" onclick=\"handleSearch(this.value)\">Next</button>\n"
                    + "                                                </li>\n");
        }

        out.println("                                        </ul>\n"
                + "                                    </nav>\n"
                + "                                </div>\n"
                + "                            </div>"
        );
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
        processRequest(request, response);
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
