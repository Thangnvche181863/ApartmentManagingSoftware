<%-- 
    Document   : userhome
    Created on : Sep 15, 2024, 2:42:47 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
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
        <link href="./css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <!--<link href="./css/bootstrap.min.css" rel="stylesheet">-->
        <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">-->
        <link href="./vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <style>
            .chooseApt:focus li, .chooseApt:focus div{
                background-color: #198754;
            }
            .chooseApt:focus .card1, .chooseApt:focus .card2{
                color: white !important;
            }
            #carouselExampleIndicators .carousel-item img {
                max-height: 500px; /* Adjust the maximum height as needed */
                width: auto;
                margin: auto;
            }
        </style>
    </head>
    <body>
        <div id="wrapper">
            <!-- Sidebar -->
            <jsp:include page="../sidebar.jsp"/>
            <!-- End of Sidebar -->
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <!-- Topbar -->
                    <jsp:include page="../topbar.jsp"/>
                    <!-- End of Topbar -->
                    <div class="container-fluid">
                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h1 mb-0 text-gray-800"><b>Thống kê hóa đơn cần thu</b></h1>
                            <a href="/AtpMan/managerinvoicestatistic" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                                Hóa đơn đã thu        
                            </a>
                        </div>
                        <!-- Split dropend button -->

                        <!--Billing information for month-->
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h4 id="currentMonth" class="h4 mb-0 text-gray-800 text-primary font-weight-bold col-xl-5 col-md-5">Thông tin chung</h4>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body row">
                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-primary shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Tổng cần thu</div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <fmt:setLocale value = "en_US"/>
                                                        <fmt:formatNumber value="${requestScope.totalAmount} " type="number" maxFractionDigits="0"></fmt:formatNumber> VNĐ
                                                        </div>
                                                    </div>
                                                    <div class="col-auto">
                                                        <i class="fas fa-money-bill fa-2x text-gray-300"></i>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Pending Requests Card Example -->
                                    <div class="col-xl-3 col-md-6 mb-4">
                                        <div class="card border-left-warning shadow h-100 py-2">
                                            <div class="card-body">
                                                <div class="row no-gutters align-items-center">
                                                    <div class="col mr-2">
                                                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                            Tổng hóa đơn cần thanh toán</div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        ${requestScope.totalUnPaidInvoice}
                                                    </div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Earnings (Monthly) Card Example -->
                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-success shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                        Tổng thu tháng </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">

                                                    </div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Earnings (Monthly) Card Example -->
                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-success shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                        Tổng thu tháng </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">

                                                    </div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <!-- Content Row -->

                        <!--Billing information for month-->
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h4 id="currentMonth" class="h4 mb-0 text-gray-800 text-primary font-weight-bold col-xl-5 col-md-5">Tìm kiếm hóa đơn</h4>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body row">
                                <div class="col-md-6 row">
                                    <div class="col-12 row">
                                        <label for="fromDate" class="col-sm-4 col-form-label font-weight-bold">Từ ngày</label>
                                        <div class="col-sm-8">
                                            <input type="date" class="form-control" id="fromDate" name="fromDate" min="${requestScope.minDate}" onchange="minDate()">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 row">
                                    <div class="col-12 row">
                                        <label for="toDate" class="col-sm-4 col-form-label font-weight-bold">Đến ngày</label>
                                        <div class="col-sm-8">
                                            <input type="date" class="form-control" id="toDate" name="toDate" min="" max="${requestScope.maxDate}" onchange="maxDate()">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 row">
                                    <div class="col-12 row">
                                        <label for="orderInfo" class="col-sm-4 col-form-label font-weight-bold">Nội dung thanh toán</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="orderInfo" name="orderInfo" min="">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 row">
                                    <div class="col-12 row d-flex flex-row-reverse">
                                        <input class="btn btn-primary" type="submit" value="Tìm kiếm" onclick="handleSearch($('#invoiceTable .pagination .page-item.active button.page-link').val())">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Content Row -->

                        <!-- table here -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h5 class="m-0 font-weight-bold text-primary col-md-9">Danh sách hóa đơn</h5>
                                <div class="col-md-2">
                                    <select id="invoicePerPage" name="invoicePerPage" class="form-select font-weight-bold text-primary text-uppercase" aria-label="Default select example" onchange="handleSearch()">
                                        <option value="5">Số lượng hiển thị: 5</option>
                                        <option value="10">Số lượng hiển thị: 10</option>
                                        <option value="25">Số lượng hiển thị: 25</option>
                                        <option value="50">Số lượng hiển thị: 50</option>
                                    </select>
                                </div>
                            </div>
                            <div id="invoiceTable" class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead style="background-color: #4e73df; color: white">
                                            <tr>
                                                <th>#</th>
                                                <th>Đơn giá</th>
                                                <th>Căn hộ</th>
                                                <th>Ngày tạo hóa đơn</th>
                                                <th>Ngày hết hạn thanh toán</th>
                                                <th>Trạng thái</th>
                                                <th>Thông tin</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="countServiceTable" value="0"/>
                                            <c:forEach items="${requestScope.invoiceList}" var="invoice">
                                                <c:set var="countServiceTable" value="${countServiceTable+1}"/>
                                                <tr>
                                                    <td>${countServiceTable}</td>
                                                    <td><fmt:formatNumber value="${invoice.getAmount()}" type="number" maxFractionDigits="0"></fmt:formatNumber> VNĐ</td>
                                                    <td>${invoice.apartmentName}</td>
                                                    <td><fmt:formatDate pattern="dd/MM/YYY" value="${invoice.issueDate}"></fmt:formatDate></td>
                                                    <td><fmt:formatDate pattern="dd/MM/YYY" value="${invoice.dueDate}"></fmt:formatDate></td>
                                                        <td></td>
                                                        <td><input class="btn btn-primary" type="submit" value="Chi tiết" onclick="handleDetails(${invoice.invoiceId})"></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                        <tfoot style="background-color: #4e73df; color: white" class="h5">
                                            <tr>
                                                <th colspan="9">
                                                    <div  class="d-flex justify-content-between">
                                                        <span>
                                                            Tổng số hóa đơn cần thanh toán: ${requestScope.totalUnPaidInvoice}
                                                        </span>
                                                        <span class="d-flex flex-row-reverse">
                                                            Tổng tiền: <fmt:formatNumber value="${requestScope.totalAmount}" type="number" maxFractionDigits="0"></fmt:formatNumber> VNĐ
                                                            </span>
                                                        </div>
                                                    </th>
                                                </tr>
                                            </tfoot>
                                        </table>
                                        <div class="d-flex flex-row-reverse">
                                            <nav aria-label="Page navigation">
                                                <ul class="pagination justify-content-start">
                                                <c:if test="${1 > 1}">
                                                    <li class="page-item">
                                                        <button class="page-link" value="${requestScope.currentPage - 1}" onclick="handleSearch(this.value)">Previous</button>
                                                    </li>
                                                </c:if>

                                                <c:forEach var="i" begin="1" end="${requestScope.totalUnPaidInvoicePage}">
                                                    <li class="page-item ${i == 1 ? 'active' : ''}">
                                                        <button class="page-link" value="${i}" onclick="handleSearch(this.value)">${i}</button>
                                                    </li>
                                                </c:forEach>

                                                <c:if test="${1 < requestScope.totalUnPaidInvoicePage}">
                                                    <li class="page-item">
                                                        <button class="page-link" value="${1 + 1}" onclick="handleSearch(this.value)">Next</button>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>   
                        </div>
                        <div class="card shadow mb-4" id="serviceCard">
                        </div>
                    </div>
                </div>
            </div>
            <!-- Bootstrap core JavaScript-->
            <script src="./vendor/jquery/jquery.min.js"></script>
            <script src="./vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
            <script src="./vendor/chart.js/Chart.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="./js/sb-admin-2.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="./vendor/jquery-easing/jquery.easing.min.js"></script> 

            <!-- Page level plugins -->
            <script src="./vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="./vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!--Page level custom scripts--> 
            <script src="./js/demo/datatables-demo.js"></script>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

            <script>
                                                        function handleSearch(page) {
                                                            let fromDate = $("#fromDate").val();
                                                            let toDate = $("#toDate").val();
                                                            let currentPage = page;
                                                            let invoicePerPage = $("#invoicePerPage").val();

                                                            console.log("fromdate ", fromDate);
                                                            console.log("toDate ", toDate);
                                                            console.log("currentPage ", currentPage);
                                                            console.log("invoicePerPage ", invoicePerPage);
                                                            $.ajax({
                                                                url: "/AtpMan/unpaidinvoicetableajax",
                                                                type: "get", //send it through post method
                                                                data: {
                                                                    fromDate: fromDate,
                                                                    toDate: toDate,
                                                                    currentPage: currentPage,
                                                                    invoicePerPage: invoicePerPage
                                                                },
                                                                success: function (data) {
                                                                    $("#invoiceTable").html(data);
                                                                },
                                                                error: function (xhr) {
                                                                    //Do Something to handle error
                                                                }
                                                            });
                                                        }

                                                        function handleDetails(invoiceID) {
                                                            let invoiceId = invoiceID;

                                                            console.log("invoiceId ", invoiceId);
                                                            $.ajax({
                                                                url: "/AtpMan/unpaidinvoiceserviceajax",
                                                                type: "get", //send it through post method
                                                                data: {
                                                                    invoiceId: invoiceId
                                                                },
                                                                success: function (data) {
                                                                    $("#serviceCard").html(data);
                                                                },
                                                                error: function (xhr) {
                                                                    //Do Something to handle error
                                                                }
                                                            });
                                                        }

                                                        function handleSearchDetails(page) {
                                                            let invoiceId = $("#invoiceID").val();
                                                            let currentPage = page;
                                                            let searchTerm = $("#searchService").val();
                                                            let servicePerPage = $("#servicePerPage").val();
                                                            console.log("invoiceId ", invoiceId);
                                                            console.log("currentPage ", currentPage);
                                                            console.log("searchTerm ", searchTerm);
                                                            console.log("invoicePerPage ", invoicePerPage);
                                                            $.ajax({
                                                                url: "/AtpMan/invoicestatservicesearchajax",
                                                                type: "get", //send it through post method
                                                                data: {
                                                                    invoiceId: invoiceId,
                                                                    currentPage: currentPage,
                                                                    searchTerm: searchTerm,
                                                                    servicePerPage: servicePerPage
                                                                },
                                                                success: function (data) {
                                                                    $("#serviceTable").html(data);
                                                                },
                                                                error: function (xhr) {
                                                                    //Do Something to handle error
                                                                }
                                                            });
                                                        }

                                                        function submitMonth() {
                                                            document.getElementById('chooseMonthYear').submit();
                                                        }

                                                        function minDate() {
                                                            const fromDateValue = $("#fromDate").val();
                                                            const toDateInput = document.getElementById('toDate');

                                                            // Cập nhật thuộc tính min cho toDate bằng giá trị của fromDate
                                                            toDateInput.min = fromDateValue;
                                                        }
                                                        function maxDate() {
                                                            const toDateValue = $("#toDate").val();
                                                            const fromDateInput = document.getElementById('fromDate');

                                                            // Cập nhật thuộc tính max cho fromDate bằng giá trị của toDate
                                                            fromDateInput.max = toDateValue;
                                                        }

//                                                        document.getElementById('fromDate').addEventListener('change', function () {
//                                                            const fromDateValue = $("#fromDate").val();
//                                                            const toDateInput = document.getElementById('toDate');
//
//                                                            // Cập nhật thuộc tính min cho toDate bằng giá trị của fromDate
//                                                            toDateInput.min = fromDateValue;
//                                                        }
//                                                        );

            </script>
    </body>
</html>
