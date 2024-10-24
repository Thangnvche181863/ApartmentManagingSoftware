
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin 2 - Cards</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <!-- Icon Font Stylesheet -->
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
            />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
            rel="stylesheet"
            />
        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">


        <script>
            function confirmDelete() {
                return confirm("Sure delete?");
            }
        </script>
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

    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">APT MAN<sup>2</sup></div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item">
                    <a class="nav-link" href="managerPage">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Dashboard</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Interface
                </div>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item active">
                    <a class="nav-link" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true"
                       aria-controls="collapseTwo">
                        <i class="fas fa-fw fa-cog"></i>
                        <span>Components</span>
                    </a>
                    <div id="collapseTwo" class="collapse show" aria-labelledby="headingTwo"
                         data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Custom Components:</h6>
                            <a class="collapse-item" href="buttons.html">Buttons</a>
                            <a class="collapse-item active" href="cards.html">Cards</a>
                        </div>
                    </div>
                </li>

                <!-- Nav Item - Utilities Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                       aria-expanded="true" aria-controls="collapseUtilities">
                        <i class="fas fa-fw fa-wrench"></i>
                        <span>Utilities</span>
                    </a>
                    <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                         data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Custom Utilities:</h6>
                            <a class="collapse-item" href="utilities-color.html">Colors</a>
                            <a class="collapse-item" href="utilities-border.html">Borders</a>
                            <a class="collapse-item" href="utilities-animation.html">Animations</a>
                            <a class="collapse-item" href="utilities-other.html">Other</a>
                        </div>
                    </div>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Addons
                </div>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                       aria-expanded="true" aria-controls="collapsePages">
                        <i class="fas fa-fw fa-folder"></i>
                        <span>Pages</span>
                    </a>
                    <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Login Screens:</h6>
                            <a class="collapse-item" href="login.html">Login</a>
                            <a class="collapse-item" href="register.html">Register</a>
                            <a class="collapse-item" href="forgot-password.html">Forgot Password</a>
                            <div class="collapse-divider"></div>
                            <h6 class="collapse-header">Other Pages:</h6>
                            <a class="collapse-item" href="404.html">404 Page</a>
                            <a class="collapse-item" href="blank.html">Blank Page</a>
                        </div>
                    </div>
                </li>

                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="charts.html">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Charts</span></a>
                </li>

                <!-- Nav Item - Tables -->
                <li class="nav-item">
                    <a class="nav-link" href="servicelist">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Danh Sách Dịch Vụ</span></a>
                </li>


                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    <!-- Topbar -->
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    <!-- End of Topbar -->











                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Danh Sách Dịch Vụ</h1>
                            <p>
                                <a class="btn btn-info" href="registlist">Danh Sách Đăng Kí</a>
                            </p>
                            <p>
                                <a class="btn btn-primary" href="serviceadd?page=${currentPage}">Thêm Dịch Vụ</a>
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
                                            <th class="text-center">Sắp Xếp Phí</th>
                                            <th class="text-center">Tổng</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <select name="recordsPerPage" id="recordsPerPage" onchange="this.form.submit()" style="width: 80px;">
                                                    <option >10</option>
                                                    <option value="25" <c:if test="${recordsPerPage == 25}">selected</c:if>>25</option>
                                                    <option value="50" <c:if test="${recordsPerPage == 50}">selected</c:if>>50</option>
                                                    <option value="100" <c:if test="${recordsPerPage == 100}">selected</c:if>>100</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select name="type" id="type" onchange="this.form.submit()" style="width: 100px;">
                                                        <option value="">All</option>
                                                    <c:forEach items="${serviceType}" var="ls">
                                                        <option value="${ls.type}" <c:if test="${type == ls.type}">selected</c:if>>${ls.type}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <input type="text" value="${search}" name="search" class="search-box" placeholder="Nhập từ khóa..." onchange="this.form.submit()"/>
                                            </td>
                                            <td>
                                                <select name="orderBy" id="orderBy" style="width: 110px;" onchange="this.form.submit()">
                                                    <option value="">All</option>
                                                    <option value="asc" <c:if test="${orderBy == 'asc'}">selected</c:if>>Tăng Dần</option>
                                                    <option value="desc" <c:if test="${orderBy == 'desc'}">selected</c:if>>Giảm Dần</option>
                                                    </select>
                                                </td>
                                                <td class="total-label text-center" style="width: 130px;">
                                                <input type="hidden" name="page" value="${currentPage}"/>
                                                <%=  (Integer) request.getAttribute("totalservice") %> dịch vụ
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
                                            <%
                                                int currentPage = (Integer) request.getAttribute("currentPage");
                                                int totalPages = (Integer) request.getAttribute("totalPages");
                                                int recordsPerPage = (Integer) request.getAttribute("recordsPerPage");
                                                String search = (String) request.getAttribute("search");
                                                String type = (String) request.getAttribute("type");
                                                String orderBy = (String) request.getAttribute("orderBy");


                                                // Hiển thị nút "Previous" nếu không phải trang đầu tiên
                                                if (currentPage > 1) {
                                            %>
                                            <a href="servicelist?page=<%= currentPage - 1 %>&recordsPerPage=<%= recordsPerPage %>&type=<%= type %>&search=<%= search %>&orderBy=<%= orderBy %>">Previous</a>
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
                                            <a href="servicelist?page=<%= i %>&recordsPerPage=<%= recordsPerPage %>&type=<%= type %>&search=<%= search %>&orderBy=<%= orderBy %>"><%= i %></a>
                                            <%
                                                    }
                                                }

                                                // Hiển thị nút "Next" nếu không phải trang cuối cùng
                                                if (currentPage < totalPages) {
                                            %>
                                            <a href="servicelist?page=<%= currentPage + 1 %>&recordsPerPage=<%= recordsPerPage %>&type=<%= type %>&search=<%= search %>&orderBy=<%= orderBy %>">Next</a>
                                            <%
                                                }
                                            %>
                                        </div>
                                        <table class="table table-bordered"  width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Tên</th>
                                                    <th>Loại</th>
                                                    <th>Phí (VND)</th>
                                                    <th class="action text-center">Chức Năng</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${listservice}" var="ls">
                                                    <tr>
                                                        <td>${ls.name}</td>
                                                        <td>${ls.type}</td>
                                                        <!--                                                        data-order: add to save addition infomation about data-->
                                                        <td>
                                                            <fmt:setLocale value="en_US" />
                                                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${ls.fee}"/>
                                                        </td>
                                                        <td style="width: 150px" class="text-center">
                                                            <a class="btn btn-primary btn-sm text-center" href="serviceedit?id=${ls.serviceId}&page=${currentPage}" style="height: 30px; width: 50px">Sửa</a>
                                                            <a class="btn btn-danger btn-sm text-center" href="servicedelete?id=${ls.serviceId}&page=${currentPage}" style="height: 30px; width: 50px"
                                                               onclick="return confirmDelete();">Xóa</a>
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
                <!-- End of Main Content -->



            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="login.html">Logout</a>
                    </div>
                </div>
            </div>
        </div>



        <!-- Bootstrap core JavaScript-->
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