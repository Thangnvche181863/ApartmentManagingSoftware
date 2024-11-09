<%-- 
    Document   : registServiceTenant
    Created on : Oct 26, 2024, 4:07:21 PM
    Author     : thang
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="DAO.ServiceDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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

        <!-- Icon Font Stylesheet -->
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
            />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
            rel="stylesheet"
            />

        <!-- Libraries Stylesheet -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>
    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <%@include file="../sidebar.jsp" %>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">


                    <!-- Topbar -->

                    <%@include file = "../topbar.jsp" %>

                    <!-- End of Topbar -->



                    <!-- Begin Page Content -->
                    <div class="container-fluid">


                        <div class="container-fluid feature bg-light" id="navigate">

                            <div class="container">

                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="row">
                                        <div class="card border-left-success shadow h-100 py-2">
                                            <div class="btn-group dropend">
                                                <!--<a class="chooseApt" href="/AtpMan/userapartmentinfo?apartmentID=${requestScope.apartment.apartmentID}&buildingID=${requestScope.apartment.buildingID}" style="text-decoration: none">-->
                                                <div class="card-body">
                                                    <div class="row no-gutters align-items-center">
                                                        <div class="col mr-2">
                                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1 card1" style="font-size: 1rem; margin-bottom: 0.5rem;">
                                                                Căn hộ
                                                            </div>
                                                            <div class="h4 mb-0 font-weight-bold text-gray-800 card1">
                                                                ${requestScope.apartment.apartmentNumber} - ${requestScope.building.name}
                                                            </div>
                                                        </div>

                                                        <div class="col-auto">
                                                            <i class="fas fa-home fa-2x text-gray-300"></i>
                                                        </div> 
                                                    </div>
                                                </div>
                                                <!--</a>-->

                                                <c:if test="${requestScope.apartmentList.size() > 1}">
                                                    <button type="button" class="btn btn-success dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
                                                        <span class="visually-hidden">Toggle Dropright</span>
                                                    </button>
                                                    <ul class="dropdown-menu">
                                                        <!-- Dropdown menu links -->
                                                        <c:forEach items="${requestScope.apartmentList}" var="apart">
                                                            <a class="chooseApt" href="registServiceTenant?apartmentID=${apart.getApartmentID()}&buildingID=${apart.getBuildingID()}" style="text-decoration: none">
                                                                <li>
                                                                    <div class="card-body">
                                                                        <div class="row no-gutters align-items-center">
                                                                            <div class="col mr-2">
                                                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1 card1">
                                                                                    Căn hộ
                                                                                </div>
                                                                                <div class="h5 mb-0 font-weight-bold text-gray-800 card2">
                                                                                    ${apart.apartmentNumber} - Tầng ${apart.floor} - Diện tích ${apart.area} m2   
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

                                <div
                                    class="text-center mx-auto pb-2 wow fadeInUp"
                                    data-wow-delay="0.2s"
                                    style="max-width: 800px"
                                    >
                                    <h2 class="text-primary">Dịch Vụ Đã Đăng Kí</h2>

                                </div>
                                <!--service list-->
                                <div class="row">
                                    <c:forEach items="${serviceContractList}" var="serviceContract">
                                        <div class="col-xl-3 col-md-6 mb-4">
                                            <a href="registDetail?apartmentID=${apartmentID}&serviceID=${serviceContract.getService().getServiceId()}&serviceContractID=${serviceContract.serviceContractId}" style="text-decoration: none">
                                                <div class="card border-left-primary shadow-sm h-100 py-2" style="border-radius: 10px;">
                                                    <div class="card-body">
                                                        <div class="row no-gutters align-items-center">
                                                            <div class="col ml-4">
                                                                <!-- Tăng kích thước chữ của tên dịch vụ và giảm padding -->
                                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1" style="font-size: 1rem; margin-bottom: 0.5rem;">
                                                                    ${serviceContract.getService().getName()}
                                                                </div>

                                                                <!-- Thêm màu đỏ và giảm kích thước chữ cho số tiền -->
                                                                <div class="h5 mb-0 font-weight-bold text-danger" style="font-size: 1.25rem;">
                                                                    <fmt:setLocale value="en_US" />
                                                                    <fmt:formatNumber type="number" maxFractionDigits="3" value="${serviceContract.getAmount()}"/>₫
                                                                </div>
                                                            </div>

                                                            <!-- Giảm kích thước icon và căn chỉnh lại để nhỏ gọn hơn -->
                                                            <div class="col-auto">
                                                                <i class="${serviceContract.getService().getIcon()} " style="font-size: 2.5rem; color: var(--bs-primary);"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <!-- /.container-fluid -->

                        <!-- Begin Page Content -->


                        <div class="container-fluid feature bg-light pb-5" id="navigate">
                            <div class="container py-5">
                                <div
                                    class="text-center mx-auto pb-2 wow fadeInUp"
                                    data-wow-delay="0.2s"
                                    style="max-width: 800px"
                                    >
                                    <h2 class="text-primary">Dịch Vụ Chưa Đăng Kí</h2>
                                </div>
                                <!--service list-->
                                <div class="row">
                                    <c:forEach items="${list}" var="service">
                                        <div class="col-xl-3 col-md-6 mb-4">
                                            <a href="registDetail?apartmentID=${apartmentID}&serviceID=${service.getServiceId()}" style="text-decoration: none">
                                                <div class="card border-left-warning shadow-sm h-100 py-2" style="border-radius: 10px;">
                                                    <div class="card-body">
                                                        <div class="row no-gutters align-items-center">
                                                            <div class="col ml-4">
                                                                <!-- Tăng kích thước chữ của tên dịch vụ và giảm padding -->
                                                                <div class="text-xs font-weight-bold text-warning text-uppercase mb-1" style="font-size: 1rem; margin-bottom: 0.5rem;">
                                                                    ${service.getName()}
                                                                </div>

                                                                <!-- Thêm màu đỏ và giảm kích thước chữ cho số tiền -->
                                                                <div class="h5 mb-0 font-weight-bold text-danger" style="font-size: 1.25rem; opacity: 0.5;">
                                                                    <fmt:setLocale value="en_US" />
                                                                    <fmt:formatNumber type="number" maxFractionDigits="3" value="${service.getFee()}"/>₫
                                                                </div>
                                                            </div>

                                                            <!-- Giảm kích thước icon và căn chỉnh lại để nhỏ gọn hơn -->
                                                            <div class="col-auto">
                                                                <i class="${service.getIcon()}" style="font-size: 2.5rem; color: lightgray;"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </c:forEach>

                                </div>

                            </div>


                        </div>


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

            <%-- Thông báo thành công --%>
            <% if ("success".equals(request.getParameter("status"))) { %>
            <script>
                alert("Thêm thành công!");
            </script>
            <% } %>
            <!-- Bootstrap core JavaScript-->
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

            <!-- Core plugin JavaScript-->
            <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="js/sb-admin-2.min.js"></script>
            <!-- Page level plugins -->
            <script src="vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!-- Page level custom scripts -->
            <script src="js/demo/datatables-demo.js"></script>
            <script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
    <df-messenger
        intent="WELCOME"
        chat-title="APT MAN"
        agent-id="6708e5b6-3bfa-416e-adc7-1fea6c2d914a"
        language-code="en"
        ></df-messenger>
    </body>
</html>
