<!DOCTYPE html>
<html lang="en">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin 2 - Dashboard</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

    </head>

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
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Earnings (Monthly) Card Example -->
                        <c:set value="${requestScope.list}" var="countList"></c:set>
                        <c:set value="0" var="count"></c:set>
                        <c:set value="${requestScope.listInvoice}" var="invoiceList"></c:set>
                        <c:set value="0" var="invoice"></c:set>
                        <c:forEach items="${listApartment}" var="o">
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                    ID: ${o.apartmentID}</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">Type: ${o.apartmentType}</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">Number: ${o.apartmentNumber}</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">Service fee: <fmt:formatNumber value="${o.price}" minFractionDigits = "0" type="currency" currencySymbol="VND" /></div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">Area: ${o.area} m² </div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">Floor: ${o.floor}</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">No. of Resident: ${countList.get(pageScope.count)} </div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">Unpaid Invoice: ${invoiceList.get(pageScope.invoice)} </div>
                                                <div class="text-xs font-weight-bold text-warning text-uppercase mb-1" style =" margin-top: 20px ">
                                                    <a href="residentofapartment?apartmentID=${o.apartmentID}" class ="text-warning" style="text-decoration: none" >View all Resident</a></div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-home fa-2x text-gray-400"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:set value="${count + 1}" var="count"></c:set>
                            <c:set value="${invoice + 1}" var="invoice"></c:set>
                        </c:forEach>


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
                        <script src="vendor/chart.js/Chart.min.js"></script>

                        <!-- Page level custom scripts -->
                        <script src="js/demo/chart-area-demo.js"></script>
                        <script src="js/demo/chart-pie-demo.js"></script>

                        </body>

                        </html>