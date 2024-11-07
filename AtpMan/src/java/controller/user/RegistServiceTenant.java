/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAO.ApartmentDAO;
import DAO.BuildingDAO;
import DAO.ServiceContractDAO;
import DAO.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Apartment;
import model.Building;
import model.Customer;
import model.Service;
import model.ServiceContract;

/**
 *
 * @author thang
 */
public class RegistServiceTenant extends HttpServlet {

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
            out.println("<title>Servlet RegistServiceTenant</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistServiceTenant at " + request.getContextPath() + "</h1>");
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

        int apartmentID = 0;
        String apartmentID_raw = request.getParameter("apartmentID");

        ApartmentDAO apartmentDAO = new ApartmentDAO();
        BuildingDAO buildingDAO = new BuildingDAO();
        ServiceDAO sdao = new ServiceDAO();
        ServiceContractDAO serviceContractDAO = new ServiceContractDAO();

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        Apartment apartment = apartmentDAO.getApartmentByCustomerId(customer.getCustomerID());

        if (apartmentID_raw != null) {
            try {
                apartmentID = Integer.parseInt(apartmentID_raw);
            } catch (NumberFormatException e) {
            }
        } else {
            apartmentID = apartment.getApartmentID();
        }

        List<Service> list = serviceContractDAO.unregisteredService2(apartmentID, Date.valueOf(LocalDate.now()));
        Building building = buildingDAO.getBuildingByApartmentID(apartment.getApartmentID());

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

        List<ServiceContract> serviceContractList = null;
        LocalDate date = LocalDate.now();
        try {
            serviceContractList = serviceContractDAO.getCurrentServiceContractNotPaging(apartmentID, Date.valueOf(date));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistServiceTenant.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("apartmentID", apartmentID);
        request.setAttribute("apartment", apartment);
        request.setAttribute("building", building);
        request.setAttribute("list", list);
        request.setAttribute("serviceContractList", serviceContractList);
        request.getRequestDispatcher("/user/registServiceTenant.jsp").forward(request, response);
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
