/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

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
public class UserInvoiceStatDetailsAjax extends HttpServlet {

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

        ServiceContractDAO serviceContractDAO = new ServiceContractDAO();
        InvoiceDAO invoiceDAO = new InvoiceDAO();

        String invoiceId_raw = request.getParameter("invoiceId");

        int invoiceId = 0;
        try {
            invoiceId = Integer.parseInt(invoiceId_raw);
        } catch (NumberFormatException e) {
        }

        ServiceContract serviceContract = serviceContractDAO.getServiceContractByInvoiceId(invoiceId);

        Map<String, Double> invoiceMap = invoiceDAO.getAmountByInvoiceId(invoiceId);
        String invoiceCode = "";
        double invoiceAmount = 1;

        if (invoiceMap != null && !invoiceMap.isEmpty()) {
            for (Map.Entry<String, Double> entry : invoiceMap.entrySet()) {
                invoiceCode = entry.getKey();
                invoiceAmount = entry.getValue();
            }
        }

        Locale locale = Locale.US;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat decimalFormat = new DecimalFormat("#,###.#", symbols);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (invoiceId != 0) {
            out.print("<div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">\n"
                    + "                            <h5 class=\"m-0 font-weight-bold text-primary text-gray-800 col-md-5\">Chi tiết dịch vụ hóa đơn: mã HĐ " + invoiceCode + "</h5>\n"
                    + "                        </div>\n"
                    + "                        <div id=\"serviceTable\" class=\"card-body\">\n"
                    + "                            <div class=\"table-responsive\">\n"
                    + "                                <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">\n"
                    + "                                    <thead style=\"background-color: #4e73df; color: white\">\n"
                    + "                                        <tr>\n"
                    + "                                            <th>Tên</th>\n"
                    + "                                            <th>Loại dịch vụ</th>\n"
                    + "                                            <th>Ngày đăng kí</th>\n"
                    + "                                            <th>Ngày kết thúc</th>\n"
                    + "                                            <th>Đơn giá</th>\n"
                    + "                                        </tr>\n"
                    + "                                    </thead>\n"
                    + "                                    <tbody>\n");
            if (serviceContract != null) {

                out.println("                                            <tr>\n"
                        + "                                                <td>" + serviceContract.getService().getName() + "</td>\n"
                        + "                                                <td>" + serviceContract.getService().getType() + "</td>\n"
                        + "                                                <td>" + simpleDateFormat.format(serviceContract.getStartDate()) + "</td>\n"
                        + "                                                <td>" + simpleDateFormat.format(serviceContract.getEndDate()) + "</td>\n"
                        + "                                                <td>" + decimalFormat.format(serviceContract.getAmount()) + " VNĐ</td>\n"
                        + "                                              </tr>\n");

            } else {
                out.println("                                                <tr>\n"
                        + "                                                    <td colspan=\"6\"><h4>Không tìm thấy dịch vụ có tên hoặc loại tương ứng</h4></td>\n"
                        + "                                                  </tr>\n");
            }

        } else {
            out.println("<div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">"
                    + "<h5 class=\"m-0 font-weight-bold text-primary col-md-5\">Có lỗi xảy ra</h5>\n"
                    + "</div>");
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
