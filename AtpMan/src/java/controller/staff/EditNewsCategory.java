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
public class EditNewsCategory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        NewsCategoryDAO dao = new NewsCategoryDAO();
        NewsCategory cat = dao.getByID(id);

        request.setAttribute("cat", cat);
        request.getRequestDispatcher("editNewsCategory.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("categoryID");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        int id = Integer.parseInt(idStr);
        NewsCategoryDAO dao = new NewsCategoryDAO();
        boolean isUpdated = dao.updateCategory(id, name, description);

        if (isUpdated) {
            request.setAttribute("message", "Thành công sửa mục tin.");
        } else {
            request.setAttribute("message", "Lỗi đã xảy ra khi sửa mục tin.");
        }
        response.sendRedirect("newscategorymanage");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
