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
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>
    <body>
        <div id="wrapper">
            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="userhome.jsp">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-building"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">APT MAN</div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item active">
                    <a class="nav-link" href="userhome.jsp">
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
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                       aria-expanded="true" aria-controls="collapseTwo">
                        <i class="fas fa-fw fa-cog"></i>
                        <span>Components</span>
                    </a>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Custom Components:</h6>
                            <a class="collapse-item" href="buttons.html">Buttons</a>
                            <a class="collapse-item" href="cards.html">Cards</a>
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
                    <a class="nav-link" href="tables.html">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Tables</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <!-- End of Sidebar -->
            <div id="content-wrapper" class="d-flex flex-column">
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

                            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
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

                            <div class="topbar-divider d-none d-sm-block"></div>

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">AdminName</span>
                                    <img class="img-profile rounded-circle"
                                         src="img/undraw_profile.svg">
                                </a>
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
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                    <!-- End of Topbar -->
                    <div class="container-fluid">
                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h1 mb-0 text-gray-800"><b>Trang chủ</b>  </h1>
                            <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                                <i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                        </div>
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="row">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    Căn hộ
                                                </div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                    ${requestScope.apartment.departmentType} - Tầng ${requestScope.apartment.floor} - Diện tích ${requestScope.apartment.area} m2   
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-home fa-2x text-gray-300"></i>
                                            </div> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800 text-primary">Tổng hóa đơn trong năm ${requestScope.currentYear}</h1>
                        </div>
                        <div class="row">
                            <!-- Billing information for one year -->
                            <div class="row">
                                <!-- Total information for one year -->
                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-primary shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Tổng hóa đơn (${requestScope.currentYear})
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <fmt:formatNumber value="${requestScope.totalBill}" type="number" maxFractionDigits="0"></fmt:formatNumber>
                                                            VNĐ
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
                                                            Tổng đã thanh toán
                                                        </div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <fmt:formatNumber value="${requestScope.paid} " type="number" maxFractionDigits="0"></fmt:formatNumber>
                                                            VNĐ
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
                                                        <fmt:formatNumber value="${requestScope.unpaid} " type="number" maxFractionDigits="0"></fmt:formatNumber>
                                                            VNĐ
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
                                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                                            Tổng số hóa đơn</div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.numOfInvoice}</div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-comments fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End Billing information for one year -->

                            <!--Billing information for month-->
                            <div class="row">
                                <div class="d-flex align-items-center justify-content-between mb-4">
                                    <h1 id="currentMonth" class="h3 mb-0 text-gray-800 text-primary col-xl-5 col-md-5">Thông tin hóa đơn trong tháng</h1>

                                    <form class="d-flex col-xl-7 col-md-7" action="userhome" method="GET" id="chooseMonthYear">
                                        <div class="col-xl-6 col-md-6">
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
                                <!-- Earnings (Monthly) Card Example -->
                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-primary shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Tổng hóa đơn trong tháng</div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <fmt:formatNumber value="${requestScope.invoiceCurrent.amount} " type="number" maxFractionDigits="0"></fmt:formatNumber> VNĐ
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
                                    <div class="col-xl-3 col-md-6 mb-4">
                                        <div class="card border-left-success shadow h-100 py-2">
                                            <div class="card-body">
                                                <div class="row no-gutters align-items-center">
                                                    <div class="col mr-2">
                                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                            Ngày phát hành</div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <fmt:formatDate pattern="dd/MM/YYY" value="${requestScope.invoiceCurrent.issueDate}"></fmt:formatDate>
                                                        <c:if test="${requestScope.invoiceCurrent.issueDate == null}">không khả dụng</c:if>
                                                        </div>
                                                    </div>
                                                    <div class="col-auto">
                                                        <i class="fas fa-calendar fa-2x text-gray-300"></i>
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
                                                            Ngày hết hạn</div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <fmt:formatDate pattern="dd/MM/YYY" value="${requestScope.invoiceCurrent.dueDate}"></fmt:formatDate>
                                                        <c:if test="${requestScope.invoiceCurrent.dueDate == null}">không khả dụng</c:if>
                                                        </div>
                                                    </div>
                                                    <div class="col-auto">
                                                        <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Pending Requests Card Example -->
                                    <div class="col-xl-3 col-md-6 mb-4">
                                    <c:if test="${requestScope.invoiceCurrent.status == 1}">
                                        <c:set var="colorTab" value="success"></c:set>
                                        <c:set var="status" value="Đã thanh toán"/>
                                    </c:if>
                                    <c:if test="${requestScope.invoiceCurrent.status == 0}">
                                        <c:set var="colorTab" value="danger"></c:set>
                                        <c:set var="status" value="Chưa thanh toán"/>
                                    </c:if>
                                    <c:if test="${requestScope.invoiceCurrent.dueDate == null}">
                                        <c:set var="colorTab" value="secondary"></c:set>
                                        <c:set var="status" value="Không khả dụng"/>
                                    </c:if>
                                    <div class="card border-left-${colorTab} shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-${colorTab} text-uppercase mb-1">
                                                        Trạng thái
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${pageScope.status}</div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-comments fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--End Current month's billing information-->

                            <!-- Content Row -->

                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="chart-tab" data-toggle="tab" href="#chart" role="tab">Biều đồ</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="table-tab" data-toggle="tab" href="#table" role="tab">Bảng dịch vụ</a>
                                </li>
                            </ul>
                            <div class="tab-content" id="myTabContent">
                                <br>
                                <div class="tab-pane fade show active" id="chart" role="tabpanel">
                                    <!-- chart here -->
                                    <div class="row">
                                        <!-- Area Chart -->
                                        <div class="col-xl-8 col-lg-7">
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

                                        <!-- Pie Chart -->
                                        <div class="col-xl-4 col-lg-5">
                                            <div class="card shadow mb-4">
                                                <!-- Card Header - Dropdown -->
                                                <div
                                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                                    <h6 class="m-0 font-weight-bold text-primary">Biểu đồ tỉ lệ dịch vụ trong hóa đơn</h6>
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
                                                    <div class="chart-pie pt-4 pb-2">
                                                        <canvas id="myPieChart"></canvas>
                                                    </div>
                                                    <div class="mt-4 text-center small">

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>   
                                </div>
                                <div class="tab-pane fade" id="table" role="tabpanel">
                                    <!-- table here -->
                                    <div class="card shadow mb-4">
                                        <div class="card-header py-3">
                                            <h6 class="m-0 font-weight-bold text-primary">Danh sách dịch vụ trong hóa đơn</h6>
                                        </div>
                                        <div class="card-body">
                                            <div class="table-responsive">
                                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                    <thead>
                                                        <tr>
                                                            <th>Tên</th>
                                                            <th>Loại dịch vụ</th>
                                                            <th>Ngày đăng kí</th>
                                                            <th>Ngày kết thúc</th>
                                                            <th>Đơn giá</th>
                                                            <th>Tỉ lệ</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.invoiceCurrent.getServiceContractList()}" var="serviceContract">
                                                            <tr>
                                                                <td>${serviceContract.getService().getName()}</td>
                                                                <td>${serviceContract.getService().getType()}</td>
                                                                <td><fmt:formatDate pattern="dd/MM/YYY" value="${serviceContract.getStartDate()}"></fmt:formatDate></td>
                                                                <td><fmt:formatDate pattern="dd/MM/YYY" value="${serviceContract.getEndDate()}"></fmt:formatDate></td>
                                                                <td data-order="${serviceContract.getAmount()}"><fmt:formatNumber value="${serviceContract.getAmount()}" type="number" maxFractionDigits="0"></fmt:formatNumber> VNĐ</td>
                                                                <td data-order="${serviceContract.getAmount()/requestScope.invoiceCurrent.getAmount()}"><fmt:formatNumber value="${serviceContract.getAmount()/requestScope.invoiceCurrent.getAmount()}" type="percent" maxFractionDigits="0"></fmt:formatNumber></td>
                                                                </tr>
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
                </div>
            </div>

            <!-- Bootstrap core JavaScript-->
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="vendor/chart.js/Chart.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="js/sb-admin-2.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="vendor/jquery-easing/jquery.easing.min.js"></script> 

            <!-- Page level plugins -->
            <script src="vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!-- Page level custom scripts -->
            <script src="js/demo/datatables-demo.js"></script>
            <script>
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
                <c:forEach items="${requestScope.invoiceCurrent.getServiceContractList()}" var="serviceContract">
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
