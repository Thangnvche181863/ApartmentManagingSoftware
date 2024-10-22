/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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

        // In thông tin người dùng từ session
        Staff staff = (Staff) session.getAttribute("user");

        String name = (String) request.getParameter("name");
        String phoneNumber = (String) request.getParameter("phoneNumber");
        // pick file upload form
        String imgPath = request.getParameter("imgPath"); // Đường dẫn ảnh cũ
        Part filePart = request.getPart("img"); // "img" is name in input of form
        String fileName = filePart.getSubmittedFileName();

        String fileURL;

        // Kiểm tra xem người dùng có tải lên file ảnh mới không
        if (fileName != null && !fileName.isEmpty()) {
            // Kiểm tra loại file
            String fileType = filePart.getContentType();
            

            // Lưu ảnh mới
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadPath = applicationPath + File.separator + UPLOAD_DIR;

            // Tạo thư mục nếu chưa tồn tại
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            fileURL = request.getContextPath() + "/" + UPLOAD_DIR + "/" + fileName;
        } else {
            // Giữ lại đường dẫn ảnh cũ nếu không chọn ảnh mới
            fileURL = imgPath;
        }

        // Xóa file tạm để giải phóng bộ nhớ
        filePart.delete();

        // In thông tin được gửi từ form
        StaffDAO dao = new StaffDAO();

        // Thực hiện cập nhật và in kết quả trả về
        int n = dao.UpdateStaffInfo(name, phoneNumber, fileURL, staff.getStaffID());
        System.out.println("Rows affected: " + n);
        request.setAttribute("userType", "staff");

        if (n > 0) {
            // Lấy lại thông tin người dùng mới và in ra
            staff.setName(name);
            staff.setStaffImg(fileURL);
            
            // Cập nhật session và điều hướng lại trang profile
            session.setAttribute("user", staff);
            System.out.println("oke");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            // In thông báo lỗi
            System.out.println("Profile update failed");
            request.setAttribute("message", "Profile update failed");
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
