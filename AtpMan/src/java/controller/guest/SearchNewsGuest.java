/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.guest;

import DAO.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.News;

/**
 *
 * @author PC
 */
public class SearchNewsGuest extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 9;
    private static final Logger logger = Logger.getLogger(SearchNewsGuest.class.getName());

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    NewsDAO newsDAO = new NewsDAO();
    String searchParam = request.getParameter("search");

    if (searchParam != null) {
        searchParam = searchParam.trim().replaceAll("\\s+", " ");
    }

    int currentPage = 1; // Default to the first page

    try {
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<News> newsList = new ArrayList<>();
        int totalRows = 0;

        // If searchParam is not null and not empty
        if (searchParam != null && !searchParam.isEmpty()) {
            // Split searchParam into separate words
            String[] searchTerms = searchParam.split(" ");

            // Use a set to avoid duplicates
            Set<News> uniqueNewsSet = new HashSet<>();

            // Get total results for all terms to calculate totalRows correctly
            for (String term : searchTerms) {
                term = term.trim();
                if (!term.isEmpty()) {
                    // Get results for the current term
                    List<News> results = newsDAO.getNewsByTitle(term); // Assuming this method retrieves all matching news
                    uniqueNewsSet.addAll(results);
                }
            }

            // Convert the set back to a list and slice for pagination
            totalRows = uniqueNewsSet.size(); // Correct totalRows
            newsList = new ArrayList<>(uniqueNewsSet); // Convert back to list
            
            // Get the results for the current page
            int start = (currentPage - 1) * RECORDS_PER_PAGE;
            int end = Math.min(start + RECORDS_PER_PAGE, totalRows);
            newsList = newsList.subList(start, end); // Get sublist for current page
            
            logger.log(Level.INFO, "Total rows calculated: {0}", totalRows);
        }

        // Calculate total pages based on unique results
        int totalPages = (int) Math.ceil((double) totalRows / RECORDS_PER_PAGE);
        logger.log(Level.INFO, "Total pages calculated: {0}", totalPages);
        
        // Check if no search results found
        if (searchParam == null || searchParam.isEmpty() || totalRows == 0) {
            request.setAttribute("message", "Nothing is found"); // Set the message attribute
        } else {
            // Set attributes for the JSP
            request.setAttribute("news", newsList);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalRows", totalRows);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("search", searchParam); // Pass the search term
        }

        // Forward to JSP
        request.getRequestDispatcher("searchnewsGuest.jsp").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace(); // Log the exception for debugging
        response.sendRedirect("forbiddenpage.jsp"); // Redirect on error
    }
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
