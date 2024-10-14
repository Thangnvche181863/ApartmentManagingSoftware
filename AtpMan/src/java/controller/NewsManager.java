/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.News;

/**
 *
 * @author PC
 */
public class NewsManager extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 6;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    NewsDAO newsDAO = new NewsDAO();
    String searchParam = request.getParameter("search");

    int currentPage = 1; // Default page number
    try {
        // Get current page from the request
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<News> newsList;
        int totalRows;

        // Check if searchParam is provided and not empty
        if (searchParam != null && !searchParam.trim().isEmpty()) {
            // Get news by Title, ordered by postDate desc
            totalRows = newsDAO.getNumberOfRowsByTitle(searchParam);
            newsList = newsDAO.getNewsByPageAndTitle(searchParam, currentPage, RECORDS_PER_PAGE);
        } else {
            // Get all news normally, ordered by postDate desc
            totalRows = newsDAO.getNumberOfRows();
            newsList = newsDAO.getNewsByPage(currentPage, RECORDS_PER_PAGE);
        }

        
        int totalPages = (int) Math.ceil((double) totalRows / RECORDS_PER_PAGE);

    
        request.setAttribute("news", newsList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        
        request.getRequestDispatcher("newsManager.jsp").forward(request, response);

    } catch (NumberFormatException e) {
       
        response.sendRedirect("forbiddenpage.jsp");
    }
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
