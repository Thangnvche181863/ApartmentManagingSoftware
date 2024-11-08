/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CustomerDAO;
import DAO.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import model.Customer;
import model.Staff;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class ProfileController extends HttpServlet {

    private static final String UPLOAD_DIR = "uploadFile";

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

            HttpSession session = request.getSession();

            Object user = session.getAttribute("user");
            if (user instanceof Customer) {
                Customer customer = (Customer) session.getAttribute("user");

                request.setAttribute("user", customer);
                request.setAttribute("userType", "customer");

            }
            if (user instanceof Staff) {
                Staff staff = (Staff) session.getAttribute("user");
                request.setAttribute("user", staff);
                request.setAttribute("userType", "staff");

                //update profile
            }
            request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        HttpSession session = request.getSession();

        // Lấy đối tượng user từ session và kiểm tra loại
        Object user = session.getAttribute("user");

        // Thuộc tính chung
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String imgPath = request.getParameter("imgPath"); // Đường dẫn ảnh cũ
        Part filePart = request.getPart("img"); // "img" là tên của input trong form
        String fileName = filePart.getSubmittedFileName();

        String fileURL;

        // Kiểm tra xem người dùng có tải lên file ảnh mới không
        if (fileName != null && !fileName.isEmpty()) {
            String fileType = filePart.getContentType();
            if (!fileType.equals("image/jpeg") && !fileType.equals("image/png") && !fileType.equals("image/gif")) {
                request.setAttribute("message", "Chỉ được upload file ảnh (JPG, PNG, GIF).");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                return;
            }

            String applicationPath = request.getServletContext().getRealPath("");
            String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            fileURL = request.getContextPath() + "/" + UPLOAD_DIR + "/" + fileName;
        } else {
            fileURL = imgPath;
        }

        filePart.delete();

        // Cập nhật thông tin dựa trên loại user
        if (user instanceof Staff) {
            Staff staff = (Staff) user;
            StaffDAO dao = new StaffDAO();
            int n = dao.UpdateStaffInfo(name, phoneNumber, fileURL, staff.getStaffID());

            if (n > 0) {
                staff.setName(name);
                staff.setAvatar(fileURL);
                session.setAttribute("user", staff);
                request.setAttribute("userType", "staff");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Profile update failed");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
        } else if (user instanceof Customer) {
            Customer customer = (Customer) user;
             CustomerDAO dao = new CustomerDAO();
             int n = dao.updateCustomerInfo(name, phoneNumber, fileURL, customer.getCustomerID());

            // Cập nhật session và hiển thị lại thông tin cho Customer
            customer.setName(name);
            customer.setAvatar(fileURL);
            session.setAttribute("user", customer);
            request.setAttribute("userType", "customer");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "User type not recognized");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
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
