<%-- 
    Document   : editNews
    Created on : Oct 14, 2024, 5:12:56 PM
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
        <title>Edit News</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- Include TinyMCE -->
        <script src="https://cdn.tiny.cloud/1/n0b2uh23r0ya9qhhy07odsf6v4qhzjpn6aoav7c4rzx6ocd4/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>

        <script>
            tinymce.init({
                selector: '#newsContent',
                plugins: 'image link media',
                toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent | link image media',
                height: 300
            });
        </script>
    </head>
    <body>
        <div class="container mt-5">
            <center><h2>Edit News</h2></center> 
            <!-- Display success or error message -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
            <c:if test="${not empty success}">
                <div class="alert alert-success">${success}</div>
            </c:if>
            <form id="newsForm" action="EditNews" method="post" enctype="multipart/form-data">
                <input type="hidden" name="newsId" value="${requestScope.news.newsID}" />  <!-- This should hold a valid ID -->

                <div class="form-group">
                    <label for="newsTitle">News Title:</label>
                    <input type="text" class="form-control" id="newsTitle" name="newsTitle" value="${news.newsTitle}" required>
                </div>
                
                <div class="form-group">
                    <label for="newsDescription">News Description: </label>
                    <input type="text" class="form-control" id="newsDescription" name="newsDescription" value="${news.description}" >
                </div>

                <!-- File input for image thumbnail -->
                <div class="form-group">
                    <label for="newsImg">News Thumbnail (Optional):</label>
                    <input type="file" class="form-control-file" id="newsImg" name="newsImg" accept="image/*">
                    <!-- Display current thumbnail if available -->
                    <c:if test="${news.newsImg != null && !news.newsImg.isEmpty()}">
                        <img src="${news.newsImg}" alt="Current Thumbnail" style="max-width: 150px; margin-top: 10px;">
                    </c:if>
                </div>

                <div class="form-group">
                    <label for="newsCategory">News Category:</label>
                    <select class="form-control" id="newsCategory" name="newsCategory">
                        <c:forEach var="category" items="${newsCategories}">
                            <option value="${category.newsCategoryID}" 
                                    <c:if test="${category.newsCategoryID == news.newsCategoryID}">selected</c:if>>${category.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="newsContent">News Content:</label>
                    <textarea class="form-control" id="newsContent" name="newsContent" rows="10">${news.newsContent}</textarea>
                </div>




                <input type="submit"  class="btn btn-primary" value="Update" />
            </form>
        </div>
        <script>
            document.getElementById("newsForm").addEventListener("submit", function (event) {
                const fileInput = document.getElementById("newsImg");
                const filePath = fileInput.value;
                const allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;

                if (filePath) {
                    if (!allowedExtensions.exec(filePath)) {
                        alert("Please upload a valid image file (jpg, jpeg, png, gif).");
                        fileInput.value = ''; // Clear the input
                        event.preventDefault(); // Prevent form submission
                    }
                }
            });
        </script>
    </body>
</html>
