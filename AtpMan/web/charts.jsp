
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
                <li class="nav-item">
                    <a class="nav-link" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true"
                       aria-controls="collapseTwo">
                        <i class="fas fa-fw fa-cog"></i>
                        <span>Components</span>
                    </a>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
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
                <li class="nav-item active">
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

                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>

                        <!-- Topbar Search -->
                        <form
                            class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                            <div class="input-group">
                                <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                       aria-label="Search" aria-describedby="basic-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                        <i class="fas fa-search fa-sm"></i>
                                    </button>
                                </div>
                            </div>
                        </form>

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">


                            <li class="nav-item dropdown no-arrow d-sm-none">
                                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-search fa-fw"></i>
                                </a>
                                <!-- Dropdown - Messages -->
                                <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                     aria-labelledby="searchDropdown">
                                    <form class="form-inline mr-auto w-100 navbar-search">
                                        <div class="input-group">
                                            <input type="text" class="form-control bg-light border-0 small"
                                                   placeholder="Search for..." aria-label="Search"
                                                   aria-describedby="basic-addon2">
                                            <div class="input-group-append">
                                                <button class="btn btn-primary" type="button">
                                                    <i class="fas fa-search fa-sm"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>

                            <!-- Nav Item - Alerts -->
                            <li class="nav-item dropdown no-arrow mx-1">
                                <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-bell fa-fw"></i>
                                    <!-- Counter - Alerts -->
                                    <span class="badge badge-danger badge-counter">3+</span>
                                </a>
                                <!-- Dropdown - Alerts -->
                                <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="alertsDropdown">
                                    <h6 class="dropdown-header">
                                        Alerts Center
                                    </h6>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="mr-3">
                                            <div class="icon-circle bg-primary">
                                                <i class="fas fa-file-alt text-white"></i>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="small text-gray-500">December 12, 2019</div>
                                            <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="mr-3">
                                            <div class="icon-circle bg-success">
                                                <i class="fas fa-donate text-white"></i>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="small text-gray-500">December 7, 2019</div>
                                            $290.29 has been deposited into your account!
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="mr-3">
                                            <div class="icon-circle bg-warning">
                                                <i class="fas fa-exclamation-triangle text-white"></i>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="small text-gray-500">December 2, 2019</div>
                                            Spending Alert: We've noticed unusually high spending for your account.
                                        </div>
                                    </a>
                                    <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                                </div>
                            </li>

                            <!-- Nav Item - Messages -->
                            <li class="nav-item dropdown no-arrow mx-1">
                                <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-envelope fa-fw"></i>
                                    <!-- Counter - Messages -->
                                    <span class="badge badge-danger badge-counter">7</span>
                                </a>
                                <!-- Dropdown - Messages -->
                                <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="messagesDropdown">
                                    <h6 class="dropdown-header">
                                        Message Center
                                    </h6>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="dropdown-list-image mr-3">
                                            <img class="rounded-circle" src="img/undraw_profile_1.svg"
                                                 alt="...">
                                            <div class="status-indicator bg-success"></div>
                                        </div>
                                        <div class="font-weight-bold">
                                            <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                                problem I've been having.</div>
                                            <div class="small text-gray-500">Emily Fowler · 58m</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="dropdown-list-image mr-3">
                                            <img class="rounded-circle" src="img/undraw_profile_2.svg"
                                                 alt="...">
                                            <div class="status-indicator"></div>
                                        </div>
                                        <div>
                                            <div class="text-truncate">I have the photos that you ordered last month, how
                                                would you like them sent to you?</div>
                                            <div class="small text-gray-500">Jae Chun · 1d</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="dropdown-list-image mr-3">
                                            <img class="rounded-circle" src="img/undraw_profile_3.svg"
                                                 alt="...">
                                            <div class="status-indicator bg-warning"></div>
                                        </div>
                                        <div>
                                            <div class="text-truncate">Last month's report looks great, I am very happy with
                                                the progress so far, keep up the good work!</div>
                                            <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="dropdown-list-image mr-3">
                                            <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                                 alt="...">
                                            <div class="status-indicator bg-success"></div>
                                        </div>
                                        <div>
                                            <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                                told me that people say this to all dogs, even if they aren't good...</div>
                                            <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                                </div>
                            </li>

                            <!--abc-->
                            <div class="topbar-divider d-none d-sm-block"></div>
                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <c:if test="${sessionScope.user != null}">
                                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                            <strong style="color: black;">${sessionScope.user.name}</strong>
                                        </span>
                                        <img class="img-profile rounded-circle" src="img/undraw_profile.svg">
                                    </a>
                                </c:if>

                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="profile.jsp">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Profile
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Settings
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Activity Log
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="logout" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>

                    <!-- End of Topbar -->


                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800 text-primary"><b>Thống kê dịch vụ năm ${requestScope.currentYear}</b></h1>
                        </div>
                        <div class="row">
                            <!-- Total information for one year -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                    Tổng hóa đơn
                                                </div>
                                                <fmt:setLocale value = "en_US"/>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                    ${statistic.totalBill} đơn
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-money-bill fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End Billing information for one year -->

                            <!--Paid amount billing information year-->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    Tổng số tiền
                                                </div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                    <fmt:formatNumber value="${statistic.totalAmount} " type="number" maxFractionDigits="0"></fmt:formatNumber>
                                                        VND
                                                    </div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--Paid amount billing information year-->

                                <!-- UnPaid amount billing information year -->
                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-warning shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                        Chưa thanh toán
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                    ${statistic.totalUnBill} hóa đơn
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-comments fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--UnPaid amount billing information year-->
                            <!-- Pending Requests Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-info shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    Tiền chưa thanh toán
                                                </div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                    <fmt:formatNumber value="${statistic.totalUnPay} " type="number" maxFractionDigits="0"></fmt:formatNumber>
                                                        VND
                                                    </div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-comments fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <!--Billing information for month-->            
                                <div class="d-flex align-items-center justify-content-between mb-4">
                                    <h1 id="currentMonth" class="h3 mb-0 text-gray-800 text-primary col-xl-5 col-md-5">Thông tin hóa đơn trong tháng</h1>
                                    <form class="d-flex col-xl-7 col-md-7" action="userhome" method="GET" id="chooseMonthYear">
                                        <div class="col-xl-6 col-md-6">
                                            <input type="hidden" name="apartmentID" value="${requestScope.apartment.apartmentID}" />
                                        <fmt:setLocale value = "vi_VN"/>
                                        <label for="month" class="form-label">Chọn Tháng</label>
                                        <select id="month" name="selectMonth" class="form-select me-2" aria-label="Select Month" onchange="submitMonth()">
                                            <c:forEach items="${requestScope.dateList}" var="dList">
                                                <fmt:formatDate value="${dList}" pattern="M" var="month"/>
                                                <option ${pageScope.month == requestScope.currentMonth ? 'selected' : ''} value="${month}">
                                                    <fmt:formatDate value="${dList}" pattern="MMMM"></fmt:formatDate>
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
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>
        <script>
                                            function paging(param) {
                                                let pageChoose = param.value;
                                                $.ajax({
                                                    url: "/AtpMan/userhomenews",
                                                    type: "post", //send it through post method
                                                    data: {
                                                        page: pageChoose
                                                    },
                                                    success: function (data) {
                                                        $("#newsContent").html(data);
                                                        //                                                            generate.innerHTML = data;
                                                    },
                                                    error: function (xhr) {
                                                        //Do Something to handle error
                                                    }
                                                });
                                            }
                                            function handleSearch(page) {
                                                let searchTerm = document.getElementById("searchService").value;
                                                let selectMonth = ${requestScope.currentMonth};
                                                let selectYears = ${requestScope.currentYear};
                                                let apartmentID = ${requestScope.apartmentID};
                                                let currentPage = page;
                                                console.log("search ", searchTerm);
                                                console.log("month ", selectMonth);
                                                console.log("year ", selectYears);
                                                console.log("aptId ", apartmentID);
                                                console.log("current ", currentPage);
                                                $.ajax({
                                                    url: "/AtpMan/userhometableajax",
                                                    type: "get", //send it through post method
                                                    data: {
                                                        searchTerm: searchTerm,
                                                        selectMonth: selectMonth,
                                                        selectYear: selectYears,
                                                        apartmentID: apartmentID,
                                                        currentPage: currentPage
                                                    },
                                                    success: function (data) {
                                                        $("#searchTable").html(data);
                                                        //                                                                                console.log("data",data);
                                                        //                                                            generate.innerHTML = data;
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
                                            // Pie Chart Example
                                            var ctx = document.getElementById("myPieChart");
                                            var myPieChart = new Chart(ctx, {
                                                type: 'doughnut',
                                                data: {
                                                    labels: serviceList,
                                                    datasets: [{
                                                            data: amountService,
                                                            backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc', '#f6c23e', '#e74a3b', '#5a5c69', '#f8c8db', '#b3d0d6', '#ffcc00', '#ff6347', '#6c757d', '#007bff'],
                                                            hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
                                                            hoverBorderColor: "rgba(234, 236, 244, 1)",
                                                        }],
                                                },
                                                options: {
                                                    maintainAspectRatio: false,
                                                    tooltips: {
                                                        backgroundColor: "rgb(255,255,255)",
                                                        bodyFontColor: "#858796",
                                                        borderColor: '#dddfeb',
                                                        borderWidth: 1,
                                                        xPadding: 15,
                                                        yPadding: 15,
                                                        displayColors: false,
                                                        caretPadding: 10,
                                                        callbacks: {
                                                            label: function (tooltipItem, data) {
                                                                var value = number_format(data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index]);
                                                                return value + ' VNĐ';
                                                            }
                                                        }
                                                    },
                                                    legend: {
                                                        display: true,
                                                        position: 'bottom', // Hoặc 'top', 'left', 'right'
                                                        labels: {
                                                            boxWidth: 10, // Kích thước của hộp màu
                                                            padding: 10 // Khoảng cách giữa các mục
                                                        }
                                                    },
                                                    cutoutPercentage: 60,
                                                },
                                            });


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