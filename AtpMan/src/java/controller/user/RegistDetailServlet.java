/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAO.DiscountDAO;
import DAO.ServiceContractDAO;
import DAO.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Discount;
import model.Service;
import model.ServiceContract;

/**
 *
 * @author thang
 */
public class RegistDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet RegistDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistDetailServlet at " + request.getContextPath() + "</h1>");
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

        String serviceID = request.getParameter("serviceID");
        String apartmentID = request.getParameter("apartmentID");
        String serviceContractID = request.getParameter("serviceContractID");
        ServiceContractDAO scdao = new ServiceContractDAO();

        // cần try catch khi parse hết các param tránh lỗi - Khang
        
        DiscountDAO ddao = new DiscountDAO();

        Discount discount = ddao.getDiscountById(Integer.parseInt(serviceID));

        request.setAttribute("discount1Month", discount.getOneMonth());
        request.setAttribute("discount2Month", discount.getTwoMonth());
        request.setAttribute("discount3Month", discount.getThreeMonth());

        LocalDate currentDate = LocalDate.now();

        ServiceDAO sdao = new ServiceDAO();

        // cach khac
//        List<ServiceContract> list = null;
//        try {
//            list = scdao.getCurrentServiceContractNotPaging(Integer.parseInt(apartmentID), Date.valueOf(currentDate));
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(RegistDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        List<ServiceContract> list = scdao.getAll();
        // tại sao lại lấy tất service chứ không phải lấy cụ thể service đấy trong db
        for (ServiceContract sc : list) {
            if (sc.getServiceId() == Integer.parseInt(serviceID) && sc.getApartmentId() == Integer.parseInt(apartmentID)
                    && sc.getEndDate().compareTo(Date.valueOf(currentDate)) > 0) { //if (sc.getServiceId() == serviceID && sc.getApartmentId() == apartmentID) {
                // update logic - thêm compareDate - Khang
                request.setAttribute("serviceContract", sc);

            }
        }

        Service service = sdao.findById(Integer.parseInt(serviceID));

        request.setAttribute("amount", scdao.pickServiceContract(Integer.parseInt(serviceID)));
        request.setAttribute("apartmentID", apartmentID);
        request.setAttribute("service", service);
        request.setAttribute("serviceContractID", serviceContractID);
        request.getRequestDispatcher("/user/registDetail.jsp").forward(request, response);
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
