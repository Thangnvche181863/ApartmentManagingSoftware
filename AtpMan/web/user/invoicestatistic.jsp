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
        <link href="./css/bootstrap.min.css" rel="stylesheet">
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
                    <%@include file="../topbar.jsp" %>
                    <!-- End of Topbar -->
                    <div class="container-fluid">
                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h1 mb-0 text-gray-800"><b>Thống kê hóa đơn</b></h1>
                        </div>
                        <!-- Split dropend button -->

                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="row">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="btn-group dropend">
                                        <a class="chooseApt" href="/AtpMan/invoicestatistic?apartmentID=${requestScope.apartment.apartmentID}&buildingID=${requestScope.apartment.buildingID}" style="text-decoration: none">
                                            <div class="card-body">
                                                <div class="row no-gutters align-items-center">
                                                    <div class="col mr-2">
                                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1 card1">
                                                            Căn hộ
                                                        </div>
                                                        <div class="h4 mb-0 font-weight-bold text-gray-800 card1">
                                                            ${requestScope.apartment.apartmentNumber} - ${requestScope.apartment.name}
                                                        </div>
                                                        <div class="text-sm font-weight-bold text-success text-uppercase mb-1 card1">
                                                            (Bấm để xem chi tiết)
                                                        </div>
                                                    </div>

                                                    <div class="col-auto">
                                                        <i class="fas fa-home fa-2x text-gray-300"></i>
                                                    </div> 
                                                </div>
                                            </div>
                                        </a>
                                        <c:if test="${requestScope.apartmentList.size() > 1}">
                                            <button type="button" class="btn btn-success dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
                                                <span class="visually-hidden">Toggle Dropright</span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <!-- Dropdown menu links -->
                                                <c:forEach items="${requestScope.apartmentList}" var="apartment">
                                                    <a class="chooseApt" href="/AtpMan/invoicestatistic?apartmentID=${apartment.apartmentID}" style="text-decoration: none">
                                                        <li>
                                                            <div class="card-body">
                                                                <div class="row no-gutters align-items-center">
                                                                    <div class="col mr-2">
                                                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1 card1">
                                                                            Căn hộ
                                                                        </div>
                                                                        <div class="h5 mb-0 font-weight-bold text-gray-800 card2">
                                                                            ${apartment.apartmentNumber} - ${apartment.name}   
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-auto">
                                                                        <i class="fas fa-home fa-2x text-gray-300"></i>
                                                                    </div> 
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </a>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!--Billing information for month-->
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h4 id="currentMonth" class="h4 mb-0 text-gray-800 text-primary font-weight-bold col-xl-5 col-md-5">Thông tin hóa đơn trong tháng</h4>
                                <form class="d-flex col-xl-7 col-md-7" action="invoicestatistic" method="GET" id="chooseMonthYear">
                                    <div class="col-xl-6 col-md-6">
                                        <input type="hidden" name="apartmentID" value="${requestScope.apartment.apartmentID}" />
                                        <fmt:setLocale value = "vi_VN"/>
                                        <label for="month" class="form-label">Chọn Tháng</label>
                                        <select id="month" name="selectMonth" class="form-select me-2" aria-label="Select Month" onchange="submitMonth()">
                                            <c:forEach items="${requestScope.listOfMonth}" var="month">
                                                <option ${pageScope.month == requestScope.currentMonth ? 'selected' : ''} value="${month}">
                                                    Tháng ${pageScope.month}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-xl-6 col-md-6">
                                        <label for="year" class="form-label">Chọn Năm</label>
                                        <select id="year" name="selectYear" class="form-select" aria-label="Select Year" onchange="submitMonth()">
                                            <c:forEach items="${requestScope.listOfYear}" var="yList">
                                                <option ${requestScope.currentYear == pageScope.yList ? 'selected' : ''} value="${pageScope.yList}">
                                                    ${yList}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </form>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body row">
                                <div class="col-xl-6 col-md-6 mb-4">
                                    <div class="card border-left-primary shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Tổng hóa đơn trong tháng</div>
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

                                    <!-- Earnings (Monthly) Card Example -->
                                    <div class="col-xl-6 col-md-6 mb-4">
                                        <div class="card border-left-success shadow h-100 py-2">
                                            <div class="card-body">
                                                <div class="row no-gutters align-items-center">
                                                    <div class="col mr-2">
                                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                            Số lượng hóa đơn    
                                                        </div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        ${requestScope.totalInvoice}
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
                    </div>
                    <!-- Content Row -->

                    <!-- chart here -->
                    <!-- Area Chart -->
                    <div class="col-xl-12 col-lg-12">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div
                                class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Biểu đồ hóa đơn trong 12 tháng</h6>
                                <div class="dropdown no-arrow">
                                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                         aria-labelledby="dropdownMenuLink">
                                        <div class="dropdown-header">Dropdown Header:</div>
                                        <a class="dropdown-item" href="#">Action</a>
                                        <a class="dropdown-item" href="#">Another action</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#">Something else here</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body">
                                <div class="chart-area">
                                    <canvas id="myAreaChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- table here -->
                    <div class="card shadow mb-4">
                        <!-- Card Header - Dropdown -->
                        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                            <h4 id="currentMonth" class="h4 mb-0 text-gray-800 text-primary font-weight-bold col-xl-5 col-md-5">Tìm kiếm thanh toán</h4>
                        </div>
                        <!-- Card Body -->
                        <div class="card-body row">
                            <div class="col-md-6 row">
                                <div class="col-12 row">
                                    <label for="invoiceCode" class="col-sm-4 col-form-label font-weight-bold">Mã hóa đơn</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="invoiceCode" name="invoiceCode" oninput="handleSearchInvoice($('#invoiceTable .pagination .page-item.active button.page-link').val())">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 row">
                                <div class="col-12 row">
                                    <label for="transactionNo" class="col-sm-4 col-form-label font-weight-bold">Mã giao dịch</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="transactionNo" name="transactionNo" min="" oninput="handleSearchInvoice($('#invoiceTable .pagination .page-item.active button.page-link').val())">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 row">
                                <div class="col-12 row">
                                    <label for="orderInfo" class="col-sm-4 col-form-label font-weight-bold">Nội dung thanh toán</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="orderInfo" name="orderInfo" min="" oninput="handleSearchInvoice($('#invoiceTable .pagination .page-item.active button.page-link').val())">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 row">
                                <div class="col-12 row">
                                    <label for="sort" class="col-sm-4 col-form-label font-weight-bold">Sắp xếp theo giá</label>
                                    <div class="col-md-8">
                                        <select id="sort" name="sort" class="form-select" aria-label="Default select example" onchange="handleSearchInvoice($('#invoiceTable .pagination .page-item.active button.page-link').val())">
                                            <option value="" selected>Mặc định</option>
                                            <option value="asc">Tăng dần</option>
                                            <option value="desc">Giảm dần</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 row">
                                <div class="col-12 row">
                                    <label for="bankCode" class="col-sm-4 col-form-label font-weight-bold">Mã ngân hàng</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="bankCode" name="bankCode" min="" oninput="handleSearchInvoice($('#invoiceTable .pagination .page-item.active button.page-link').val())">
                                    </div>
                                </div>
                            </div>
                            <!--                            <div class="col-md-6 d-flex flex-row-reverse">
                                                            <div class="col-2">
                                                                <input class="btn btn-primary" type="submit" value="Tìm kiếm" onclick="handleSearch($('#invoiceTable .pagination .page-item.active button.page-link').val())">
                                                            </div>
                                                        </div>-->
                        </div>
                    </div>
                    <div class="card shadow mb-4">
                        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                            <h5 class="m-0 font-weight-bold text-primary text-gray-800 col-md-9">Danh sách hóa đơn</h5>
                            <div class="col-md-3">
                                <select id="invoicePerPage" name="invoicePerPage" class="form-select font-weight-bold text-primary text-uppercase" aria-label="Default select example" onchange="handleSearchInvoice($('#invoiceTable .pagination .page-item.active button.page-link').val())">
                                    <option value="5">Số lượng hiển thị: 5</option>
                                    <option value="10">Số lượng hiển thị: 10</option>
                                </select>
                            </div>
                        </div>
                        <div id="invoiceTable" class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead style="background-color: #4e73df; color: white">
                                        <tr>
                                            <th>#</th>
                                            <th>Mã giao dịch</th>
                                            <th>Mã hóa đơn</th>
                                            <th>Đơn giá</th>
                                            <th>Ngân hàng</th>
                                            <th>Nội dung</th>
                                            <th>Ngày thanh toán</th>
                                            <th>Thông tin</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="countInvoiceTable" value="0"/>
                                        <c:forEach items="${requestScope.invoiceCurrentList}" var="invoice">
                                            <c:set var="countInvoiceTable" value="${countInvoiceTable+1}"/>
                                            <tr>
                                                <td>${countInvoiceTable}</td>
                                                <td>${invoice.transactionNo}</td>
                                                <td>${invoice.invoiceCode}</td>
                                                <td><fmt:formatNumber value="${invoice.getAmount()}" type="number" maxFractionDigits="0"></fmt:formatNumber> VNĐ</td>
                                                <td>${invoice.bankCode}</td>
                                                <td>${invoice.orderInfo}</td>
                                                <td><fmt:formatDate pattern="dd/MM/YYY HH:mm:ss" value="${invoice.transactionDate}"></fmt:formatDate></td>
                                                <td><input class="btn btn-primary" type="submit" value="Chi tiết" onclick="handleDetails(${invoice.invoiceId})"></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot style="background-color: #4e73df; color: white" class="h5">
                                        <tr>
                                            <th colspan="9">
                                                <div  class="d-flex justify-content-between">
                                                    <span>
                                                        Tổng số hóa đơn thanh toán: ${requestScope.totalPaidInvoice}
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
                                                    <button class="page-link" value="${requestScope.currentPage - 1}" onclick="handleSearchInvoice(this.value)">Previous</button>
                                                </li>
                                            </c:if>

                                            <c:forEach var="i" begin="1" end="${requestScope.totalInvoicePage}">
                                                <li class="page-item ${i == 1 ? 'active' : ''}">
                                                    <button class="page-link" value="${i}" onclick="handleSearchInvoice(this.value)">${i}</button>
                                                </li>
                                            </c:forEach>

                                            <c:if test="${1 < requestScope.totalInvoicePage}">
                                                <li class="page-item">
                                                    <button class="page-link" value="${1 + 1}" onclick="handleSearchInvoice(this.value)">Next</button>
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
                                                        function handleSearchInvoice(page) {
                                                            let invoiceCode = $("#invoiceCode").val();
                                                            let transactionNo = $("#transactionNo").val();
                                                            let orderInfo = $("#orderInfo").val();
                                                            let bankCode = $("#bankCode").val();
                                                            let sort = $("#sort").val();

                                                            let selectMonth = ${requestScope.currentMonth};
                                                            let selectYears = ${requestScope.currentYear};
                                                            let apartmentID = ${requestScope.apartment.apartmentID};
                                                            let currentPage = page;
                                                            let invoicePerPage = $("#invoicePerPage").val();

                                                            console.log("invoiceCode ", invoiceCode);
                                                            console.log("transactionNo ", transactionNo);
                                                            console.log("orderInfo ", orderInfo);
                                                            console.log("bankCode ", bankCode);
                                                            console.log("sort ", sort);
                                                            console.log("month ", selectMonth);
                                                            console.log("year ", selectYears);
                                                            console.log("aptId ", apartmentID);
                                                            console.log("currentPage ", currentPage);
                                                            console.log("invoicePerPage ", invoicePerPage);
                                                            $.ajax({
                                                                url: "/AtpMan/userinvoiceajax",
                                                                type: "get", //send it through post method
                                                                data: {
                                                                    invoiceCode: invoiceCode,
                                                                    transactionNo: transactionNo,
                                                                    orderInfo: orderInfo,
                                                                    bankCode: bankCode,
                                                                    selectMonth: selectMonth,
                                                                    selectYear: selectYears,
                                                                    apartmentId: apartmentID,
                                                                    currentPage: currentPage,
                                                                    invoicePerPage: invoicePerPage,
                                                                    sort: sort
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
                                                                url: "/AtpMan/userinvoicestatdetailsajax",
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

                                                        function submitMonth() {
                                                            document.getElementById('chooseMonthYear').submit();
                                                        }

                                                        let d = new Date();
                                                        const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

                                                        let month = months.find((value, index) => {
                                                            if (d.getMonth()) {
                                                                return index == d.getMonth() - 1;
                                                            } else {
                                                                return index == 12;
                                                            }
                                                        });
                                                        console.log(month)

                                                        //            document.getElementById("currentMonth").innerHTML += "(" + month + ", " + d.getFullYear() + ")";

                                                        // take data from servlet to js
                                                        const amountList = [
            <c:forEach items="${requestScope.amoutMonth}" var="amountList">
                ${amountList},
            </c:forEach>
                                                        ];
                                                        console.log(amountList)
                                                        const serviceList = [
            <c:forEach items="${requestScope.serviceList}" var="serviceContract">
                                                            "${serviceContract.getService().getName()}",
            </c:forEach>
                                                        ];
                                                        const amountService = [
            <c:forEach items="${requestScope.serviceList}" var="serviceContract">
                <c:out value="${serviceContract.getAmount()}"/>,
            </c:forEach>
                                                        ];
                                                        console.log(amountService);


                                                        function number_format(number, decimals, dec_point, thousands_sep) {
                                                            // *     example: number_format(1234.56, 2, ',', ' ');
                                                            // *     return: '1 234,56'
                                                            number = (number + '').replace(',', '').replace(' ', '');
                                                            var n = !isFinite(+number) ? 0 : +number,
                                                                    prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
                                                                    sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
                                                                    dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
                                                                    s = '',
                                                                    toFixedFix = function (n, prec) {
                                                                        var k = Math.pow(10, prec);
                                                                        return '' + Math.round(n * k) / k;
                                                                    };
                                                            // Fix for IE parseFloat(0.55).toFixed(0) = 0;
                                                            s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
                                                            if (s[0].length > 3) {
                                                                s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
                                                            }
                                                            if ((s[1] || '').length < prec) {
                                                                s[1] = s[1] || '';
                                                                s[1] += new Array(prec - s[1].length + 1).join('0');
                                                            }
                                                            return s.join(dec);
                                                        }
                                                        // Area Chart Example
                                                        var ctx = document.getElementById("myAreaChart");
                                                        var myLineChart = new Chart(ctx, {
                                                            type: 'line',
                                                            data: {
                                                                labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                                                                datasets: [{
                                                                        label: "Amount",
                                                                        lineTension: 0.3,
                                                                        backgroundColor: "rgba(78, 115, 223, 0.05)",
                                                                        borderColor: "rgba(78, 115, 223, 1)",
                                                                        pointRadius: 3,
                                                                        pointBackgroundColor: "rgba(78, 115, 223, 1)",
                                                                        pointBorderColor: "rgba(78, 115, 223, 1)",
                                                                        pointHoverRadius: 3,
                                                                        pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                                                                        pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                                                                        pointHitRadius: 10,
                                                                        pointBorderWidth: 2,
                                                                        data: amountList,
                                                                    }],
                                                            },
                                                            options: {
                                                                maintainAspectRatio: false,
                                                                layout: {
                                                                    padding: {
                                                                        left: 10,
                                                                        right: 25,
                                                                        top: 25,
                                                                        bottom: 0
                                                                    }
                                                                },
                                                                scales: {
                                                                    xAxes: [{
                                                                            time: {
                                                                                unit: 'date'
                                                                            },
                                                                            gridLines: {
                                                                                display: false,
                                                                                drawBorder: false
                                                                            },
                                                                            ticks: {
                                                                                maxTicksLimit: 7
                                                                            }
                                                                        }],
                                                                    yAxes: [{
                                                                            ticks: {
                                                                                maxTicksLimit: 5,
                                                                                padding: 10,
                                                                                // Include a dollar sign in the ticks
                                                                                callback: function (value, index, values) {
                                                                                    return number_format(value) + ' VNĐ';
                                                                                }
                                                                            },
                                                                            gridLines: {
                                                                                color: "rgb(234, 236, 244)",
                                                                                zeroLineColor: "rgb(234, 236, 244)",
                                                                                drawBorder: false,
                                                                                borderDash: [2],
                                                                                zeroLineBorderDash: [2]
                                                                            }
                                                                        }],
                                                                },
                                                                legend: {
                                                                    display: false
                                                                },
                                                                tooltips: {
                                                                    backgroundColor: "rgb(255,255,255)",
                                                                    bodyFontColor: "#858796",
                                                                    titleMarginBottom: 10,
                                                                    titleFontColor: '#6e707e',
                                                                    titleFontSize: 14,
                                                                    borderColor: '#dddfeb',
                                                                    borderWidth: 1,
                                                                    xPadding: 15,
                                                                    yPadding: 15,
                                                                    displayColors: false,
                                                                    intersect: false,
                                                                    mode: 'index',
                                                                    caretPadding: 10,
                                                                    callbacks: {
                                                                        label: function (tooltipItem, chart) {
                                                                            var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                                                                            return datasetLabel + ': ' + number_format(tooltipItem.yLabel) + ' VNĐ';
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        });
        </script>
    </body>
</html>
