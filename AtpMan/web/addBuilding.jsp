<!DOCTYPE html>
<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Thêm tòa nhà</title>

        <!-- Custom fonts and stylesheets -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,300,400,600,700" rel="stylesheet">
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>

    <body id="page-top">
        <%@include file="sidebar.jsp" %>

        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <%@include file="topbar.jsp" %>
                <form action="building" method="POST" onsubmit="return validateForm()">
                    <input type="hidden" name="service" value="add">
                    <div class="container rounded bg-white mt-5 mb-5">
                        <div class="row">
                            <div class="col-md-12 border-right">
                                <div class="p-3 py-5">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h4 class="text-right">Thêm tòa nhà bạn quản lý</h4>
                                        <a href="building">Quay lại</a>
                                        <% 
                                        String message = request.getParameter("message"); 
                                        if (message != null && !message.isEmpty()) {
                                        %>
                                        <div class="alert alert-info"><%= message %></div>
                                        <% 
                                            } 
                                        %>
                                    </div>

                                    <div class="row mt-3">
                                        <div class="col-md-12">
                                            <label class="labels">Tên tòa</label>
                                            <input type="text" name="name" class="form-control" placeholder="Tên tòa nhà" required>
                                        </div>
                                        <div class="col-md-12">
                                            <label class="labels">Số tầng</label>
                                            <input type="number" id="numFloor" name="numFloor" class="form-control" placeholder="Số tầng" required>
                                        </div>
                                        <div class="col-md-12">
                                            <label class="labels">Số phòng</label>
                                            <input type="number" id="numApartment" name="numApartment" class="form-control" placeholder="Số phòng" required>
                                        </div>
                                        <div class="col-md-12">
                                            <label class="labels">Địa chỉ</label>
                                            <input type="text" name="address" class="form-control" placeholder="Địa chỉ" required>
                                        </div>
                                    </div>

                                    <div class="mt-5 text-center">
                                        <button class="btn btn-primary profile-button" type="submit">Tạo</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div> 

        <!-- JavaScript validation -->
        <script>
            function validateForm() {
                const numFloor = document.getElementById("numFloor").value;
                const numApartment = document.getElementById("numApartment").value;

                if (numFloor <= 0 || !Number.isInteger(Number(numFloor))) {
                    alert("Số tầng phải là số tự nhiên lớn hơn 0");
                    return false;
                }

                if (numApartment <= 0 || !Number.isInteger(Number(numApartment))) {
                    alert("Số phòng phải là số tự nhiên lớn hơn 0");
                    return false;
                }
                return true;
            }
        </script>

        <!-- Scripts -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
        <script src="js/sb-admin-2.min.js"></script>
    </body>
</html>
