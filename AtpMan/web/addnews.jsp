<%-- 
    Document   : addnews
    Created on : Sep 25, 2024, 5:22:52 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.io.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add News</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

         <!-- Include TinyMCE -->
        <script src="https://cdn.tiny.cloud/1/n0b2uh23r0ya9qhhy07odsf6v4qhzjpn6aoav7c4rzx6ocd4/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>

        <script>
            tinymce.init({
                selector: '#newsContent', // Target the textarea
                plugins: 'image link media',
                toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent | link image media',
                height: 300
            });

            // Ensure the TinyMCE content is saved before form submission
            $(document).ready(function () {
                $('#newsForm').on('submit', function (e) {
                    console.log("Form is being submitted...");
                    
                    tinymce.triggerSave(); // Update textarea with TinyMCE content

                    // Debug: Check if the textarea now has content
                    console.log("News content:", $('#newsContent').val());

                    if ($('#newsContent').val() === '') {
                        e.preventDefault();  // Prevent form submission if content is missing
                        alert("News content is empty!");
                    }
                });
            });
        </script>

    </head>
    <body>

        <div class="container mt-5">
            <center><h2>Add News</h2></center> 
                <% if (request.getAttribute("key") != null) { %>
            <div class="alert alert-danger text-center" role="alert">
                <%= request.getAttribute("key") %>
            </div>
            <% } %>
            <form id="newsForm" action="AddNews" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="newsTitle">News Title:</label>
                    <input type="text" class="form-control" id="newsTitle" name="newsTitle" required>
                </div>

                <div class="form-group">
                    <label for="newsContent">News Content:</label>
                    <textarea class="form-control" id="newsContent" name="newsContent" rows="10" ></textarea>
                </div>

                <div class="form-group">
                    <label for="newsImg">News Image:</label>
                    <input type="file" class="form-control-file" id="newsImg" name="newsImg" accept="image/*" >
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

        </div>

    </body>
</html>
