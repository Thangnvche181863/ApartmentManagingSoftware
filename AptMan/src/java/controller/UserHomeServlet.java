/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import DAO.*;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.Month;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;

/**
 *
 * @author ADMIN
 */
public class UserHomeServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserHomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserHomeServlet at " + request.getContextPath() + "</h1>");
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
        String month_raw = request.getParameter("selectMonth");
        String year_raw = request.getParameter("selectYear");

        // get session resident account
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        // get current date for user first access
        int month = LocalDate.now().getMonthValue() > 0 ? LocalDate.now().getMonthValue() - 1 : 12;
        int year = LocalDate.now().getMonthValue() > 0 ? LocalDate.now().getYear() : LocalDate.now().getYear() - 1;

        if (year_raw != null) {
            try {
                year = Integer.parseInt(year_raw);
            } catch (NumberFormatException e) {
            }
        }

        InvoiceDAO invoiceDAO = new InvoiceDAO();
        ApartmentDAO apartmentDAO = new ApartmentDAO(); 

//        Apartment apartment = apartmentDAO.getApartmentByCustomerId(customer.getCustomerID());
        List<Invoice> iList = invoiceDAO.getAllInvoiceByApartmentID(1);
        List<Date> dList = invoiceDAO.getAllApartmentInvoiceDate(1);
        LinkedHashSet<Integer> listOfYear = listOfYear(dList);
        LinkedHashSet<Date> listOfMonth = listOfMonth(dList, year);

        if (month_raw != null) {
            try {
                boolean contain = false;
                month = Integer.parseInt(month_raw);
                for (Date date : listOfMonth) {
                    if (date.toLocalDate().getMonthValue() == month) {
                        contain = true;
                    }
                }
                if (!contain) {
                    month = LocalDate.now().getMonthValue() > 0 ? LocalDate.now().getMonthValue() - 1 : 12;
                }
            } catch (NumberFormatException e) {
            }
        }

        Invoice invoiceCurrent = invoiceDAO.getAllInvoiceByApartmentIDandMonth(1, month, year);

        List<ServiceContract> serviceList = invoiceCurrent.getServiceContractList();

        // parameter for current year
        double total = totalAmount(iList, year);
        int numOfInvoice = numInvoiceInYear(iList, year);
        double paid = paidAmount(iList, year);
        double unpaid = unPaidAmount(iList, year);

        NewsDAO newsDAO = new NewsDAO();

        //get current page from the request
        String pageParam = request.getParameter("page");
        int currentPage;
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        } else {
            currentPage = 1;
        }

        int totalRows = newsDAO.getNumberOfRows();
        //calculate totalPages
        int totalPages = (int) Math.ceil((double) totalRows / RECORDS_PER_PAGE);

        List<News> newsList = newsDAO.getNewsByPage(currentPage, RECORDS_PER_PAGE);
        List<News> bannerList = newsDAO.getNewsForBanner();

        request.setAttribute("newsBanner", bannerList);
        request.setAttribute("news", newsList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        // area chart
        List<Double> amoutMonth = listAmountByMonth(iList, year);
        request.setAttribute("amoutMonth", amoutMonth);

        request.setAttribute("dateList", listOfMonth);
        request.setAttribute("currentMonth", month);
        request.setAttribute("currentYear", year);
        request.setAttribute("listOfYear", listOfYear);

        request.setAttribute("totalBill", total);
        request.setAttribute("numOfInvoice", numOfInvoice);
        request.setAttribute("paid", paid);
        request.setAttribute("unpaid", unpaid);

        request.setAttribute("invoiceCurrent", invoiceCurrent);
        request.setAttribute("serviceList", serviceList);

//        request.setAttribute("apartment", apartment);
        request.getRequestDispatcher("userhome.jsp").forward(request, response);
    }

    protected int numInvoiceInYear(List<Invoice> list, int year) {
        int count = 0;
        for (Invoice invoice : list) {
            if (invoice.getIssueDate().toLocalDate().getYear() == year) {
                count += 1;
            }
        }
        return count;
    }

    protected LinkedHashSet<Integer> listOfYear(List<Date> list) {
        LinkedHashSet<Integer> yList = new LinkedHashSet<>();
        for (Date date : list) {
            yList.add(date.toLocalDate().getYear());
        }
        return yList;
    }

    protected LinkedHashSet<Date> listOfMonth(List<Date> list, int year) {
        LinkedHashSet<Date> mList = new LinkedHashSet<>();
        for (Date date : list) {
            if (date.toLocalDate().getYear() == year) {
                mList.add(date);
            }
        }
        return mList;
    }

    protected double totalAmount(List<Invoice> list, int year) {
        double total = 0;
        for (Invoice invoice : list) {
            if (invoice.getIssueDate().toLocalDate().getYear() == year) {
                total += invoice.getAmount();
            }
        }
        return total;
    }

    protected double paidAmount(List<Invoice> list, int year) {
        double total = 0;
        for (Invoice invoice : list) {
            // check if invoice is paid or not
            // status = 1 => paid
            // status = 0 => unpaid
            if (invoice.getStatus() == 1 && invoice.getIssueDate().toLocalDate().getYear() == year) {
                total += invoice.getAmount();
            }
        }
        return total;
    }

    protected double unPaidAmount(List<Invoice> list, int year) {
        double total = 0;
        for (Invoice invoice : list) {
            // check if invoice is paid or not
            // status = 1 => paid
            // status = 0 => unpaid
            if (invoice.getStatus() == 0 && invoice.getIssueDate().toLocalDate().getYear() == year) {
                total += invoice.getAmount();
            }
        }
        return total;
    }

    protected List<Double> listAmountByMonth(List<Invoice> list, int year) {
        double init = 0;
        List<Double> amountList = new ArrayList<>(Arrays.asList(init, init, init, init, init, init, init, init, init, init, init, init));
        for (Invoice invoice : list) {
            if (invoice.getIssueDate().toLocalDate().getYear() == year) {
                int month = invoice.getIssueDate().toLocalDate().getMonthValue();
                amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
            }
        }
        return amountList;
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
//        processRequest(request, response);
        String month = request.getParameter("selectMonth");
        PrintWriter out = response.getWriter();
        out.print(month);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
