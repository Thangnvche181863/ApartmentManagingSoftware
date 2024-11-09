<%-- 
    Document   : requestcomplaint
    Created on : Nov 8, 2024, 11:18:45 AM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Customer Feedback Form">
        <meta name="author" content="WuanTun">
        <title>Request Service</title>

        <!-- Custom fonts and styles -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,300,400,600,700,800,900" rel="stylesheet">
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <style>
            .container {
                max-width: 600px;
                margin-top: 40px;
                padding: 20px;
                background-color: #f8f9fc;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            h2 {
                color: #4e73df;
                text-align: center;
                font-weight: 700;
                margin-bottom: 20px;
            }

            label {
                font-weight: bold;
                color: #4e73df;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-control {
                border-radius: 5px;
                border: 1px solid #ddd;
            }

            button[type="submit"] {
                width: 100%;
                padding: 10px;
                background-color: #4e73df;
                border: none;
                color: #fff;
                font-size: 16px;
                font-weight: bold;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            button[type="submit"]:hover {
                background-color: #2e59d9;
            }

            .form-icon {
                color: #4e73df;
                margin-right: 5px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Yêu cầu thêm dịch vụ</h2>
            <form action="requestcomplaintservlet" method="post">
                <div class="form-group">
                    <label for="title">
                        <i class="bi bi-file-text form-icon"></i>Tiêu đề:
                    </label>
                    <input type="text" name="title" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="description">
                        <i class="bi bi-chat-dots form-icon"></i>Mô tả:
                    </label>
                    <textarea name="description" rows="5" class="form-control" required></textarea>
                </div>
                <div class="form-group">
                    <label for="type">
                        <i class="bi bi-tools form-icon"></i>Loại dịch vụ:
                    </label>
                    <select name="type" class="form-control" required>
                        <option value="" selected>Chọn loại dịch vụ</option>
                        <option value="Sữa chữa">Sửa chữa</option>
                        <option value="Vệ sinh">Vệ sinh</option>
                        <option value="Khác">Khác</option>
                    </select>
                </div>
                <button type="submit">Gửi yêu cầu</button>
            </form>
        </div>

        <!-- JavaScript Libraries -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
        <script src="js/sb-admin-2.min.js"></script>
    </body>
</html>
