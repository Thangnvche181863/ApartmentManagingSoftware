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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.News;
import model.NewsCategory;

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
        NewsCategoryDAO categoryDAO = new NewsCategoryDAO();
        List<NewsCategory> categories = categoryDAO.getAll();
        String categoryParam = request.getParameter("category");

        int currentPage = 1; // Default page number
        try {
            
            String pageParam = request.getParameter("page");
            if (pageParam != null) {
                currentPage = Integer.parseInt(pageParam);
            }

            // Set default value for category if not provided or "all"
            if (categoryParam == null || categoryParam.equalsIgnoreCase("all")) {
                categoryParam = "all";
            }

            List<News> newsList;
            int totalRows;

            if (searchParam != null && !searchParam.trim().isEmpty()) {
                // If category is "All", search by title only
                if (categoryParam.equals("all")) {
                    totalRows = newsDAO.getNumberOfRowsByTitle(searchParam);
                    newsList = newsDAO.getNewsByPageAndTitle(searchParam, currentPage, RECORDS_PER_PAGE);
                } else {
                    // If specific category is selected, search by title and category
                    totalRows = newsDAO.getNumberOfRowsByTitleAndCategory(searchParam, categoryParam);
                    newsList = newsDAO.getNewsByPageAndTitleAndCategory(searchParam, categoryParam, currentPage, RECORDS_PER_PAGE);
                }
            } else {
                // If no search query, filter by category
                if (categoryParam.equals("all")) {
                    // Get all news when category is "All"
                    totalRows = newsDAO.getNumberOfRows();
                    newsList = newsDAO.getNewsByPage(currentPage, RECORDS_PER_PAGE);
                } else {
                    // Filter by specific category
                    totalRows = newsDAO.getNumberOfRowsByCategory(categoryParam);
                    newsList = newsDAO.getNewsByPageAndCategory(categoryParam, currentPage, RECORDS_PER_PAGE);
                }
            }

            int totalPages = (int) Math.ceil((double) totalRows / RECORDS_PER_PAGE);

            request.setAttribute("news", newsList);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("newsCategories", categories);
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
