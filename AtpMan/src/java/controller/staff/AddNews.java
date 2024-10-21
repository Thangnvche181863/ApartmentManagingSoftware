/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.NewsCategoryDAO;
import DAO.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import model.News;
import model.NewsCategory;
import model.Staff;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB threshold
        maxFileSize = 1024 * 1024 * 5, // 5MB max size
        maxRequestSize = 1024 * 1024 * 5 * 5) // 25MB max request size

public class AddNews extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsCategoryDAO categoryDAO = new NewsCategoryDAO();
        List<NewsCategory> categories = categoryDAO.getAll();

        // Set categories to the request
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("addnews.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String newsTitle = request.getParameter("newsTitle");
        String newsContent = request.getParameter("newsContent");
        String newsDescription = request.getParameter("newsDescription");

        // Process image upload
        Part filePart = request.getPart("newsImg");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadDir = getServletContext().getRealPath("/") + "img"; // Path to upload directory

        // Save the image to the upload directory
        File file = new File(uploadDir, fileName);
        filePart.write(file.getAbsolutePath());

        // Get staffID from session (if available)
        HttpSession session = request.getSession(false);
        int staffID = 1; // Default value
        if (session != null && session.getAttribute("user") != null) {
            Staff staff = (Staff) session.getAttribute("user");
            staffID = staff.getStaffID(); // Assuming `getStaffID()` exists
        }

      
        int newsCategoryID = Integer.parseInt(request.getParameter("newsCategory"));

        // Hardcoded taskID (can be updated if needed)
        int taskID = 1;

        // Format the newsContent, no longer needed since i use tinymce
        String formattedContent = newsContent.replace("\n", "<br>");
       
       

      
        java.sql.Timestamp currentTime = new java.sql.Timestamp(System.currentTimeMillis());

        News news = new News(0, staffID, taskID, newsCategoryID, newsTitle, formattedContent, currentTime, "img/" + fileName,newsDescription);

        NewsDAO dao = new NewsDAO();
        boolean isAdded = dao.addNews(news);

        if (isAdded) {
            response.sendRedirect("NewsDetail?id=" + news.getNewsID());
        } else {
            String result = "Failed to add News!";
            request.setAttribute("key", result);
            request.getRequestDispatcher("addnews.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
