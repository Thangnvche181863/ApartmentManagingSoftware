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
public class NewsDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO newsDAO = new NewsDAO();
        News newNews = new News();
        //get id
        String idParam = request.getParameter("id");

        // Check if the id parameter is valid
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);

                News newsItem = newsDAO.getNewsById(id);

                // Check if news exists
                if (newsItem != null) {
                    request.setAttribute("news", newsItem);
                    request.getRequestDispatcher("news-detail.jsp").forward(request, response);

                } else {
                    // Handle the case where the news with the given ID doesn't exist
                    request.setAttribute("error", "News item not found.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                // Handle invalid ID format
                request.setAttribute("error", "Invalid news ID.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            // Handle missing ID
            request.setAttribute("error", "Missing news ID.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
