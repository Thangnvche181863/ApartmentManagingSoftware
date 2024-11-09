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

            .gradient-custom {
                /* fallback for old browsers */
                background: #f093fb;

                /* Chrome 10-25, Safari 5.1-6 */
                background: -webkit-linear-gradient(to bottom right, rgba(240, 147, 251, 1), rgba(245, 87, 108, 1));

                /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
                background: linear-gradient(to bottom right, rgba(240, 147, 251, 1), rgba(245, 87, 108, 1))
            }

            .card-registration .select-input.form-control[readonly]:not([disabled]) {
                font-size: 1rem;
                line-height: 2.15;
                padding-left: .75em;
                padding-right: .75em;
            }
            .card-registration .select-arrow {
                top: 13px;
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
                    <div class="container py-5 ">
                        <div class="row justify-content-center align-items-center h-100">
                            <div class="col-12 col-lg-9 col-xl-7">
                                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                                    <div class="card-body p-4 p-md-5">
                                        <h2 class="mb-4 pb-2 pb-md-0 mb-md-5 h1 text-gray-900 fw-bold">Tạo thông tin thanh toán</h2>
                                        <form action="/AtpMan/vnpayajax" id="frmCreateOrder" method="post">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div data-mdb-input-init class="form-outline">
                                                        <label class="form-label h4" for="firstName">Số tiền</label>
                                                        <fmt:setLocale value = "en_US"/>
                                                        <input class="form-control form-control-lg" 
                                                               data-val="true" 
                                                               data-val-number="The field Amount must be a number." 
                                                               data-val-required="The Amount field is required." 
                                                               min="1" 
                                                               type="text" 
                                                               value="<fmt:formatNumber value="${requestScope.amount}" type="number" maxFractionDigits="0"/> VNĐ"
                                                               disabled=""
                                                               />
                                                        <input id="amount" name="amount" type="hidden" value="${requestScope.amount}">
                                                        <input id="invoiceId" name="invoiceId" type="hidden" value="${requestScope.invoiceId}">
                                                        <input id="paymentType" name="paymentType" type="hidden" value="${requestScope.paymentType}">
                                                    </div>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="col-12">
                                                    <label class="form-label select-label h4">Chọn phương thức thanh toán</label>
                                                    <select name="bankCode" class="form-select form-control-lg">
                                                        <option selected value="">Cổng thanh toán VNPAYQR</option>
                                                        <option value="VNPAYQR">Thanh toán bằng ứng dụng hỗ trợ VNPAYQR</option>
                                                        <option value="VNBANK">Thanh toán qua thẻ ATM/Tài khoản nội địa</option>
                                                        <option value="INTCARD">Thanh toán qua thẻ quốc tế</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="col-12">
                                                    <h4 class="mb-2 pb-1">Chọn ngôn ngữ thanh toán: </h4>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="language" id="languageVN"
                                                               value="vn" checked />
                                                        <label class="form-check-label" for="languageVN">Tiếng Việt</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="language" id="languageEN"
                                                               value="en" />
                                                        <label class="form-check-label" for="languageEN">Tiếng Anh</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mt-4 pt-2">
                                                <input data-mdb-ripple-init class="btn btn-primary btn-lg" type="submit" value="Thanh toán" />
                                                <a href="/AtpMan/registServiceTenant" style="text-decoration: none;"><button type="button" class="btn btn-warning btn-lg">Quay lại</button></a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
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
            $("#frmCreateOrder").submit(function () {
                var postData = $("#frmCreateOrder").serialize();
                var submitUrl = $("#frmCreateOrder").attr("action");
                console.log(submitUrl);
                console.log(postData);
                $.ajax({
                    type: "POST",
                    url: submitUrl,
                    data: postData,
                    dataType: 'JSON',
                    success: function (x) {
                        if (x.code === '00') {
                            if (window.vnpay) {
                                vnpay.open({width: 768, height: 600, url: x.data});
                            } else {
                                location.href = x.data;
                            }
                            return false;
                        } else {
                            alert(x.Message);
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.error("Error:", textStatus, errorThrown); // Kiểm tra lỗi
                    }
                });
                return false;
            });
        </script> 
    </body>
</html>
