/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import DAO.NewsCategoryDAO;
import DAO.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.News;
import model.NewsCategory;

/**
 *
 * @author PC
 */
public class NewsCategoryManager extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        NewsCategoryDAO ncDAO = new NewsCategoryDAO();
        String searchParam = request.getParameter("search");
        if (searchParam != null) {
            searchParam = searchParam.trim().replaceAll("\\s+", " ");
        }

        int currentPage = 1;

        try {
            List<NewsCategory> newsCateList;
            int totalRows;

            String pageParam = request.getParameter("page");
            if (pageParam != null) {
                currentPage = Integer.parseInt(pageParam);
            }

            if (searchParam != null && !searchParam.isEmpty()) {
                totalRows = ncDAO.getNumberOfRowsByName(searchParam);
                newsCateList = ncDAO.getNewsCategoryByPageAndName(searchParam, currentPage, RECORDS_PER_PAGE);
            } else {
                totalRows = ncDAO.getNumberOfRows();
                newsCateList = ncDAO.getNewsCategoryByPage(currentPage, RECORDS_PER_PAGE);
            }

            int totalPages = (int) Math.ceil((double) totalRows / RECORDS_PER_PAGE);

            request.setAttribute("newsCategory", newsCateList);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("search", searchParam); // Add search parameter to request
            request.getRequestDispatcher("newscategorymanager.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("message", "An error occurred while processing your request: " + e.getMessage());
            response.sendRedirect("newscategorymanager.jsp");
        } catch (Exception e) {

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
