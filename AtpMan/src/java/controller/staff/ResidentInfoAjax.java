/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.CustomerDAO;
import DAO.LivingDAO;
import DAO.OwnershipDAO;
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

/**
 *
 * @author ADMIN
 */
public class ResidentInfoAjax extends HttpServlet {

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
        OwnershipDAO ownershipDAO = new OwnershipDAO();

        String residentId_raw = request.getParameter("residentId");

        int residentId = 0;
        try {
            residentId = Integer.parseInt(residentId_raw);
        } catch (Exception e) {
        } finally {
        }

//        Locale locale = Locale.US;
//        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
//        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (residentId != 0) {
            Customer customer = customerDAO.getCustomer(residentId);
            List<String> apartmentHistoryList = livingDAO.getAllResidentApartmentLiving(residentId);
            List<String> apartmentHistoryOwnList = ownershipDAO.getAllResidentApartmentOwner(residentId);
            if (customer != null) {
                out.println("<div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">\n"
                        + "                                <h5 class=\"m-0 font-weight-bold text-primary text-gray-800 col-md-9\">Thông tin cư dân: " + customer.getName() + "</h5>\n"
                        + "                            </div>\n"
                        + "                            <div id=\"activeResidentTable\" class=\"card-body row\">\n"
                        + "                                <div class=\"col-md-3\">\n"
                        + "                                    <span class=\"font-weight-bold text-gray-900\">Ngày sinh: </span><span class=\"font-weight-bold\">" + (customer.getDob() != null ? dateFormat.format(customer.getDob()) : "không khả dụng") + "</span>\n"
                        + "                                </div>\n"
                        + "                                <div class=\"col-md-3\">\n"
                        + "                                    <span class=\"font-weight-bold text-gray-900\">Email: </span><span class=\"font-weight-bold\">" + (customer.getEmail() != null ? customer.getEmail() : "") + "</span>\n"
                        + "                                </div>\n"
                        + "                                <div class=\"col-md-4\">\n"
                        + "                                    <span class=\"font-weight-bold text-gray-900\">Sđt: </span><span class=\"font-weight-bold\">" + (customer.getPhoneNumber() != null ? customer.getPhoneNumber() : "") + "</span>\n"
                        + "                                </div>\n"
                        + "                                <div class=\"col-md-3\">\n"
                        + "                                    <span class=\"font-weight-bold text-gray-900\">Loại cư dân: </span><span class=\"font-weight-bold\">" + (customer.getIsOwner() == 1 ? "Chủ sở hữu" : "Người ở") + "</span>\n"
                        + "                                </div>\n"
                        + "                                <div class=\"col-md-5 row\">\n"
                        + "                                    <span class=\"font-weight-bold text-gray-900 col-md-3\">Lịch sử cư trú: </span>\n"
                        + "                                    <ul class=\"col-md-9\" style=\"list-style-type: none;\">\n");
                for (String string : apartmentHistoryList) {
                    out.println("<li><span class=\"font-weight-bold\">" + string + "</span></li>");
                }
                out.println("                                    </ul>\n"
                        + "                                </div>"
                        + "                                <div class=\"col-md-6 row\">\n"
                        + "                                    <span class=\"font-weight-bold text-gray-900 col-md-9\">Lịch sử sở hữu: </span>\n"
                        + "                                    <ul class=\"col-md-9\" style=\"list-style-type: none;\">\n");
                for (String ownership : apartmentHistoryOwnList) {
                    out.println("<li><span class=\"font-weight-bold\">" + ownership + "</span></li>");
                }
                out.println("                                    </ul>\n" + "                                </div>\n"
                        + "                            </div> "
                );
            } else {
                out.println("<div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">\n"
                        + "                                <h5 class=\"m-0 font-weight-bold text-primary text-gray-800 col-md-9\">Thông tin cư dân: </h5>\n"
                        + "                            </div>\n"
                        + "                            <div id=\"activeResidentTable\" class=\"card-body row\">\n"
                        + "                                <div class=\"col-md-12\">\n"
                        + "                                    <span class=\"font-weight-bold text-gray-900\">Có lỗi xảy ra</span>\n"
                        + "                                </div>\n"
                        + "                            </div> ");
            }

        } else {
            out.println("<div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">\n"
                    + "                                <h5 class=\"m-0 font-weight-bold text-primary text-gray-800 col-md-9\">Thông tin cư dân: </h5>\n"
                    + "                            </div>\n"
                    + "                            <div id=\"activeResidentTable\" class=\"card-body row\">\n"
                    + "                                <div class=\"col-md-12\">\n"
                    + "                                    <span class=\"font-weight-bold text-gray-900\">Có lỗi xảy ra</span>\n"
                    + "                                </div>\n"
                    + "                            </div> ");
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
