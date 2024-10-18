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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
                    // Get news content
                    String newsContent = newsItem.getNewsContent();

                    // Extract <img> and <video> tags
                    String mediaTags = extractMediaTags(newsContent);

                    // Set attributes to be forwarded to JSP
                    request.setAttribute("news", newsItem);
                    request.setAttribute("mediaTags", mediaTags);

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
   // Method to extract <img> and <video> tags and format them for the carousel
    private String extractMediaTags(String content) {
        StringBuilder mediaTags = new StringBuilder();

        // Regex for <img> and <video> tags
        String regex = "(<img[^>]+>|<video[^>]+>[\\s\\S]*?<\\/video>)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        int index = 0;

        while (matcher.find()) {
            String mediaTag = matcher.group();
            // Wrap each media tag in a carousel item div
            mediaTags.append("<div class=\"carousel-item")
                    .append(index == 0 ? " active" : "") // Add active class to the first item
                    .append("\">")
                    .append(mediaTag)
                    .append("</div>");
            index++;
        }
        return mediaTags.toString();
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
