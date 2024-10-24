/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Paths;

//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 *
 * @author thang
 */
@MultipartConfig
public class ServiceAddServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServiceAddServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServiceAddServlet at " + request.getContextPath() + "</h1>");
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
        int page = 1;
        int recordsPerPage = 10;
        ServiceDAO sdao = new ServiceDAO();
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("page", page);
        request.setAttribute("totalservice", sdao.totalService());
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("totalPages", sdao.count(recordsPerPage));
        request.setAttribute("listservice", sdao.servicePaging(page, recordsPerPage));
        request.setAttribute("serviceType", sdao.getAllType());
        request.getRequestDispatcher("serviceadd.jsp").forward(request, response);
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

        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String fee = request.getParameter("fee");
        String description = request.getParameter("description");
        String icon = request.getParameter("icon");

        // pick file upload form
        Part filePart = request.getPart("img"); // "img" is name in input of form
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên file gốc

        // Kiểm tra loại file
        // Đường dẫn lưu trữ file
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;

        // Tạo thư mục nếu chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Đường dẫn đầy đủ của file sẽ được lưu
        String filePath = uploadPath + File.separator + fileName;

        filePart.write(filePath);
        ServiceDAO sdao = new ServiceDAO();
        String fileURL = request.getContextPath() + "/" + UPLOAD_DIR + "/" + fileName;
        String fileType = filePart.getContentType();

        if (!fileType.startsWith("image/")) {
            request.setAttribute("name", name);
            request.setAttribute("type", type);
            request.setAttribute("fee", fee);
            request.setAttribute("description", description);
            request.setAttribute("icon", icon);
            request.setAttribute("img", fileURL);
            request.setAttribute("serviceType", sdao.getAllType());
            request.setAttribute("errorMessage", "Please upload a valid image file.");
            request.getRequestDispatcher("serviceadd.jsp").forward(request, response);
            return;
        }

        fee = fee.replace(",", ""); // Loại bỏ dấu phẩy

        sdao.insertService(name, type, BigDecimal.valueOf(Double.parseDouble(fee)), description.replace("\n", "<br>"), fileURL, icon);
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
