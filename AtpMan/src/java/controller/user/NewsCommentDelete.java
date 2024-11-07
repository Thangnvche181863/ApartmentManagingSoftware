/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAO.NewsCommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class NewsCommentDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newsCommentIDParam = request.getParameter("id");

        if (newsCommentIDParam != null || newsCommentIDParam.isEmpty()) {
            try {
                int newsCommentID = Integer.parseInt(newsCommentIDParam);
                NewsCommentDAO dao = new NewsCommentDAO();
                boolean isDeleted = dao.deleteCommentById(newsCommentID);

                if (isDeleted) {
                    // If deletion is successful
                    String successMessage = "Comment deleted successfully with ID: " + newsCommentID;

                    request.setAttribute("message", successMessage);  // Set success message in request attribute
                } else {
                    // If deletion fails
                    String errorMessage = "Failed to delete comment with ID: " + newsCommentID;

                    request.setAttribute("message", errorMessage);  // Set error message in request attribute
                }
            } catch (NumberFormatException e) {

                String errorMessage = "Invalid newsID format: " + newsCommentIDParam;
                request.setAttribute("message", errorMessage);
            }
        } else {
            String errorMessage = "No newsID provided in the request.";

            request.setAttribute("message", errorMessage);
        }

        // Get the URL of the page that referred to this servlet
        String referer = request.getHeader("referer");

        if (referer != null) {
            response.sendRedirect(referer); // Preserves query parameters
        } else {
            // Redirect to a default page if referer is null
            response.sendRedirect("News"); // Replace with an appropriate fallback page
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
