/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

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
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import model.Apartment;
import model.Building;
import model.Customer;
import model.ServiceContract;
import DAO.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import model.Living;

/**
 *
 * @author ADMIN
 */
public class UserApartmentInfoAjax extends HttpServlet {

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

        //get parameter
        String userSearchTerm = request.getParameter("userSearchTerm");
        String serviceSearchTerm = request.getParameter("serviceSearchTerm");
        String currentResidentPage_raw = request.getParameter("currentResidentPage");
        String currentServicePage_raw = request.getParameter("currentServicePage");
        String residentPerPage_raw = request.getParameter("residentPerPage");
        String servicePerPage_raw = request.getParameter("servicePerPage");

        String apartmentID_raw = request.getParameter("apartmentID");
        String buildingID_raw = request.getParameter("buildingID");

        //handle param
        List<String> userSearchTermList = null;
        List<String> serviceSearchTermList = null;

        if (userSearchTerm != null && !userSearchTerm.isBlank()) {
            String[] searchArr = userSearchTerm.trim().split("\\s+");
            userSearchTermList = new ArrayList<>(Arrays.asList(searchArr));
            //free memory
            searchArr = null;
        }

        if (serviceSearchTerm != null && !serviceSearchTerm.isBlank()) {
            String[] searchArr = serviceSearchTerm.trim().split("\\s+");
            serviceSearchTermList = new ArrayList<>(Arrays.asList(searchArr));
            //free memory
            searchArr = null;
        }

        // handle current page
        int currentResidentPage = 1;
        int currentServicePage = 1;

