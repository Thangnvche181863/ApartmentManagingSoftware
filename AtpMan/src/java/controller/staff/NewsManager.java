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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    if (searchParam != null) {
        searchParam = searchParam.trim().replaceAll("\\s+", " ");  
    }
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
        if (categoryParam == null || categoryParam.equalsIgnoreCase("all") || categoryParam.isEmpty()) {
            categoryParam = "all";
        }

        List<News> newsList = new ArrayList<>();
        int totalRows = 0;

        if (searchParam != null && !searchParam.trim().isEmpty()) {
            // Split the searchParam into individual terms
            String[] searchTerms = searchParam.split(" ");
            Set<News> uniqueNewsSet = new HashSet<>(); // Use HashSet to avoid duplicates

            // If category is "All", search by title only
            if (categoryParam.equals("all")) {
                for (String term : searchTerms) {
                    term = term.trim();
                    if (!term.isEmpty()) {
                        // Fetch news for each term
                        List<News> results = newsDAO.getNewsByPageAndTitle(term, currentPage, RECORDS_PER_PAGE);
                        uniqueNewsSet.addAll(results); // Add results to the set
                    }
                }
                newsList = new ArrayList<>(uniqueNewsSet);
                totalRows = newsList.size(); // Total unique rows
            } else {
                // If a specific category is selected, search by title and category
                for (String term : searchTerms) {
                    term = term.trim();
                    if (!term.isEmpty()) {
                        List<News> results = newsDAO.getNewsByPageAndTitleAndCategory(term, categoryParam, currentPage, RECORDS_PER_PAGE);
                        uniqueNewsSet.addAll(results); // Add results to the set
                    }
                }
                newsList = new ArrayList<>(uniqueNewsSet);
                totalRows = uniqueNewsSet.size(); // Total unique rows for the category
            }
        } else {
            // If no search query, filter by category
            if (categoryParam.equals("all")) {
                // Get all news when category is "All"
                totalRows = newsDAO.getNumberOfRows();
                newsList = newsDAO.getNewsByPageManage(currentPage, RECORDS_PER_PAGE);
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
