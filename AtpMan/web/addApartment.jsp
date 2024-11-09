<!DOCTYPE html>
<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Thêm căn hộ</title>

        <!-- Custom fonts and stylesheets -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,300,400,600,700" rel="stylesheet">
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>
    <script>
        function validateForm() {
            const floor = document.getElementById("floor").value;
            const area = document.getElementById("area").value;
            const price = document.getElementsByName("price")[0].value;
            const maintenanceFee = document.getElementsByName("maintenanceFee")[0].value;

            // Kiểm tra tầng phải là số tự nhiên lớn hơn 0
            if (floor <= 0 || !Number.isInteger(Number(floor))) {
                alert("Tầng phải là số tự nhiên lớn hơn 0");
                return false;
            }

            // Kiểm tra diện tích phải là số tự nhiên lớn hơn 0
            if (area <= 0 || !Number.isInteger(Number(area))) {
                alert("Diện tích phải là số tự nhiên lớn hơn 0");
                return false;
            }

            // Kiểm tra giá phải là số lớn hơn hoặc bằng 0
            if (price < 0 || isNaN(price)) {
                alert("Giá căn hộ không được âm và phải là số hợp lệ");
                return false;
            }

            // Kiểm tra phí bảo trì phải là số lớn hơn hoặc bằng 0
            if (maintenanceFee < 0 || isNaN(maintenanceFee)) {
                alert("Phí bảo trì không được âm và phải là số hợp lệ");
                return false;
            }

            return true;
        }
    </script>

    <body id="page-top">
        <div id="wrapper">
            <%@include file="sidebar.jsp" %>

            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <%@include file="topbar.jsp" %>
                    <form action="addApartment" method="POST" onsubmit="return validateForm()">
                        <input type="hidden" name="service" value="add">
                        <input type="hidden" name="buildingID" value="${buildingID}">

                        <div class="container rounded bg-white mt-5 mb-5">
                            <div class="row">
                                <div class="col-md-12 border-right">
                                    <div class="p-3 py-5">
                                        <div class="d-flex justify-content-between align-items-center mb-3">
                                            <h4 class="text-right">Thêm căn hộ mới</h4>
                                            <% 
                                                String buildingID = request.getParameter("buildingID");
                                                // Sử dụng buildingID trong trang JSP
                                            %>

                                            <a href="apartment?buildingID=<%=buildingID%>">Quay lại</a>
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
                                                <label class="labels">Số căn hộ</label>
                                                <input type="text" name="apartmentNumber" class="form-control" placeholder="Số căn hộ" required>
                                            </div>
                                            <div class="col-md-12">
                                                <label class="labels">Loại căn hộ</label>
                                                <input type="text" name="apartmentType" class="form-control" placeholder="Loại căn hộ" required>
                                            </div>
                                            <div class="col-md-12">
                                                <label class="labels">Giá</label>
                                                <input type="number" name="price" step="0.01" class="form-control" placeholder="Giá căn hộ" required>
                                            </div>
                                            <div class="col-md-12">
                                                <label class="labels">Phí bảo trì</label>
                                                <input type="number" name="maintenanceFee" step="0.01" class="form-control" placeholder="Phí bảo trì" required>
                                            </div>
                                            <div class="col-md-12">
                                                <label class="labels">Tầng</label>
                                                <input type="number" id="floor" name="floor" class="form-control" placeholder="Tầng" required>
                                            </div>
                                            <div class="col-md-12">
                                                <label class="labels">Diện tích</label>
                                                <input type="number" id="area" name="area" class="form-control" placeholder="Diện tích (m²)" required>
                                            </div>
                                        </div>

                                        <div class="mt-5 text-center">
                                            <button class="btn btn-primary profile-button" type="submit">Thêm căn hộ</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div> 
        </div>
        <!-- JavaScript validation -->
        <script>
            function validateForm() {
                const floor = document.getElementById("floor").value;
                const area = document.getElementById("area").value;

                if (floor <= 0 || !Number.isInteger(Number(floor))) {
                    alert("Tầng phải là số tự nhiên lớn hơn 0");
                    return false;
                }

                if (area <= 0 || !Number.isInteger(Number(area))) {
                    alert("Diện tích phải là số tự nhiên lớn hơn 0");
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
