/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.NewsCommentDAO;
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
import model.NewsComment;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class NewsDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO newsDAO = new NewsDAO();
        NewsCommentDAO newsCommentDAO = new NewsCommentDAO();

        // Get the session
        HttpSession session = request.getSession();
        System.out.println("Session ID: " + session.getId());

        // Get id parameter
        String idParam = request.getParameter("id");
        System.out.println("Received ID parameter: " + idParam);

        // Check if the id parameter is valid
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                System.out.println("Parsed ID: " + id);

                News newsItem = newsDAO.getNewsById(id);
                System.out.println("Fetched news item: " + newsItem);

                // Check if news exists
                if (newsItem != null) {
                    // Get news content
                    String newsContent = newsItem.getNewsContent();
                    System.out.println("News content retrieved, length: " + (newsContent != null ? newsContent.length() : 0));

                    // Extract <img> and <video> tags from newsContent
                    String mediaTags = extractMediaTags(newsContent);
                    System.out.println("Media tags extracted: " + mediaTags);

                    List<NewsComment> comments = newsCommentDAO.getByNewsID(id);
                    System.out.println("Comments fetched for news ID " + id + ": " + (comments != null ? comments.size() : 0));

                    // Set attributes to be forwarded to JSP
                    request.setAttribute("news", newsItem);
                    request.setAttribute("mediaTags", mediaTags);
                    request.setAttribute("comments", comments);

                    // Check session user details
                    Object sessionUser = session.getAttribute("user");
                    System.out.println("Session User: " + sessionUser);

                    // Forward request to JSP
                    request.getRequestDispatcher("news-detail.jsp").forward(request, response);

                } else {
                    // Handle the case where the news with the given ID doesn't exist
                    request.setAttribute("error", "News item not found.");
                    System.out.println("News item not found for ID: " + id);
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                // Handle invalid ID format
                request.setAttribute("error", "Invalid news ID.");
                System.out.println("Invalid ID format: " + idParam);
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            // Handle missing ID
            request.setAttribute("error", "Missing news ID.");
            System.out.println("Missing news ID parameter.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        System.out.println("End of doGet method.");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get parameters from the request
            String commentText = request.getParameter("commentText");
            String newsIDStr = request.getParameter("newsID");
            String customerIDStr = request.getParameter("customerID");
            String staffIDStr = request.getParameter("staffID");
            
            // Parse IDs
            int newsID = Integer.parseInt(newsIDStr);
            Integer customerID = customerIDStr != null && !customerIDStr.isEmpty() ? Integer.parseInt(customerIDStr) : null;
            Integer staffID = staffIDStr != null && !staffIDStr.isEmpty() ? Integer.parseInt(staffIDStr) : null;
            
            java.sql.Timestamp currentTime = new java.sql.Timestamp(System.currentTimeMillis());
            // Create a new NewsComment object
            NewsComment newComment = new NewsComment();
            newComment.setNewsID(newsID);
            newComment.setCustomerID(customerID); // Can be null
            newComment.setStaffID(staffID); // Can be null
            newComment.setCommentText(commentText);
            newComment.setCommentDate(currentTime); // Current time as comment date
            
            // Initialize DAO and add the comment
            NewsCommentDAO newsCommentDAO = new NewsCommentDAO();
            boolean result = newsCommentDAO.addNewsComment(newComment);
            
            // Redirect or forward based on the result
            if (result) {
                // Redirect to the same news detail page to see the new comment
                response.sendRedirect("NewsDetail?id=" + newsID);
            } else {
                // Handle the error (e.g., show an error message)
                request.setAttribute("errorMessage", "Failed to add comment.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response); // Forward to an error page
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewsDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
