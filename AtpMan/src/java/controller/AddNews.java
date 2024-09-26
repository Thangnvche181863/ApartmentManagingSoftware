/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import model.News;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB threshold
        maxFileSize = 1024 * 1024 * 5, // 5MB max size
        maxRequestSize = 1024 * 1024 * 5 * 5) // 25MB max request size

public class AddNews extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters from the form
        String newsTitle = request.getParameter("newsTitle");
        String newsContent = request.getParameter("newsContent");

        // Process image upload
        Part filePart = request.getPart("newsImg");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadDir = getServletContext().getRealPath("/") + "img"; // Path to upload directory

        // Save the image to the upload directory
        File file = new File(uploadDir, fileName);
        filePart.write(file.getAbsolutePath());

        
        int staffID = 1;
        int taskID = 1;
        int newsCategoryID = 11;

        //format the newsContent 
        String formattedContent = newsContent.replace("\n", "<br>");

        
        News news = new News(0, staffID, taskID, newsCategoryID, newsTitle, formattedContent, new Date(), "img/" + fileName);

        
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
