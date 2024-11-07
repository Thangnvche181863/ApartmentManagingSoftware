/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAO.ApartmentDAO;
import DAO.CustomerDAO;
import DAO.InvoiceDAO;
import jakarta.mail.internet.MailDateFormat;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import model.Apartment;
import model.Customer;
import model.Invoice;
import utils.UserHomeUtil;

/**
 *
 * @author ADMIN
 */
public class UserInvoiceAjax extends HttpServlet {

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

        UserHomeUtil userUtil = new UserHomeUtil();
        InvoiceDAO invoiceDAO = new InvoiceDAO();

        String month_raw = request.getParameter("selectMonth");
        String year_raw = request.getParameter("selectYear");
        String invoiceCode = request.getParameter("invoiceCode");
        String transactionNo = request.getParameter("transactionNo");
        String orderInfo = request.getParameter("orderInfo");
        String bankCode = request.getParameter("bankCode");
        String currentPage_raw = request.getParameter("currentPage");
        String invoicePerPage_raw = request.getParameter("invoicePerPage");
        String apartmentId_raw = request.getParameter("apartmentId");
        String sort = request.getParameter("sort");

        int month = 0;
        if (month_raw != null && !month_raw.trim().isEmpty()) {
            try {
                month = Integer.parseInt(month_raw);
            } catch (NumberFormatException e) {
            }
        }
        int year = 0;
        if (year_raw != null && !year_raw.trim().isEmpty()) {
            try {
                year = Integer.parseInt(year_raw);
            } catch (NumberFormatException e) {
            }
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

        int apartmentId = 0;
        try {
            apartmentId = Integer.parseInt(apartmentId_raw);
        } catch (NumberFormatException e) {
        }

        List<String> orderInfoList = userUtil.stringToList(orderInfo);

        int numOfInvoice = invoiceDAO.countInvoiceByApartmentIDandMonth2(apartmentId, month, year, invoiceCode, transactionNo, bankCode, orderInfoList);
        int totalInvoicePage = (int) Math.ceil((double) numOfInvoice / invoicePerPage);

        if (currentPage > totalInvoicePage) {
            currentPage = 1;
        }

        List<Invoice> invoiceCurrentList = invoiceDAO.getInvoiceByApartmentIDandMonth2(apartmentId, month, year, currentPage, invoicePerPage, invoiceCode, transactionNo, bankCode, orderInfoList, sort);

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
                + "                                            <th>Ngày thanh toán</th>\n"
                + "                                            <th>Thông tin</th>\n"
                + "                                        </tr>\n"
                + "                                    </thead>\n"
                + "                                    <tbody>\n");
        if (invoiceCurrentList == null || invoiceCurrentList.isEmpty()) {
            out.println("                                                <tr>\n"
                    + "                                                    <td colspan=\"6\"><h4>Không tìm thấy hóa đơn tương ứng</h4></td>\n"
                    + "                                                  </tr>\n");
        } else {
            int count = (numOfInvoice - 1) * invoicePerPage;
            for (Invoice invoice : invoiceCurrentList) {
                count++;
                out.println("                                            <tr>\n"
                        + "                                                <td>" + count + "</td>\n"
                        + "                                                <td>" + invoice.getTransactionNo() + "</td>\n"
                        + "                                                <td>" + invoice.getInvoiceCode() + "</td>\n"
                        + "                                                <td>" + decimalFormat.format(invoice.getAmount()) + " VNĐ</td>\n"
                        + "                                                <td>" + invoice.getBankCode() + "</td>\n"
                        + "                                                <td>" + invoice.getOrderInfo() + "</td>\n"
                        + "                                                <td>" + dateFormat.format(invoice.getTransactionDate()) + "</td>\n"
                        + "                                                <td><input class=\"btn btn-primary\" type=\"submit\" value=\"Chi tiết\" onclick=\"handleDetails(" + invoice.getInvoiceId() + " )\"></td>\n"
                        + "                                                </tr>\n");
            }
        }
        out.println("                                    </tbody>\n"
                + "                                    <tfoot style=\"background-color: #4e73df; color: white\" class=\"h5\">\n"
                + "                                        <tr>\n"
                + "                                            <th colspan=\"9\">\n"
                + "                                                <div  class=\"d-flex justify-content-between\">\n"
                + "                                                    <span>\n"
                + "                                                        Tổng số hóa đơn thanh toán: " + numOfInvoice + "\n"
                + "                                                    </span>\n"
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
                    + "                                                    <button class=\"page-link\" value=\"" + (currentPage - 1) + "\" onclick=\"handleSearchInvoice(this.value)\">Previous</button>\n"
                    + "                                                </li>\n");
        }
        for (int i = 1; i <= totalInvoicePage; i++) {
            out.println("                                                <li class=\"page-item " + (i == currentPage ? "active" : "") + "\">\n"
                    + "                                                    <button class=\"page-link\" value=\"" + i + "\" onclick=\"handleSearchInvoice(this.value)\">" + i + "</button>\n"
                    + "                                                </li>\n");
        }
        if (currentPage < totalInvoicePage) {
            out.println("                                                <li class=\"page-item\">\n"
                    + "                                                    <button class=\"page-link\" value=\"" + (currentPage + 1) + "\" onclick=\"handleSearchInvoice(this.value)\">Next</button>\n"
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
