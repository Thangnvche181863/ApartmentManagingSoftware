<%-- 
    Document   : sidebar
    Created on : Sep 20, 2024, 5:58:51 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${userRole == 'staff'}">
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">WELCOME <sup>2</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="/AtpMan/managerPage">
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
            <a class="nav-link" href="home.jsp">
                <i class="fas fa-house-user fa-table"></i>
                <span>Home</span></a>
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
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#income_expenditure" 
               aria-expanded="true" aria-controls="collapsePages">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Quản lý thu chi</span>
            </a>
            <div id="income_expenditure" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="/AtpMan/managerinvoicestatistic">Quản lý hóa đơn</a>
                    <a class="collapse-item" href="/AtpMan/statistic">Thống Kê Phụ Phí</a>
                    <a class="collapse-item" href="/AtpMan/costStatistic">Tổng Hợp Phụ Phí</a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Tables -->
        <li class="nav-item active">
            <a class="nav-link" href="servicelist">
                <i class="fas fa-fw fa-table"></i>
                <span>Danh Sách Dịch Vụ</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseCreateAccount"
               aria-expanded="true" aria-controls="collapseCreateAccount">
                <i class="fas fa-fw fa-folder"></i>
                <span>Tạo tài khoản</span>
            </a>
            <div id="collapseCreateAccount" class="collapse" aria-labelledby="headingCreateAccount" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Tạo tài khoản</h6>
                    <a class="collapse-item" href="createaccount">Người dùng</a>
                    <a class="collapse-item" href="register.html">Nhân viên</a>
                </div>
            </div>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseNews"
               aria-expanded="true" aria-controls="collapseNews">
                <i class="fas fa-fw fa-folder"></i>
                <span>Quản lý Tin</span>
            </a>
            <div id="collapseNews" class="collapse" aria-labelledby="headingNews" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Mục lục:</h6>
                    <a class="collapse-item" href="newsmanage">Quản lý tin</a>
                    <a class="collapse-item" href="newscategorymanage">Quản lý mục tin</a>
                    <a class="collapse-item" href="newscommentmanage">Quản lý bình luận</a>
                    <h6 class="collapse-header">Trang Chủ:</h6>
                    <a class="collapse-item" href="News">Trang tin tức</a>
                    <a class="collapse-item" href="homepageGuest">Trang chủ cho khách</a>
                </div>
            </div>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="/AtpMan/residentmanage">
                <i class="fas fa-fw fa-users"></i>
                <span>Quản lý cư dân</span></a>
        </li>

        <!-- Nav Item - Tables -->
        <li class="nav-item">
            <a class="nav-link" href="managerPage">
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
</c:if>
<c:if test="${userRole == 'customer'}">
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/AtpMan/user/userhome">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-building"></i>
            </div>
            <div class="sidebar-brand-text mx-3">APT MAN</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="/AtpMan/user/userhome">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Trang chủ</span></a>
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

        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseInvoiceStat"
               aria-expanded="true" aria-controls="collapseInvoiceStat">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Thống kê</span>
            </a>
            <div id="collapseInvoiceStat" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="/AtpMan/invoicestatistic">Thống kê hóa đơn</a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Regist service -->
        <li class="nav-item">
            <a class="nav-link" href="/AtpMan/registServiceTenant">
                <i class="fas fa-fw fa-table"></i>
                <span>Đăng Kí Dịch Vụ</span></a>
        </li>

        <!-- Nav Item - Tables -->
        <li class="nav-item">
            <a class="nav-link" href="servicelist">
                <i class="fas fa-fw fa-table"></i>
                <span>Service List</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div> 
    </ul>
</c:if>
