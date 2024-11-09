/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

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
public class EditComment extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    } 

   
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    NewsCommentDAO commentDAO = new NewsCommentDAO();

   
    int commentID = Integer.parseInt(request.getParameter("commentID"));
    String commentText = request.getParameter("commentText");

    
    boolean isUpdated = commentDAO.updateComment(commentID, commentText);

    
    if (isUpdated) {
        request.getSession().setAttribute("successMessage", "Comment updated successfully.");
    } else {
        request.getSession().setAttribute("errorMessage", "Failed to update comment.");
        response.sendRedirect("forbiddenpage.url");
    }

    // Redirect back to the previous page (preserving any URL parameters)
    String referer = request.getHeader("Referer");
    if (referer != null && !referer.isEmpty()) {
        response.sendRedirect(referer);
    } 
}


    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
