/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.guest;

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

        int currentPage = 1; // Default page number
        try {
            // Get current page from the request
            String pageParam = request.getParameter("page");
            if (pageParam != null) {
                currentPage = Integer.parseInt(pageParam);
            }

            int totalRows = newsDAO.getNumberOfRowsForTin();
            // Calculate total pages
            int totalPages = (int) Math.ceil((double) totalRows / RECORDS_PER_PAGE);

            // Fetch news data based on the current page
            List<News> newsList = newsDAO.getNewsByPage(currentPage, RECORDS_PER_PAGE);
            List<News> bannerList = newsDAO.getNewsForBanner();

            // Set attributes for the JSP page
            request.setAttribute("newsBanner", bannerList);
            request.setAttribute("news", newsList);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);

            // Forward to about.jsp
            request.getRequestDispatcher("about.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // If the page parameter is not a valid number, redirect to forbiddenpage.jsp
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
