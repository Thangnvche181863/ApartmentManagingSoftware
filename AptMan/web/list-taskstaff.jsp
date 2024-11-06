<%-- 
    Document   : list-taskstaff
    Created on : Nov 6, 2024, 10:28:38 PM
    Author     : WuanTun
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Tasks của tôi</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <h2>Danh sách Task được giao</h2>

        <table border="1">
            <tr>
                <th>Tên Task</th>
                <th>Loại</th>
                <th>Mô tả</th>
            </tr>
            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td>${task.taskName}</td>
                    <td>${task.taskType}</td>
                    <td>${task.description}</td>
                    
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
