<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Công Việc Đã giao</title>

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

                <div class="container-fluid">
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Công việc đã giao
                            <a class="btn btn-primary" href="assignment">Chưa giao</a>
                            <a class="btn btn-primary" href="task">Đã giao </a>
                        </h1>
                        <!-- Hiển thị thông báo nếu có -->
                        <c:if test="${not empty sessionScope.mess}">
                            <div class="alert alert-success" role="alert">
                                ${sessionScope.mess}
                            </div>
                            <c:remove var="mess" scope="session"/> <!-- Xóa thông báo sau khi hiển thị -->
                        </c:if>
                    </div>

                    <!-- Form lọc công việc -->
                    <div class="form-container">
                        <form action="task" method="POST">
                            <table>
                                <thead>
                                    <tr>
                                        <th class="text-center">Số Dòng</th>
                                        <th class="text-center">Phân Loại</th>
                                        <th class="text-center">Tìm Kiếm</th>
                                        <th class="text-center">Tổng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <select name="recordsPerPage" onchange="this.form.submit()" style="width: 80px;">
                                                <option value="10" <c:if test="${recordsPerPage == 10}">selected</c:if>>10</option>
                                                <option value="25" <c:if test="${recordsPerPage == 25}">selected</c:if>>25</option>
                                                <option value="50" <c:if test="${recordsPerPage == 50}">selected</c:if>>50</option>
                                                <option value="100" <c:if test="${recordsPerPage == 100}">selected</c:if>>100</option>
                                                </select>
                                            </td>
                                            <td>
                                                <select name="status" onchange="this.form.submit()">
                                                    <option value="0" <c:if test="${selectedStatus == '0'}">selected</c:if>>Tất cả</option>
                                                <option value="Da hoan thanh" <c:if test="${selectedStatus == 'Da hoan thanh'}">selected</c:if>>Đã hoàn thành</option>
                                                <option value="Chua hoan thanh" <c:if test="${selectedStatus == 'Chua hoan thanh'}">selected</c:if>>Chưa hoàn thành</option>
                                                </select>
                                            </td>


                                            </td>
                                            <td>
                                                <input type="text" value="" name="search" class="search-box" placeholder="Nhập từ khóa..." onchange="this.form.submit()" />
                                            </td>
                                            <td class="total-label text-center">
                                            ${amountOfAssignment} việc
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>

                    <!-- Bảng công việc -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                                <div class="pagination">
                                    <c:if test="${currentPage > 1}">
                                        <a href="task?page=${currentPage - 1}">Trước</a>
                                    </c:if>
                                    <c:forEach begin="1" end="${totalPages}" var="i">
                                        <c:choose>
                                            <c:when test="${i == currentPage}">
                                                <strong>${i}</strong> <!-- Hiển thị số trang hiện tại -->
                                            </c:when>
                                            <c:otherwise>
                                                <a href="task?page=${i}">${i}</a> <!-- Liên kết đến các trang khác -->
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:if test="${currentPage < totalPages}">
                                        <a href="task?page=${currentPage + 1}">Sau</a>
                                    </c:if>
                                </div>


                                <table class="table table-bordered" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Người chịu trách nhiệm</th>
                                            <th>Tên công việc</th>
                                            <th>Ngày giao</th>
                                            <th>Hạn</th>
                                            <th class="text-center">Trạng thái</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listAssignments}" var="ls">
                                            <tr>
                                                <td>${ls.staffName}</td>
                                                <td>${ls.taskName}</td>
                                                <td>${ls.startTime}</td>
                                                <td>${ls.endTime}</td>
                                                <td class="text-center">
                                                    <c:choose>
                                                        <c:when test="${ls.status == 'Da hoan thanh'}">
                                                            <span class="text-warning ">Đã hoàn thành</span>  
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="text-primary">Chưa hoàn thành</span>  
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>


                                            </tr>
                                            <!-- Modal giao việc -->



                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container-fluid">

                    <!-- Thêm biểu đồ hình tròn -->
                    <div class="card shadow mb-4">
                        <div class="card-body text-center">
                            <h4>Tỷ lệ công việc đã hoàn thành và chưa hoàn thành</h4>
                            <style>
                                #completionChart {
                                    width: 400px ;
                                    height: 400px ;
                                    margin: auto;
                                }
                                .completion-percentage {
                                    font-weight: bold;
                                    margin-top: 15px;
                                    color: #4e73df;
                                }
                            </style>
                            <div style="display: flex; justify-content: center; align-items: center;">
                                <canvas id="completionChart"></canvas>
                            </div>
                            <div class="completion-percentage">
                                Đã hoàn thành: 
                                <span id="completedPercentage"></span>% công việc
                            </div>
                        </div>
                    </div>

                </div>


            </div>
        </div>



        <!-- Scripts -->
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var completedTasks = ${numberCompleteAssignment};
                var uncompletedTasks = ${numberUncompleteAssignment};

                // Tính tỷ lệ phần trăm công việc đã hoàn thành
                var totalTasks = completedTasks + uncompletedTasks;
                var completedPercentage = ((completedTasks / totalTasks) * 100).toFixed(2);

                // Hiển thị tỷ lệ phần trăm công việc đã hoàn thành
                document.getElementById("completedPercentage").innerText = completedPercentage;

                var ctx = document.getElementById('completionChart').getContext('2d');
                var completionChart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: ['Đã hoàn thành', 'Chưa hoàn thành'],
                        datasets: [{
                                data: [completedTasks, uncompletedTasks],
                                backgroundColor: ['#4e73df', '#e74a3b'],
                                hoverBackgroundColor: ['#2e59d9', '#d9534f'],
                                borderWidth: 1
                            }]
                    },
                    options: {
                        responsive: true,
                        plugins: {
                            legend: {
                                position: 'right' // Chuyển chú thích sang bên phải
                            },
                            tooltip: {
                                callbacks: {
                                    label: function (tooltipItem) {
                                        return tooltipItem.label + ': ' + tooltipItem.raw + ' (' +
                                                (tooltipItem.raw / totalTasks * 100).toFixed(2) + '%)';
                                    }
                                }
                            }
                        }
                    }
                });
            });

        </script>




        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
        <script src="js/sb-admin-2.min.js"></script>
    </body>
</html>
