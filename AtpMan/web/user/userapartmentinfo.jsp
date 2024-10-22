<%-- 
    Document   : userapartmentinfo
    Created on : Oct 15, 2024, 12:35:59 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                    <a class="nav-link" href="user/userhome">
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
                        <fmt:setLocale value = "en_US"/>
                        <div id="ajaxcontent">
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <h1 class="h1 mb-0 text-gray-800"><b>Thông tin căn hộ</b></h1>
                            </div>
                            <div class="col-xl-12 col-md-12 mb-12">
                                <div class="row">
                                    <div class="card border-left-success shadow h-100 py-2">
                                        <div class="btn-group dropend">
                                            <div class="card-body">
                                                <div class="row no-gutters align-items-center">
                                                    <div class="col mr-2">
                                                        <!--building information-->
                                                        <div class="row">
                                                            <div class="h2 font-weight-bold text-success text-uppercase mb-1 col-xxl-2 col-xl-3 col-lg-4 col-md-5">
                                                                Tòa Nhà:
                                                            </div>
                                                            <div class="col-xxl-3 col-xl-4 col-lg-6 col-md-6">
                                                                <select id="buildingID" name="buildingID" class="form-select h2 font-weight-bold text-success text-uppercase mb-1" aria-label="Default select example" onchange="changeSelect()">
                                                                    <c:if test="${sessionScope.user.isOwner == 1}">
                                                                        <c:forEach items="${requestScope.buildingList}" var="building">
                                                                            <option ${(requestScope.building.buildingID == building.buildingID) ? 'selected':''} value="${building.buildingID}">${building.name}</option>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                    <c:if test="${sessionScope.user.isOwner == 0}">
                                                                        <option value="${requestScope.building.buildingID}">${requestScope.building.name}</option>
                                                                    </c:if>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-900">
                                                            Số Tầng:  &nbsp; <span class="h5 mb-0 font-weight-bold text-gray-700">${requestScope.building.numFloor} tầng</span>
                                                        </div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-900">
                                                            Số Căn hộ: &nbsp; <span class="h5 mb-0 font-weight-bold text-gray-700">${requestScope.building.numApartment} căn hộ</span> 
                                                        </div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-900">
                                                            Địa chỉ: &nbsp; <span class="h5 mb-0 font-weight-bold text-gray-700">${requestScope.building.address}</span>
                                                        </div>
                                                        <br>
                                                        <!--apartment information-->
                                                        <div class="row">
                                                            <div class="h3 font-weight-bold text-primary text-uppercase mb-1 col-xxl-2 col-xl-3 col-lg-4 col-md-5">
                                                                Căn hộ:
                                                            </div>
                                                            <div class="col-xxl-3 col-xl-4 col-lg-6 col-md-6">
                                                                <select id="apartmentID" name="apartmentID" class="form-select h2 font-weight-bold text-primary text-uppercase mb-1" aria-label="Default select example" onchange="changeSelect()">
                                                                    <c:if test="${sessionScope.user.isOwner == 1}">
                                                                        <c:forEach items="${requestScope.building.apartmentList}" var="apartment">
                                                                            <option ${(requestScope.apartment.apartmentID == apartment.apartmentID) ? 'selected' : ''} value="${apartment.apartmentID}">${apartment.apartmentNumber}</option>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                    <c:if test="${sessionScope.user.isOwner == 0}">
                                                                        <option value="${requestScope.apartment.apartmentID}">${requestScope.apartment.apartmentNumber}</option>
                                                                    </c:if>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-900 card1">
                                                            Loại căn hộ: &nbsp; <span class="h5 mb-0 font-weight-bold text-gray-700">${requestScope.apartment.apartmentType}</span>
                                                        </div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-900 card1">
                                                            Tầng: &nbsp; <span class="h5 mb-0 font-weight-bold text-gray-700">${requestScope.apartment.floor} </span>
                                                        </div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-900 card1">
                                                            Tổng diện tích: &nbsp; <span class="h5 mb-0 font-weight-bold text-gray-700">${requestScope.apartment.area} m2   </span>
                                                        </div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-900 card1">
                                                            Giá trị căn hộ: &nbsp; <span class="h5 mb-0 font-weight-bold text-gray-700"><fmt:formatNumber value=" ${requestScope.apartment.price}" type="number" maxFractionDigits="0"></fmt:formatNumber> VNĐ   </span>
                                                            </div>
                                                            <div class="h5 mb-0 font-weight-bold text-gray-900 card1">
                                                                Tổng số người ở: &nbsp; <span class="h5 mb-0 font-weight-bold text-gray-700">${requestScope.customerList.size()} người</span>
                                                        </div>
                                                    </div>
                                                    <div class="col-auto">
                                                        <i class="fas fa-building fa-10x text-gray-300"></i>
                                                    </div> 
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> 
                            <br>
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <h1 class="h1 mb-0 text-gray-800"><b>Thông tin cư dân</b></h1>
                            </div>
                            <div class="card shadow mb-4">
                                <div class="card-header py-3 row">
                                    <h5 class="m-0 font-weight-bold text-primary col-md-8">Danh sách cư dân đăng ký trong căn hộ</h5>
                                    <div class="col-md-4">
                                        <div class="input-group rounded ">
                                            <!--reset the current page to 1 cause of search can reduce the number of page-->
                                            <input id="searchService" name="searchService" type="text" value="" oninput="handleSearch($('#searchTable .pagination .page-item.active button.page-link').val())" class="form-control" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                                            <div class="input-group-append">
                                                <span class="input-group-text btn-primary border-0" id="search-addon">
                                                    <i class="fas fa-search"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="searchTable" class="card-body">
                                    <div>
                                        <table class="table table-striped table-hover table-bordered">
                                            <thead style="background-color: #4e73df; color: white">
                                                <tr>
                                                    <th>#</th>
                                                    <th>Tên</th>
                                                    <th>Tuổi</th>
                                                    <th>Email</th>
                                                    <th>Số điện thoại</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="count" value="0"/>
                                                <c:forEach items="${requestScope.customerList}" var="resident">
                                                    <c:set var="count" value="${count + 1}"/>
                                                    <tr>
                                                        <td>${count}</td>
                                                        <td>${resident.name}</td>
                                                        <td>${resident.age}</td>
                                                        <td>${resident.email}</td>
                                                        <td>${resident.phoneNumber}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="d-flex flex-row-reverse">
                                            <nav aria-label="Page navigation">
                                                <ul class="pagination justify-content-start">
                                                    <c:if test="${requestScope.currentServicePage > 1}">
                                                        <li class="page-item">
                                                            <button class="page-link" value="${requestScope.currentServicePage - 1}" onclick="handleSearch(this.value)">Previous</button>
                                                        </li>
                                                    </c:if>

                                                    <c:forEach var="i" begin="1" end="${requestScope.totalServicePages}">
                                                        <li class="page-item ${i == requestScope.currentServicePage ? 'active' : ''}">
                                                            <button class="page-link" value="${i}" onclick="handleSearch(this.value)">${i}</button>
                                                        </li>
                                                    </c:forEach>

                                                    <c:if test="${requestScope.currentServicePage < requestScope.totalServicePages}">
                                                        <li class="page-item">
                                                            <button class="page-link" value="${requestScope.currentServicePage + 1}" onclick="handleSearch(this.value)">Previous</button>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </nav>
                                        </div>
                                    </div>
                                </div>   
                            </div>
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <h1 class="h1 mb-0 text-gray-800"><b>Thông tin dịch vụ</b></h1>
                            </div>
                            <div class="card shadow mb-4">
                                <div class="card-header py-3 row">
                                    <h5 class="m-0 font-weight-bold text-primary col-md-8">Danh sách dịch vụ đã đăng ký trong căn hộ</h5>
                                    <div class="col-md-4">
                                        <div class="input-group rounded ">
                                            <!--reset the current page to 1 cause of search can reduce the number of page-->
                                            <input id="searchService" name="searchService" type="text" value="" oninput="handleSearch($('#searchTable .pagination .page-item.active button.page-link').val())" class="form-control" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                                            <div class="input-group-append">
                                                <span class="input-group-text btn-primary border-0" id="search-addon">
                                                    <i class="fas fa-search"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="searchTable" class="card-body">
                                    <div>
                                        <table class="table table-striped table-hover table-bordered">
                                            <thead style="background-color: #4e73df; color: white">
                                                <tr>
                                                    <th>#</th>
                                                    <th>Tên</th>
                                                    <th>Loại dịch vụ</th>
                                                    <th>Ngày đăng kí</th>
                                                    <th>Ngày kết thúc</th>
                                                    <th>Đơn giá</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="countServiceTable" value="0"/>
                                                <c:set var="total" value="0"/>
                                                <c:forEach items="${requestScope.serviceContractList}" var="serviceContract">
                                                    <c:set var="countServiceTable" value="${countServiceTable + 1}"/>
                                                    <c:set var="total" value="${total + serviceContract.getAmount()}"/>
                                                    <tr>
                                                        <td>${countServiceTable}</td>
                                                        <td>${serviceContract.getService().getName()}</td>
                                                        <td>${serviceContract.getService().getType()}</td>
                                                        <td><fmt:formatDate pattern="dd/MM/YYY" value="${serviceContract.getStartDate()}"></fmt:formatDate></td>
                                                        <td><fmt:formatDate pattern="dd/MM/YYY" value="${serviceContract.getEndDate()}"></fmt:formatDate></td>
                                                        <td><fmt:formatNumber value="${serviceContract.getAmount()}" type="number" maxFractionDigits="0"></fmt:formatNumber> VNĐ</td>
                                                        </tr>
                                                </c:forEach>
                                            </tbody>
                                            <tfoot style="background-color: #4e73df; color: white">
                                                <tr>
                                                    <th colspan="6">Tổng tiền dịch vụ: <fmt:formatNumber value="${total}" type="number" maxFractionDigits="0"></fmt:formatNumber> VNĐ</th>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                            <div class="d-flex flex-row-reverse">
                                                <nav aria-label="Page navigation">
                                                    <ul class="pagination justify-content-start">
                                                    <c:if test="${requestScope.currentServicePage > 1}">
                                                        <li class="page-item">
                                                            <button class="page-link" value="${requestScope.currentServicePage - 1}" onclick="handleSearch(this.value)">Previous</button>
                                                        </li>
                                                    </c:if>

                                                    <c:forEach var="i" begin="1" end="${requestScope.totalServicePages}">
                                                        <li class="page-item ${i == requestScope.currentServicePage ? 'active' : ''}">
                                                            <button class="page-link" value="${i}" onclick="handleSearch(this.value)">${i}</button>
                                                        </li>
                                                    </c:forEach>

                                                    <c:if test="${requestScope.currentServicePage < requestScope.totalServicePages}">
                                                        <li class="page-item">
                                                            <button class="page-link" value="${requestScope.currentServicePage + 1}" onclick="handleSearch(this.value)">Previous</button>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </nav>
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
        <script src="./vendor/jquery/jquery.min.js"></script>
        <script src="./"></script>
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

        <!-- Page level custom scripts -->
        <script src="./js/demo/datatables-demo.js"></script>
        <script>
                                                                function changeSelect() {
                                                                    let buildingId = $("#buildingID").val();
                                                                    let apartmentId = $("#apartmentID").val();
                                                                    console.log("buildingID ", buildingId);
                                                                    console.log("apartmentID ", apartmentId);
                                                                    $.ajax({
                                                                        url: "/AtpMan/userapartmentinfoajax",
                                                                        type: "get", //send it through post method
                                                                        data: {
                                                                            apartmentID: apartmentId,
                                                                            buildingID: buildingId
                                                                        },
                                                                        success: function (data) {
                                                                            $("#ajaxcontent").html(data);
                                                                            console.log("success");
//                                                            generate.innerHTML = data;
                                                                        },
                                                                        error: function (xhr) {
                                                                            console.log("error");
                                                                            //Do Something to handle error
                                                                        }
                                                                    });
                                                                }
        </script>
    </body>
</html>
