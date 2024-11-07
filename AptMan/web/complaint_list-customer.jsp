<%-- 
    Document   : complaint_list-customer
    Created on : Nov 6, 2024, 1:48:57 PM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách yêu cầu của bạn</title>

        <style>
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                font-family: Arial, sans-serif;
            }

            th, td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            thead th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            select {
                width: 80%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            form label {
                margin-right: 10px;
                font-weight: bold;
            }

            .form-container {
                max-width: 600px;
                margin: 0 auto;
            }

            td:last-child {
                font-weight: bold;
                color: #333;
            }

            .total-label {
                font-weight: bold;
                color: #666;
            }

            .search-box {
                padding: 8px;
                width: 100%;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .pagination {
                font-size: 0.8em; /* Adjust font size as needed */
                margin: 0;
                padding: 0;
                list-style: none;
            }

            .pagination a {
                padding: 5px 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
                text-decoration: none;
                color: #333;
            }

            .pagination a:hover {
                background-color: #f0f0f0;
            }

            .pagination strong {
                padding: 5px 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
                background-color: #f0f0f0;
                color: #333;
            }
        </style>

    </head>
    <body>
        <%@include file="sidebar.jsp" %>
        <div class="container">
            <h1>Danh sách yêu cầu của bạn</h1>
            <table>
                <tr>
                    <th>Tên yêu cầu</th>
                    <th>Nội dung</th>
                    <th>Trạng thái</th>
                    <th>Loại</th>
                    <th>Ngày yêu cầu</th>
                </tr>
                <c:forEach var="complaint" items="${customerComplaints}">
                    <tr>
                        <td>${complaint.title}</td>
                        <td>${complaint.description}</td>
                        <td>
                            <c:choose>
                                <c:when test="${complaint.status == 0}">Chưa hoàn thành</c:when>
                                <c:otherwise>Đã hoàn thành</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${complaint.type}</td>
                        <td><fmt:formatDate value="${complaint.dateRequested}" pattern="dd/MM/yyyy" /></td>
                    </tr>
                </c:forEach>
            </table>
            
            
        </div>
        
         <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="js/sb-admin-2.min.js"></script>
            <!-- Page level plugins -->
            <script src="vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!-- Page level custom scripts -->
            <script src="js/demo/datatables-demo.js"></script>
    </body>
</html>

