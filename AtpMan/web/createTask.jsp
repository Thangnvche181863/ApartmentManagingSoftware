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
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,300,400,600,700" rel="stylesheet">
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>

    <style>
        /* Add your custom styles here */
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

        select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        form label {
            margin-right: 10px;
            font-weight: bold;
        }

        .form-container {
            max-width: 600px;
            margin: 0 auto;
        }

        td:last-child {
            font-weight: bold;
            color: #333;
        }

        .total-label {
            font-weight: bold;
            color: #666;
        }

        .search-box {
            padding: 8px;
            width: 100%;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        /* Styling for pagination */
        .pagination {
            font-size: 0.9em;
            margin-top: 10px;
        }

        .pagination a {
            padding: 5px 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            text-decoration: none;
            color: #333;
        }

        .pagination strong {
            padding: 5px 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            background-color: #f0f0f0;
            color: #333;
        }

    </style>

    <body id="page-top">
        <%@include file="sidebar.jsp" %>

        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <%@include file="topbar.jsp" %>
                <form action="assignment" method="POST">
                    <input type="hidden" name="service" value="create">
                    <div class="container rounded bg-white mt-5 mb-5">
                        <div class="row">
                            <div class="col-md-12 border-right">

                                <div class="p-3 py-5">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h4 class="text-right">Thêm công việc</h4>
                                    </div>

                                    <div class="row mt-3">
                                        <div class="col-md-12"><label class="labels">Tên công việc</label><input type="text" name="taskName" class="form-control" placeholder="Tên công việc" value=""></div>
                                        <div class="col-md-12"><label class="labels">Mô tả</label><input type="text" name="description" class="form-control" placeholder="Mô tả công việc" value=""></div>
                                        <div class="col-md-12">
                                            <label class="labels">Loại công việc</label>
                                            <select name="taskType" class="form-control">
                                                <option value="Bảo trì">Bảo trì</option>
                                                <option value="Dịch vụ">Dịch vụ</option>
                                                <option value="Chi tiêu">Chi tiêu</option>
                                                <option value="An ninh">An ninh</option>
                                            </select>
                                        </div>


                                    </div>


                                    <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Tạo</button></div>

                                </div>

                            </div>

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
