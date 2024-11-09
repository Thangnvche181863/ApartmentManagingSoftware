<%-- 
    Document   : userapartmentinfo
    Created on : Oct 15, 2024, 12:35:59 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
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
            <jsp:include page="../sidebar.jsp"/>
            <!-- End of Sidebar --> 
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <!-- Topbar -->
                    <jsp:include page="../topbar.jsp"/>
                    <!-- End of Topbar -->
                    <div class="container-fluid">
                        <fmt:setLocale value = "en_US"/>
                        <div id="ajaxcontent">
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <h1 class="h1 mb-0 text-gray-800"><b>Thông tin căn hộ</b></h1>
                            </div>
                            <div class="col-xl-12 col-md-12 mb-12">
                                <div class="row">
                                    <form id="submitForm" action="/AtpMan/userapartmentinfo" method="get">
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
                                                                    <select id="buildingID" name="buildingID" class="form-select h2 font-weight-bold text-success text-uppercase mb-1" aria-label="Default select example" onchange="submit()">
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
                                                                    <select id="apartmentID" name="apartmentID" class="form-select h2 font-weight-bold text-primary text-uppercase mb-1" aria-label="Default select example" onchange="submit()">
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
                                                                    Tổng số người ở: &nbsp; 
                                                                <c:if test="${requestScope.customerList.size()>0}"><span class="h5 mb-0 font-weight-bold text-gray-700">${requestScope.customerList.size()} người</span></c:if>
                                                                <c:if test="${requestScope.customerList.size()==0}"><span class="h5 mb-0 font-weight-bold text-gray-700">Chưa có người ở</span></c:if>
                                                                </div>
                                                            </div>
                                                            <div class="col-auto">
                                                                <i class="fas fa-building fa-10x text-gray-300"></i>
                                                            </div> 
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div> 
                                <br>
                                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                    <h1 class="h1 mb-0 text-gray-800"><b>Thông tin cư dân</b></h1>
                                    <button class="btn btn-primary" data-toggle="modal" data-target="#addResidentModal">Thêm cư dân</button>
                                </div>
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3 row">
                                        <h5 class="m-0 font-weight-bold text-primary col-md-5">Danh sách cư dân đăng ký trong căn hộ</h5>
                                        <div class="col-md-3">
                                            <select id="residentPerPage" name="residentPerPage" class="form-select h2 font-weight-bold text-primary text-uppercase mb-1" aria-label="Default select example" onchange="handleResidentTable($('#residentTable .pagination .page-item.active button.page-link').val())">
                                                <option value="5">Số lượng hiển thị: 5</option>
                                                <option value="10">Số lượng hiển thị: 10</option>
                                            </select>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="input-group rounded ">
                                                <!--reset the current page to 1 cause of search can reduce the number of page-->
                                                <input id="searchResident" name="searchResident" type="text" value="" oninput="handleResidentTable($('#residentTable .pagination .page-item.active button.page-link').val())" class="form-control" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                                                <div class="input-group-append">
                                                    <span class="input-group-text btn-primary border-0" id="search-addon">
                                                        <i class="fas fa-search"></i>
                                                    </span>
                                                </div>
                                                    </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="residentTable" class="card-body">
                                        <table class="table table-striped table-hover table-bordered">
                                            <thead style="background-color: #4e73df; color: white">
                                                <tr>
                                                    <th>#</th>
                                                    <th>Tên</th>
                                                    <th>Ngày sinh</th>
                                                    <th>Email</th>
                                                    <th>Số điện thoại</th>
                                                    <th>Ngày vào ở</th>
                                                    <th>Phân loại</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:if test="${not empty requestScope.customerList}">
                                                <c:set var="count" value="0"/>
                                                <c:forEach items="${requestScope.customerList}" var="resident">
                                                    <c:set var="count" value="${count + 1}"/>
                                                    <tr>
                                                        <td>${count}</td>
                                                        <td>${resident.name}</td>
                                                        <td><fmt:formatDate value="${resident.dob}" pattern="dd/MM/yyyy"/></td>
                                                        <td>${resident.email}</td>
                                                        <td>${resident.phoneNumber}</td>
                                                        <td><fmt:formatDate value="${resident.livingDate}" pattern="dd/MM/yyyy"/></td>
                                                        <td class="${resident.isOwner == 1 ? 'text-primary font-weight-bold' : ''}">${resident.isOwner == 1 ? 'Chủ sở hữu' : 'Người ở'}</td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty requestScope.customerList}">
                                                <tr>
                                                    <td colspan="7">Không có dữ liệu</td>
                                                </tr>
                                            </c:if>
                                        </tbody>
                                    </table>
                                    <div class="d-flex flex-row-reverse">
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination justify-content-start">
                                                <c:if test="${0 > 1}">
                                                    <li class="page-item">
                                                        <button class="page-link" value="${requestScope.currentResidentPage - 1}" onclick="handleResidentTable(this.value)">Previous</button>
                                                    </li>
                                                </c:if>

                                                <c:forEach var="i" begin="1" end="${requestScope.totalResidentPage}">
                                                    <li class="page-item ${i == 1 ? 'active' : ''}">
                                                        <button class="page-link" value="${i}" onclick="handleResidentTable(this.value)">${i}</button>
                                                    </li>
                                                </c:forEach>

                                                <c:if test="${1 < requestScope.totalResidentPage}">
                                                    <li class="page-item">
                                                        <button class="page-link" value="${2}" onclick="handleResidentTable(this.value)">Next</button>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>   
                            </div>
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <h1 class="h1 mb-0 text-gray-800"><b>Thông tin dịch vụ</b></h1>
                            </div>
                            <div class="card shadow mb-4">
                                <div class="card-header py-3 row">
                                    <h5 class="m-0 font-weight-bold text-primary col-md-5">Danh sách dịch vụ đã đăng ký trong căn hộ</h5>
                                    <div class="col-md-3">
                                        <select id="servicePerPage" name="servicePerPage" class="form-select font-weight-bold text-primary text-uppercase" aria-label="Default select example" onchange="handleServiceTable($('#serviceTable .pagination .page-item.active button.page-link').val())">
                                            <option value="5">Số lượng hiển thị: 5</option>
                                            <option value="10">Số lượng hiển thị: 10</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="input-group rounded ">
                                            <!--reset the current page to 1 cause of search can reduce the number of page-->
                                            <input id="searchService" name="searchService" type="text" value="" oninput="handleServiceTable($('#serviceTable .pagination .page-item.active button.page-link').val())" class="form-control" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                                            <div class="input-group-append">
                                                <span class="input-group-text btn-primary border-0" id="search-addon">
                                                    <i class="fas fa-search"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="serviceTable" class="card-body">
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
                                                <c:if test="${not empty requestScope.serviceContractList}">
                                                    <c:set var="countServiceTable" value="0"/>
                                                    <c:forEach items="${requestScope.serviceContractList}" var="serviceContract">
                                                        <c:set var="countServiceTable" value="${countServiceTable + 1}"/>
                                                        <tr>
                                                            <td>${countServiceTable}</td>
                                                            <td>${serviceContract.getService().getName()}</td>
                                                            <td>${serviceContract.getService().getType()}</td>
                                                            <td><fmt:formatDate pattern="dd/MM/YYY" value="${serviceContract.getStartDate()}"></fmt:formatDate></td>
                                                            <td><fmt:formatDate pattern="dd/MM/YYY" value="${serviceContract.getEndDate()}"></fmt:formatDate></td>
                                                            <td><fmt:formatNumber value="${serviceContract.getAmount()}" type="number" maxFractionDigits="0"></fmt:formatNumber> VNĐ</td>
                                                            </tr>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${empty requestScope.serviceContractList}">
                                                    <tr>
                                                        <td colspan="6">Không có dữ liệu dịch vụ</td>
                                                    </tr>
                                                </c:if>
                                            </tbody>
                                            <tfoot style="background-color: #4e73df; color: white" class="h5">
                                                <tr>
                                                    <th colspan="6">Tổng tiền dịch vụ: <fmt:formatNumber value="${requestScope.totalAmount}" type="number" maxFractionDigits="0"></fmt:formatNumber> VNĐ</th>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                            <div class="d-flex flex-row-reverse">
                                                <nav aria-label="Page navigation">
                                                    <ul class="pagination justify-content-start">
                                                    <c:if test="${requestScope.currentServicePage > 1}">
                                                        <li class="page-item">
                                                            <button class="page-link" value="${requestScope.totalServicePage - 1}" onclick="handleServiceTable(this.value)">Previous</button>
                                                        </li>
                                                    </c:if>

                                                    <c:forEach var="i" begin="1" end="${requestScope.totalServicePage}">
                                                        <li class="page-item ${i == 1 ? 'active' : ''}">
                                                            <button class="page-link" value="${i}" onclick="handleServiceTable(this.value)">${i}</button>
                                                        </li>
                                                    </c:forEach>

                                                    <c:if test="${1 < requestScope.totalServicePage}">
                                                        <li class="page-item">
                                                            <button class="page-link" value="${2}" onclick="handleServiceTable(this.value)">Next</button>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </nav>
                                        </div>
                                    </div>
                                </div>   
                            </div>
                        </div>

                        <!-- Modal -->
                        <div class="modal fade" id="addResidentModal" tabindex="-1" role="dialog" aria-labelledby="addResidentModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="addResidentModalLabel">Thêm cư dân</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form id="addResidentForm" method="POST" action="RegisResidentApartment">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="residentName">Tên</label>
                                                <input type="text" class="form-control" id="residentName" name="name" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="residentEmail">Email</label>
                                                <input type="email" class="form-control" id="residentEmail" name="email" >
                                            </div>
                                            <div class="form-group">
                                                <label for="residentPhoneNumber">Số điện thoại</label>
                                                <input type="text" class="form-control" id="residentPhoneNumber" name="phone" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="residentDob">Ngày sinh</label>
                                                <input type="date" class="form-control" id="residentDob" name="dob" required="">
                                            </div>
                                            <div class="form-group">

                                                <input type="hidden" type="number" class="form-control" id="apartmentID"  name="apartmentID" value="${requestScope.apartment.apartmentID}" required>

                                            </div>

                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                                            <button type="submit" class="btn btn-primary">Lưu</button>
                                        </div>


                                    </form>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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

                                                                function handleResidentTable() {
                                                                    let apartmentId = $("#apartmentID").val();
                                                                    let userSearchTerm = $("#searchResident").val();
                                                                    let residentPerPage = $("#residentPerPage").val();
                                                                    console.log("apartmentID ", apartmentId);
                                                                    console.log("searchResident ", userSearchTerm);
                                                                    console.log("residentPerPage ", residentPerPage);
                                                                    $.ajax({
                                                                        url: "/AtpMan/userapartmentinforesidenttableajax",
                                                                        type: "post", //send it through post method
                                                                        data: {
                                                                            apartmentID: apartmentId,
                                                                            userSearchTerm: userSearchTerm,
                                                                            residentPerPage: residentPerPage
                                                                        },
                                                                        success: function (data) {
                                                                            $("#residentTable").html(data);
                                                                            console.log("success");
//                                                            generate.innerHTML = data;
                                                                        },
                                                                        error: function (xhr) {
                                                                            console.log("error");
                                                                            //Do Something to handle error
                                                                        }
                                                                    });
                                                                }

                                                                function handleServiceTable(param) {
                                                                    let apartmentId = $("#apartmentID").val();
                                                                    let serviceSearchTerm = $("#searchService").val();
                                                                    let servicePerPage = $("#servicePerPage").val();
                                                                    let currentServicePage = param;
                                                                    console.log("apartmentID ", apartmentId);
                                                                    console.log("searchService ", serviceSearchTerm);
                                                                    console.log("servicePerPage ", servicePerPage);
                                                                    $.ajax({
                                                                        url: "/AtpMan/userapartmentinfoservicetableajax",
                                                                        type: "post", //send it through post method
                                                                        data: {
                                                                            apartmentID: apartmentId,
                                                                            serviceSearchTerm: serviceSearchTerm,
                                                                            currentServicePage: currentServicePage,
                                                                            servicePerPage: servicePerPage
                                                                        },
                                                                        success: function (data) {
                                                                            $("#serviceTable").html(data);
                                                                            console.log("success");
//                                                            generate.innerHTML = data;
                                                                        },
                                                                        error: function (xhr) {
                                                                            console.log("error");
                                                                            //Do Something to handle error
                                                                        }
                                                                    });
                                                                }
                                                                function submit() {
                                                                    document.getElementById("submitForm").submit();
                                                                }
        </script>
    </body>
</html>