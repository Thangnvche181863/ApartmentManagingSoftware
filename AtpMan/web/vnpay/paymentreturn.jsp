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
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
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
        <link href="./css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="./css/bootstrap.min.css" rel="stylesheet">
        <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">-->
        <link href="./vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

        <link href="../assets/bootstrap.min.css" rel="stylesheet"/>
        <!-- Custom styles for this template -->
        <link href="../assets/jumbotron-narrow.css" rel="stylesheet">      
        <script src="../assets/jquery-1.11.3.min.js"></script>
        <style>
            .payment-result-container {
                background-color: #ffffff;
                border-radius: 12px;
                padding: 30px 40px;
                max-width: 700px;
                box-shadow: 0 6px 15px rgba(0, 0, 0, 0.15);
                text-align: center;
                color: #003366;
            }
            .payment-result-container h2 {
                color: #0066cc;
                font-size: 26px;
                margin-bottom: 25px;
                text-transform: uppercase;
                letter-spacing: 1px;
            }
            .payment-result-container .status-icon {
                font-size: 48px;
                color: #4a90e2;
                margin-bottom: 20px;
            }
            .payment-result-container .detail {
                text-align: left;
                margin-bottom: 15px;
                line-height: 1.6;
            }
            .detail p {
                font-size: 15px;
                margin: 5px 0;
                color: #333;
            }
            .detail p span {
                font-weight: bold;
                color: #0066cc;
            }
            .status-success {
                color: #0066cc;
                font-weight: bold;
                font-size: 16px;
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
                    <jsp:include page="../topbar.jsp"/>
                    <!--End of Topbar-->

                    <fmt:setLocale value = "en_US"/>
                    <div class="payment-result-container container">
                        <div class="header clearfix">
                            <h2 class="fw-bold">Kết quả thanh toán</h2>
                        </div>
                        <div class="detail table-responsive">
                            <div class="form-group">
                                <p><span>Mã giao dịch thanh toán (Số hóa đơn):</span> <label>${requestScope.vnp_TxnRef}</label></p>
                            </div>
                            <div class="form-group">
                                <p><span>Số tiền:</span> <label><fmt:formatNumber value="${requestScope.vnp_Amount}" type="number" maxFractionDigits="0"></fmt:formatNumber> VNĐ</label></p>
                                </div>
                                <div class="form-group">
                                    <p><span>Mô tả giao dịch:</span> <label>${requestScope.vnp_OrderInfo}</label></label></p>
                            </div>
                            <div class="form-group">
                                <p><span>Mã lỗi thanh toán:</span> <label>${requestScope.vnp_ResponseCode}</label></p>
                            </div>
                            <div class="form-group">
                                <p><span>Mã ngân hàng thanh toán:</span> <label>${requestScope.vnp_BankCode}</label></p>
                            </div>
                            <div class="form-group">
                                <p><span>Thời gian thanh toán:</span> <label>${requestScope.vnp_PayDate}</label></p>
                            </div>
                            <div class="form-group">
                                <p>
                                    <span>Tình trạng giao dịch:</span> 
                                    <span class="${ requestScope.vnp_ResponseCode == 0 ?'text-success' : 'text-danger'}">${requestScope.message}</span>
                                </p>
                            </div>
                        </div>
                        <a href="${requestScope.returnURL}" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Quay lại</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="./vendor/jquery/jquery.min.js"></script>
        <script src="./vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="./vendor/chart.js/Chart.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="./js/sb-admin-2.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="./vendor/jquery-easing/jquery.easing.min.js"></script> 


        <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
        <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
        <script type="text/javascript">

        </script> 
    </body>
</html>
