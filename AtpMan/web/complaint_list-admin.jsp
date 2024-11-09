<%-- 
    Document   : complaint_list-admin
    Created on : Nov 8, 2024, 11:17:49 AM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="vi">
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


        <!--        <script>
                    function confirmDelete() {
                        return confirm("Bạn muốn xóa chứ?");
                    }
                </script>-->
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                font-family: Arial, sans-serif;
            }

            th, td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;

            }
            th{
                font-weight: bold;
                color: black;
            }
            thead th {
                background-color: black;
                font-weight: bold;
            }

            select {
                width: 80%;
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
            .custom-approve-btn {
                background-color: #015FC9; /* Màu xanh theo yêu cầu */
                color: #ffffff;            /* Màu chữ trắng */
                border: none;              /* Loại bỏ viền mặc định */
            }

            /*            .custom-approve-btn:hover {
                            background-color: #0D1EC4;  Màu đậm hơn khi hover 
                        }*/

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
                    <div class="container">

                        <h1>Danh sách các yêu cầu</h1>


                        <form id="sortForm" action="complaintlist" method="get">
                            <input type="hidden" name="page" value="1">
                            <input type="hidden" name="search" value="${search}">
                            <input type="hidden" name="searchField" value="${searchField}">
                            <select name="sort" id="sortSelect">
                                <option value="date" ${sort == 'date' ? 'selected' : ''}>Ngày</option>
                                <option value="customerName" ${sort == 'customerName' ? 'selected' : ''}>Tên khách hàng</option>
                                <option value="type" ${sort == 'type' ? 'selected' : ''}>Loại</option>
                                <option value="status" ${sort == 'status' ? 'selected' : ''}>Trạng thái</option>
                            </select>
                            <button type="submit" class="btn btn-primary">Sắp xếp</button>
                        </form>



                        <table>
                            <tr>
                                <th>Tên khách hàng</th>
                                <th>Tên yêu cầu</th>
                                <th>Nội dung</th>
                                <th>Trạng thái</th>
                                <th>Loại</th>
                                <th>Ngày yêu cầu</th>
                                <th>Xác nhận</th>
                            </tr>
                            <c:forEach var="complaint" items="${complaints}">
                                <tr>
                                    <td style="color : black">${complaint.customerName}</td>                  
                                    <td>${complaint.title}</td>
                                    <td>${complaint.description}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${complaint.status == 0}">Chưa hoàn thành</c:when>
                                            <c:otherwise>Đã hoàn thành</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${complaint.type}</td>
                                    <td><fmt:formatDate value="${complaint.dateResquested}" pattern="dd/MM/yyyy" /></td>

                                    <td>
                                        <c:if test="${complaint.status == 0}">
                                            <form action="complaintlist" method="post">
                                                <input type="hidden" name="requestID" value="${complaint.requestID}" />
                                                <input type="hidden" name="action" value="accept" />
                                                <button type="submit" name="status" value="1" class="btn btn-primary">
                                                    Chấp thuận
                                                </button>
                                            </form>
                                        </c:if>                           
                                        <c:if test="${complaint.status == 1}">
                                            <button disabled class="btn custom-approve-btn rounded-pill">Đã xác nhận</button>
                                        </c:if>
                                    </td>


                                </tr>
                            </c:forEach>
                        </table>
                        <div class="d-flex justify-content-center mt-4">
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <!-- Previous page -->
                                    <c:if test="${currentPage > 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="complaintlist?page=${currentPage-1}&search=${search}&searchField=${searchField}&sort=${sort}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <!-- Page numbers -->
                                    <c:forEach begin="1" end="${totalPages}" var="i">
                                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                                            <a class="page-link" href="complaintlist?page=${i}&search=${search}&searchField=${searchField}&sort=${sort}">
                                                ${i}
                                            </a>
                                        </li>
                                    </c:forEach>

                                    <!-- Next page -->
                                    <c:if test="${currentPage < totalPages}">
                                        <li class="page-item">
                                            <a class="page-link" href="complaintlist?page=${currentPage+1}&search=${search}&searchField=${searchField}&sort=${sort}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>

                        <div id="rejectPopup" style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background-color: #fff; padding: 50px; border: 1px solid #ccc; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);">
                            <form id="rejectForm" action="rejectRequest" method="post">
                                <input type="hidden" id="rejectRequestID" name="requestID" />
                                <label for="reason">Lý do từ chối:</label>
                                <textarea id="reason" name="reason" required></textarea>
                                <br />
                                <button type="submit">Xác nhận từ chối</button>
                                <button type="button" onclick="closeRejectPopup()">Hủy</button>
                            </form>
                        </div>



                        <div style="display: flex; justify-content: space-between;">

                            <a href="managerPage" data-mdb-ripple-init class="btn btn-primary btn-lg">Trở về</a>
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

        <%-- Thông báo thành công --%>
        <% if ("success".equals(request.getParameter("status"))) { %>
        <script>
            alert("Thêm thành công!");
        </script>
        <% } %>

        <script>
            // Gửi form tìm kiếm
            function submitSearchForm() {
                document.getElementById('sortSelect').name = '';  // Để không gửi giá trị sort khi tìm kiếm
                document.getElementById('searchForm').submit();
            }

            // Gửi form sắp xếp
            function submitSortForm() {
                document.getElementById('searchInput').name = '';  // Để không gửi giá trị search khi sắp xếp
                document.getElementById('sortForm').submit();
            }

            // Hiển thị popup từ chối
            function showRejectPopup(requestID) {
                document.getElementById('rejectRequestID').value = requestID;
                document.getElementById('rejectPopup').style.display = 'block';
            }

            // Đóng popup từ chối
            function closeRejectPopup() {
                document.getElementById('rejectPopup').style.display = 'none';
            }

            //                function showApprovePopup(requestID) {
            //                    document.getElementById('approveRequestID').value = requestID;
            //                    document.getElementById('approvePopup').style.display = 'block';
            //                }
            //
            //                function closeApprovePopup() {
            //                    document.getElementById('approvePopup').style.display = 'none';
            //                }

        </script>
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








