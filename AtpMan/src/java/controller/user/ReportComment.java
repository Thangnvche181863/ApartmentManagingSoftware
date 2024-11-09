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
public class ReportComment extends HttpServlet {
   
   

    
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Retrieve commentID from request parameter
    String idParam = request.getParameter("id");
    
    if (idParam != null) {
        try {
            int commentID = Integer.parseInt(idParam);
            NewsCommentDAO commentDAO = new NewsCommentDAO(); 
            
            // Use DAO method to report the comment
            boolean isReported = commentDAO.reportComment(commentID);
            
            // Determine redirect URL based on report status
            String referer = request.getHeader("referer");
            
            if (isReported) {
                String reportMessage = "Successfully Reported";
                // Preserve query parameters from referer
                String redirectUrl = referer != null ? referer : "homepageGuest";
                
                // Append the report message and the original query string if it exists
                String originalQuery = request.getQueryString(); // Get original query string
                if (originalQuery == null) {
                    originalQuery = ""; // Initialize if null
                } else {
                    originalQuery += "&"; // Add an '&' for the next parameter
                }
                
                // Append the report message as a query parameter
                String redirectWithParams = redirectUrl + (redirectUrl.contains("?") ? "&" : "?") 
                                            + originalQuery + "ReportMessage=" + reportMessage;

                response.sendRedirect(redirectWithParams); // Redirect to the referer with parameters
            } else {
                // Redirect to forbiddenpage.jsp if the report failed
                response.sendRedirect("forbiddenpage.jsp");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid comment ID format.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while reporting the comment.");
        }
    } else {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing comment ID.");
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
