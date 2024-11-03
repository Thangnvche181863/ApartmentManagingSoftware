/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.InvoiceDAO;
import DAO.ServiceContractDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import model.ServiceContract;

/**
 *
 * @author ADMIN
 */
public class InvoiceStatServiceSearchAjax extends HttpServlet {

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
        PrintWriter out = response.getWriter();

        InvoiceDAO invoiceDAO = new InvoiceDAO();
        ServiceContractDAO serviceContractDAO = new ServiceContractDAO();

        String invoiceId_raw = request.getParameter("invoiceId");
        String searchTerm = request.getParameter("searchTerm");
        String currentPage_raw = request.getParameter("currentPage");
        String servicePerPage_raw = request.getParameter("servicePerPage");

        List<String> searchTermList = new ArrayList<>();

        if (searchTerm != null && !searchTerm.trim().isEmpty() && !searchTerm.trim().equalsIgnoreCase("")) {
            String[] searchTermArray = searchTerm.trim().split("\\s+");
            searchTermList = Arrays.asList(searchTermArray);
        }

        int invoiceId = 0;
        try {
            invoiceId = Integer.parseInt(invoiceId_raw);
        } catch (NumberFormatException e) {
        }

        int currentPage = 1;
        if (currentPage_raw != null) {
            try {
                currentPage = Integer.parseInt(currentPage_raw);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }

        int servicePerPage = 5;
        try {
            servicePerPage = Integer.parseInt(servicePerPage_raw);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        List<ServiceContract> serviceContractList = serviceContractDAO.getCurrentServiceContractByInvoiceId(invoiceId, currentPage, servicePerPage, searchTermList);
        int serviceCount = serviceContractDAO.countCurrentServiceContractByInvoiceId(invoiceId, null);
        Map<String, Double> invoiceMap = invoiceDAO.getAmountByInvoiceId(invoiceId);
        double invoiceAmount = 1;

        for (Map.Entry<String, Double> entry : invoiceMap.entrySet()) {
            invoiceAmount = entry.getValue();
        }

        int totalPages = (int) Math.ceil((double) serviceCount / servicePerPage);

        Locale locale = Locale.US;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat decimalFormat = new DecimalFormat("#,###.#", symbols);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (invoiceId != 0) {
            out.print("                            <div class=\"table-responsive\">\n"
                    + "                                <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">\n"
                    + "                                    <thead style=\"background-color: #4e73df; color: white\">\n"
                    + "                                        <tr>\n"
                    + "                                            <th>#</th>\n"
                    + "                                            <th>Tên</th>\n"
                    + "                                            <th>Loại dịch vụ</th>\n"
                    + "                                            <th>Ngày đăng kí</th>\n"
                    + "                                            <th>Ngày kết thúc</th>\n"
                    + "                                            <th>Đơn giá</th>\n"
                    + "                                            <th>Tỉ lệ</th>\n"
                    + "                                        </tr>\n"
                    + "                                    </thead>\n"
                    + "                                    <tbody>\n");
            if (serviceContractList != null && !serviceContractList.isEmpty()) {
                int count = (currentPage - 1) * servicePerPage;
                for (ServiceContract serviceContract : serviceContractList) {
                    count++;
                    out.println("                                            <tr>\n"
                            + "                                                <td>" + count + "</td>\n"
                            + "                                                <td>" + serviceContract.getService().getName() + "</td>\n"
                            + "                                                <td>" + serviceContract.getService().getType() + "</td>\n"
                            + "                                                <td>" + simpleDateFormat.format(serviceContract.getStartDate()) + "</td>\n"
                            + "                                                <td>" + simpleDateFormat.format(serviceContract.getEndDate()) + "</td>\n"
                            + "                                                <td>" + decimalFormat.format(serviceContract.getAmount()) + " VNĐ</td>\n"
                            + "                                                <td>" + decimalFormat.format(serviceContract.getAmount().doubleValue() / invoiceAmount * 100) + "%</td>\n"
                            + "                                              </tr>\n");
                }
            } else {
                out.println("                                                <tr>\n"
                        + "                                                    <td colspan=\"7\"><h4>Không tìm thấy dịch vụ có tên hoặc loại tương ứng</h4></td>\n"
                        + "                                                  </tr>\n");
            }

            out.println("                                    </tbody>\n"
                    + "                                    <tfoot style=\"background-color: #4e73df; color: white\" class=\"h5\">\n"
                    + "                                        <tr>\n"
                    + "                                            <th colspan=\"7\">\n"
                    + "                                                Tổng tiền dịch vụ: " + decimalFormat.format(invoiceAmount) + " VNĐ\n"
                    + "                                                </th>\n"
                    + "                                            </tr>\n"
                    + "                                        </tfoot>\n"
                    + "                                    </table>\n"
                    + "                                    <div class=\"d-flex flex-row-reverse\">\n"
                    + "                                        <nav aria-label=\"Page navigation\">\n"
                    + "                                            <ul class=\"pagination justify-content-start\">\n");
            if (currentPage > 1) {
                out.println("<li class=\"page-item\">\n"
                        + "   <button class=\"page-link\" value=\"" + (currentPage - 1) + "\" onclick=\"\">Previous</button>\n"
                        + "   </li>");
            }
            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
                    out.println("<li class=\"page-item active\">\n"
                            + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"\">" + i + "</button>\n"
                            + " </li>");
                } else {
                    out.println("<li class=\"page-item\">\n"
                            + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"\">" + i + "</button>\n"
                            + " </li>");
                }
            }
            if (currentPage < totalPages) {
                out.println("<li class=\"page-item\">\n"
                        + "   <button class=\"page-link\" value=\"" + (currentPage + 1) + "\" onclick=\"\">Previous</button>\n"
                        + "   </li>");
            }
            out.println("                                        </ul>\n"
                    + "                                    </nav>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
            );
        } else {
            out.println("                                                <tr>\n"
                    + "                                                    <td colspan=\"7\"><h4>Không tìm thấy dịch vụ có tên hoặc loại tương ứng</h4></td>\n"
                    + "                                                  </tr>\n");
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
