<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Tạo công việc mới</title>
        <!-- Custom fonts and stylesheets -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <style>
            /* Custom styles */
            table {
                width: 110%;
                border-collapse: collapse;
                margin-top: 20px;
                font-family: Arial, sans-serif;
            }
            th, td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            thead th {
                background-color: #f2f2f2;
                font-weight: bold;
            }
            select, .search-box {
                width: 100%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            form label {
                font-weight: bold;
            }
            .form-container {
                max-width: 600px;
                margin: 0 auto;
            }
            .pagination a, .pagination strong {
                padding: 5px 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
                text-decoration: none;
                color: #333;
            }
            .pagination strong {
                background-color: #f0f0f0;
            }
        </style>
        <script>
            function loadApartments() {
                var buildingId = document.getElementById("building").value;

                if (buildingId !== "") {
                    var xhr = new XMLHttpRequest();
                    xhr.open("GET", "addResident?buildingId=" + buildingId, true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) { //404, 400, 500
                            // Replace apartment select box options with the response from the server
                            document.getElementById("apartment").innerHTML = xhr.responseText;
                        }
                    };
                    xhr.send();
                }
            }

        </script>


    </head>

    <body id="page-top">
        <%@include file="sidebar.jsp" %>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <%@include file="topbar.jsp" %>
                <form action="addResident" method="POST">
                    <input type="hidden" name="service" value="create">
                    <div class="container rounded bg-white mt-5 mb-5">
                        <div class="row">
                            <div class="col-md-12 border-right">
                                <div class="p-3 py-5">
                                    <div class="mb-3">
                                        <a href="managerPage" class="btn btn-primary">Trở về Trang chủ</a>
                                    </div>
                                    <h4 class="text-left">Thêm người dân</h4>

                                    <!-- Hiển thị thông báo nếu có -->
                                    <c:if test="${not empty param.message}">
                                        <div class="alert alert-info">
                                            ${param.message}
                                        </div>
                                    </c:if>

                                    <div class="row mt-3">
                                        <div class="col-md-12">
                                            <label>Họ và tên</label>
                                            <input type="text" name="name" class="form-control" placeholder="Họ và tên" required>
                                        </div>

                                        <div class="col-md-12">
                                            <label>Email</label>
                                            <input type="email" name="email" class="form-control" placeholder="Email" required>
                                        </div>

                                        <div class="col-md-12">
                                            <label>Số điện thoại</label>
                                            <input type="tel" name="phoneNumber" class="form-control" placeholder="Số điện thoại" required>
                                        </div>

                                        <div class="col-md-12">
                                            <label>Năm sinh</label>
                                            <input type="date" name="dob" class="form-control" required>
                                        </div>

                                        <div class="col-md-12">
                                            <label>Vai trò</label>
                                            <select name="isOwner" class="form-control" required>
                                                <option value="1">Người ở</option>
                                                <option value="2">Người cho thuê</option>
                                            </select>
                                        </div>
                                    </div>

                                    <!-- Dropdown danh sách căn hộ -->
                                    <div class="row mt-3">
                                        <div class="col-md-6 mb-4">
                                            <label for="building" class="col-md-12">Building</label>
                                            <select class="form-control" name="building" id="building" required onchange="loadApartments()">
                                                <option value="" selected>Chọn tòa nhà</option>
                                                <c:forEach items="${listBuildings}" var="b">
                                                    <option value="${b.buildingID}">${b.name}</option>
                                                </c:forEach>
                                            </select>
                                            <small class="text-danger" id="buildingError"></small>
                                        </div>

                                        <div class="col-md-6 mb-4">
                                            <label for="apartment" class="col-md-12">Apartment</label>
                                            <select class="form-control" name="apartment" id="apartment" required>
                                                <option value="" selected>Chọn căn hộ</option>
                                            </select>
                                            <small class="text-danger" id="apartmentError"></small>
                                        </div>
                                    </div>

                                    <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Tạo</button></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- Scripts -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
        <script src="js/sb-admin-2.min.js"></script>
    </body>
</html>
