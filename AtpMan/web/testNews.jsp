<%-- 
    Document   : test
    Created on : Sep 20, 2024, 10:29:30 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Test News Servlet</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>News List - Test Page</h1>
        
        <!-- Display total pages and current page -->
        <div class="alert alert-info">
            <strong>Current Page:</strong> ${currentPage}<br>
            <strong>Total Pages:</strong> ${totalPages}
        </div>
        
        <!-- Display the news list -->
        <c:if test="${not empty news}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>News ID</th>
                        <th>Title</th>
                        <th>Content</th>
                        <th>Post Date</th>
                        <th>Image</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="newsItem" items="${news}">
                        <tr>
                            <td>${newsItem.newsID}</td>
                            <td>${newsItem.newsTitle}</td>
                            <td>${newsItem.newsContent}</td>
                            <td>${newsItem.postDate}</td>
                            <td><img src="${newsItem.newsImg}" alt="News Image" width="100"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${empty news}">
            <div class="alert alert-warning">No news found!</div>
        </c:if>
        
        <!-- Pagination links -->
        <nav>
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="News?page=${currentPage - 1}">Previous</a>
                    </li>
                </c:if>
                
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="News?page=${i}">${i}</a>
                    </li>
                </c:forEach>
                
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="News?page=${currentPage + 1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</body>
</html>
