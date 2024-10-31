/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAO.ApartmentDAO;
import DAO.CustomerDAO;
import DAO.InvoiceDAO;
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import model.Apartment;
import model.Customer;
import model.Invoice;
import model.ServiceContract;
import utils.UserHomeUtil;

/**
 *
 * @author ADMIN
 */
public class UserHomeTableAjax extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 5;

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
        UserHomeUtil userHomeUtil = new UserHomeUtil();

        String month_raw = request.getParameter("selectMonth");
        String year_raw = request.getParameter("selectYear");
        String apartmentID_raw = request.getParameter("apartmentID");
        String searchTerm = request.getParameter("searchTerm");
        String currentPage_raw = request.getParameter("currentPage");

        // get session resident account
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

//        CustomerDAO customerDAO = new CustomerDAO();
//        customer = customerDAO.getCustomer(1);
        // get current date for user first access
        int month = LocalDate.now().getMonthValue() > 0 ? LocalDate.now().getMonthValue() - 1 : 12;
        int year = LocalDate.now().getMonthValue() > 0 ? LocalDate.now().getYear() : LocalDate.now().getYear() - 1;
        int apartmentID = 0;

        int currentPage = 1;
        if (currentPage_raw != null) {
            try {
                currentPage = Integer.parseInt(currentPage_raw);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }

        if (year_raw != null) {
            try {
                year = Integer.parseInt(year_raw);
            } catch (NumberFormatException e) {
            }
        }

        if (apartmentID_raw != null) {
            try {
                apartmentID = Integer.parseInt(apartmentID_raw);
            } catch (NumberFormatException e) {
            }
        }

        // create dao
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        ApartmentDAO apartmentDAO = new ApartmentDAO();

        // get apartment user is living
        Apartment apartment = apartmentDAO.getApartmentByCustomerId(customer.getCustomerID());

        // if user is owner
        if (customer.getIsOwner() == 1) {
            List<Apartment> apartmentList = apartmentDAO.getAllApartmentByOwner(customer.getCustomerID());
            if (apartmentID != 0) {
                for (Apartment apartment1 : apartmentList) {
                    if (apartment1.getApartmentID() == apartmentID) {
                        apartment = apartment1;
                    }
                }
            } // if owner not living
            else if (apartment == null) {
                apartment = apartmentList.get(0);
            }
            request.setAttribute("apartmentList", apartmentList);
        }

        List<Date> dList = invoiceDAO.getAllApartmentInvoiceDate(apartment.getApartmentID());
        LinkedHashSet<Date> listOfMonth = userHomeUtil.listOfMonth(dList, year);

        if (month_raw != null) {
            try {
                boolean contain = false;
                month = Integer.parseInt(month_raw);

                // handle month get by request exist in database
                for (Date date : listOfMonth) {
                    if (date.toLocalDate().getMonthValue() == month) {
                        contain = true;
                    }
                }
                if (!contain) {
                    if (!listOfMonth.isEmpty()) {
                        Date firstElement = listOfMonth.iterator().next();
                        month = firstElement.toLocalDate().getMonthValue();
                    }
                }
            } catch (NumberFormatException e) {
            }
        }
        List<String> searchTermList = new ArrayList<>();

        if (searchTerm != null && !searchTerm.trim().isEmpty() && !searchTerm.trim().equalsIgnoreCase("")) {
            String[] searchTermArray = searchTerm.trim().split("\\s+");
            searchTermList = Arrays.asList(searchTermArray);
            out.print(searchTermList.get(0));
        }

        int totalServiceRows = invoiceDAO.countInvoiceByApartmentIDandMonth(apartment.getApartmentID(), month, year, searchTermList);
        int totalPages = (int) Math.ceil((double) totalServiceRows / RECORDS_PER_PAGE);

        if (currentPage > totalPages) {
            currentPage = 1;
        }

        Invoice invoiceCurrent = invoiceDAO.getInvoiceByApartmentIDandMonth(apartment.getApartmentID(), month, year, currentPage, RECORDS_PER_PAGE, searchTermList);
        List<ServiceContract> serviceList = invoiceCurrent.getServiceContractList();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Locale locale = Locale.US;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat format = new DecimalFormat("#,###", symbols);
        DecimalFormat format2 = new DecimalFormat("#.#");

        out.println("                                            <div class=\"table-responsive\">\n"
                + "                                                <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">\n"
                + "                                                    <thead style=\"background-color: #4e73df; color: white\">\n"
                + "                                                        <tr>\n"
                + "                                                            <th>#</th>\n"
                + "                                                            <th>Tên</th>\n"
                + "                                                            <th>Loại dịch vụ</th>\n"
                + "                                                            <th>Ngày đăng kí</th>\n"
                + "                                                            <th>Ngày kết thúc</th>\n"
                + "                                                            <th>Đơn giá</th>\n"
                + "                                                            <th>Tỉ lệ</th>\n"
                + "                                                        </tr>\n"
                + "                                                    </thead>\n"
                + "                                                    <tbody>\n");
        int countServiceTable = (currentPage - 1) * RECORDS_PER_PAGE;
        if (serviceList != null) {
            for (ServiceContract serviceContract : serviceList) {
                out.println("                                                            <tr>\n"
                        + "                                                                <td>" + (++countServiceTable) + "</td>\n"
                        + "                                                                <td>" + serviceContract.getService().getName() + "</td>\n"
                        + "                                                                <td>" + serviceContract.getService().getType() + "</td>\n"
                        + "                                                                <td>" + dateFormat.format(serviceContract.getStartDate()) + "</td>\n"
                        + "                                                                <td>" + dateFormat.format(serviceContract.getEndDate()) + "</td>\n"
                        + "                                                                <td>" + format.format(serviceContract.getAmount()) + " VNĐ</td>\n"
//                        + "                                                                <td>" + format2.format((serviceContract.getAmount() / invoiceCurrent.getAmount()) * 100) + "%</td>\n"
                        + "                                                                </tr>\n");
            }
        } else {
            out.println("                                                            <tr>\n"
                    + "                                                                <td colspan=\"7\"><h4>Không tìm thấy dịch vụ có tên hoặc loại tương ứng</h4></td>\n"
                    + "                                                              </tr>\n");
        }
        out.println("                                                    </tbody>\n"
                + "                                                </table>\n");
        out.println("<div class=\"d-flex flex-row-reverse\">\n"
                + "                                                <nav aria-label=\"Page navigation\">\n"
                + "                                                    <ul class=\"pagination justify-content-start\">");
        if (currentPage > 1) {
            out.println("<li class=\"page-item\">\n"
                    + "   <button class=\"page-link\" value=\"" + (currentPage - 1) + "\" onclick=\"handleSearch(this.value)\">Previous</button>\n"
                    + "   </li>");

        }

        for (int i = 1; i <= totalPages; i++) {
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

        if (currentPage < totalPages) {
            out.println("<li class=\"page-item\">\n"
                    + "   <button class=\"page-link\" value=\"" + (currentPage + 1) + "\" onclick=\"handleSearch(this.value)\">Previous</button>\n"
                    + "   </li>");

        }

        out.println("\n"
                + "                                                    </ul>\n"
                + "                                                </nav>\n"
                + "                                    </div>");
        out.println("                                            </div>"
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