        try {
            currentResidentPage = Integer.parseInt(currentResidentPage_raw);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        try {
            currentServicePage = Integer.parseInt(currentServicePage_raw);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        // handle number of rows per page
        int residentPerPage = 5;
        int servicePerPage = 5;

        try {
            residentPerPage = Integer.parseInt(residentPerPage_raw);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        try {
            servicePerPage = Integer.parseInt(servicePerPage_raw);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        ApartmentDAO apartmentDAO = new ApartmentDAO();
        BuildingDAO buildingDAO = new BuildingDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        LivingDAO livingDAO = new LivingDAO();
        ServiceContractDAO serviceContractDAO = new ServiceContractDAO();

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
//        Customer customer = customerDAO.getCustomer(1);

        int apartmentID = 0;
        if (apartmentID_raw != null) {
            try {
                apartmentID = Integer.parseInt(apartmentID_raw);
            } catch (NumberFormatException e) {
            }
        }

        int buildingID = 0;
        if (buildingID_raw != null) {
            try {
                buildingID = Integer.parseInt(buildingID_raw);
            } catch (NumberFormatException e) {
            }
        }

        // get apartment that user live in, if user is tenant => always get apartment info through this, no need apartmentID
        // if user is owner can null if not live in
        Apartment apartment = apartmentDAO.getApartmentByID(apartmentID);
        List<Building> buildingList = null;
        Building building = buildingDAO.getBuildingByApartmentID(apartment.getApartmentID());
        Living living = livingDAO.getLivingInfoByUserId(customer.getCustomerID());

        // checking apartmentID and buildingID is null or not a number, get the living apartment or first apartment
        // only if customer is owner need to change apartment or building to check
        // if customer is tenant, no need to check the apartmentID and buildingID
        if (customer.getIsOwner() == 1) {
            buildingList = buildingDAO.getAllBuildingByOwnership(customer.getCustomerID());
            for (Building building1 : buildingList) {
                if (building1.getBuildingID() == buildingID) {
                    building = building1;
                }
            }
            boolean check = false;
            for (Apartment apartment1 : building.getApartmentList()) {
                if (apartment1.getApartmentID() == apartmentID) {
                    apartment = apartment1;
                    check = true;
                }
            }
            if (!check) {
                apartment = building.getApartmentList().get(0);
            }
        }

        List<Customer> customerList = customerDAO.getLivingInApartment(apartment.getApartmentID(), currentResidentPage, residentPerPage, null);
        LocalDate date = LocalDate.now();
        List<ServiceContract> serviceContractList = serviceContractDAO.getCurrentServiceContract(apartment.getApartmentID(), Date.valueOf(date), currentServicePage, servicePerPage, null);

        int totalResident = customerDAO.countLivingInApartment(apartmentID, userSearchTermList);
        int totalService = serviceContractDAO.countCurrentServiceContract(apartment.getApartmentID(), Date.valueOf(date), serviceSearchTermList);

        int totalResidentPage = (int) Math.ceil((double) totalResident / residentPerPage);
        int totalServicePage = (int) Math.ceil((double) totalService / servicePerPage);

        BigDecimal totalAmount = serviceContractDAO.totalAmountCurrentServiceContract(apartment.getApartmentID(), Date.valueOf(date), null);
        double totalA = 0;
        
        if(totalAmount != null) totalA = totalAmount.doubleValue();
        
        Locale locale = Locale.US;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Format số
        String formattedPrice = decimalFormat.format(apartment.getPrice());
        out.println("<div class=\"d-sm-flex align-items-center justify-content-between mb-4\">\n"
                + "                                <h1 class=\"h1 mb-0 text-gray-800\"><b>Thông tin căn hộ</b></h1>\n"
                + "                            </div>\n"
                + "                            <div class=\"col-xl-12 col-md-12 mb-12\">\n"
                + "                                <div class=\"row\">\n"
                + "                                    <div class=\"card border-left-success shadow h-100 py-2\">\n"
                + "                                        <div class=\"btn-group dropend\">\n"
                + "                                            <div class=\"card-body\">\n"
                + "                                                <div class=\"row no-gutters align-items-center\">\n"
                + "                                                    <div class=\"col mr-2\">\n"
                + "                                                        <!--building information-->\n"
                + "                                                        <div class=\"row\">\n"
                + "                                                            <div class=\"h2 font-weight-bold text-success text-uppercase mb-1 col-xxl-2 col-xl-3 col-lg-4 col-md-5\">\n"
                + "                                                                Tòa Nhà:\n"
                + "                                                            </div>\n"
                + "                                                            <div class=\"col-xxl-3 col-xl-4 col-lg-6 col-md-6\">\n"
                + "                                                                <select id=\"buildingID\" name=\"buildingID\" class=\"form-select h2 font-weight-bold text-success text-uppercase mb-1\" aria-label=\"Default select example\" onchange=\"changeSelect()\">\n");
        for (Building building1 : buildingList) {
            out.println("                                                            <option " + (building1.getBuildingID() == building.getBuildingID() ? "selected" : "") + " value=\"" + building1.getBuildingID() + "\">" + building1.getName() + "</option>\n");
        }
        out.println("                                                                </select>\n"
                + "                                                            </div>\n"
                + "                                                        </div>\n"
                + "                                                        <div class=\"h5 mb-0 font-weight-bold text-gray-900\">\n"
                + "                                                            Số Tầng:  &nbsp; <span class=\"h5 mb-0 font-weight-bold text-gray-700\">" + building.getNumFloor() + " tầng</span>\n"
                + "                                                        </div>\n"
                + "                                                        <div class=\"h5 mb-0 font-weight-bold text-gray-900\">\n"
                + "                                                            Số Căn hộ: &nbsp; <span class=\"h5 mb-0 font-weight-bold text-gray-700\">" + building.getNumApartment() + " căn hộ</span> \n"
                + "                                                        </div>\n"
                + "                                                        <div class=\"h5 mb-0 font-weight-bold text-gray-900\">\n"
                + "                                                            Địa chỉ: &nbsp; <span class=\"h5 mb-0 font-weight-bold text-gray-700\">" + building.getAddress() + "</span>\n"
                + "                                                        </div>\n"
                + "                                                        <br>\n"
                + "                                                        <!--apartment information-->\n"
                + "                                                        <div class=\"row\">\n"
                + "                                                            <div class=\"h3 font-weight-bold text-primary text-uppercase mb-1 col-xxl-2 col-xl-3 col-lg-4 col-md-5\">\n"
                + "                                                                Căn hộ:\n"
                + "                                                            </div>\n"
                + "                                                            <div class=\"col-xxl-3 col-xl-4 col-lg-6 col-md-6\">\n"
                + "                                                                <select id=\"apartmentID\" name=\"apartmentID\" class=\"form-select h2 font-weight-bold text-primary text-uppercase mb-1\" aria-label=\"Default select example\" onchange=\"changeSelect()\">\n");
        if (customer.getIsOwner() == 1) {
            for (Apartment apartment1 : building.getApartmentList()) {
                out.println("<option " + (apartment.getApartmentID() == apartment1.getApartmentID() ? "selected" : "") + " value=\"" + apartment1.getApartmentID() + "\">" + apartment1.getApartmentNumber() + "</option>\n");
            }
        } else {
            out.println("<option selected value=\"" + apartment.getApartmentID() + "\">" + apartment.getApartmentNumber() + "</option>\n");
        }
        out.println("                                                                </select>\n"
                + "                                                            </div>\n"
                + "                                                        </div>\n"
                + "                                                        <div class=\"h5 mb-0 font-weight-bold text-gray-900 card1\">\n"
                + "                                                            Loại căn hộ: &nbsp; <span class=\"h5 mb-0 font-weight-bold text-gray-700\">" + apartment.getApartmentType() + "</span>\n"
                + "                                                        </div>\n"
                + "                                                        <div class=\"h5 mb-0 font-weight-bold text-gray-900 card1\">\n"
                + "                                                            Tầng: &nbsp; <span class=\"h5 mb-0 font-weight-bold text-gray-700\">" + apartment.getFloor() + "</span>\n"
                + "                                                        </div>\n"
                + "                                                        <div class=\"h5 mb-0 font-weight-bold text-gray-900 card1\">\n"
                + "                                                            Tổng diện tích: &nbsp; <span class=\"h5 mb-0 font-weight-bold text-gray-700\">" + apartment.getArea() + " m2</span>\n"
                + "                                                        </div>\n"
                + "                                                        <div class=\"h5 mb-0 font-weight-bold text-gray-900 card1\">\n"
                + "                                                            Giá trị căn hộ: &nbsp; <span class=\"h5 mb-0 font-weight-bold text-gray-700\">" + formattedPrice + " VNĐ</span>\n"
                + "                                                            </div>\n"
                + "                                                            <div class=\"h5 mb-0 font-weight-bold text-gray-900 card1\">\n"
                + "                                                                Tổng số người ở: &nbsp; \n");
        if (!customerList.isEmpty()) {
            out.println("<span class=\"h5 mb-0 font-weight-bold text-gray-700\">" + customerList.size() + " người</span>");
        } else {
            out.println("<span class=\"h5 mb-0 font-weight-bold text-gray-700\"> chưa có người ở </span>");
        }
        out.println("                                                       </div>\n"
                + "                                                    </div>\n"
                + "                                                    <div class=\"col-auto\">\n"
                + "                                                        <i class=\"fas fa-building fa-10x text-gray-300\"></i>\n"
                + "                                                    </div> \n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "                            </div> \n"
                + "                            <br>\n"
                + "                            <div class=\"d-sm-flex align-items-center justify-content-between mb-4\">\n"
                + "                                <h1 class=\"h1 mb-0 text-gray-800\"><b>Thông tin cư dân</b></h1>\n"
                + "                            </div>\n"
                + "                            <div class=\"card shadow mb-4\">\n"
                + "                                    <div class=\"card-header py-3 row\">\n"
                + "                                        <h5 class=\"m-0 font-weight-bold text-primary col-md-5\">Danh sách cư dân đăng ký trong căn hộ</h5>\n"
                + "                                        <div class=\"col-md-3\">\n"
                + "                                            <select id=\"residentPerPage\" name=\"residentPerPage\" class=\"form-select h2 font-weight-bold text-primary text-uppercase mb-1\" aria-label=\"Default select example\" onchange=\"handleResidentTable($('#residentTable .pagination .page-item.active button.page-link').val())\">\n"
                + "                                                <option value=\"5\">Số lượng hiển thị: 5</option>\n"
                + "                                                <option value=\"10\">Số lượng hiển thị: 10</option>\n"
                + "                                            </select>\n"
                + "                                        </div>"
                + "                                        <div class=\"col-md-4\">\n"
                + "                                            <div class=\"input-group rounded \">\n"
                + "                                                <!--reset the current page to 1 cause of search can reduce the number of page-->\n"
                + "                                                <input id=\"searchResident\" name=\"searchResident\" type=\"text\" value=\"\" oninput=\"handleResidentTable($('#residentTable .pagination .page-item.active button.page-link').val())\" class=\"form-control\" placeholder=\"Search\" aria-label=\"Search\" aria-describedby=\"search-addon\" />\n"
                + "                                                <div class=\"input-group-append\">\n"
                + "                                                    <span class=\"input-group-text btn-primary border-0\" id=\"search-addon\">\n"
                + "                                                        <i class=\"fas fa-search\"></i>\n"
                + "                                                    </span>\n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                    <div id=\"residentTable\" class=\"card-body\">\n"
                + "                                <table class=\"table table-striped table-hover table-bordered\">\n"
                + "                                    <thead style=\"background-color: #4e73df; color: white\">\n"
                + "                                        <tr>\n"
                + "                                            <th>#</th>\n"
                + "                                            <th>Tên</th>\n"
                + "                                            <th>Ngày sinh</th>\n"
                + "                                            <th>Email</th>\n"
                + "                                            <th>Số điện thoại</th>\n"
                + "                                            <th>Ngày vào ở</th>\n"
                + "                                        </tr>\n"
                + "                                    </thead>\n"
                + "                                    <tbody>\n");
        int count = 0;
        if (customerList.isEmpty()) {
            out.println("<tr><td colspan=\"6\">Không có dữ liệu người ở</td></tr>");
        } else {
            for (Customer resident : customerList) {
                count++;
                out.print("                                            <tr>\n"
                        + "                                                <td>" + count + "</td>\n"
                        + "                                                <td>" + resident.getName() + "</td>\n"
                        + "                                                <td>" + dateFormat.format(resident.getDob()) + "</td>\n"
                        + "                                                <td>" + ((resident.getEmail() != null) ? resident.getEmail() : "") + "</td>\n"
                        + "                                                <td>" + resident.getPhoneNumber() + "</td>\n"
                        + "                                                <td>" + dateFormat.format(living.getStartDate()) + "</td>\n"
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
                    + "   <button class=\"page-link\" value=\"" + (currentResidentPage - 1) + "\" onclick=\"handleResidentTable(this.value)\">Previous</button>\n"
                    + "   </li>");
        }
        for (int i = 1; i <= totalResidentPage; i++) {
            if (i == currentResidentPage) {
                out.println("<li class=\"page-item active\">\n"
                        + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"handleResidentTable(this.value)\">" + i + "</button>\n"
                        + " </li>");
            } else {
                out.println("<li class=\"page-item\">\n"
                        + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"handleResidentTable(this.value)\">" + i + "</button>\n"
                        + " </li>");
            }

        }
        if (currentResidentPage < totalResidentPage) {
            out.println("<li class=\"page-item\">\n"
                    + "   <button class=\"page-link\" value=\"" + (currentResidentPage + 1) + "\" onclick=\"handleResidentTable(this.value)\">Previous</button>\n"
                    + "   </li>");

        }

        out.println("\n"
                + "                                                    </ul>\n"
                + "                                                </nav>\n"
                + "                                    </div>\n"
                + "                                    </div>\n"
                + "                                    </div>\n");
        out.println("                            <div class=\"d-sm-flex align-items-center justify-content-between mb-4\">\n"
                + "                                <h1 class=\"h1 mb-0 text-gray-800\"><b>Thông tin dịch vụ</b></h1>\n"
                + "                            </div>\n"
                + "                            <div class=\"card shadow mb-4\">\n"
                + "                                <div class=\"card-header py-3 row\">\n"
                + "                                    <h5 class=\"m-0 font-weight-bold text-primary col-md-5\">Danh sách dịch vụ đã đăng ký trong căn hộ</h5>\n"
                + "                                    <div class=\"col-md-3\">\n"
                + "                                        <select id=\"servicePerPage\" name=\"servicePerPage\" class=\"form-select font-weight-bold text-primary text-uppercase\" aria-label=\"Default select example\" onchange=\"handleServiceTable($('#serviceTable .pagination .page-item.active button.page-link').val())\">\n"
                + "                                            <option value=\"5\">Số lượng hiển thị: 5</option>\n"
                + "                                            <option value=\"10\">Số lượng hiển thị: 10</option>\n"
                + "                                        </select>\n"
                + "                                    </div>\n"
                + "                                    <div class=\"col-md-4\">\n"
                + "                                        <div class=\"input-group rounded \">\n"
                + "                                            <!--reset the current page to 1 cause of search can reduce the number of page-->\n"
                + "                                            <input id=\"searchService\" name=\"searchService\" type=\"text\" value=\"\" oninput=\"handleServiceTable($('#serviceTable .pagination .page-item.active button.page-link').val())\" class=\"form-control\" placeholder=\"Search\" aria-label=\"Search\" aria-describedby=\"search-addon\" />\n"
                + "                                            <div class=\"input-group-append\">\n"
                + "                                                <span class=\"input-group-text btn-primary border-0\" id=\"search-addon\">\n"
                + "                                                    <i class=\"fas fa-search\"></i>\n"
                + "                                                </span>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "                                <div id=\"serviceTable\" class=\"card-body\">\n"
                + "                                    <div>\n"
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
        count = 0;
        if (serviceContractList.isEmpty()) {
            out.println("<tr><td colspan=\"6\">Không có dữ liệu dịch vụ</td></tr>");
        } else {
            for (ServiceContract serviceContract : serviceContractList) {
                count++;
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
                + "                                            <tfoot style=\"background-color: #4e73df; color: white\"  class=\"h5\">\n"
                + "                                                <tr>\n"
                + "                                                    <th colspan=\"6\">Tổng tiền dịch vụ: " + decimalFormat.format(totalA) + " VNĐ</th>\n"
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
                + "                                    </div>\n"
                + "                                </div>   \n"
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
