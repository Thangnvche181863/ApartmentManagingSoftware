<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Danh Sách Nhân viên</title>

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
    <script>
        function setEditRoleData(staffID, roleID) {
            $('#staffID').val(staffID);
            $('#roleID').val(roleID); // Cập nhật giá trị cho roleID
        }
    </script>

    <body id="page-top">
        <!-- Include sidebar -->
        <%@include file="sidebar.jsp" %>

        <!-- Main content -->
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <!-- Include topbar -->
                <%@include file="topbar.jsp" %>

                <!-- Page content -->
                <div class="container-fluid">
                    <!-- Page heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Danh Sách Công Việc</h1>
                        <a class="btn btn-info" href="registlist">Danh Sách Đăng Kí</a>
                        <a class="btn btn-primary" href="#">Thêm công việc</a>
                    </div>

                    <!-- Form for filtering staff -->
                    <div class="form-container">
                        <form action="staff" method="POST">
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
                                                <select name="roleID" onchange="this.form.submit()" style="width: 100px;">
                                                    <option value="0" <c:if test="${roleID == 0}">selected</c:if>>Tất cả</option>
                                                <c:forEach items="${staffType}" var="ls">
                                                    <option value="${ls.roleID}" <c:if test="${roleID == ls.roleID}">selected</c:if>>${ls.role_name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="text" value="${search}" name="search" class="search-box" placeholder="Nhập từ khóa..." onchange="this.form.submit()" />
                                        </td>
                                        <td class="total-label text-center">
                                            ${AmountOfTask} Công việc
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>

                </div>

                <!-- Staff table -->
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <div class="table-responsive">
                            <!-- Pagination logic -->
                            <div class="pagination">
                                <% int currentPage = (Integer) request.getAttribute("currentPage");
                                   int totalPages = (Integer) request.getAttribute("totalPages");
                                   if (currentPage > 1) { %>
                                <a href="staff?page=<%= currentPage - 1 %>">Previous</a>
                                <% }
                                   for (int i = 1; i <= totalPages; i++) {
                                       if (i == currentPage) { %>
                                <strong><%= i %></strong>
                                <% } else { %>
                                <a href="staff?page=<%= i %>"><%= i %></a>
                                <% }
                                   }
                                   if (currentPage < totalPages) { %>
                                <a href="staff?page=<%= currentPage + 1 %>">Next</a>
                                <% } %>
                            </div>

                            <table class="table table-bordered" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>Mã nhân viên</th>
                                        <th>Họ và tên</th>
                                        <th>Chức vụ</th>
                                        <th>Email</th>
                                        <th>Số điện thoại</th>
                                        <th class="text-center">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listTask}" var="ls">
                                        <tr>
                                            <td>${ls.taskID}</td>
                                            <td>${ls.taskName}</td>
                                            <td>${ls.description}</td>
                                            <td>${ls.taskType}</td>
                                           

                                            <!-- Dismiss button -->
                                            <td class="text-center">
                                                <div class="d-flex justify-content-center">
                                                    <a href="#" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#dismissModal-${ls.taskID}">
                                                        Sa thải
                                                    </a>
                                                    <!--                                                    <button class="btn btn-warning btn-sm ml-2" data-toggle="modal" data-target="#editRoleModal" 
                                                                                                                data-staff-id="${ls.staffID}" data-role-id="${ls.roleID}" 
                                                                                                                onclick="setEditRoleData(${ls.staffID}, ${ls.roleID})">
                                                                                                            Chỉnh Sửa
                                                                                                        </button>-->
                                                </div>
                                            </td>



                                            <!-- Dismiss confirmation modal -->
                                    <div class="modal fade" id="dismissModal-${ls.taskID}" tabindex="-1" role="dialog">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Xác nhận</h5>
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                </div>
                                                <div class="modal-body">
                                                    Bạn có chắc chắn muốn sa thải nhân viên này không?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Không</button>
                                                    <form action="staff" method="post" class="d-inline">
                                                        <input type="hidden" name="staffID" value="${ls.taskID}" />
                                                        <input type="hidden" name="service" value="dismiss" />
                                                        <button type="submit" class="btn btn-danger">Sa thải</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!--Modal de sua vai tro cua nhan vien
                                <div class="modal fade" id="editRoleModal" tabindex="-1" role="dialog" aria-labelledby="editRoleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="editRoleModalLabel">Chỉnh Sửa Vai Trò Nhân Viên</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form id="editRoleForm" action="staff?service=edit" method="POST">
                                                    <input type="hidden" name="id" id="staffID" value="">
                                                    <div class="form-group">
                                                        <label for="roleID">Chọn Vai Trò</label>
                                                        <select name="roleID" id="roleID" class="form-control" required>
                <c:forEach items="${roleTypes}" var="role">
                    <option value="${role.roleID}">${role.role_name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật vai trò</button>
    </form>
</div>
</div>
</div>
</div>-->



            </div>
        </div>

        <!-- Scripts -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
        <script src="js/sb-admin-2.min.js"></script>
    </div>
</body>

</html>



