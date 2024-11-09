<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
                                                                   uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
                                                                   uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <meta name="description" content="" />
        <meta name="author" content="" />

        <title>SB Admin 2 - Cards</title>

        <!-- Custom fonts for this template-->
        <link
            href="vendor/fontawesome-free/css/all.min.css"
            rel="stylesheet"
            type="text/css"
            />
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet"
            />

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet" />
        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet" />
        <!-- Icon Font Stylesheet -->
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
            />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
            rel="stylesheet"
            />

        <style>
            table {
                width: 100%;
                border-collapse: collapse;
                font-family: Arial, sans-serif;
            }

            th,
            td {
                border: 1px solid #dddddd;
                text-align: center;
                padding: 12px;
            }

            th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            td {
                color: black;
            }

            table tbody tr:hover {
                background-color: #f1f1f1;
            }
        </style>
    </head>

    <body id="page-top">
        <!-- Page Wrapper -->
        <div id="wrapper">
            <!-- Sidebar -->
            <jsp:include page="sidebar.jsp" />
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Main Content -->
                <div id="content">
                    <!-- Topbar -->
                    <%@include file = "topbar.jsp" %>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid mb-5">
                        <!-- Page Heading -->
                        <div
                            class="d-sm-flex align-items-center justify-content-between mb-4"
                            >
                            <h1 class="h3 mb-0 text-gray-800">Thông Tin Chi Tiết</h1>
                            <p>
                                <a class="btn btn-info" href="registlist">Danh Sách Đăng Kí</a>
                            </p>
                        </div>

                        <div class="row d-flex">
                            <!-- Earnings (Monthly) Card Example -->

                            <table
                                border="1"
                                cellpadding="10"
                                cellspacing="0"
                                style="width: 100%"
                                >
                                <thead>
                                    <tr>
                                        <th>Thuộc tòa</th>
                                        <th>Số phòng</th>
                                        <th>Giá trị phòng (VND)</th>
                                        <th>Phí duy trì (VND)</th>
                                        <th>Tổng số dịch vụ</th>
                                        <th>Tổng tiền dịch vụ (VND)</th>
                                        <th>Diện tích (m²)</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>${apart.name}</td>
                                        <td>${apart.apartmentNumber}</td>
                                        <td>
                                            <fmt:setLocale value="en_US" />
                                            <fmt:formatNumber
                                                type="number"
                                                maxFractionDigits="3"
                                                value="${apart.price}"
                                                />
                                        </td>
                                        <td>
                                            <fmt:setLocale value="en_US" />
                                            <fmt:formatNumber
                                                type="number"
                                                maxFractionDigits="3"
                                                value="${apart.maintenanceFee}"
                                                />
                                        </td>
                                        <td>${statistic.getTotalContract()}</td>
                                        <td>
                                            <fmt:setLocale value="en_US" />
                                            <fmt:formatNumber
                                                type="number"
                                                maxFractionDigits="3"
                                                value="${statistic.getTotalAmount()}"
                                                />
                                        </td>
                                        <td>${apart.area}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="container-fluid">
                        <!-- Page Heading -->
                        <div
                            class="d-sm-flex align-items-center justify-content-between mb-4"
                            >
                            <h1 class="h3 mb-10 text-gray-800">Dịch Vụ Đã Đăng Kí</h1>
                        </div>

                        <div class="row d-flex">
                            <!-- Earnings (Monthly) Card Example -->

                            <table
                                border="1"
                                cellpadding="10"
                                cellspacing="0"
                                style="width: 100%"
                                >
                                <thead>
                                    <tr>
                                        <th>Tên</th>
                                        <th>Loại</th>
                                        <th>Phí</th>
                                        <th>Ngày đăng kí</th>
                                        <th>Thời hạn</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${apart.getList()}" var="apart">
                                        <tr>
                                            <td>${apart.getService().getName()}</td>
                                            <td>${apart.getService().getType()}</td>
                                            <td>
                                                <fmt:setLocale value="en_US" />
                                                <fmt:formatNumber
                                                    type="number"
                                                    maxFractionDigits="3"
                                                    value="${apart.getAmount()}"
                                                    />
                                            </td>
                                            <td>
                                                <fmt:formatDate
                                                    value="${apart.startDate}"
                                                    pattern="dd/MM/yyyy"
                                                    />
                                            </td>
                                            <td>
                                                <fmt:formatDate
                                                    value="${apart.endDate}"
                                                    pattern="dd/MM/yyyy"
                                                    />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
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
                        <a class="btn btn-primary" href="logout">Logout</a>
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
        <script src="vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/chart-area-demo.js"></script>
        <script src="js/demo/chart-pie-demo.js"></script>
        <script src="js/demo/chart-bar-demo.js"></script>
    </body>
</html>
