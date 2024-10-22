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
import model.*;
import DAO.*;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserApartmentInfoServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserApartmentInfoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserApartmentInfoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
//        processRequest(request, response);
        ApartmentDAO apartmentDAO = new ApartmentDAO();
        BuildingDAO buildingDAO = new BuildingDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        ServiceContractDAO serviceContractDAO = new ServiceContractDAO();
        
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
                    if(building1.getBuildingID() == buildingID){
                        building = building1;
                    }
                }
                apartment = building.getApartmentList().get(0);
            }
        }
        List<Customer> customerList = customerDAO.getLivingInApartment(apartment.getApartmentID());
        LocalDate date = LocalDate.now();
        List<ServiceContract> serviceContractList = serviceContractDAO.getCurrentServiceContract(apartment.getApartmentID(), Date.valueOf(date));
        
        request.setAttribute("building", building);
        request.setAttribute("buildingList", buildingList);
        request.setAttribute("apartment", apartment);
        request.setAttribute("customerList", customerList);
        request.setAttribute("serviceContractList", serviceContractList);
        request.getRequestDispatcher("user/userapartmentinfo.jsp").forward(request, response); 
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
