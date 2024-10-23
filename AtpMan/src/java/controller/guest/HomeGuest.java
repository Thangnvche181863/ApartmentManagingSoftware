/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.guest;

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
public class HomeGuest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO newsDAO = new NewsDAO();
        NewsCategoryDAO ncDAO = new NewsCategoryDAO();

        List<News> teamMembers = newsDAO.getTeamMembers();
        List<News> banner = newsDAO.getBannerHomepage();
        List<News> managementFeature = newsDAO.getManagementFeature();
        List<News> residentFeature = newsDAO.getResidentFeature();

        request.setAttribute("resident", residentFeature);
        request.setAttribute("management", managementFeature);
        request.setAttribute("teamMembers", teamMembers);
        request.setAttribute("banner", banner);
        List<NewsCategory> categories = ncDAO.getNewscategoryGreaterThan10();
        request.setAttribute("categories", categories);

        for (NewsCategory category : categories) {
            List<News> newsList = newsDAO.getNewsByCategoryId(category.getNewsCategoryID());
            category.setNewsList(newsList); // Assuming you have a setNewsList method in your NewsCategory model
        }

        request.getRequestDispatcher("home.jsp").forward(request, response);
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
