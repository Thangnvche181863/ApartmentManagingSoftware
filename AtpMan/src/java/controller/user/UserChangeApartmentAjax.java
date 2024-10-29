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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import model.Apartment;
import model.Customer;
import model.Invoice;
import utils.UserHomeUtil;

/**
 *
 * @author ADMIN
 */
public class UserChangeApartmentAjax extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        UserHomeUtil userHomeUtil = new UserHomeUtil();

        String month_raw = request.getParameter("selectMonth");
        String year_raw = request.getParameter("selectYear");
        String apartmentID_raw = request.getParameter("apartmentID");

        CustomerDAO customerDAO = new CustomerDAO();

        // get session resident account
        HttpSession session = request.getSession();
        Customer customer = customerDAO.getCustomer(1);

        // get current date for user first access
        int month = LocalDate.now().getMonthValue() > 0 ? LocalDate.now().getMonthValue() - 1 : 12;
        int year = LocalDate.now().getMonthValue() > 0 ? LocalDate.now().getYear() : LocalDate.now().getYear() - 1;
        int apartmentID = 0;

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
        Apartment apartment = apartmentDAO.getApartmentByLiving(customer.getCustomerID());

        // if user is owner
        List<Apartment> apartmentList = null;
        if (customer.getIsOwner() == 1) {
            apartmentList = apartmentDAO.getAllApartmentByOwner(customer.getCustomerID());
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
        }

        List<Invoice> iList = invoiceDAO.getAllInvoiceByApartmentID(apartment.getApartmentID());
        List<Date> dList = invoiceDAO.getAllApartmentInvoiceDate(apartment.getApartmentID());
        LinkedHashSet<Integer> listOfYear = userHomeUtil.listOfYear(dList);
        LinkedHashSet<Date> listOfMonth = userHomeUtil.listOfMonth(dList, year);

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
                    if (!listOfMonth.isEmpty()) {
                        Date firstElement = listOfMonth.iterator().next();
                        month = firstElement.toLocalDate().getMonthValue();
                    }
                }
            } catch (NumberFormatException e) {
            }
        }

        Invoice invoiceCurrent = invoiceDAO.getInvoiceByApartmentIDandMonthYear(apartment.getApartmentID(), month, year);

