<%-- 
    Document   : complaint_list-customer
    Created on : Nov 8, 2024, 11:17:58 AM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&family=Inter:slnt,wght@-10..0,100..900&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
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
        <div id="wrapper">
            <!-- Sidebar -->
            <jsp:include page="./sidebar.jsp"/>
            <!-- End of Sidebar -->
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <!-- Topbar -->
                    <jsp:include page="./topbar.jsp"/>
                    <!-- End of Topbar -->
                    <div class="container">
                        <h1>Danh sách yêu cầu của bạn</h1>
                        <table>
                            <tr>
                                <th>Tên yêu cầu</th>
                                <th>Nội dung</th>
                                <th>Trạng thái</th>
                                <!--                    <th>Loại</th>-->
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
        <!--                        <td>${complaint.type}</td>-->
                                <td><fmt:formatDate value="${complaint.dateResquested}" pattern="dd/MM/yyyy" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="pagination-container" style="margin-top: 20px; text-align: center;">
                            <c:if test="${totalPages > 1}">
                                <ul class="pagination" style="display: inline-flex; list-style: none; gap: 10px;">
                                    <%-- Previous page link --%>
                                    <c:if test="${currentPage > 1}">
                                        <li>
                                            <a href="?page=${currentPage - 1}" style="text-decoration: none; color: #007bff;">
                                                &laquo; Trang trước
                                            </a>
                                        </li>
                                    </c:if>

                                    <%-- Page numbers --%>
                                    <c:forEach begin="1" end="${totalPages}" var="pageNum">
                                        <li>
                                        <c:choose>
                                            <c:when test="${pageNum == currentPage}">
                                                <strong style="padding: 5px 10px; background-color: #007bff; color: white; border-radius: 3px;">
                                                    ${pageNum}
                                                </strong>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="?page=${pageNum}" style="text-decoration: none; color: #007bff; padding: 5px 10px;">
                                                    ${pageNum}
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                        </li>
                                    </c:forEach>

                                    <%-- Next page link --%>
                                    <c:if test="${currentPage < totalPages}">
                                        <li>
                                            <a href="?page=${currentPage + 1}" style="text-decoration: none; color: #007bff;">
                                                Trang sau &raquo;
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </c:if>
                        </div>

                    </div>
                </div>
            </div>


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
