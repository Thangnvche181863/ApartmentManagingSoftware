/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.vnpay;

import DAO.InvoiceDAO;
import DAO.InvoiceServiceDAO;
import DAO.ServiceContractDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import com.vnpay.common.Config;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Invoice;
import model.ServiceContract;

/**
 *
 * @author ADMIN
 */
public class PayReturnServlet extends HttpServlet {

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
            out.println("<title>Servlet PayReturnServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PayReturnServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        InvoiceDAO invoiceDAO = new InvoiceDAO();

        String paymentType = request.getParameter("paymentType");

        String returnURL = "/AtpMan/user/userhome";
        if (paymentType.equals("payExistInvoice")) {
            returnURL = "/AtpMan/user/userhome";
        } else if (paymentType.equals("payRegisInvoice")) {
            returnURL = "/AtpMan/registServiceTenant";
        }
        
        request.setAttribute("returnURL", returnURL);

        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        if (fields.containsKey("invoiceId")) {
            fields.remove("invoiceId");
        }
        if (fields.containsKey("paymentType")) {
            fields.remove("paymentType");
        }
        String signValue = Config.hashAllFields(fields);

        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        String vnp_Amount = request.getParameter("vnp_Amount");
        String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
        String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
        String vnp_TransactionNo = request.getParameter("vnp_TransactionNo");
        String vnp_BankCode = request.getParameter("vnp_BankCode");
        String vnp_PayDate = request.getParameter("vnp_PayDate");

        // convert to localdate time
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        LocalDateTime transactionDate = LocalDateTime.parse(vnp_PayDate, inputFormatter);
        String transactionDateFormat = transactionDate.format(outputFormatter);

        long amount = 0;
        try {
            amount = Integer.parseInt(vnp_Amount);
        } catch (NumberFormatException e) {
        }

        request.setAttribute("vnp_Amount", amount / 100);
        request.setAttribute("vnp_PayDate", transactionDateFormat);
        request.setAttribute("vnp_TxnRef", vnp_TxnRef);
        request.setAttribute("vnp_OrderInfo", vnp_OrderInfo);
        request.setAttribute("vnp_ResponseCode", vnp_ResponseCode);
        request.setAttribute("vnp_BankCode", vnp_BankCode);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                if (paymentType.equals("payExistInvoice")) {
                    String invoiceIdSession = (String) session.getAttribute("invoiceIdPayment");
                    String invoiceId_raw = request.getParameter("invoiceId");

                    int invoiceId = 0;
                    try {
                        invoiceId = Integer.parseInt(invoiceId_raw);
                    } catch (NumberFormatException e) {
                    }
                    if (invoiceIdSession != null) {
                        invoiceDAO.updateInvoiceTransaction(Integer.parseInt(invoiceIdSession), Timestamp.valueOf(transactionDate), vnp_TxnRef, vnp_TransactionNo, vnp_BankCode, vnp_OrderInfo);
                        session.removeAttribute("invoiceIdPayment");
                        request.setAttribute("message", "Thành công");
                    } else {
                        session.removeAttribute("invoiceIdPayment");
                        request.setAttribute("message", "Không thành công");
                    }
                } else if (paymentType.equals("payRegisInvoice")) {
                    LocalDate currentDate = LocalDate.now();
                    ServiceContract serviceContract = (ServiceContract) session.getAttribute("serviceContractSession");

                    if (serviceContract != null) {
                        ServiceContractDAO scdao = new ServiceContractDAO();
                        InvoiceServiceDAO invoiceServiceDAO = new InvoiceServiceDAO();

                        Invoice invoice = new Invoice();
                        invoice.setApartmentId(serviceContract.getApartmentId());
                        invoice.setAmount(serviceContract.getAmount().doubleValue());
                        invoice.setIssueDate(Date.valueOf(currentDate));
                        invoice.setDueDate(Date.valueOf(currentDate));
                        invoice.setStatus(1);
                        invoice.setTransactionDate(Timestamp.valueOf(transactionDate));
                        invoice.setInvoiceCode(vnp_TxnRef);
                        invoice.setTransactionNo(vnp_TransactionNo);
                        invoice.setBankCode(vnp_BankCode);
                        invoice.setOrderInfo(vnp_OrderInfo);
                        
                        
                        
                        boolean stt = invoiceDAO.insertInvoiceForRegistService(invoice, serviceContract);
                        
                        session.removeAttribute("serviceContract");
                        request.setAttribute("message", "Thành công");
                    } else {
                        request.setAttribute("message", "Không thành công");
                    }
                } else {
                    session.removeAttribute("invoiceIdPayment");
                    session.removeAttribute("serviceContract");
                    request.setAttribute("message", "Không thành công");
                }

                request.getRequestDispatcher("/vnpay/paymentreturn.jsp").forward(request, response);
            } else {
                session.removeAttribute("invoiceIdPayment");
                session.removeAttribute("serviceContract");
                request.setAttribute("message", "Không thành công");
                request.getRequestDispatcher("/vnpay/paymentreturn.jsp").forward(request, response);
            }
        } else {
            session.removeAttribute("invoiceIdPayment");
            session.removeAttribute("serviceContract");
            request.setAttribute("message", "Sai chữ ký");
            request.getRequestDispatcher("/vnpay/paymentreturn.jsp").forward(request, response);
        }
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
