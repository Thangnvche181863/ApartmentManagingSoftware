<%-- 
    Document   : newscategoryadd
    Created on : Oct 23, 2024, 11:11:31 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <title>Add News Category</title>
    </head>
    <body>
        <div class="container mt-5">
            <center><h2>Add News</h2></center> 
                <% if (request.getAttribute("key") != null) { %>
            <div class="alert alert-danger text-center" role="alert">
                <%= request.getAttribute("key") %>
            </div>
            <% } %>
            <form id="newsForm" action="AddNewsCategory" method="post" >
                <div class="form-group">
                    <label for="newsTitle">News Category Name:</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>



                <div class="form-group">
                    <label for="newsTitle">News Category Description:</label>
                    <input type="text" class="form-control" id="description" name="description" >
                </div>



                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

        </div>
    </body>
</html>
