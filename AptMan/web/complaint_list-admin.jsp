<%-- 
    Document   : complaint_list-admin
    Created on : Oct 17, 2024, 12:08:03 AM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Complaint List</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fc;
                margin: 0;
                padding: 20px;
            }
            h1 {
                text-align: center;
                color: #4e73df;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #4e73df;
                color: white;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
        </style>
    </head>
    <body>
        <h1>Danh sách các yêu cầu</h1
        <form action="complaintlist" method="get">
            <input type="text" name="search" placeholder="Tìm kiếm" />
            <select name="sort">
                <option value="dateRequested">Sắp xếp theo ngày yêu cầu</option>
                <option value="customerName">Sắp xếp theo tên khách hàng</option>
                <option value="status">Sắp xếp theo trạng thái</option>
                <option value="type">Sắp xếp theo loại</option>
            </select>

            <button type="submit">Sắp xếp</button>
        </form>
        <table>
            <tr>
                <th>STT</th>
                <th>Tên khách hàng</th>
                <th>Tiêu đề</th>
                <th>Nội dung</th>
                <th>Trạng thái</th>
                <th>Loại</th>
                <th>Ngày yêu cầu</th>
                <th>Xác nhận</th>
            </tr>
            <c:forEach var="complaint" items="${complaints}">
                <tr>
                    <td>${complaint.requestID}</td>
                    <td>${complaint.customerName}</td>                  
                    <td>${complaint.title}</td>
                    <td>${complaint.description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${complaint.status == 0}">
                                Chưa hoàn thành
                            </c:when>
                            <c:otherwise>
                                Đã hoàn thành
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${complaint.type}</td>
                    <td>${complaint.dateRequested}</td>
                    <td>
                        <c:if test="${complaint.status == 0}">
                            <!-- Nút Chấp thuận -->
                            <form action="complaintlist" method="post">
                                <input type="hidden" name="requestID" value="${complaint.requestID}" />
                                <button type="submit" name="status" value="1">Chấp thuận</button>
                            </form>

                            <!-- Nút Từ chối gắn sự kiện mở popup -->
                            <button onclick="showRejectPopup(${complaint.requestID})">Từ chối</button>
                        </c:if>

                        <c:if test="${complaint.status == 1}">
                            <button disabled>Đã xác nhận</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <!-- Popup nhập lý do từ chối -->
        <!-- Popup nhập lý do từ chối -->
        <div id="rejectPopup" style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background-color: #fff; padding: 20px; border: 1px solid #ccc; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);">
            <form id="rejectForm" action="rejectRequest" method="post">
                <input type="hidden" id="rejectRequestID" name="requestID" />
                <label for="reason">Lý do từ chối:</label>
                <textarea id="reason" name="reason" required></textarea>
                <br />
                <button type="submit">Xác nhận từ chối</button>
                <button type="button" onclick="closeRejectPopup()">Hủy</button>
            </form>
        </div>

        <script>
            // Hiển thị popup từ chối
            function showRejectPopup(requestID) {
                document.getElementById('rejectRequestID').value = requestID;
                document.getElementById('rejectPopup').style.display = 'block';
            }

            // Đóng popup từ chối
            function closeRejectPopup() {
                document.getElementById('rejectPopup').style.display = 'none';
            }
        </script>
    </body>
</html>

