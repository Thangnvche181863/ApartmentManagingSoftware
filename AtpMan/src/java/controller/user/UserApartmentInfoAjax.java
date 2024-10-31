/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAO.ApartmentDAO;
import DAO.BuildingDAO;
import DAO.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import model.Apartment;
import model.Building;
import model.Customer;

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

        ApartmentDAO apartmentDAO = new ApartmentDAO();
        BuildingDAO buildingDAO = new BuildingDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        String apartmentID_raw = request.getParameter("apartmentID");
        String buildingID_raw = request.getParameter("buildingID");

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

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
        Apartment apartment = apartmentDAO.getApartmentByCustomerId(customer.getCustomerID());
        List<Building> buildingList = null;
        Building building = null;
        if (apartment != null) {
            building = buildingDAO.getBuildingByApartmentID(apartment.getApartmentID());
        }

        // checking apartmentID and buildingID is null or not a number, get the living apartment or first apartment
        // only if customer is owner need to change apartment or building to check
        // if customer is tenant, no need to check the apartmentID and buildingID
        if (customer.getIsOwner() == 1) {
            buildingList = buildingDAO.getAllBuildingByOwnership(customer.getCustomerID());
            if (apartmentID == 0 && buildingID == 0) {
                if (apartment == null) {
                    apartment = buildingList.get(0).getApartmentList().get(0);
                    building = buildingList.get(0);
                } else {
                    for (Building building1 : buildingList) {
                        if (building1.getBuildingID() == apartment.getBuildingID()) {
                            building = building1;
                        }
                    }
                }
            } // 
            else if (apartmentID != 0 && buildingID == 0) {
                for (Building building1 : buildingList) {
                    // get apartment info
                    for (Apartment apartment1 : building1.getApartmentList()) {
                        if (apartment1.getApartmentID() == apartmentID) {
                            apartment = apartment1;
                        }
                    }
                    if (building1.getBuildingID() == apartment.getBuildingID()) {
                        building = building1;
                    }
                }
            } else if (apartmentID != 0 && buildingID != 0) {
                apartment = null;
                for (Building building1 : buildingList) {
                    if (building1.getBuildingID() == buildingID) {
                        building = building1;
                    }
                }
                for (Apartment apartment1 : building.getApartmentList()) {
                    if (apartment1.getApartmentID() == apartmentID) {
                        apartment = apartment1;
                    }
                }
                if (apartment == null) {
                    apartment = building.getApartmentList().get(0);
                }
//                buildingList = buildingDAO.getAllBuildingByOwnership(customer.getCustomerID());
//                apartment = apartmentDAO.getApartmentByID(apartmentID);
//                for (Building building1 : buildingList) {
//                    if (building1.getBuildingID() == apartment.getBuildingID()) {
//                        building = building1;
//                    }
//                }
            } // apartmentID == 0 and buildingID != 0
            else {
                for (Building building1 : buildingList) {
                    if (building1.getBuildingID() == buildingID) {
                        building = building1;
                    }
                }
                apartment = building.getApartmentList().get(0);
            }
        }
        List<Customer> customerList = customerDAO.getLivingInApartment(apartment.getApartmentID());

        Locale locale = Locale.US;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);

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
        for (Apartment apartment1 : building.getApartmentList()) {
            out.println("<option " + (apartment.getApartmentID() == apartment1.getApartmentID() ? "selected" : "") + " value=\"" + apartment1.getApartmentID() + "\">" + apartment1.getApartmentNumber() + "</option>\n");
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
                + "                                                                Tổng số người ở: &nbsp; <span class=\"h5 mb-0 font-weight-bold text-gray-700\">" + customerList.size() + " người</span>\n"
                + "                                                        </div>\n"
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
                + "                            <div class=\"col-xl-12 col-md-12 mb-12\">\n"
                + "                                <table class=\"table table-striped table-hover table-bordered\">\n"
                + "                                    <thead style=\"background-color: #4e73df; color: white\">\n"
                + "                                        <tr>\n"
                + "                                            <th>#</th>\n"
                + "                                            <th>Name</th>\n"
                + "                                            <th>Ngày sinh</th>\n"
                + "                                            <th>Email</th>\n"
                + "                                            <th>Phone</th>\n"
                + "                                        </tr>\n"
                + "                                    </thead>\n"
                + "                                    <tbody>\n");
        int count = 0;
        for (Customer resident : customerList) {
            count++;
            out.print("                                            <tr>\n"
                    + "                                                <td>" + count + "</td>\n"
                    + "                                                <td>" + resident.getName() + "</td>\n"
                    + "                                                <td>" + resident.getDob()+ "</td>\n"
                    + "                                                <td>" + resident.getEmail() + "</td>\n"
                    + "                                                <td>" + resident.getPhoneNumber() + "</td>\n"
                    + "                                            </tr>\n");
        }
        out.println("</tbody>\n"
                + "                                </table>\n"
                + "                            </div>\n"
                + "                            <div class=\"d-sm-flex align-items-center justify-content-between mb-4\">\n"
                + "                                <h1 class=\"h1 mb-0 text-gray-800\"><b>Thông tin hóa đơn</b></h1>\n"
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
