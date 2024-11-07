/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAO.ApartmentDAO;
import DAO.BuildingDAO;
import DAO.InvoiceDAO;
import DAO.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import model.Apartment;
import model.Customer;
import model.Invoice;
import model.ServiceContract;
import utils.UserHomeUtil;

/**
 *
 * @author ADMIN
 */
public class InvoiceStatisticServlet extends HttpServlet {

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
            out.println("<title>Servlet InvoiceStatisticServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InvoiceStatisticServlet at " + request.getContextPath() + "</h1>");
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
        UserHomeUtil userHomeUtil = new UserHomeUtil();

        String month_raw = request.getParameter("selectMonth");
        String year_raw = request.getParameter("selectYear");
        String apartmentID_raw = request.getParameter("apartmentID");

        // get session resident account
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        // create dao
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        ApartmentDAO apartmentDAO = new ApartmentDAO();
        BuildingDAO buildingDAO = new BuildingDAO();

        // get current date for user first access
        int month = LocalDate.now().getMonthValue() > 0 ? LocalDate.now().getMonthValue() - 1 : 12;
        int year = LocalDate.now().getMonthValue() > 0 ? LocalDate.now().getYear() : LocalDate.now().getYear() - 1;
        int apartmentID = 0;

        int rowsPerPage = 5;

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

        // get apartment user is living
        Apartment apartment = apartmentDAO.getApartmentByLiving(customer.getCustomerID());

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

        apartmentID = apartment.getApartmentID();
        LinkedHashMap<Integer, Double> amountInMonth = invoiceDAO.getAmountMonth(apartment.getApartmentID(), year);
        List<Double> amountMonth = userHomeUtil.listAmountByMonth2(amountInMonth, year);

        List<Date> dList = invoiceDAO.getAllApartmentInvoiceDate(apartment.getApartmentID());
        LinkedHashSet<Integer> listOfYear = userHomeUtil.listOfYear(dList);
        LinkedHashSet<Integer> listOfMonth = userHomeUtil.listOfMonth2(amountInMonth, year);

        if (month_raw != null) {
            try {
                boolean contain = false;
                month = Integer.parseInt(month_raw);
                for (Integer iMonth : listOfMonth) {
                    if (iMonth == month) {
                        contain = true;
                    }
                }
                if (!contain) {
                    if (!listOfMonth.isEmpty()) {
                        month = listOfMonth.iterator().next();
                    }
                }
            } catch (NumberFormatException e) {
            }
        } else {
            boolean contain = false;
            for (Integer iMonth : listOfMonth) {
                if (iMonth == month) {
                    contain = true;
                }
            }
            if (!contain) {
                if (!listOfMonth.isEmpty()) {
                    month = listOfMonth.iterator().next();
                }
            }
        }

        List<Invoice> invoiceCurrentList = invoiceDAO.getInvoiceByApartmentIDandMonth2(apartment.getApartmentID(), month, year, 1, 5, null, null, null, null, null);
        double totalAmountCurrent = userHomeUtil.totalAmountInMonth(amountInMonth, month);

        // calculate for service table
        int numOfInvoiceInMonth = invoiceDAO.countInvoiceByApartmentIDandMonth(apartment.getApartmentID(), month, year);
        int totalInvoicePage = (int) Math.ceil((double) numOfInvoiceInMonth / rowsPerPage);
        
        //get current page from the request
        String pageParam = request.getParameter("page");
        int currentPage = 1;
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        } else {
            currentPage = 1;
        }
        
        if(currentPage > totalInvoicePage){
            currentPage = 1;
        }

        request.setAttribute("currentServicePage", "1");
        request.setAttribute("totalInvoicePage", totalInvoicePage);
        request.setAttribute("totalAmount", totalAmountCurrent);

        // area chart
        request.setAttribute("amoutMonth", amountMonth);

        request.setAttribute("listOfMonth", listOfMonth);
        request.setAttribute("currentMonth", month);
        request.setAttribute("currentYear", year);
        request.setAttribute("listOfYear", listOfYear);
        request.setAttribute("totalInvoice", numOfInvoiceInMonth);
        request.setAttribute("invoiceCurrentList", invoiceCurrentList);

        request.setAttribute("numOfInvoiceInMonth", numOfInvoiceInMonth);

        request.setAttribute("apartment", apartment);
        request.getRequestDispatcher("/user/invoicestatistic.jsp").forward(request, response);
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
