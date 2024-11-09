/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.CustomerDAO;
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
import model.Customer;
import utils.UserHomeUtil;

/**
 *
 * @author ADMIN
 */
public class RegisResidentTableAjax extends HttpServlet {

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

        UserHomeUtil userUtil = new UserHomeUtil();
        CustomerDAO customerDAO = new CustomerDAO();

        String buildingId_raw = request.getParameter("selectBuilding");
        String apartmentNumber = request.getParameter("apartmentNumber");
        String residentName = request.getParameter("residentName");
        String currentPage_raw = request.getParameter("currentPage");
        String residentPerPage_raw = request.getParameter("residentPerPage");

        int buildingId = 0;
        try {
            buildingId = Integer.parseInt(buildingId_raw);
        } catch (NumberFormatException e) {
        }

        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(currentPage_raw);
        } catch (NumberFormatException e) {
        }

        int residentPerPage = 5;
        try {
            residentPerPage = Integer.parseInt(residentPerPage_raw);
        } catch (NumberFormatException e) {
        }

        List<String> residentNameList = userUtil.stringToList(residentName);

        int totalResidentSearch = customerDAO.countRegistResidentForManage(buildingId, apartmentNumber, residentNameList);
        int totalPage = (int) Math.ceil((double) totalResidentSearch / residentPerPage);

        if (currentPage > totalPage) {
            currentPage = 1;
        }

        List<Customer> customerList = customerDAO.getRegistResidentForManage(currentPage, residentPerPage, buildingId, apartmentNumber, residentNameList);

        Locale locale = Locale.US;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        out.println("<div class=\"table-responsive\">\n"
                + "                                <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">\n"
                + "                                    <thead style=\"background-color: #4e73df; color: white\">\n"
                + "                                        <tr>\n"
                + "                                            <th>#</th>\n"
                + "                                            <th>Họ Tên</th>\n"
                + "                                            <th>Ngày sinh</th>\n"
                + "                                            <th>Email</th>\n"
                + "                                            <th>Số điện thoại</th>\n"
                + "                                            <th>Căn hộ</th>\n"
                + "                                            <th>Loại cư dân</th>\n"
                + "                                            <th>Trạng thái</th>\n"
                + "                                            <th>Hành động</th>\n"
                + "                                        </tr>\n"
                + "                                    </thead>\n"
                + "                                    <tbody>\n");
        if (customerList != null && !customerList.isEmpty()) {
            int count = (currentPage - 1) * residentPerPage;
            for (Customer resident : customerList) {
                count++;
                out.println("                                            <tr>\n"
                        + "                                                <td>" + count + "</td>\n"
                        + "                                                <td>" + resident.getName() + "</td>\n"
                        + "                                                <td>" + (resident.getDob() != null ? dateFormat.format(resident.getDob()) : "") + "</td>\n"
                        + "                                                <td>" + (resident.getEmail() != null ? resident.getEmail() : "") + "</td>\n"
                        + "                                                <td>" + resident.getPhoneNumber() + "</td>\n"
                        + "                                                <td>" + (resident.getApartmentNumber() != null ? resident.getApartmentNumber() : "") + "</td>\n"
                        + "                                                <td class=\"" + (resident.getIsOwner() == 1 ? "text-primary font-weight-bold" : "") + "\">" + (resident.getIsOwner() == 1 ? "Chủ căn hộ" : "Người ở") + "</td>\n"
                        + "                                                <td>Đang chờ duyệt</td>\n"
                        + "                                                <td>\n"
                        + "                                                    <form id=\"acceptForm-" + resident.getCustomerID() + "\" action=\"residentmanage\" method=\"post\">\n"
                        + "                                                            <input type=\"hidden\" name=\"customerId\" value=\"" + resident.getCustomerID() + "\">\n"
                        + "                                                            <input type=\"hidden\" name=\"action\" value=\"accept\">\n"
                        + "                                                        </form>\n"
                        + "                                                        <form id=\"declineForm-" + resident.getCustomerID() + "\" action=\"residentmanage\" method=\"post\">\n"
                        + "                                                            <input type=\"hidden\" name=\"customerId\" value=\"" + resident.getCustomerID() + "\">\n"
                        + "                                                            <input type=\"hidden\" name=\"action\" value=\"decline\">\n"
                        + "                                                        </form>\n"
                        + "                                                        <input class=\"btn btn-success\" type=\"submit\" value=\"Duyệt\" onclick=\"handleAccept(" + resident.getCustomerID() + ")\">\n"
                        + "                                                        <input class=\"btn btn-danger\" type=\"submit\" value=\"Xóa\" onclick=\"handleDecline(" + resident.getCustomerID() + ")\">"
                        + "                                                </td>\n"
                        + "                                            </tr>\n");
            }
        } else {
            out.println("<tr><td colspan=\"9\">Không có dữ liệu cư dân</td></tr>");
        }
        out.println("                                    </table>\n"
                + "                                    <div class=\"d-flex flex-row-reverse\">\n"
                + "                                        <nav aria-label=\"Page navigation\">\n"
                + "                                            <ul class=\"pagination justify-content-start\">\n");
        if (currentPage > 1) {
            out.println("<li class=\"page-item\">\n"
                    + "   <button class=\"page-link\" value=\"" + (currentPage - 1) + "\" onclick=\"handleSearch(this.value)\">Previous</button>\n"
                    + "   </li>");

        }

        for (int i = 1; i <= totalPage; i++) {
            if (i == currentPage) {
                out.println("<li class=\"page-item active\">\n"
                        + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"handleSearch(this.value)\">" + i + "</button>\n"
                        + " </li>");
            } else {
                out.println("<li class=\"page-item\">\n"
                        + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"handleSearch(this.value)\">" + i + "</button>\n"
                        + " </li>");
            }

        }

        if (currentPage < totalPage) {
            out.println("<li class=\"page-item\">\n"
                    + "   <button class=\"page-link\" value=\"" + (currentPage + 1) + "\" onclick=\"handleSearch(this.value)\">Previous</button>\n"
                    + "   </li>");

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
