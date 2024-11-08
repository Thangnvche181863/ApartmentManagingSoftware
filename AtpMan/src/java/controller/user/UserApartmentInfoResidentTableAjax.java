/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAO.ApartmentDAO;
import DAO.BuildingDAO;
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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import model.Customer;
import model.Living;

/**
 *
 * @author ADMIN
 */
public class UserApartmentInfoResidentTableAjax extends HttpServlet {

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

        CustomerDAO customerDAO = new CustomerDAO();
        LivingDAO livingDAO = new LivingDAO();

        //get parameter
        String userSearchTerm = request.getParameter("userSearchTerm");
        String currentResidentPage_raw = request.getParameter("currentResidentPage");
        String residentPerPage_raw = request.getParameter("residentPerPage");
        String apartmentID_raw = request.getParameter("apartmentID");

        //handle param
        List<String> userSearchTermList = null;
        if (userSearchTerm != null && !userSearchTerm.isBlank()) {
            String[] searchArr = userSearchTerm.trim().split("\\s+");
            userSearchTermList = new ArrayList<>(Arrays.asList(searchArr));
            //free memory
            searchArr = null;
        }

        // handle current page
        int currentResidentPage = 1;

        try {
            currentResidentPage = Integer.parseInt(currentResidentPage_raw);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        // handle number of rows per page
        int residentPerPage = 5;
        try {
            residentPerPage = Integer.parseInt(residentPerPage_raw);
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

        int totalResident = customerDAO.countLivingInApartment(apartmentID, userSearchTermList);
        int totalResidentPage = (int) Math.ceil((double) totalResident / residentPerPage);
        if(currentResidentPage > totalResidentPage){
            currentResidentPage = 1;
        }
        
        List<Customer> customerList = customerDAO.getLivingInApartment(apartmentID, currentResidentPage, residentPerPage, userSearchTermList);
        
        Locale locale = Locale.US;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        out.println("                                <table class=\"table table-striped table-hover table-bordered\">\n"
                + "                                    <thead style=\"background-color: #4e73df; color: white\">\n"
                + "                                        <tr>\n"
                + "                                            <th>#</th>\n"
                + "                                            <th>Tên</th>\n"
                + "                                            <th>Ngày sinh</th>\n"
                + "                                            <th>Email</th>\n"
                + "                                            <th>Số điện thoại</th>\n"
                + "                                            <th>Ngày vào ở</th>\n"
                + "                                            <th>Phân loại</th>\n"
                + "                                        </tr>\n"
                + "                                    </thead>\n"
                + "                                    <tbody>\n");
        int count = 0;
        if (customerList.isEmpty()) {
            out.println("<tr><td colspan=\"7\">Không có dữ liệu người ở</td></tr>");
        } else {
            for (Customer resident : customerList) {
                count++;
                out.print("                                            <tr>\n"
                        + "                                                <td>" + count + "</td>\n"
                        + "                                                <td>" + resident.getName() + "</td>\n"
                        + "                                                <td>" + dateFormat.format(resident.getDob()) + "</td>\n"
                        + "                                                <td>" + resident.getEmail() + "</td>\n"
                        + "                                                <td>" + resident.getPhoneNumber() + "</td>\n"
                        + "                                                <td>" + dateFormat.format(resident.getLivingDate()) + "</td>\n"
                        + "                                                <td class='"+(resident.getIsOwner() == 1 ? "text-primary font-weight-bold" : "")+"' >" + (resident.getIsOwner() == 1 ? "Chủ sở hữu" : "Người ở") + "</td>\n"
                        + "                                            </tr>\n");
            }
        }
        out.println("                                   </tbody>\n"
                + "                                </table>\n");
        out.println("<div class=\"d-flex flex-row-reverse\">\n"
                + "                                                <nav aria-label=\"Page navigation\">\n"
                + "                                                    <ul class=\"pagination justify-content-start\">");
        if (currentResidentPage > 1) {
            out.println("<li class=\"page-item\">\n"
                    + "   <button class=\"page-link\" value=\"" + (currentResidentPage - 1) + "\" onclick=\"handleSearch(this.value)\">Previous</button>\n"
                    + "   </li>");

        }

        for (int i = 1; i <= totalResidentPage; i++) {
            if (i == currentResidentPage) {
                out.println("<li class=\"page-item active\">\n"
                        + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"handleSearch(this.value)\">" + i + "</button>\n"
                        + " </li>");
            } else {
                out.println("<li class=\"page-item\">\n"
                        + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"handleSearch(this.value)\">" + i + "</button>\n"
                        + " </li>");
            }

        }

        if (currentResidentPage < totalResidentPage) {
            out.println("<li class=\"page-item\">\n"
                    + "   <button class=\"page-link\" value=\"" + (currentResidentPage + 1) + "\" onclick=\"handleSearch(this.value)\">Previous</button>\n"
                    + "   </li>");

        }

        out.println("\n"
                + "                                                    </ul>\n"
                + "                                                </nav>\n"
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
