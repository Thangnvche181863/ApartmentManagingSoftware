<%-- 
    Document   : feedback-customer
    Created on : Oct 16, 2024, 6:14:47 PM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Form Feedback Dịch Vụ</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fc;
                margin: 0;
                padding: 20px;
            }
            h2 {
                text-align: center;
                color: #4e73df;
            }
            form {
                max-width: 500px;
                margin: 0 auto;
                padding: 20px;
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            label {
                display: block;
                margin-bottom: 5px;
                color: #4e73df;
            }
            input[type="text"],
            textarea {
                width: 95%; /* Giữ chiều rộng là 90% */
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
            }
            textarea {
                height: 100px; /* Chiều cao cố định cho textarea */
                resize: vertical; /* Cho phép người dùng chỉ kéo dài chiều cao */
                max-height: 300px; /* Chiều cao tối đa cho textarea */
            }
            button {
                background-color: #4e73df;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s ease;
            }
            button:hover {
                background-color: #3a5cb1;
            }
        </style>

    </head>
    <body>
        <h2>Đánh giá sau sử dụng dịch vụ</h2>
        <form action="requestservlet" method="post">
            <label for="title">Title:</label>
            <input type="text" name="title" required>

            <label for="description">Description:</label>
            <textarea name="description" rows="5" required></textarea>
            <select name="type">
                <option value="" selected>Choose type</option>
                <option value="Sữa chữa">Sửa chữa</option>
                <option value="Vệ sinh">Vệ sinh</option>
                <option value="Khác">Khác</option>
            </select>
                
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
