
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


        <script>
            function confirmDelete() {
                return confirm("Sure delete?");
            }
        </script>

        <style>
            .pagination {
                font-size: 0.8em; /* Adjust font size as needed */
                margin: 0;
                padding: 0;
                list-style: none;
            }

            .pagination a {
                padding: 5px 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
                text-decoration: none;
                color: #333;
            }

            .pagination a:hover {
                background-color: #f0f0f0;
            }

            .pagination strong {
                padding: 5px 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
                background-color: #f0f0f0;
                color: #333;
            }
        </style>

    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <%@include file="sidebar.jsp" %>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <%@include file = "topbar.jsp" %>
                    <!-- End of Topbar -->











                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800"><b>Danh Sách Đăng Kí Năm ${year}</b></h1>
                            <p>
                                <a class="btn btn-info" href="servicelist">Danh Sách Dịch Vụ</a>
                            </p>
                        </div>

                        <form action="registlist" method="POST">
                            <div>
                                <label for="year">Năm:</label>
                                <select name="year" id="year" style="border-radius: 5px" onchange="this.form.submit()">
                                    <c:forEach var="i" begin="2022" end="2024">
                                        <option value="${i}" <c:if test="${i == year}">selected</c:if>>${i}</option>
                                    </c:forEach>
                                </select>&nbsp;&nbsp;&nbsp;&nbsp;
                                <label for="month">Tháng:</label>
                                <select name="month" id="month" style="border-radius: 5px" onchange="this.form.submit()">
                                    <!-- Lặp qua các tháng từ 1 đến 12 -->
                                    <c:forEach var="i" begin="1" end="12">
                                        <option value="${i}" <c:if test="${i == month}">selected</c:if>>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <label for="row">số dòng:</label>
                                <select name="recordsPerPage" id="recordsPerPage" style="border-radius: 5px" onchange="this.form.submit()">
                                    <option >25</option>
                                    <option value="50" <c:if test="${recordsPerPage == 50}">selected</c:if>>50</option>
                                    <option value="100" <c:if test="${recordsPerPage == 100}">selected</c:if>>100</option>
                                    </select>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <label for="buildingtype">Chọn Tòa Nhà:</label>
                                    <select name="buildingtype" id="buildingType" style="border-radius: 5px" onchange="this.form.submit()">
                                        <option value="">All</option>
                                    <c:forEach items="${listbuilding}" var="ls">
                                        <option value="${ls.name}" 
                                                <c:if test="${buildingtype == ls.name}">selected</c:if>>${ls.name}</option>
                                    </c:forEach>
                                </select>&nbsp;&nbsp;&nbsp;&nbsp;
                                <label for="apartmentType">Loại Căn Hộ:</label>
                                <select name="apartmentType" id="apartmentType" style="border-radius: 5px" onchange="this.form.submit()">
                                    <option value="">All</option>
                                    <c:forEach items="${listdepartment}" var="ls">
                                        <option value="${ls.apartmentType}" 
                                                <c:if test="${apartmentType == ls.apartmentType}">selected</c:if>>${ls.apartmentType}</option>
                                    </c:forEach>
                                </select>&nbsp;&nbsp;&nbsp;&nbsp;
                                <label for="search">Tìm kiếm:</label>
                                <input type="text" name="search" value="${search}" id="search" placeholder="Nhập số phòng" style="border-radius: 5px"  onchange="this.form.submit()"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <label for="serviceType">Xếp Theo Tổng Tiền</label>
                                <select name="orderBy" id="serviceType" style="border-radius: 5px" onchange="this.form.submit()">
                                    <option value="">All</option>
                                    <option value="asc" <c:if test="${orderBy == 'asc'}">selected</c:if>>Tăng dần</option>
                                    <option value="desc" <c:if test="${orderBy == 'desc'}">selected</c:if>>Giảm dần</option>
                                    </select>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="hidden" name="page" value="${currentPage}"/>
                            </div>
                        </form>

                        <!-- Begin Page Content -->


                        <div class="container-fluid">

                            <!-- DataTales Example -->
                            <div class="card shadow mb-4">
                                <div class="card-body">                                  
                                    <div class="table-responsive">
                                        Tổng: <%=  (Integer) request.getAttribute("totalRoom") %> phòng&nbsp;&nbsp;&nbsp;&nbsp;
                                        <div class="d-flex justify-content-between">
                                            <div class="pagination">
                                                <%
                                                    int currentPage = (Integer) request.getAttribute("currentPage");
                                                    int totalPages = (Integer) request.getAttribute("totalPages");
                                                    int recordsPerPage = (Integer) request.getAttribute("recordsPerPage");
                                                    String buildingtype = (String) request.getAttribute("buildingtype");
                                                    String apartmentType = (String) request.getAttribute("apartmentType");
                                                    String search = (String) request.getAttribute("search");
                                                    String orderBy = (String) request.getAttribute("orderBy");
                                                    String month = (String) request.getAttribute("month");
                                                    String year = (String) request.getAttribute("year");
    //                                                int month = (Integer) request.getAttribute("month");
    //                                                int year = (Integer) request.getAttribute("year");

                                                    // Hiển thị nút "Previous" nếu không phải trang đầu tiên
                                                    if (currentPage > 1) {
                                                %>
                                                <a href="registlist?page=<%= currentPage - 1 %>&year=<%=year%>&month=<%=month%>&recordsPerPage=<%= recordsPerPage %>&buildingtype=<%= buildingtype %>&apartmentType=<%= apartmentType %>&search=<%= search %>&orderBy=<%= orderBy %>">Previous</a>
                                                <%
                                                    }

                                                    // Hiển thị danh sách các trang
                                                    for (int i = 1; i <= totalPages; i++) {
                                                        if (i == currentPage) {
                                                %>
                                                <strong><%= i %></strong>
                                                <%
                                                        } else {
                                                %>
                                                <a href="registlist?page=<%= i %>&year=<%=year%>&month=<%=month%>&recordsPerPage=<%= recordsPerPage %>&buildingtype=<%= buildingtype %>&apartmentType=<%= apartmentType %>&search=<%= search %>&orderBy=<%= orderBy %>"><%= i %></a>
                                                <%
                                                        }
                                                    }

                                                    // Hiển thị nút "Next" nếu không phải trang cuối cùng
                                                    if (currentPage < totalPages) {
                                                %>
                                                <a href="registlist?page=<%= currentPage + 1 %>&year=<%=year%>&month=<%=month%>&recordsPerPage=<%= recordsPerPage %>&buildingtype=<%= buildingtype %>&apartmentType=<%= apartmentType %>&search=<%= search %>&orderBy=<%= orderBy %>">Next</a>
                                                <%
                                                    }
                                                %>
                                            </div>
                                            <div><b><strong>Tổng thu:  
                                                <fmt:setLocale value="en_US" />
                                                <fmt:formatNumber type="number" maxFractionDigits="3" value="${totalFinance}"/>
                                                    </strong>
                                                </b>
                                            </div>
                                        </div>
                                        <table class="table table-bordered"  width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">Số Phòng</th>
                                                    <th class="text-center">Loại Căn Hộ</th>
                                                    <th class="text-center">Số Tầng</th>
                                                    <th class="text-center">Tổng Tiền Dịch Vụ (VND)</th>
                                                    <th class="text-center">Thông Tin</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${listapart}" var="ls">
                                                    <tr>
                                                        <td class="text-center">${ls.apartmentNumber}</td>
                                                        <td class="text-center">${ls.apartmentType}</td>
                                                        <td class="text-center">${ls.floor}</td>
                                                        <td class="text-center">
                                                            <fmt:setLocale value="en_US" />
                                                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${ls.totalAmount}"/>
                                                        </td>
                                                        <td class="text-center"><a href="inforapartmentservice?id=${ls.apartmentID}&month=${month}&year=${year}">Chi tiết</a></td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
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

    </body>

</html>