//        for (Invoice invoice : iList) {
//            if (invoice.getIssueDate().toLocalDate().getMonthValue() == month && invoice.getIssueDate().toLocalDate().getYear() == year) {
//                invoiceCurrent = invoice;
//            }
//        }
        double total = userHomeUtil.totalAmount(iList, year);
        int numOfInvoice = userHomeUtil.numInvoiceInYear(iList, year);
        double paid = userHomeUtil.paidAmount(iList, year);
        double unpaid = userHomeUtil.unPaidAmount(iList, year);

        Locale locale = Locale.US;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");

        out.println("<div class=\"col-xl-3 col-md-6 mb-4\">\n"
                + "                                <div class=\"row\">\n"
                + "                                    <div class=\"card border-left-success shadow h-100 py-2\">\n"
                + "                                        <div class=\"btn-group dropend\">\n"
                + "                                            <a class=\"chooseApt\" href=\"/AtpMan/userapartmentinfo?apartmentID=" + apartment.getApartmentID() + "&buildingID=" + apartment.getBuildingID() + "\" style=\"text-decoration: none\">\n"
                + "                                                <div class=\"card-body\">\n"
                + "                                                    <div class=\"row no-gutters align-items-center\">\n"
                + "                                                        <div class=\"col mr-2\">\n"
                + "                                                            <div class=\"text-xs font-weight-bold text-success text-uppercase mb-1 card1\">\n"
                + "                                                                Căn hộ\n"
                + "                                                            </div>\n"
                + "                                                            <div class=\"h4 mb-0 font-weight-bold text-gray-800 card1\">\n"
                + "                                                                " + apartment.getApartmentNumber() + " - " + apartment.getName() + "\n"
                + "                                                            </div>\n"
                + "                                                            <div class=\"text-sm font-weight-bold text-success text-uppercase mb-1 card1\">\n"
                + "                                                                (Bấm để xem chi tiết)\n"
                + "                                                            </div>\n"
                + "                                                        </div>\n"
                + "\n"
                + "                                                        <div class=\"col-auto\">\n"
                + "                                                            <i class=\"fas fa-home fa-2x text-gray-300\"></i>\n"
                + "                                                        </div> \n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                            </a>\n"
                + "                                                <button type=\"button\" class=\"btn btn-success dropdown-toggle dropdown-toggle-split\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">\n"
                + "                                                    <span class=\"visually-hidden\">Toggle Dropright</span>\n"
                + "                                                </button>\n"
                + "                                                <ul class=\"dropdown-menu\">\n"
                + "                                                    <!-- Dropdown menu links -->\n");
        if (apartmentList != null && apartmentList.size() > 1) {
            for (Apartment apartment1 : apartmentList) {
                out.println("                                                        <a class=\"chooseApt\" href=\"#\" style=\"text-decoration: none\" onclick=\"changeApartment( " + apartment1.getApartmentID() + ")\">\n"
                        + "                                                            <li>\n"
                        + "                                                                <div class=\"card-body\">\n"
                        + "                                                                    <div class=\"row no-gutters align-items-center\">\n"
                        + "                                                                        <div class=\"col mr-2\">\n"
                        + "                                                                            <div class=\"text-xs font-weight-bold text-success text-uppercase mb-1 card1\">\n"
                        + "                                                                                Căn hộ\n"
                        + "                                                                            </div>\n"
                        + "                                                                            <div class=\"h5 mb-0 font-weight-bold text-gray-800 card2\">\n"
                        + "                                                                                " + apartment1.getApartmentNumber() + " - " + apartment1.getName() + "\n"
                        + "                                                                            </div>\n"
                        + "                                                                        </div>\n"
                        + "                                                                        <div class=\"col-auto\">\n"
                        + "                                                                            <i class=\"fas fa-home fa-2x text-gray-300\"></i>\n"
                        + "                                                                        </div> \n"
                        + "                                                                    </div>\n"
                        + "                                                                </div>\n"
                        + "                                                            </li>\n"
                        + "                                                        </a>\n");
            }
        }
        out.println("                                                </ul>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "                            </div> \n"
                + "                            <div class=\"d-sm-flex align-items-center justify-content-between mb-4\">\n"
                + "                                <h1 class=\"h3 mb-0 text-gray-800 text-primary\">Tổng hóa đơn trong năm " + year + "</h1>\n"
                + "                            </div>\n"
                + "                            <!-- Billing information for one year -->\n"
                + "                            <div class=\"row\">\n"
                + "                                <!-- Total information for one year -->\n"
                + "                                <div class=\"col-xl-3 col-md-6 mb-4\">\n"
                + "                                    <div class=\"card border-left-primary shadow h-100 py-2\">\n"
                + "                                        <div class=\"card-body\">\n"
                + "                                            <div class=\"row no-gutters align-items-center\">\n"
                + "                                                <div class=\"col mr-2\">\n"
                + "                                                    <div class=\"text-xs font-weight-bold text-primary text-uppercase mb-1\">\n"
                + "                                                        Tổng hóa đơn (" + year + ")\n"
                + "                                                    </div>\n"
                + "                                                    <div class=\"h5 mb-0 font-weight-bold text-gray-800\">\n"
                + "                                                        " + decimalFormat.format(total) + "\n"
                + "                                                            VNĐ\n"
                + "                                                        </div>\n"
                + "                                                    </div>\n"
                + "                                                    <div class=\"col-auto\">\n"
                + "                                                        <i class=\"fas fa-money-bill fa-2x text-gray-300\"></i>\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                    <!-- End Billing information for one year -->\n"
                + "\n"
                + "                                    <!--Paid amount billing information year-->\n"
                + "                                    <div class=\"col-xl-3 col-md-6 mb-4\">\n"
                + "                                        <div class=\"card border-left-success shadow h-100 py-2\">\n"
                + "                                            <div class=\"card-body\">\n"
                + "                                                <div class=\"row no-gutters align-items-center\">\n"
                + "                                                    <div class=\"col mr-2\">\n"
                + "                                                        <div class=\"text-xs font-weight-bold text-success text-uppercase mb-1\">\n"
                + "                                                            Tổng đã thanh toán\n"
                + "                                                        </div>\n"
                + "                                                        <div class=\"h5 mb-0 font-weight-bold text-gray-800\">\n"
                + "                                                        "+decimalFormat.format(paid)+"\n"
                + "                                                            VNĐ\n"
                + "                                                        </div>\n"
                + "                                                    </div>\n"
                + "                                                    <div class=\"col-auto\">\n"
                + "                                                        <i class=\"fas fa-dollar-sign fa-2x text-gray-300\"></i>\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                    <!--Paid amount billing information year-->\n"
                + "\n"
                + "                                    <!-- UnPaid amount billing information year -->\n"
                + "                                    <div class=\"col-xl-3 col-md-6 mb-4\">\n"
                + "                                        <div class=\"card border-left-warning shadow h-100 py-2\">\n"
                + "                                            <div class=\"card-body\">\n"
                + "                                                <div class=\"row no-gutters align-items-center\">\n"
                + "                                                    <div class=\"col mr-2\">\n"
                + "                                                        <div class=\"text-xs font-weight-bold text-warning text-uppercase mb-1\">\n"
                + "                                                            Chưa thanh toán\n"
                + "                                                        </div>\n"
                + "                                                        <div class=\"h5 mb-0 font-weight-bold text-gray-800\">\n"
                + "                                                        "+decimalFormat.format(unpaid)+"\n"
                + "                                                            VNĐ\n"
                + "                                                        </div>\n"
                + "                                                    </div>\n"
                + "                                                    <div class=\"col-auto\">\n"
                + "                                                        <i class=\"fas fa-comments fa-2x text-gray-300\"></i>\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                    <!--UnPaid amount billing information year-->\n"
                + "                                    <!-- Pending Requests Card Example -->\n"
                + "                                    <div class=\"col-xl-3 col-md-6 mb-4\">\n"
                + "                                        <div class=\"card border-left-info shadow h-100 py-2\">\n"
                + "                                            <div class=\"card-body\">\n"
                + "                                                <div class=\"row no-gutters align-items-center\">\n"
                + "                                                    <div class=\"col mr-2\">\n"
                + "                                                        <div class=\"text-xs font-weight-bold text-info text-uppercase mb-1\">\n"
                + "                                                            Tổng số hóa đơn</div>\n"
                + "                                                        <div class=\"h5 mb-0 font-weight-bold text-gray-800\">"+ numOfInvoice +"</div>\n"
                + "                                                </div>\n"
                + "                                                <div class=\"col-auto\">\n"
                + "                                                    <i class=\"fas fa-comments fa-2x text-gray-300\"></i>\n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                            <!-- End Billing information for one year -->\n"
                + "                            <div id=\"invoiceMonth\">"
        );

        out.println("<div class=\"d-flex align-items-center justify-content-between mb-4 row\">\n"
                + "                                <div class=\"d-sm-flex align-items-center justify-content-between mb-4 col-xl-5 col-md-5\">\n"
                + "                                    <h1 id=\"currentMonth\" class=\"h3 mb-0 text-gray-800 text-primary \">Thông tin hóa đơn trong tháng</h1>\n"
                + "                                </div>\n"
                + "                                <form class=\"d-flex col-xl-7 col-md-7\" action=\"userhome\" method=\"GET\" id=\"chooseMonthYear\">\n"
                + "                                    <div class=\"col-xl-6 col-md-6\">\n"
                + "                                        <input id=\"apartmentID\" type=\"hidden\" name=\"apartmentID\" value=\"" + apartment.getApartmentID() + "\" />\n"
                + "                                        <fmt:setLocale value = \"vi_VN\"/>\n"
                + "                                        <label for=\"month\" class=\"form-label\">Chọn Tháng</label>\n"
                + "                                        <select id=\"month\" name=\"selectMonth\" class=\"form-select me-2\" aria-label=\"Select Month\" onchange=\"changeMonthAndYear()\">\n");
        for (Date months : listOfMonth) {
            out.println("                                   <option " + (month == months.toLocalDate().getMonthValue() ? "selected" : "") + " value=\"" + months.toLocalDate().getMonthValue() + "\">\n"
                    + "                                          " + monthFormat.format(months) + ""
                    + "                                      </option>\n");
        }

        out.println("                                        </select>\n"
                + "                                    </div>\n"
                + "                                    <div class=\"col-xl-6 col-md-6\">\n"
                + "                                        <label for=\"year\" class=\"form-label\">Chọn Năm</label>\n"
                + "                                        <select id=\"year\" name=\"selectYear\" class=\"form-select\" aria-label=\"Select Year\" onchange=\"changeMonthAndYear()\">\n");
        for (Integer years : listOfYear) {
            out.println("                                   <option " + (years == year ? "selected" : "") + " value=\"" + years + "\">\n"
                    + "                                                    " + years + "\n"
                    + "                                      </option>\n");
        }
        out.println("                                        </select>\n"
                + "                                    </div>\n"
                + "                                </form>\n"
                + "                            </div>\n"
                + "                            <div class=\"row\">"
        );
        out.println("<div class=\"col-xl-3 col-md-6 mb-4\">\n"
                + "                                <div class=\"card border-left-primary shadow h-100 py-2\">\n"
                + "                                    <div class=\"card-body\">\n"
                + "                                        <div class=\"row no-gutters align-items-center\">\n"
                + "                                            <div class=\"col mr-2\">\n"
                + "                                                <div class=\"text-xs font-weight-bold text-primary text-uppercase mb-1\">\n"
                + "                                                    Tổng hóa đơn trong tháng</div>\n"
                + "                                                <div class=\"h5 mb-0 font-weight-bold text-gray-800\">\n"
                + "                                                    " + (invoiceCurrent != null ? decimalFormat.format(invoiceCurrent.getAmount()) + " VNĐ" : "Không khả dụng") + "\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                                <div class=\"col-auto\">\n"
                + "                                                    <i class=\"fas fa-money-bill fa-2x text-gray-300\"></i>\n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "\n"
                + "                                <!-- Earnings (Monthly) Card Example -->\n"
                + "                                <div class=\"col-xl-3 col-md-6 mb-4\">\n"
                + "                                    <div class=\"card border-left-success shadow h-100 py-2\">\n"
                + "                                        <div class=\"card-body\">\n"
                + "                                            <div class=\"row no-gutters align-items-center\">\n"
                + "                                                <div class=\"col mr-2\">\n"
                + "                                                    <div class=\"text-xs font-weight-bold text-success text-uppercase mb-1\">\n"
                + "                                                        Ngày phát hành</div>\n"
                + "                                                    <div class=\"h5 mb-0 font-weight-bold text-gray-800\">\n"
                + "                                                    " + (invoiceCurrent != null ? dateFormat.format(invoiceCurrent.getIssueDate()) : "Không khả dụng") + "\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                                <div class=\"col-auto\">\n"
                + "                                                    <i class=\"fas fa-calendar fa-2x text-gray-300\"></i>\n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "\n"
                + "\n"
                + "                                <!-- Pending Requests Card Example -->\n"
                + "                                <div class=\"col-xl-3 col-md-6 mb-4\">\n"
                + "                                    <div class=\"card border-left-warning shadow h-100 py-2\">\n"
                + "                                        <div class=\"card-body\">\n"
                + "                                            <div class=\"row no-gutters align-items-center\">\n"
                + "                                                <div class=\"col mr-2\">\n"
                + "                                                    <div class=\"text-xs font-weight-bold text-warning text-uppercase mb-1\">\n"
                + "                                                        Ngày hết hạn</div>\n"
                + "                                                    <div class=\"h5 mb-0 font-weight-bold text-gray-800\">\n"
                + "                                                    " + (invoiceCurrent != null ? dateFormat.format(invoiceCurrent.getDueDate()) : "Không khả dụng") + "\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                                <div class=\"col-auto\">\n"
                + "                                                    <i class=\"fas fa-calendar fa-2x text-gray-300\"></i>\n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "\n"
                + "                                <!-- Pending Requests Card Example -->\n"
                + "                                <div class=\"col-xl-3 col-md-6 mb-4\">\n");
        String colorTab = "secondary";
        String status = "Không khả dụng";
        if (invoiceCurrent != null) {
            switch (invoiceCurrent.getStatus()) {
                case 1:
                    colorTab = "success";
                    status = "Đã thanh toán";
                    break;
                case 0:
                    colorTab = "danger";
                    status = "Chưa thanh toán";
                    break;
                default:
                    colorTab = "secondary";
                    status = "Không khả dụng";
                    break;
            }
        }
        out.println("                               <div class=\"card border-left-" + colorTab + " shadow h-100 py-2\">\n"
                + "                                    <div class=\"card-body\">\n"
                + "                                        <div class=\"row no-gutters align-items-center\">\n"
                + "                                            <div class=\"col mr-2\">\n"
                + "                                                <div class=\"text-xs font-weight-bold text-" + colorTab + " text-uppercase mb-1\">\n"
                + "                                                    Trạng thái\n"
                + "                                                </div>\n"
                + "                                                <div class=\"h5 mb-0 font-weight-bold text-gray-800\">" + status + "</div>\n"
                + "                                            </div>\n"
                + "                                            <div class=\"col-auto\">\n"
                + "                                                <i class=\"fas fa-comments fa-2x text-gray-300\"></i>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "                            </div>"
                + "                            </div>"
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
