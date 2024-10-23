/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.staff;

import DAO.NewsCategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.NewsCategory;

/**
 *
 * @author PC
 */
public class AddNewsCategory extends HttpServlet {
   
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      String name = request.getParameter("name");
      String description = request.getParameter("description");
      NewsCategory nc = new NewsCategory(0,name,description);
      NewsCategoryDAO ncDAO = new NewsCategoryDAO();
      
      boolean isAdded = ncDAO.addNewsCategory(nc);
      
      if (isAdded) {
            response.sendRedirect("newscategorymanage");
        } else {
            String result = "Failed to add News!";
            request.setAttribute("message", result);
            request.getRequestDispatcher("addnews.jsp").forward(request, response);
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
