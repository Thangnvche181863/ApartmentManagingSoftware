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
</head>
<body>
    <h1>Danh sách yêu cầu của bạn</h1>
    <table>
        <tr>
            <th>Tiêu đề</th>
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
</body>
</html>

