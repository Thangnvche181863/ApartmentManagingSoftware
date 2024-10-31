<%-- 
    Document   : forbiddenpage
    Created on : Oct 7, 2024, 3:13:44 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&family=Inter:slnt,wght@-10..0,100..900&display=swap" rel="stylesheet">

        <link href="${requestScope.url}css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #ffffff;
                text-align: center;
            }
            .container {
                max-width: 600px;
                margin: auto;
                background-color: #ffffff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                font-size: 72px;
                color: #1c73e8;
            }
            p {
                font-size: 18px;
                color: #333;
            }
            .button {
                padding: 10px 20px;
                background-color: #1c73e8;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                font-weight: 600;
                text-decoration: none;
            }
            .button:hover {
                background-color: #155bb5;
                color: white;
            }
            img {
                max-width: 100%;
                height: auto;
            }
        </style>
    </head>
    <body>
        <div>
            <img src="${requestScope.url}img/403page.jpg" width="500px" height="1000px" alt="403 error Forbidden image"/>
            <h1>403 Forbidden</h1>
            <h4>We’re sorry, but you don’t have permission to access this page.</h4>
            <h4>If you believe this is an error, please contact support.</h4>
            <br>
            <a href="${requestScope.goback}" class="button">${requestScope.buttonText}</a>
        </div>
    </body>
</html>
