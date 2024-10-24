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

/**
 *
 * @author PC
 */
public class NewsCategoryDelete extends HttpServlet {
   
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
     String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("message", "Invalid category ID.");
            request.getRequestDispatcher("newscategorymanage").forward(request, response);
            return;
        }

        int newsCategoryID;
        try {
            newsCategoryID = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid category ID format.");
            request.getRequestDispatcher("newscategorymanage").forward(request, response);
            return;
        }

        // Check if the newsCategoryID is <= 20
        if (newsCategoryID <= 10) {
            request.setAttribute("message", "This NewsCategory is part of the system and cannot be deleted.");
            request.getRequestDispatcher("newscategorymanage").forward(request, response);
            return;
        }

        // Call the DAO method to delete the NewsCategory
        NewsCategoryDAO ncDAO = new NewsCategoryDAO();
        boolean isDeleted = ncDAO.deleteNewsCategory(newsCategoryID);

        if (isDeleted) {
            request.setAttribute("message", "News category deleted successfully.");
        } else {
            request.setAttribute("message", "Failed to delete the news category or it does not exist.");
        }

        // Forward the request back to the manager page
        request.getRequestDispatcher("newscategorymanage").forward(request, response);
    }

    

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
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
