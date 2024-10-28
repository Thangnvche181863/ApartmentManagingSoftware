/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAO.CustomerDAO;
import DAO.LivingDAO;
import DAO.ServiceContractDAO;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import model.Customer;
import model.ServiceContract;

/**
 *
 * @author ADMIN
 */
public class UserApartmentInfoServiceTableAjax extends HttpServlet {

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

        //get parameter
        String serviceSearchTerm = request.getParameter("serviceSearchTerm");
        String currentServicePage_raw = request.getParameter("currentServicePage");
        String servicePerPage_raw = request.getParameter("servicePerPage");
        String apartmentID_raw = request.getParameter("apartmentID");

        List<String> serviceSearchTermList = null;
        if (serviceSearchTerm != null && !serviceSearchTerm.isBlank()) {
            String[] searchArr = serviceSearchTerm.trim().split("\\s+");
            serviceSearchTermList = new ArrayList<>(Arrays.asList(searchArr));
            //free memory
            searchArr = null;
        }

        int currentServicePage = 1;
        try {
            currentServicePage = Integer.parseInt(currentServicePage_raw);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        int servicePerPage = 5;
        try {
            servicePerPage = Integer.parseInt(servicePerPage_raw);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        int apartmentID = 0;
        if (apartmentID_raw != null) {
            try {
                apartmentID = Integer.parseInt(apartmentID_raw);
            } catch (NumberFormatException e) {
            }
        }

        LocalDate date = LocalDate.now();
        int totalService = serviceContractDAO.countCurrentServiceContract(apartmentID, Date.valueOf(date), serviceSearchTermList);
        int totalServicePage = (int) Math.ceil((double) totalService / servicePerPage);

        if (currentServicePage > totalServicePage) {
            currentServicePage = 1;
        }

        List<ServiceContract> serviceContractList = serviceContractDAO.getCurrentServiceContract(apartmentID, Date.valueOf(date), currentServicePage, servicePerPage, serviceSearchTermList);

        Locale locale = Locale.US;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        out.print("                                    <div>\n"
                + "                                        <table class=\"table table-striped table-hover table-bordered\">\n"
                + "                                            <thead style=\"background-color: #4e73df; color: white\">\n"
                + "                                                <tr>\n"
                + "                                                    <th>#</th>\n"
                + "                                                    <th>Tên</th>\n"
                + "                                                    <th>Loại dịch vụ</th>\n"
                + "                                                    <th>Ngày đăng kí</th>\n"
                + "                                                    <th>Ngày kết thúc</th>\n"
                + "                                                    <th>Đơn giá</th>\n"
                + "                                                </tr>\n"
                + "                                            </thead>\n"
                + "                                            <tbody>\n");
        int count = (currentServicePage - 1) * servicePerPage;
        int totalAmount = 0;
        if (serviceContractList.isEmpty()) {
            out.println("<tr><td colspan=\"6\">Không có dữ liệu dịch vụ</td></tr>");
        } else {
            for (ServiceContract serviceContract : serviceContractList) {
                count++;
                totalAmount += serviceContract.getAmount().intValue();
                out.println("                                                   <tr>\n"
                        + "                                                        <td>" + count + "</td>\n"
                        + "                                                        <td>" + serviceContract.getService().getName() + "</td>\n"
                        + "                                                        <td>" + serviceContract.getService().getType() + "</td>\n"
                        + "                                                        <td>" + dateFormat.format(serviceContract.getStartDate()) + "</td>\n"
                        + "                                                        <td>" + dateFormat.format(serviceContract.getEndDate()) + "</td>\n"
                        + "                                                        <td>" + decimalFormat.format(serviceContract.getAmount().intValue()) + " VNĐ</td>\n"
                        + "                                                        </tr>\n");
            }
        }
        out.println("                                   </tbody>\n"
                + "                                            <tfoot style=\"background-color: #4e73df; color: white\">\n"
                + "                                                <tr>\n"
                + "                                                    <th colspan=\"6\">Tổng tiền dịch vụ: " + decimalFormat.format(totalAmount) + "</fmt:formatNumber> VNĐ</th>\n"
                + "                                                    </tr>\n"
                + "                                                </tfoot>"
                + "                                </table>\n");
        out.println("<div class=\"d-flex flex-row-reverse\">\n"
                + "                                                <nav aria-label=\"Page navigation\">\n"
                + "                                                    <ul class=\"pagination justify-content-start\">");
        if (currentServicePage > 1) {
            out.println("<li class=\"page-item\">\n"
                    + "   <button class=\"page-link\" value=\"" + (currentServicePage - 1) + "\" onclick=\"handleServiceTable(this.value)\">Previous</button>\n"
                    + "   </li>");
        }
        for (int i = 1; i <= totalServicePage; i++) {
            if (i == currentServicePage) {
                out.println("<li class=\"page-item active\">\n"
                        + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"handleServiceTable(this.value)\">" + i + "</button>\n"
                        + " </li>");
            } else {
                out.println("<li class=\"page-item\">\n"
                        + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"handleServiceTable(this.value)\">" + i + "</button>\n"
                        + " </li>");
            }

        }

        if (currentServicePage < totalServicePage) {
            out.println("<li class=\"page-item\">\n"
                    + "   <button class=\"page-link\" value=\"" + (currentServicePage + 1) + "\" onclick=\"handleServiceTable(this.value)\">Next</button>\n"
                    + "   </li>");

        }

        out.println("\n"
                + "                                                    </ul>\n"
                + "                                                </nav>\n"
                + "                                    </div>\n"
                + "                                    </div>\n");

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
