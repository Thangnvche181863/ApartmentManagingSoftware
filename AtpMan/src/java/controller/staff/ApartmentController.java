/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.ApartmentDAO;
import DAO.InvoiceDAO;
import DAO.LivingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Vector;
import model.Apartment;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ApartmentController", urlPatterns = {"/apartment"})
public class ApartmentController extends HttpServlet {

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
            int buildingID = Integer.parseInt(request.getParameter("buildingID"));
            
            ApartmentDAO dao = new ApartmentDAO();
            Vector<Apartment> vector = dao.getAllApartmentByID(buildingID);

            //get list amount Of unpaid Invoice
            InvoiceDAO invoicedao = new InvoiceDAO();
            List<Integer> listInvoice = invoicedao.getNumOfUnpaidInvoice(buildingID);

            LivingDAO livingdao = new LivingDAO();
            List<Integer> list = livingdao.getAmountOfResidentOfApartment(buildingID);

            
            request.setAttribute("list", list);
            request.setAttribute("listApartment", vector);
            request.setAttribute("buildingID", buildingID);
            request.getRequestDispatcher("apartment.jsp").forward(request, response);
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
        String service = request.getParameter("service");
        if (service.equals("viewedit")) {
            int apartmentID = Integer.parseInt(request.getParameter("apartmentID"));
            ApartmentDAO dao = new ApartmentDAO();
            Apartment apartment = dao.getApartmentByID(apartmentID);
            String buildingID =request.getParameter("buildingID");
            request.setAttribute("buildingID", buildingID);

            request.setAttribute("apartmentID", apartmentID);
            request.setAttribute("apartment", apartment);  // Gửi thông tin căn hộ vào JSP
            request.getRequestDispatcher("editApartment.jsp").forward(request, response);
        }
        if (service.equals("edit")) {
            int apartmentID = Integer.parseInt(request.getParameter("apartmentID"));
            System.out.println("+++" + apartmentID);
            String apartmentNumber = request.getParameter("apartmentNumber");
            String apartmentType = request.getParameter("apartmentType");
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
            BigDecimal maintenanceFee = BigDecimal.valueOf(Double.parseDouble(request.getParameter("maintenanceFee")));
            int floor = Integer.parseInt(request.getParameter("floor"));
            int area = Integer.parseInt(request.getParameter("area"));
            ApartmentDAO dao = new ApartmentDAO();
            boolean success = dao.editApartment(apartmentID, apartmentNumber, apartmentType, price, maintenanceFee, floor, area);
            if (success) {
                System.out.println("Bú");
            } else {
                System.out.println("Cúc");
            }
        String buildingID = request.getParameter("buildingID");
            request.setAttribute("buildingID", buildingID);

            response.sendRedirect("apartment?buildingID=" + buildingID);
        }

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
