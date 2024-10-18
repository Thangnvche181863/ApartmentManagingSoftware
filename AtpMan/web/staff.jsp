<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Staff - Dashboard</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
              rel="stylesheet">
        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
    </head>

    <style>
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
    <body id="page-top">

        <!-- Page Wrapper -->
        <%@include file="sidebar.jsp" %>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <%@include file="topbar.jsp" %>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Danh Sách Nhân viên</h1>
                        <p>
                            <a class="btn btn-info" href="registlist">Danh Sách Đăng Kí</a>
                        </p>
                        <p>
                            <a class="btn btn-primary" href="serviceadd">Thêm Nhân viên</a>
                        </p>
                    </div>

                    <div class="form-container">
                        <form action="servicelist" method="POST">
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
                                            <select name="recordsPerPage" id="recordsPerPage" onchange="this.form.submit()" style="width: 80px;">
                                                <option value="10">10</option>
                                                <option value="25" <c:if test="${recordsPerPage == 25}">selected</c:if>>25</option>
                                                <option value="50" <c:if test="${recordsPerPage == 50}">selected</c:if>>50</option>
                                                <option value="100" <c:if test="${recordsPerPage == 100}">selected</c:if>>100</option>
                                                </select>
                                            </td>
                                            <td>
                                                <select name="type" id="type" onchange="this.form.submit()" style="width: 100px;">
                                                    <option value="All">All</option>
                                                <c:forEach items="${serviceType}" var="ls">
                                                    <option value="${ls.type}" <c:if test="${type == ls.type}">selected</c:if>>${ls.type}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="text" value="${search}" name="search" class="search-box" placeholder="Nhập từ khóa..." onchange="this.form.submit()"/>
                                        </td>
                                        
                                            <td class="total-label text-center" style="width: 130px;">
                                            <%=  (Integer) request.getAttribute("totalservice") %> Nhân viên
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>


                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <div class="pagination">
                                        <div class="pagination">
                                            <%
                                                int currentPage = (Integer) request.getAttribute("currentPage");
                                                int totalPages = (Integer) request.getAttribute("totalPages");

                                                // Hiển thị nút "Previous" nếu không phải trang đầu tiên
                                                if (currentPage > 1) {
                                            %>
                                            <a href="servicelist?page=<%= currentPage - 1 %>">Previous</a>
                                            <%
                                                }

                                                // Hiển thị danh sách các trang
                                                for (int i = 1; i <= totalPages; i++) {
                                                    if (i == currentPage) {
                                            %>
                                            <strong><%= i %></strong>
                                            <%
                                                    } else {
                                            %>
                                            <a href="servicelist?page=<%= i %>"><%= i %></a>
                                            <%
                                                    }
                                                }

                                                // Hiển thị nút "Next" nếu không phải trang cuối cùng
                                                if (currentPage < totalPages) {
                                            %>
                                            <a href="servicelist?page=<%= currentPage + 1 %>">Next</a>
                                            <%
                                                }
                                            %>
                                    </div>
                                    <table class="table table-bordered"  width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Mã nhân viên</th>
                                                <th>Họ và tên</th>
                                                <th>Chức vụ</th>
                                                <th>Email</th>
                                                <th>Phone number</th>
                                                <th class="action text-center">Chức Năng</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listStaff}" var="ls">
                                                <tr>
                                                    <td>${ls.staffID}</td>
                                                    <td>${ls.name}</td>
                                                    <td>${ls.roleID}</td>
                                                    <td>${ls.email}</td>
                                                    <td>${ls.phoneNumber}</td>
                                                    <td>
                                                        
                                                        <a href="#" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#dismissModal-${ls.staffID}">
                                                            Sa thải
                                                        </a>

                                                       
                                                        <div class="modal fade" id="dismissModal-${ls.staffID}" tabindex="-1" role="dialog"
                                                             aria-labelledby="dismissModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog" role="document">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title" id="dismissModalLabel">Xác nhận</h5>
                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                            <span aria-hidden="true">&times;</span>
                                                                        </button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        Bạn có chắc chắn muốn sa thải nhân viên này không?
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Không</button>

                                                                        <form action="staff" method="post" class="d-inline">
                                                                            <input type="hidden" name="staffID" value="${ls.staffID}" />
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

                    </div>



                </div>
                <!-- /.container-fluid -->
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>
    </body>

</html>


<!--<h2 class="text-center">Danh sách nhân viên</h2>

            Bảng quản lý nhân viên
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Mã nhân viên</th>
                        <th>Họ và tên</th>
                        <th>Chức vụ</th>
                        <th>Email</th>
                        <th>Điện thoại</th>
                        <th>Sa thải?</th>
                     
                    </tr>
                </thead>
                <tbody>
<c:forEach var="employee" items="${listStaff}">
    <tr>
        <td>${employee.staffID}</td>
        <td>${employee.name}</td>
        <td>${employee.roleID}</td>
        <td>${employee.email}</td>
        <td>${employee.phoneNumber}</td>
        <td>
             Button trigger modal 
            <a href="#" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#dismissModal-${employee.staffID}">
                Sa thải
            </a>

             Modal 
            <div class="modal fade" id="dismissModal-${employee.staffID}" tabindex="-1" role="dialog"
                aria-labelledby="dismissModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="dismissModalLabel">Xác nhận</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Bạn có chắc chắn muốn sa thải nhân viên này không?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Không</button>

                            <form action="staff" method="post" class="d-inline">
                                <input type="hidden" name="staffID" value="${employee.staffID}" />
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
</table>-->