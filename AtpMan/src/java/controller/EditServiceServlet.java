/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Paths;
import model.Service;

/**
 *
 * @author thang
 */
@MultipartConfig
public class EditServiceServlet extends HttpServlet {

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
//            /* TODO output your page here. You may use following sample code. */
//            String id = request.getParameter("id");
//            ServiceDAO sdao = new ServiceDAO();
//            Service service = sdao.findById(Integer.parseInt(id));
//            request.setAttribute("service", service);
//            request.getRequestDispatcher("serviceedit.jsp").forward(request, response);
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
        String id = request.getParameter("id");
        ServiceDAO sdao = new ServiceDAO();
        Service service = sdao.findById(Integer.parseInt(id));
        request.setAttribute("page", request.getParameter("page"));
        request.setAttribute("service", service);
        request.setAttribute("serviceType", sdao.getAllType());
        request.getRequestDispatcher("serviceedit.jsp").forward(request, response);
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

        int page = 1;
        int recordsPerPage = 10;

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String fee = request.getParameter("fee");
        String description = request.getParameter("description");
        String icon = request.getParameter("icon");
        String imgPath = request.getParameter("imgPath"); // Đường dẫn ảnh cũ

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }

        // pick file upload form
        Part filePart = request.getPart("img"); // "img" is name in input of form
        String fileName = filePart.getSubmittedFileName();

        String fileURL;

        // Kiểm tra xem người dùng có tải lên file ảnh mới không
        if (fileName != null && !fileName.isEmpty()) {
            // Kiểm tra loại file
            String fileType = filePart.getContentType();
            if (!fileType.startsWith("image/")) {
                // Nếu file không hợp lệ, trả về trang edit với thông báo lỗi
                ServiceDAO sdao = new ServiceDAO();
                Service service = sdao.findById(Integer.parseInt(id));
                request.setAttribute("service", service);
                request.setAttribute("serviceType", sdao.getAllType());
                request.setAttribute("errorMessage", "Please upload a valid image file.");
                request.getRequestDispatcher("serviceedit.jsp").forward(request, response);
                return;
            }

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

        // Xử lý giá trị fee, loại bỏ dấu phẩy
        if (fee != null) {
            fee = fee.replace(",", ""); // Loại bỏ dấu phẩy
        }

        // Chuyển đổi giá trị fee sang BigDecimal
        BigDecimal feeValue = BigDecimal.ZERO; // Giá trị mặc định
        try {
            if (fee != null && !fee.isEmpty()) {
                feeValue = new BigDecimal(fee); // Chuyển đổi
            }
        } catch (NumberFormatException e) {
            // Nếu có lỗi, chuyển hướng lại với thông báo lỗi
            ServiceDAO sdao = new ServiceDAO();
            Service service = sdao.findById(Integer.parseInt(id));
            request.setAttribute("service", service);
            request.setAttribute("serviceType", sdao.getAllType());
            request.setAttribute("errorMessage", "Invalid fee value.");
            request.getRequestDispatcher("serviceedit.jsp").forward(request, response);
            return; // Dừng lại sau khi chuyển hướng
        }

        ServiceDAO sdao = new ServiceDAO();
        sdao.updateService(Integer.parseInt(id), name, type, BigDecimal.valueOf(Double.parseDouble(fee)), description.replaceAll("\n", "<br>"), fileURL, icon);
        request.setAttribute("type", "");
        request.setAttribute("search", "");
        request.setAttribute("orderBy", "");
        request.setAttribute("totalservice", sdao.totalService());
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("totalPages", sdao.count(recordsPerPage));
        request.setAttribute("listservice", sdao.servicePaging(page, recordsPerPage));
        request.setAttribute("serviceType", sdao.getAllType());
        request.getRequestDispatcher("servicelist.jsp").forward(request, response);
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
