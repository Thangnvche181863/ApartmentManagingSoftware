/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.News;
import java.sql.SQLException;
import DAO.NewsDAO;
import java.sql.SQLException;



/**
 *
 * @author PC
 */
public class NewsServlet extends HttpServlet {
   
   private static final int RECORDS_PER_PAGE = 5;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       NewsDAO newsDAO = new NewsDAO();

        // Get current page from the request
        String pageParam = request.getParameter("page");
        int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

        // Get the total number of news items
        int totalRows = newsDAO.getNumberOfRows();
        int totalPages = (int) Math.ceil((double) totalRows / RECORDS_PER_PAGE);

        // Get the news items for the current page
        List<News> newsList = newsDAO.getNewsByPage(currentPage, RECORDS_PER_PAGE);

        // Set news and pagination attributes
        request.setAttribute("news", newsList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        
        request.getRequestDispatcher("about.jsp").forward(request, response);
    
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
