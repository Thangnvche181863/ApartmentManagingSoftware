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
import DAO.InvoiceDAO;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.Month;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 *
 * @author ADMIN
 */
public class UserHomeServlet extends HttpServlet {

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

        if (month_raw != null) {
            try {
                month = Integer.parseInt(month_raw);
            } catch (NumberFormatException e) {
            }
        }
        if (year_raw != null) {
            try {
                year = Integer.parseInt(year_raw);
            } catch (NumberFormatException e) {
            }
        }

        InvoiceDAO invoiceDAO = new InvoiceDAO();

        List<Invoice> iList = invoiceDAO.getAllInvoiceByApartmentID(1);
        List<Date> dList = invoiceDAO.getAllApartmentInvoiceDate(1);
        LinkedHashSet<Integer> listOfYear = listOfYear(dList);
        LinkedHashSet<Date> listOfMonth = listOfMonth(dList, year);

        Invoice invoiceCurrent = invoiceDAO.getAllInvoiceByApartmentIDandMonth(1, month, year);

        List<ServiceContract> serviceList = invoiceCurrent.getServiceContractList();

        // parameter for current year
        double total = totalAmount(iList);
        int numOfInvoice = iList.size();
        double paid = paidAmount(iList);
        double unpaid = unPaidAmount(iList);

        // parameter for chart
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

        request.getRequestDispatcher("userhome.jsp").forward(request, response);
    }

    protected LinkedHashSet<Integer> listOfYear(List<Date> list) {
        LinkedHashSet<Integer> yList = new LinkedHashSet<>();
        for (Date date : list) {
            yList.add(date.toLocalDate().getYear());
        }
        return yList;
    }

    protected LinkedHashSet<Date> listOfMonth(List<Date> list, int year) {
        LinkedHashSet<Date> yList = new LinkedHashSet<>();
        for (Date date : list) {
            if (date.toLocalDate().getYear() == year) {
                yList.add(date);
            }
        }
        return yList;
    }

    protected double totalAmount(List<Invoice> list) {
        double total = 0;
        for (Invoice invoice : list) {
            total += invoice.getAmount();
        }
        return total;
    }

    protected double paidAmount(List<Invoice> list) {
        double total = 0;
        for (Invoice invoice : list) {
            // check if invoice is paid or not
            // status = 1 => paid
            // status = 0 => unpaid
            if (invoice.getStatus() == 1) {
                total += invoice.getAmount();
            }
        }
        return total;
    }

    protected double unPaidAmount(List<Invoice> list) {
        double total = 0;
        for (Invoice invoice : list) {
            // check if invoice is paid or not
            // status = 1 => paid
            // status = 0 => unpaid
            if (invoice.getStatus() == 0) {
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
                switch (month - 1) {
                    case 0:
                        amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
                        break;
                    case 1:
                        amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
                        break;
                    case 2:
                        amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
                        break;
                    case 3:
                        amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
                        break;
                    case 4:
                        amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
                        break;
                    case 5:
                        amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
                        break;
                    case 6:
                        amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
                        break;
                    case 7:
                        amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
                        break;
                    case 8:
                        amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
                        break;
                    case 9:
                        amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
                        break;
                    case 10:
                        amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
                        break;
                    case 11:
                        amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
                        break;

                    default:
                        throw new AssertionError();
                }
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
