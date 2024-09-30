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
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;

/**
 *
 * @author PC
 */
public class NewsDelete extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(NewsDelete.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String newsIDParam = request.getParameter("id");

        if (newsIDParam != null && !newsIDParam.isEmpty()) {
            try {
                int newsID = Integer.parseInt(newsIDParam);
                NewsDAO newsDAO = new NewsDAO();
                boolean isDeleted = newsDAO.deleteByNewsID(newsID);

                if (isDeleted) {
                    // If deletion is successful
                    String successMessage = "News deleted successfully with ID: " + newsID;
                    LOGGER.log(Level.INFO, successMessage);
                    request.setAttribute("message", successMessage);  // Set success message in request attribute
                } else {
                    // If deletion fails
                    String errorMessage = "Failed to delete News with ID: " + newsID;
                    LOGGER.log(Level.WARNING, errorMessage);
                    request.setAttribute("message", errorMessage);  // Set error message in request attribute
                }

            } catch (NumberFormatException e) {
                // Invalid newsID format
                String errorMessage = "Invalid newsID format: " + newsIDParam;
                LOGGER.log(Level.SEVERE, errorMessage, e);
                request.setAttribute("message", errorMessage);
            }
        } else {
            // No newsID provided
            String errorMessage = "No newsID provided in the request.";
            LOGGER.log(Level.WARNING, errorMessage);
            request.setAttribute("message", errorMessage);
        }

        // Forward the request to the JSP page
        request.getRequestDispatcher("newsmanage").forward(request, response);
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
