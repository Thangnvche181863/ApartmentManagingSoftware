/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.NewsCategoryDAO;
import DAO.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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

/**
 *
 * @author PC
 */
public class EditNews extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int newsId = Integer.parseInt(request.getParameter("id"));

        
        NewsDAO newsDAO = new NewsDAO();
        News news = newsDAO.getNewsById2(newsId);

        // Fetch all categories for the dropdown
        NewsCategoryDAO categoryDAO = new NewsCategoryDAO();
        List<NewsCategory> categories = categoryDAO.getAll();

        
        request.setAttribute("news", news);
        request.setAttribute("newsCategories", categories);

       
        request.getRequestDispatcher("editNews.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String newsId = request.getParameter("newsId");
        PrintWriter out = response.getWriter();
//        out.print(newsId);

      
        String newsTitle = request.getParameter("newsTitle");
        String newsContent = request.getParameter("newsContent");
        String newsCategoryParam = request.getParameter("newsCategory");
//        out.print(newsTitle);

        int newsCategoryID = Integer.parseInt(newsCategoryParam);

        // Get staffID from session (default to 1 if not logged in)
        HttpSession session = request.getSession(false);
        Staff staff = (Staff) (session != null ? session.getAttribute("user") : null);
        int staffID = (staff != null) ? staff.getStaffID() : 1;

      
        String formattedContent = newsContent.replace("\n", "<br>");
        java.sql.Timestamp currentTime = new java.sql.Timestamp(System.currentTimeMillis());
       
        News news = new News(Integer.parseInt(newsId), staffID, 1, newsCategoryID, newsTitle, formattedContent, currentTime, "");

      
        NewsDAO dao = new NewsDAO();
        boolean isUpdated = dao.updateNews(news);

        if (isUpdated) {
            // Redirect to the news management page on success
            response.sendRedirect("newsmanage"); // Change this to your actual news management page
        } else {
            // Forward to the error page on failure
            request.setAttribute("error", "Failed to update News!");
            request.getRequestDispatcher("forbiddenpage.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
