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
        <link href="../css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">-->
        <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
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
            .pay div{
                text-decoration: none;
            }
            .pay div:hover{
                color: #015fc9 !important;
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
                    <%@include file = "../topbar.jsp" %>
                    <!-- End of Topbar -->
                    <div class="container-fluid">
                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h1 mb-0 text-gray-800"><b>Trang chủ</b></h1>
                        </div>
                        <!-- Split dropend button -->

                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="row">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="btn-group dropend">
                                        <a class="chooseApt" href="/AtpMan/userapartmentinfo?apartmentID=${requestScope.apartment.apartmentID}&buildingID=${requestScope.apartment.buildingID}" style="text-decoration: none">
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
                                                    <a class="chooseApt" href="userhome?apartmentID=${apartment.apartmentID}" style="text-decoration: none">
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

                        <!-- Total information for one year -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <div class="d-sm-flex align-items-center justify-content-between mb-4 col-xl-5 col-md-5">
                                    <h1 id="currentMonth" class="h3 mb-0 text-gray-800 text-primary ">Tổng hóa đơn trong năm ${requestScope.currentYear}</h1>
                                </div>
                                <form class="d-flex col-xl-7 col-md-7" action="userhome" method="GET" id="chooseMonthYear">
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
                            <div id="" class="card-body row">
                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-primary shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Tổng hóa đơn (${requestScope.currentYear})
                                                    </div>
                                                    <fmt:setLocale value = "en_US"/>
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
                                <!--Paid amount billing information year-->
                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-success shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                        Tổng thanh toán trong tháng
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <fmt:formatNumber value="${requestScope.totalAmountCurrent} " type="number" maxFractionDigits="0"></fmt:formatNumber>
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
                                                            Tổng hóa đơn trong tháng
                                                        </div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        ${requestScope.numOfInvoiceInMonth}
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
                            </div>
                            <!-- End Billing information for one year -->
                        </div>
<!--                                    <div class="card border-left-${colorTab} shadow h-100 py-2">
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
                            </div>-->

                        <!-- Content Row -->

                        <!-- News -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800 text-primary">Thông tin cập nhật</h1>
                        </div>
                        <!-- Modal Search Start -->
                        <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-fullscreen">
                                <div class="modal-content rounded-0">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body d-flex align-items-center bg-primary">
                                        <div class="input-group w-75 mx-auto d-flex">
                                            <input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                                            <span id="search-icon-1" class="btn bg-light border nput-group-text p-3"><i class="fa fa-search"></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <!-- News Carousel -->
                        <div class="row justify-content-center">
                            <div class="col-md-10">
                                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                                    <ol class="carousel-indicators">
                                        <c:forEach var="news" items="${newsList}" varStatus="status">
                                            <li data-target="#carouselExampleIndicators" data-slide-to="${status.index}" class="${status.first ? 'active' : ''}"></li>
                                            </c:forEach>
                                    </ol>
                                    <div class="carousel-inner">
                                        <c:forEach var="banner" items="${newsBanner}" varStatus="status">
                                            <div class="carousel-item ${status.first ? 'active' : ''}">
                                                <a href="<c:out value='NewsDetail?id=${banner.newsID}'/>">   
                                                    <img class="d-block w-100" src="../<c:out value='${banner.newsImg}'/>" alt="Slide ${status.index + 1}">
                                                    <div class="carousel-caption d-none d-md-block">
                                                        <h5 style="color:#FFFF00;"><c:out value='${banner.newsTitle}'/></h5>
                                                        <p style="color:#FFFF00;"><c:out value='${banner.description}'/></p>
                                                    </div>
                                                </a>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!-- News Carousel End -->


                        <!-- Recent News Section Start -->
                        <section    class="section bg-light py-5">
                            <div class="container">
                                <div id="newsContent" class="row">
                                    <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
                                        <div class="page-wrapper">
                                            <div class="blog-top clearfix">
                                                <h4 class="pull-left">Recent News <a href="#"><i class="fa fa-rss"></i></a></h4>
                                            </div><!-- end blog-top -->

                                            <div class="blog-list clearfix">
                                                <!-- Loop through the news list -->
                                                <c:forEach  items="${news}" var="newsItem">
                                                    <div class="blog-box row">
                                                        <div class="col-md-4">
                                                            <div class="post-media">
                                                                <a href="/AtpMan/NewsDetail?id=${newsItem.newsID}" title="">
                                                                    <img src="../${newsItem.newsImg}" alt="" class="img-fluid">
                                                                    <div class="hovereffect"></div>
                                                                </a>
                                                            </div><!-- end media -->
                                                        </div><!-- end col -->

                                                        <div class="blog-meta big-meta col-md-8">
                                                            <h4><a href="/AtpMan/NewsDetail?id=${newsItem.newsID}" title="">
                                                                    ${newsItem.newsTitle}
                                                                </a></h4>
                                                            <p>${newsItem.description}</p>

                                                            <small class="firstsmall"><a class="bg-orange" href="#" title="">${newsItem.newsCategoryName} - </a></small>
                                                            <small>
                                                                <fmt:formatDate value="${newsItem.postDate}" pattern="EEEE dd/MM/yyyy HH:mm" />
                                                            </small>
                                                            <small>  by ${newsItem.staffName}</small>
                                                        </div><!-- end meta -->
                                                    </div><!-- end blog-box -->

                                                    <hr class="invis">
                                                </c:forEach>
                                            </div><!-- end blog-list -->
                                        </div><!-- end page-wrapper -->
                                        <!-- Pagination -->
                                        <div class="row">
                                            <div class="col-md-12">
                                                <nav aria-label="Page navigation">
                                                    <ul class="pagination justify-content-start">
                                                        <c:if test="${currentPage > 1}">
                                                            <li class="page-item">
                                                                <button class="page-link" value="${currentPage - 1}" onclick="paging(this)">Previous</button>
                                                            </li>
                                                        </c:if>

                                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                                <button class="page-link" value="${i}" onclick="paging(this)">${i}</button>
                                                            </li>
                                                        </c:forEach>

                                                        <c:if test="${currentPage < totalPages}">
                                                            <li class="page-item">
                                                                <button class="page-link" value="${currentPage + 1}" onclick="paging(this)">Next</button>
                                                            </li>
                                                        </c:if>
                                                    </ul>
                                                </nav>
                                            </div><!-- end col -->
                                        </div><!-- end row -->
                                    </div><!-- end col -->
                                </div><!-- end row -->
                            </div><!-- end container -->
                        </section>
                    </div>
                </div>
            </div>

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
                            <a class="btn btn-primary" href="/AtpMan/logout">Logout</a>
                        </div>
                    </div>
                </div>
            </div>                                                  

            <!-- Bootstrap core JavaScript-->
            <script src="../vendor/jquery/jquery.min.js"></script>
            <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
            <script src="../vendor/chart.js/Chart.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="../js/sb-admin-2.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="../vendor/jquery-easing/jquery.easing.min.js"></script> 

            <!-- Page level plugins -->
            <script src="../vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!--Page level custom scripts--> 
            <script src="../js/demo/datatables-demo.js"></script>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

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

                                                                    function submitMonth() {
                                                                        document.getElementById('chooseMonthYear').submit();
                                                                    }

                                                                    function submitPayment() {
                                                                        document.getElementById("paysubmit").submit();
                                                                    }
            </script>
            <script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
            <df-messenger
                intent="WELCOME"
                chat-title="APT MAN"
                agent-id="6708e5b6-3bfa-416e-adc7-1fea6c2d914a"
                language-code="en"
                ></df-messenger>
        </div>
    </body>
</html>
