<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Danh Sách Công Việc</title>

        <!-- Custom fonts and stylesheets -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,300,400,600,700" rel="stylesheet">
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>

    <style>
        /* Add your custom styles here */
        table {
            width: 110%;
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
            width: 100%;
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

        /* Styling for pagination */
        .pagination {
            font-size: 0.9em;
            margin-top: 10px;
        }

        .pagination a {
            padding: 5px 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            text-decoration: none;
            color: #333;
        }

        .pagination strong {
            padding: 5px 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            background-color: #f0f0f0;
            color: #333;
        }

    </style>

    <body id="page-top">
        <div id="wrapper">
            <%@include file="sidebar.jsp" %>

            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <%@include file="topbar.jsp" %>

                    <div class="container-fluid">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Công việc chưa giao
                                <a class="btn btn-primary" href="assignment">Chưa giao</a>
                                <a class="btn btn-primary" href="task">Đã giao</a>
                            </h1>
                            <a class="btn btn-primary" href="createTask.jsp">Thêm công việc</a>
                            <!-- Hiển thị thông báo nếu có -->
                            <c:if test="${not empty sessionScope.mess}">
                                <div class="alert alert-success" role="alert">
                                    ${sessionScope.mess}
                                </div>
                                <c:remove var="mess" scope="session"/> <!-- Xóa thông báo sau khi hiển thị -->
                            </c:if>
                        </div>

                        <!-- Form lọc công việc -->
                        <div class="form-container">
                            <form action="assignment" method="POST">
                                <table>
                                    <thead>
                                        <tr>
                                            <th class="text-center">Số Dòng</th>
                                            <th class="text-center">Phân Loại</th>
                                            <th class="text-center">Tìm Kiếm</th>
                                            <th class="text-center">Tổng</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <select name="recordsPerPage" onchange="this.form.submit()" style="width: 80px;">
                                                    <option value="10" <c:if test="${recordsPerPage == 10}">selected</c:if>>10</option>
                                                    <option value="25" <c:if test="${recordsPerPage == 25}">selected</c:if>>25</option>
                                                    <option value="50" <c:if test="${recordsPerPage == 50}">selected</c:if>>50</option>
                                                    <option value="100" <c:if test="${recordsPerPage == 100}">selected</c:if>>100</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select name="taskType" onchange="this.form.submit()" style="width: 100px;">
                                                        <option value="0" <c:if test="${selectedTaskType == '0'}">selected</c:if>>Tất cả</option>
                                                    <c:forEach items="${taskType}" var="type">
                                                        <option value="${type}" <c:if test="${type == selectedTaskType}">selected</c:if>>${type}</option>
                                                    </c:forEach>
                                                </select>

                                            </td>
                                            <td>
                                                <input type="text" value="${search}" name="search" class="search-box" placeholder="Nhập từ khóa..." onchange="this.form.submit()" />
                                            </td>
                                            <td class="total-label text-center">
                                                ${totalTask} Công việc
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>

                        <!-- Bảng công việc -->
                        <div class="card shadow mb-4">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <div class="pagination">
                                        <c:if test="${currentPage > 1}">
                                            <a href="assignment?page=${currentPage - 1}">Previous</a>
                                        </c:if>
                                        <c:forEach begin="1" end="${totalPages}" var="i">
                                            <c:choose>
                                                <c:when test="${i == currentPage}">
                                                    <strong>${i}</strong>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="assignment?page=${i}">${i}</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:if test="${currentPage < totalPages}">
                                            <a href="assignment?page=${currentPage + 1}">Next</a>
                                        </c:if>
                                    </div>

                                    <table class="table table-bordered" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Mã công việc</th>
                                                <th>Tên</th>
                                                <th>Mô tả</th>
                                                <th>Loại công việc</th>
                                                <th class="text-center">Giao việc</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listTask}" var="ls">
                                                <tr>
                                                    <td>${ls.taskID}</td>
                                                    <td>${ls.taskName}</td>
                                                    <td>${ls.description}</td>
                                                    <td>${ls.taskType}</td>
                                                    <td class="text-center">
                                                        <a href="#" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#assignTaskModal-${ls.taskID}">
                                                            Giao việc
                                                        </a>
                                                    </td>
                                                </tr>
                                                <!-- Modal giao việc -->
                                            <div class="modal fade" id="assignTaskModal-${ls.taskID}" tabindex="-1" role="dialog">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title">Chọn nhân viên giao việc cho công việc ${ls.taskName}</h5>
                                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form action="assignment" method="POST">
                                                                <input type="hidden" name="taskType" value="${ls.taskType}" />
                                                                <input type="hidden" name="taskID" value="${ls.taskID}" />
                                                                <input type="hidden" name="service" value="assign" />

                                                                <!-- Danh sách nhân viên -->
                                                                <select name="staffID" class="form-control">
                                                                    <c:forEach items="${listStaff}" var="o">
                                                                        <c:if test="${ls.taskType == o.roleAuthority}">
                                                                            <option value="${o.staffID}">${o.name}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </select>

                                                                <!-- Người dùng nhập số ngày cho endTime -->
                                                                <label for="endDays">Nhập số ngày để hoàn thành công việc:</label>
                                                                <input type="number" id="endDays" name="endDays" class="form-control" min="1" placeholder="Nhập số ngày" required>

                                                                <p class="mt-2">Bạn có chắc muốn giao công việc này cho nhân viên đã chọn?</p>

                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Không</button>
                                                                    <button type="submit" class="btn btn-primary">Giao việc</button>
                                                                </div>
                                                            </form>

                                                        </div>
                                                    </div>
                                                </div>

                                            </c:forEach>
                                            </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>


        <!-- Scripts -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
        <script src="js/sb-admin-2.min.js"></script>
    </body>
</html>
