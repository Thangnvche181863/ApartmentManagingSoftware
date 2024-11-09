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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import model.ServiceContract;

/**
 *
 * @author ADMIN
 */
public class UnPaidInvoiceServiceAjax extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        
        ServiceContractDAO serviceContractDAO = new ServiceContractDAO();
        InvoiceDAO invoiceDAO = new InvoiceDAO();

        String invoiceId_raw = request.getParameter("invoiceId");

        int invoiceId = 0;
        try {
            invoiceId = Integer.parseInt(invoiceId_raw);
        } catch (NumberFormatException e) {
        }

        int currentServicePage = 1;
        int servicePerPage = 5;

        List<ServiceContract> serviceContractList = serviceContractDAO.getCurrentServiceContractByInvoiceId(invoiceId, currentServicePage, servicePerPage, null);
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
            out.print("<div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">\n"
                    + "                            <h5 class=\"m-0 font-weight-bold text-primary text-gray-800 col-md-5\">Chi tiết dịch vụ hóa đơn: </h5>\n"
                    + "                            <div class=\"col-md-2\">\n"
                    + "                                <select id=\"servicePerPage\" name=\"servicePerPage\" class=\"form-select font-weight-bold text-primary text-uppercase\" aria-label=\"Default select example\" onchange=\"handleSearchDetails($('#serviceTable .pagination .page-item.active button.page-link').val())\">\n"
                    + "                                    <option value=\"5\">Số lượng hiển thị: 5</option>\n"
                    + "                                    <option value=\"10\">Số lượng hiển thị: 10</option>\n"
                    + "                                    <option value=\"25\">Số lượng hiển thị: 25</option>\n"
                    + "                                    <option value=\"50\">Số lượng hiển thị: 50</option>\n"
                    + "                                </select>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"col-md-4\">\n"
                    + "                                <div class=\"input-group rounded \">\n"
                    + "                                    <!--reset the current page to 1 cause of search can reduce the number of page-->\n"
                    + "                                    <input id=\"searchService\" name=\"searchService\" type=\"text\" value=\"\" oninput=\"handleSearchDetails($('#serviceTable .pagination .page-item.active button.page-link').val())\" class=\"form-control\" placeholder=\"Search\" aria-label=\"Search\" aria-describedby=\"search-addon\" />\n"
                    + "                                    <input id=\"invoiceID\" type=\"hidden\" name=\"invoiceID\" value=\""+ invoiceId +"\" />                            "
                    + "                                     <div class=\"input-group-append\">\n"
                    + "                                        <span class=\"input-group-text btn-primary border-0\" id=\"search-addon\">\n"
                    + "                                            <i class=\"fas fa-search\"></i>\n"
                    + "                                        </span>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                        <div id=\"serviceTable\" class=\"card-body\">\n"
                    + "                            <div class=\"table-responsive\">\n"
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
                int count = (currentServicePage - 1) * servicePerPage;
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
            if (currentServicePage > 1) {
                out.println("<li class=\"page-item\">\n"
                        + "   <button class=\"page-link\" value=\"" + (currentServicePage - 1) + "\" onclick=\"handleSearchDetails(this.value)\">Previous</button>\n"
                        + "   </li>");
            }
            for (int i = 1; i <= totalPages; i++) {
                if (i == currentServicePage) {
                    out.println("<li class=\"page-item active\">\n"
                            + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"handleSearchDetails(this.value)\">" + i + "</button>\n"
                            + " </li>");
                } else {
                    out.println("<li class=\"page-item\">\n"
                            + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"handleSearchDetails(this.value)\">" + i + "</button>\n"
                            + " </li>");
                }
            }
            if (currentServicePage < totalPages) {
                out.println("<li class=\"page-item\">\n"
                        + "   <button class=\"page-link\" value=\"" + (currentServicePage + 1) + "\" onclick=\"handleSearchDetails(this.value)\">Next</button>\n"
                        + "   </li>");
            }
            out.println("                                        </ul>\n"
                    + "                                    </nav>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </div>   "
            );
        } else {
            out.println("<div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">"
                    + "<h5 class=\"m-0 font-weight-bold text-primary col-md-5\">Có lỗi xảy ra</h5>\n"
                    + "</div>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
