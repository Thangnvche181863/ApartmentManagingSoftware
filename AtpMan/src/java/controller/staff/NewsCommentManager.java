/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.NewsCommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.NewsComment;

/**
 *
 * @author PC
 */
public class NewsCommentManager extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 6;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsCommentDAO dao = new NewsCommentDAO();
        int currentPage = 1; // Default to the first page

        // Get current page from request parameter
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1; // Reset to first page if there's a parsing error
            }
        }

        try {
            int totalRows = dao.getTotalReportedComments(); // Get total reported comments
            int totalPages = (int) Math.ceil((double) totalRows / RECORDS_PER_PAGE); // Calculate total pages

            // Fetch comments for the current page
            List<NewsComment> commentsList = dao.getReportedCommentWithPagination(currentPage, RECORDS_PER_PAGE);

            // Set attributes for the JSP
            request.setAttribute("comments", commentsList);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);
            request.getRequestDispatcher("newsCommentManager.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            response.sendRedirect("forbiddenpage.jsp");
        }
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Retrieve commentID from the request
    String commentIDParam = request.getParameter("commentID");
    int commentID = 0;

    // Validate and parse the commentID
    try {
        commentID = Integer.parseInt(commentIDParam);
    } catch (NumberFormatException e) {
        // Handle invalid commentID (not a number)
        request.setAttribute("error", "Invalid comment ID.");
        request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        return;
    }

    // Initialize the DAO and cancel the reported comment
    NewsCommentDAO newsCommentDAO = new NewsCommentDAO();
    boolean isCanceled = newsCommentDAO.cancelReportedComment(commentID);

    // Handle success or failure
    if (isCanceled) {
        request.setAttribute("message", "Bỏ Qua Báo Cáo Thành Công.");
    } else {
        request.setAttribute("message", "Bỏ Qua Báo Cáo Thất Bại.");
    }

     response.sendRedirect("newscommentmanage");
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
