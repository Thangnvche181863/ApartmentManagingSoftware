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

        <!-- CSS for overlay and form positioning -->
        <style>
            /* Overlay styling */
            .overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.5); /* Semi-transparent black background */
                display: flex;
                align-items: center;
                justify-content: center;
                z-index: 1000;
            }

            /* Centered form styling */
            .registration-form {
                background: #fff;
                padding: 20px;
                border-radius: 8px;
                width: 100%;
                max-width: 500px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }

            .registration-form h3 {
                margin-bottom: 20px;
            }
        </style>
        <script>
            function confirmDelete() {
                return confirm("Bạn muốn hủy đăng kí ?");
            }
        </script>
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

                    <!--////////////////-->
                    <div class="container">
                        <fmt:setLocale value = "en_US"/>
                        <div
                            class="text-center mx-auto wow fadeInUp"
                            data-wow-delay="0.2s"
                            style="max-width: 800px"
                            >
                            <h2 class="text-primary m-0">Thông tin đăng kí</h2>  
                        </div>
                        <c:if test="${requestScope.serviceContract != null}">
                            <p class="text-primary m-0">Hiệu lực: <span style="color: red"><fmt:formatDate
                                        value="${requestScope.serviceContract.getStartDate()}"
                                        pattern="dd/MM/yyyy"
                                        /> - <fmt:formatDate
                                        value="${requestScope.serviceContract.getEndDate()}"
                                        pattern="dd/MM/yyyy"
                                        /></span></p>
                            <p class="text-primary">Hủy đăng kí vui lòng bấm vào 
                                <span>
                                    <a href="registService?apartmentId=${apartmentID}&serviceId=${service.getServiceId()}&serviceContractId=${requestScope.serviceContractID}" style="color: red" onclick="return confirmDelete();"> Đây!</a>
                                </span>
                            <p>
                            </c:if>

                            <%--<c:if test="${serviceContract == null}">--%>
                            <!--<p class="text-primary m-0">Vui lòng--> 
                            <!--<span>-->
                                <!--<a href="registService?apartmentID=${apartmentID}&serviceID=${service.getServiceId()}" class="text-danger">đăng kí</a>-->
                            <!--</span> để sử dụng dịch vụ </p>-->
                            <%--</c:if>--%>

                            <!-- Trigger link for registration form -->
                            <c:if test="${requestScope.serviceContract == null}">
                            <p class="text-primary m-0">
                                Vui lòng 
                                <span>
                                    <a href="javascript:void(0)" onclick="toggleForm()" class="text-danger">đăng kí</a>
                                </span> 
                                để sử dụng dịch vụ 
                            </p>
                        </c:if>

                        <!-- Overlay and Registration Form (Hidden by Default) -->
                        <div id="overlay" class="overlay" style="display: none;">
                            <div id="registrationForm" class="registration-form">
                                <h3 class="text-center text-primary mb-3">Đăng Ký Dịch Vụ</h3>
                                <form action="/AtpMan/payinvoice" method="POST">
                                    <input type="hidden" name="apartmentID" value="${apartmentID}">
                                    <input type="hidden" name="serviceID" value="${service.getServiceId()}">
                                    <input type="hidden" name="amount" value="${service.getFee()}"/>
                                    <input type="hidden" name="paymentType" value="payRegisInvoice"/>

                                    <!-- Fields for displaying user info -->
                                    <div class="form-group mb-3">
                                        <label for="userName" class="text-primary">Tên người dùng</label>
                                        <input type="text" class="form-control" id="userName" value="${customer.getName()}" name="userName" readonly="" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="phone" class="text-primary">Số điện thoại</label>
                                        <input type="tel" class="form-control" id="phone" name="phone" value="${customer.getPhoneNumber()}" readonly="" />
                                    </div>

                                    <!-- Subscription plan field -->
                                    <div class="form-group mb-3">
                                        <label for="subscriptionPlan" class="text-primary">Chọn gói dịch vụ</label>
                                        <select class="form-control" id="subscriptionPlan" name="subscriptionPlan" required style="color: red" onchange="updateFee()">
                                            <option value="1">Gói 1 tháng ưu đãi ${discount1Month}%</option>
                                            <option value="2">Gói 2 tháng ưu đãi ${discount2Month}%</option>
                                            <option value="3">Gói 3 tháng ưu đãi ${discount3Month}%</option>
                                        </select>
                                    </div>

                                    <div class="form-group mb-3">
                                        <label for="fee" class="text-primary">Giá <span style="color: red">VND</span></label>
                                        <input type="text" class="form-control" id="fee" name="fee" style="color: red"
                                               value="<fmt:formatNumber value="${requestScope.service.getFee()}" type="number" maxFractionDigits="0"/>"
                                               readonly />
                                    </div>

                                    <!-- Buttons for submitting or canceling -->
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-primary">Đăng Ký</button>
                                        <button type="button" class="btn btn-secondary" onclick="toggleForm()">Hủy</button>
                                    </div>
                                </form>
                            </div>
                        </div>


                        <!-- Service Detail Section Start -->
                        <div class="container-fluid py-5">
                            <div class="container">
                                <div class="row g-5">
                                    <!-- Service Image -->
                                    <div class="col-lg-4 wow fadeInUp" data-wow-delay="0.2s">
                                        <img class="img-fluid rounded" src="${service.img}" alt="${service.name}" style="width: 100%" />
                                    </div>

                                    <!-- Service Details -->
                                    <div class="col-lg-8 wow fadeInUp" data-wow-delay="0.3s">
                                        <div class="d-flex justify-content-between">
                                            <h2 class="mb-4 text-primary">${service.name}</h2>
                                            <a href="registServiceTenant?apartmentID=${apartmentID}"><button class="btn-primary btn" style="height: 40px">Quay trở lại</button></a>
                                        </div>
                                        <p class="mb-4"><strong><span style="color: #015DC5">Loại dịch vụ:  </span></strong><span style="color: #4CA64C"> ${service.type}</span></p>
                                        <p class="mb-4"  style="color: #D80000"><strong><span style="color: #015DC5">Giá:  </span></strong><fmt:formatNumber  value="${service.getFee()}" /> VND</p>
                                        <div class="feature-icon p-4 mb-4">
                                            <i class="${service.icon}" style="font-size: 3rem; color: #015DC5"></i>
                                        </div>
                                        <p class="mb-4 " style="color: black">${service.description}</p>
                                    </div>
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


            <!-- JavaScript to toggle the overlay and form -->
            <script>
                function toggleForm() {
                    const overlay = document.getElementById("overlay");
                    overlay.style.display = overlay.style.display === "none" ? "flex" : "none";
                }
            </script>

            <script>
                // Lưu giá gốc vào biến toàn cục
                let baseFee;

                // Các biến lưu trữ tỷ lệ giảm giá
                let discount1Month;
                let discount2Month;
                let discount3Month;

                // Hàm này sẽ được gọi khi trang được tải
                function initializeFee() {
                    // Lấy giá dịch vụ từ input fee (xóa phần " VND")
                    let baseFeeStr = document.getElementById('fee').value.replace(" VND/tháng", "");
                    baseFee = parseFloat(baseFeeStr.replace(/\./g, "").replaceAll(",", "")); // Chuyển đổi sang số

                    // Lưu các giá trị giảm giá vào biến
                    discount1Month = 1 - (parseFloat('${discount1Month}') / 100);
                    discount2Month = 1 - (parseFloat('${discount2Month}') / 100);
                    discount3Month = 1 - (parseFloat('${discount3Month}') / 100);
                    updateFee(); // Cập nhật giá ban đầu
                }

                function updateFee() {
                    // Kiểm tra nếu baseFee chưa được thiết lập
                    if (baseFee === undefined) {
                        console.error("Base fee is not defined.");
                        return;
                    }

                    // Lấy giá trị gói dịch vụ đã chọn
                    let subscriptionPlan = document.getElementById('subscriptionPlan').value;
                    let updatedFee;

                    // Tính toán giá dựa trên gói dịch vụ
                    if (subscriptionPlan === "1") {
                        updatedFee = baseFee * discount1Month; 
                    } else if (subscriptionPlan === "2") {
                        updatedFee = baseFee * discount2Month; 
                    } else if (subscriptionPlan === "3") {
                        updatedFee = baseFee * discount3Month; 
                    }

                    // Cập nhật giá vào input fee
                    document.getElementById('fee').value = new Intl.NumberFormat().format(updatedFee) + " VND/tháng";
                }

                // Gọi hàm initializeFee khi trang được tải
                window.onload = initializeFee;
            </script>

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

    </body>
</html>
