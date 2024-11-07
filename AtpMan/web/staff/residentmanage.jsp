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
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.user.name}</span>
                                    <img class="img-profile rounded-circle"
                                         src="./img/undraw_profile.svg">
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
                                    <a class="dropdown-item" href="/AtpMan/logout">
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
                            <h1 class="h1 mb-0 text-gray-800"><b>Danh sách cư dân</b></h1>
                            <a href="/AtpMan/unpaidinvoicestat" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                                Hóa đơn chưa thu        
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
                                                        Tổng số cư dân</div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <fmt:setLocale value = "en_US"/>
                                                        ${requestScope.totalResident} Cư dân
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
                                                        Số tòa nhà 
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        ${requestScope.totalBuilding} Tòa
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
                                                        Số căn hộ 
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        ${requestScope.totalApartment} Căn hộ
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
                                                        Tổng chờ duyệt 
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        ${requestScope.totalResidentWaitForRegis} Người 
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
                                <h4 id="currentMonth" class="h4 mb-0 text-gray-800 text-primary font-weight-bold col-xl-5 col-md-5">Tìm kiếm cư dân</h4>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body row">
                                <div class="col-md-6 row">
                                    <div class="col-12 row">
                                        <label for="selectBuilding" class="col-sm-4 col-form-label font-weight-bold">Tòa nhà</label>
                                        <div class="col-sm-8">
                                            <!--bootstrap 4 -->
                                            <select id="selectBuilding" name="selectBuilding" class="form-control"> 
                                                <option selected value="all">Tất cả</option>
                                                <c:forEach items="${requestScope.buildingList}" var="building">
                                                    <option value="${building.buildingID}">${building.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-6 row">
                                    <div class="col-12 row">
                                        <label for="apartmentNumber" class="col-sm-4 col-form-label font-weight-bold">Căn hộ</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="apartmentNumber" name="apartmentNumber" placeholder="Nhập số căn hộ">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 row">
                                    <div class="col-12 row">
                                        <label for="residentName" class="col-sm-4 col-form-label font-weight-bold">Tên cư dân</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="residentName" name="residentName" placeholder="Nhập tên cư dân">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6 row">
                                    <div class="col-12 row">
                                        <label for="orderInfo" class="col-sm-4 col-form-label font-weight-bold">Loại cư dân</label>
                                        <div class="col-sm-8 row">
                                            <div class=" col-sm-1"></div>
                                            <div class="form-check col-sm-5">
                                                <input class="form-check-input" type="checkbox" value="1" id="owner" name="owner" checked="">
                                                <label class="form-check-label" for="owner">
                                                    Chủ hộ
                                                </label>
                                            </div>
                                            <div class="form-check col-sm-5">
                                                <input class="form-check-input" type="checkbox" value="0" id="tenant" name="tenant" checked="">
                                                <label class="form-check-label" for="tenant">
                                                    Người thuê
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 row">
                                    <div class="col-12 row d-flex">
                                        <input class="btn btn-primary" type="submit" value="Tìm kiếm" onclick="handleSearch($('#activeResidentTable .pagination .page-item.active button.page-link').val())">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Content Row -->

                        <!-- table here -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h5 class="m-0 font-weight-bold text-primary text-gray-800 col-md-9">Danh sách cư dân đang hoạt động</h5>
                                <div class="col-md-2">
                                    <select id="residentPerPage" name="residentPerPage" class="form-select font-weight-bold text-primary text-uppercase" aria-label="Default select example" onchange="handleSearch($('#activeResidentTable .pagination .page-item.active button.page-link').val())">
                                        <option value="5">Số lượng hiển thị: 5</option>
                                        <option value="10">Số lượng hiển thị: 10</option>
                                        <option value="25">Số lượng hiển thị: 25</option>
                                        <option value="50">Số lượng hiển thị: 50</option>
                                    </select>
                                </div>
                            </div>
                            <div id="activeResidentTable" class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead style="background-color: #4e73df; color: white">
                                            <tr>
                                                <th>#</th>
                                                <th>Họ Tên</th>
                                                <th>Ngày sinh</th>
                                                <th>Email</th>
                                                <th>Số điện thoại</th>
                                                <th>Căn hộ</th>
                                                <th>Loại cư dân</th>
                                                <th>Trạng thái</th>
                                                <th>Hành động</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="countInvoiceTable" value="0"/>
                                            <c:forEach items="${requestScope.customerList}" var="resident">
                                                <c:set var="countInvoiceTable" value="${countInvoiceTable+1}"/>
                                                <tr>
                                                    <td>${countInvoiceTable}</td>
                                                    <td>${resident.name}</td>
                                                    <td><fmt:formatDate value="${resident.dob}" pattern="dd/MM/yyyy"></fmt:formatDate></td>
                                                    <td>${resident.email}</td>
                                                    <td>${resident.phoneNumber}</td>
                                                    <td>${resident.apartmentNumber}</td>
                                                    <td class="${resident.isOwner == 1 ? 'text-primary font-weight-bold': ''}">${resident.isOwner == 1 ? 'Chủ căn hộ': 'Người ở'}</td>
                                                    <td>${resident.status == 1 ? 'Đang cư trú' : 'Dừng cư trú'}</td>
                                                    <td>
                                                        <form id="removeForm-${resident.customerID}" action="residentmanage" method="post">
                                                            <input type="hidden" name="customerId" value="${resident.customerID}">
                                                            <input type="hidden" name="action" value="remove">
                                                        </form>
                                                        <input class="btn btn-danger" type="submit" value="Xóa" onclick="handleRemove(${resident.customerID})">
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="d-flex flex-row-reverse">
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination justify-content-start">
                                                <c:if test="${1 > 1}">
                                                    <li class="page-item">
                                                        <button class="page-link" value="${requestScope.currentPage - 1}" onclick="handleSearch(this.value)">Previous</button>
                                                    </li>
                                                </c:if>

                                                <c:forEach var="i" begin="1" end="${requestScope.totalPage}">
                                                    <li class="page-item ${i == 1 ? 'active' : ''}">
                                                        <button class="page-link" value="${i}" onclick="handleSearch(this.value)">${i}</button>
                                                    </li>
                                                </c:forEach>

                                                <c:if test="${1 < requestScope.totalPage}">
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
                        <div class="card shadow mb-4">
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h5 class="m-0 font-weight-bold text-primary text-gray-800 col-md-9">Danh sách cư dân đang chờ duyệt</h5>
                                <div class="col-md-2">
                                    <select id="residentPerPage" name="residentPerPage" class="form-select font-weight-bold text-primary text-uppercase" aria-label="Default select example" onchange="handleSearch($('#activeResidentTable .pagination .page-item.active button.page-link').val())">
                                        <option value="5">Số lượng hiển thị: 5</option>
                                        <option value="10">Số lượng hiển thị: 10</option>
                                        <option value="25">Số lượng hiển thị: 25</option>
                                        <option value="50">Số lượng hiển thị: 50</option>
                                    </select>
                                </div>
                            </div>
                            <div id="registResidentTable" class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead style="background-color: #4e73df; color: white">
                                            <tr>
                                                <th>#</th>
                                                <th>Họ Tên</th>
                                                <th>Ngày sinh</th>
                                                <th>Email</th>
                                                <th>Số điện thoại</th>
                                                <th>Căn hộ</th>
                                                <th>Loại cư dân</th>
                                                <th>Trạng thái</th>
                                                <th>Hành động</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="countInvoiceTable" value="0"/>
                                            <c:forEach items="${requestScope.registResidentList}" var="resident2">
                                                <c:set var="countInvoiceTable" value="${countInvoiceTable+1}"/>
                                                <tr>
                                                    <td>${countInvoiceTable}</td>
                                                    <td>${resident2.name}</td>
                                                    <td><fmt:formatDate value="${resident2.dob}" pattern="dd/MM/yyyy"></fmt:formatDate></td>
                                                    <td>${resident2.email}</td>
                                                    <td>${resident2.phoneNumber}</td>
                                                    <td>${resident2.apartmentNumber}</td>
                                                    <td class="${resident2.isOwner == 1 ? 'text-primary font-weight-bold': ''}">${resident2.isOwner == 1 ? 'Chủ căn hộ': 'Người ở'}</td>
                                                    <td>Đang chờ duyệt</td>
                                                    <td>
                                                        <form id="acceptForm-${resident2.customerID}" action="residentmanage" method="post">
                                                            <input type="hidden" name="customerId" value="${resident2.customerID}">
                                                            <input type="hidden" name="action" value="accept">
                                                        </form>
                                                        <form id="declineForm-${resident2.customerID}" action="residentmanage" method="post">
                                                            <input type="hidden" name="customerId" value="${resident2.customerID}">
                                                            <input type="hidden" name="action" value="decline">
                                                        </form>
                                                        <input class="btn btn-success" type="submit" value="Duyệt" onclick="handleAccept(${resident2.customerID})">
                                                        <input class="btn btn-danger" type="submit" value="Xóa" onclick="handleDecline(${resident2.customerID})">
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="d-flex flex-row-reverse">

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
                                                            let selectBuilding = $("#selectBuilding").val();
                                                            let apartmentNumber = $("#apartmentNumber").val();
                                                            let residentName = $("#residentName").val();
                                                            let owner = $("#owner").is(":checked") ? $("#owner").val() : null;
                                                            let tenant = $("#tenant").is(":checked") ? $("#tenant").val() : null;
                                                            let currentPage = page;
                                                            let residentPerPage = $("#residentPerPage").val();

                                                            console.log("selectBuilding ", selectBuilding);
                                                            console.log("apartmentNumber ", apartmentNumber);
                                                            console.log("residentName ", residentName);
                                                            console.log("owner ", owner);
                                                            console.log("tenant ", tenant);
                                                            console.log("currentPage ", currentPage);
                                                            console.log("residentPerPage ", residentPerPage);
                                                            $.ajax({
                                                                url: "/AtpMan/activeresidenttableajax",
                                                                type: "get", //send it through post method
                                                                data: {
                                                                    selectBuilding: selectBuilding,
                                                                    apartmentNumber: apartmentNumber,
                                                                    residentName: residentName,
                                                                    owner: owner,
                                                                    tenant: tenant,
                                                                    currentPage: currentPage,
                                                                    residentPerPage: residentPerPage
                                                                },
                                                                success: function (data) {
                                                                    $("#activeResidentTable").html(data);
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
                                                                url: "/AtpMan/invoicestatservicetableajax",
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

                                                        function handleRemove(customerId) {
                                                            if (window.confirm('Bạn có muốn loại bỏ cư dân này?')) {
                                                                document.getElementById('removeForm-' + customerId).submit();
                                                            }
                                                        }
                                                        function handleDecline(customerId) {
                                                            if (window.confirm('Bạn có muốn từ chối cư dân này?')) {
                                                                document.getElementById('declineForm-' + customerId).submit();
                                                            }
                                                        }
                                                        function handleAccept(customerId) {
                                                            document.getElementById('acceptForm-' + customerId).submit();
                                                        }

            </script>
    </body>
</html>
