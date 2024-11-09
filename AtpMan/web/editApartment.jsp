<!DOCTYPE html>
<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Chỉnh sửa căn hộ</title>

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
                <form action="apartment" method="POST" onsubmit="return validateForm()">
                    <input type="hidden" name="service" value="edit">
                    <input type="hidden" name="apartmentID" value="${apartment.apartmentID}">
                    <input type="hidden" name="buildingID" value="${requestScope.buildingID}">
                    <div class="container rounded bg-white mt-5 mb-5">
                        <div class="row">
                            <div class="col-md-12 border-right">
                                <div class="p-3 py-5">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h4 class="text-right">Chỉnh sửa căn hộ</h4>

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
                                            <label class="labels">Số căn hộ</label>
                                            <input type="text" name="apartmentNumber" class="form-control" 
                                                   value="${apartment.apartmentNumber}" placeholder="Số căn hộ" required>
                                        </div>
                                        <div class="col-md-12">
                                            <label class="labels">Loại căn hộ</label>
                                            <input type="text" name="apartmentType" class="form-control" 
                                                   value="${apartment.apartmentType}" placeholder="Loại căn hộ" required>
                                        </div>
                                        <div class="col-md-12">
                                            <label class="labels">Giá</label>
                                            <input type="number" name="price" step="0.01" class="form-control" 
                                                   value="${apartment.price}" placeholder="Giá căn hộ" required>
                                        </div>
                                        <div class="col-md-12">
                                            <label class="labels">Phí bảo trì</label>
                                            <input type="number" name="maintenanceFee" step="0.01" class="form-control" 
                                                   value="${apartment.maintenanceFee}" placeholder="Phí bảo trì" required>
                                        </div>
                                        <div class="col-md-12">
                                            <label class="labels">Tầng</label>
                                            <input type="number" name="floor" class="form-control" 
                                                   value="${apartment.floor}" placeholder="Tầng" required>
                                        </div>
                                        <div class="col-md-12">
                                            <label class="labels">Diện tích</label>
                                            <input type="number" name="area" class="form-control" 
                                                   value="${apartment.area}" placeholder="Diện tích (m²)" required>
                                        </div>
                                    </div>

                                    <div class="mt-5 text-center">
                                        <button class="btn btn-primary profile-button" type="submit">Lưu thay đổi</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div> 

        <!-- JavaScript validation -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
        <script src="js/sb-admin-2.min.js"></script>
    </body>
</html>    
