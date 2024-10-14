/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAO.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import model.News;

/**
 *
 * @author ADMIN
 */
public class UserhomeNewsServlet extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 5;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        NewsDAO newsDAO = new NewsDAO();

        //get current page from the request
        String pageParam = request.getParameter("page");
        int currentPage;
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        } else {
            currentPage = 1;
        }

        int totalRows = newsDAO.getNumberOfRows();
        //calculate totalPages
        int totalPages = (int) Math.ceil((double) totalRows / RECORDS_PER_PAGE);

        List<News> newsList = newsDAO.getNewsByPage(currentPage, RECORDS_PER_PAGE);
        List<News> bannerList = newsDAO.getNewsForBanner();

//        request.setAttribute("newsBanner", bannerList);
//        request.setAttribute("news", newsList);
//        request.setAttribute("currentPage", currentPage);
//        request.setAttribute("totalPages", totalPages);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        PrintWriter out = response.getWriter();
        out.println("<input id=\"cpage\" type=\"hidden\" value=\""+ currentPage +"\" />");
        out.println("<div class=\"col-lg-9 col-md-12 col-sm-12 col-xs-12\">\n"
                + "      <div class=\"blog-top clearfix\">\n"
                + "          <h4 class=\"pull-left\">Recent News <a href=\"#\"><i class=\"fa fa-rss\"></i></a></h4>\n"
                + "      </div><!-- end blog-top -->\n"
                + "  <div class=\"blog-list clearfix\">\n");
        for (News news : newsList) {
            out.println("<div class=\"blog-box row\">\n"
                    + "     <div class=\"col-md-4\">\n"
                    + "        <div class=\"post-media\">\n"
                    + "           <a href=\"NewsDetail?id=" + news.getNewsID() + "\" title=\"\">\n"
                    + "              <img src=\"../" + news.getNewsImg() + "\" alt=\"\" class=\"img-fluid\">\n"
                    + "                  <div class=\"hovereffect\"></div>\n"
                    + "           </a>\n"
                    + "        </div><!-- end media -->\n"
                    + "     </div><!-- end col -->\n"
                    + "  <div class=\"blog-meta big-meta col-md-8\">\n"
                    + "  <h4><a href=\"NewsDetail?id=" + news.getNewsID() + "\" title=\"\">\n"
                    + "      " + news.getNewsTitle() + "\n"
                    + "      </a></h4>\n"
                    + "  <p>" + news.getNewsContent() + "</p>\n"
                    + "  <small class=\"firstsmall\"><a class=\"bg-orange\" href=\"#\" title=\"\">" + news.getNewsCategoryName() + " - </a></small>\n"
                    + "  <small>\n"
                    + "    "+ formatter.format(news.getPostDate()) +"\n"
                    + "  </small>\n"
                    + "  <small>  by " + news.getStaffName() + "</small>\n"
                    + "  </div><!-- end meta -->\n"
                    + "  </div><!-- end blog-box -->\n"
                    + "  <hr class=\"invis\">");
        }

        out.println("</div>\n"
                + "<div class=\"row\">\n"
                + "                                            <div class=\"col-md-12\">\n"
                + "                                                <nav aria-label=\"Page navigation\">\n"
                + "                                                    <ul class=\"pagination justify-content-start\">");
        if (currentPage > 1) {
            out.println("<li class=\"page-item\">\n"
                    + "   <button class=\"page-link\" value=\"" + (currentPage - 1) + "\" onclick=\"paging(this)\">Previous</button>\n"
                    + "   </li>");

        }

        for (int i = 1; i <= totalPages; i++) {
            if(i == currentPage){
                out.println("<li class=\"page-item active\">\n"
                    + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"paging(this)\">" + i + "</button>\n"
                    + " </li>");
            }else{
                out.println("<li class=\"page-item\">\n"
                    + "      <button class=\"page-link\" value=\"" + i + "\" onclick=\"paging(this)\">" + i + "</button>\n"
                    + " </li>");
            }
            
        }

        if (currentPage < totalPages) {
            out.println("<li class=\"page-item\">\n"
                    + "   <button class=\"page-link\" value=\"" + (currentPage + 1) + "\" onclick=\"paging(this)\">Previous</button>\n"
                    + "   </li>");

        }

        out.println("\n"
                + "                                                    </ul>\n"
                + "                                                </nav>\n"
                + "                                            </div><!-- end col -->\n"
                + "                                        </div><!-- end row -->\n"
                + "                                    </div>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
