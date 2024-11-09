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
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>
    <body>
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="sidebar.jsp" %>
            <!-- End of Sidebar -->
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <!-- Topbar -->
                    <%@include file = "topbar.jsp" %>
                    <!-- End of Topbar -->
                    <div class="container-fluid">
                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h1 mb-0 text-gray-800"><b>Dịch Vụ</b>  </h1>
                        </div>
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800 text-primary">Dịch vụ đã đăng ký</h1>
                        </div>
                        
                        
                        <!-- Dịch vụ đã đăng ký -->
                        <div class="row">
                            <!-- Total information for one year -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                    Total Bill (in year)</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.totalBill} VNĐ</div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-money-bill fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                                            
                                            
                        <!-- End Billing information for one year -->

                        <!--Billing information for month-->
                        <div class="row">
                            <div class="d-flex align-items-center justify-content-between mb-4">
                                <h1 id="currentMonth" class="h3 mb-0 text-gray-800 text-primary col-xl-5 col-md-5">Current month's billing information</h1>

                                <form class="d-flex col-xl-7 col-md-7" action="userhome" method="GET" id="chooseMonthYear">
                                    <div class="col-xl-6 col-md-6">
                                        <label for="month" class="form-label">Select Month</label>
                                        <select id="month" name="selectMonth" class="form-select me-2" aria-label="Select Month" onchange="submitMonth()">
                                            <c:forEach items="${requestScope.dateList}" var="dList">
                                                <fmt:formatDate value="${dList}" pattern="M" var="month"/>
                                                <option ${pageScope.month == requestScope.currentMonth ? 'selected' : ''} value="${month}">
                                                    <fmt:setLocale value="en_US"/>
                                                    <fmt:formatDate value="${dList}" pattern="MM"></fmt:formatDate>
                                                        -
                                                    <fmt:formatDate value="${dList}" pattern="MMMM"></fmt:formatDate>
                                                    </option>
                                            </c:forEach>
                                            <c:if test="${count == 0}">
                                                <option selected value="">No data</option>
                                            </c:if>
                                        </select>
                                    </div>
                                    <div class="col-xl-6 col-md-6">
                                        <label for="year" class="form-label">Select Year</label>
                                        <select id="year" name="selectYear" class="form-select" aria-label="Select Year" onchange="submitMonth()">
                                            <c:forEach items="${requestScope.listOfYear}" var="yList">
                                                <option ${requestScope.currentYear == pageScope.yList ? 'selected' : ''} value="${pageScope.yList}">
                                                    ${yList}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </form>
                            </div>
                            <!-- Earnings (Monthly) Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                    Total Bill (Monthly)</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.invoiceCurrent.amount  != 0 ? requestScope.invoiceCurrent.amount : 'unavailable'} VNĐ</div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-money-bill fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Earnings (Monthly) Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    Issue Date</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.invoiceCurrent.issueDate != null ? requestScope.invoiceCurrent.issueDate : 'unavailable'}</div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <!-- Pending Requests Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-warning shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                    Due Date</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.invoiceCurrent.dueDate != null ? requestScope.invoiceCurrent.dueDate : 'unavailable'}</div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Pending Requests Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <c:if test="${requestScope.invoiceCurrent.status == 1}">
                                    <c:set var="colorTab" value="primary"></c:set>
                                    <c:set var="status" value="Paid"/>
                                </c:if>
                                <c:if test="${requestScope.invoiceCurrent.status == 0}">
                                    <c:set var="colorTab" value="warning"></c:set>
                                    <c:set var="status" value="UnPaid"/>
                                </c:if>
                                <div class="card border-left-${colorTab} shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-${colorTab} text-uppercase mb-1">
                                                    Status</div>

                                                <div class="h5 mb-0 font-weight-bold text-gray-800">${pageScope.status}</div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-comments fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--End Current month's billing information-->

                        <!-- Content Row -->

                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="chart-tab" data-toggle="tab" href="#chart" role="tab">Chart</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="table-tab" data-toggle="tab" href="#table" role="tab">Table</a>
                            </li>
                        </ul>
                        <div class="tab-content" id="myTabContent">
                            <br>
                            <div class="tab-pane fade show active" id="chart" role="tabpanel">
                                <!-- chart here -->
                                <div class="row">
                                    <!-- Area Chart -->
                                    <div class="col-xl-8 col-lg-7">
                                        <div class="card shadow mb-4">
                                            <!-- Card Header - Dropdown -->
                                            <div
                                                class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                                <h6 class="m-0 font-weight-bold text-primary">Invoice Overview</h6>
                                                <div class="dropdown no-arrow">
                                                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                                    </a>
                                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                                         aria-labelledby="dropdownMenuLink">
                                                        <div class="dropdown-header">Dropdown Header:</div>
                                                        <a class="dropdown-item" href="#">Action</a>
                                                        <a class="dropdown-item" href="#">Another action</a>
                                                        <div class="dropdown-divider"></div>
                                                        <a class="dropdown-item" href="#">Something else here</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Card Body -->
                                            <div class="card-body">
                                                <div class="chart-area">
                                                    <canvas id="myAreaChart"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Pie Chart -->
                                    <div class="col-xl-4 col-lg-5">
                                        <div class="card shadow mb-4">
                                            <!-- Card Header - Dropdown -->
                                            <div
                                                class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                                <h6 class="m-0 font-weight-bold text-primary">Invoice Sources</h6>
                                                <div class="dropdown no-arrow">
                                                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                                    </a>
                                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                                         aria-labelledby="dropdownMenuLink">
                                                        <div class="dropdown-header">Dropdown Header:</div>
                                                        <a class="dropdown-item" href="#">Action</a>
                                                        <a class="dropdown-item" href="#">Another action</a>
                                                        <div class="dropdown-divider"></div>
                                                        <a class="dropdown-item" href="#">Something else here</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Card Body -->
                                            <div class="card-body">
                                                <div class="chart-pie pt-4 pb-2">
                                                    <canvas id="myPieChart"></canvas>
                                                </div>
                                                <div class="mt-4 text-center small">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>   
                            </div>
                            <div class="tab-pane fade" id="table" role="tabpanel">
                                <!-- table here -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Service List</h6>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>Name</th>
                                                        <th>Type</th>
                                                        <th>Registration Date</th>
                                                        <th>Expiration Date</th>
                                                        <th>Amount</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${requestScope.invoiceCurrent.getServiceContractList()}" var="serviceContract">
                                                        <tr>
                                                            <td>${serviceContract.getService().getName()}</td>
                                                            <td>${serviceContract.getService().getType()}</td>
                                                            <td>${serviceContract.getStartDate()}</td>
                                                            <td>${serviceContract.getEndDate()}</td>
                                                            <td>${serviceContract.getAmount()} VNĐ</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/chart.js/Chart.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script> 

        <!-- Page level plugins -->
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>
        <script>
                                            function submitMonth() {
                                                document.getElementById('chooseMonthYear').submit();
                                            }

                                            let d = new Date();
                                            const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

                                            let month = months.find((value, index) => {
                                                if (d.getMonth()) {
                                                    return index == d.getMonth() - 1;
                                                } else {
                                                    return index == 12;
                                                }
                                            });
                                            console.log(month)

//            document.getElementById("currentMonth").innerHTML += "(" + month + ", " + d.getFullYear() + ")";

                                            // take data from servlet to js
                                            const amountList = [
            <c:forEach items="${requestScope.amoutMonth}" var="amountList">
                ${amountList},
            </c:forEach>
                                            ];
                                            const serviceList = [
            <c:forEach items="${requestScope.serviceList}" var="serviceContract">
                                                "${serviceContract.getService().getName()}",
            </c:forEach>
                                            ];
                                            const amountService = [
            <c:forEach items="${requestScope.invoiceCurrent.getServiceContractList()}" var="serviceContract">
                <c:out value="${serviceContract.getAmount()}"/>,
            </c:forEach>
                                            ];
                                            console.log(amountService);


                                            function number_format(number, decimals, dec_point, thousands_sep) {
                                                // *     example: number_format(1234.56, 2, ',', ' ');
                                                // *     return: '1 234,56'
                                                number = (number + '').replace(',', '').replace(' ', '');
                                                var n = !isFinite(+number) ? 0 : +number,
                                                        prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
                                                        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
                                                        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
                                                        s = '',
                                                        toFixedFix = function (n, prec) {
                                                            var k = Math.pow(10, prec);
                                                            return '' + Math.round(n * k) / k;
                                                        };
                                                // Fix for IE parseFloat(0.55).toFixed(0) = 0;
                                                s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
                                                if (s[0].length > 3) {
                                                    s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
                                                }
                                                if ((s[1] || '').length < prec) {
                                                    s[1] = s[1] || '';
                                                    s[1] += new Array(prec - s[1].length + 1).join('0');
                                                }
                                                return s.join(dec);
                                            }
                                            // Pie Chart Example
                                            var ctx = document.getElementById("myPieChart");
                                            var myPieChart = new Chart(ctx, {
                                                type: 'doughnut',
                                                data: {
                                                    labels: serviceList,
                                                    datasets: [{
                                                            data: amountService,
                                                            backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc', '#f6c23e', '#e74a3b', '#5a5c69', '#f8c8db', '#b3d0d6', '#ffcc00', '#ff6347', '#6c757d', '#007bff'],
                                                            hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
                                                            hoverBorderColor: "rgba(234, 236, 244, 1)",
                                                        }],
                                                },
                                                options: {
                                                    maintainAspectRatio: false,
                                                    tooltips: {
                                                        backgroundColor: "rgb(255,255,255)",
                                                        bodyFontColor: "#858796",
                                                        borderColor: '#dddfeb',
                                                        borderWidth: 1,
                                                        xPadding: 15,
                                                        yPadding: 15,
                                                        displayColors: false,
                                                        caretPadding: 10,
                                                        callbacks: {
                                                            label: function (tooltipItem, data) {
                                                                var value = number_format(data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index]);
                                                                return value + ' VNĐ';
                                                            }
                                                        }
                                                    },
                                                    legend: {
                                                        display: true,
                                                        position: 'bottom', // Hoặc 'top', 'left', 'right'
                                                        labels: {
                                                            boxWidth: 10, // Kích thước của hộp màu
                                                            padding: 10 // Khoảng cách giữa các mục
                                                        }
                                                    },
                                                    cutoutPercentage: 60,
                                                },
                                            });


                                            // Area Chart Example
                                            var ctx = document.getElementById("myAreaChart");
                                            var myLineChart = new Chart(ctx, {
                                                type: 'line',
                                                data: {
                                                    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                                                    datasets: [{
                                                            label: "Amount",
                                                            lineTension: 0.3,
                                                            backgroundColor: "rgba(78, 115, 223, 0.05)",
                                                            borderColor: "rgba(78, 115, 223, 1)",
                                                            pointRadius: 3,
                                                            pointBackgroundColor: "rgba(78, 115, 223, 1)",
                                                            pointBorderColor: "rgba(78, 115, 223, 1)",
                                                            pointHoverRadius: 3,
                                                            pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                                                            pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                                                            pointHitRadius: 10,
                                                            pointBorderWidth: 2,
                                                            data: amountList,
                                                        }],
                                                },
                                                options: {
                                                    maintainAspectRatio: false,
                                                    layout: {
                                                        padding: {
                                                            left: 10,
                                                            right: 25,
                                                            top: 25,
                                                            bottom: 0
                                                        }
                                                    },
                                                    scales: {
                                                        xAxes: [{
                                                                time: {
                                                                    unit: 'date'
                                                                },
                                                                gridLines: {
                                                                    display: false,
                                                                    drawBorder: false
                                                                },
                                                                ticks: {
                                                                    maxTicksLimit: 7
                                                                }
                                                            }],
                                                        yAxes: [{
                                                                ticks: {
                                                                    maxTicksLimit: 5,
                                                                    padding: 10,
                                                                    // Include a dollar sign in the ticks
                                                                    callback: function (value, index, values) {
                                                                        return number_format(value) + ' VNĐ';
                                                                    }
                                                                },
                                                                gridLines: {
                                                                    color: "rgb(234, 236, 244)",
                                                                    zeroLineColor: "rgb(234, 236, 244)",
                                                                    drawBorder: false,
                                                                    borderDash: [2],
                                                                    zeroLineBorderDash: [2]
                                                                }
                                                            }],
                                                    },
                                                    legend: {
                                                        display: false
                                                    },
                                                    tooltips: {
                                                        backgroundColor: "rgb(255,255,255)",
                                                        bodyFontColor: "#858796",
                                                        titleMarginBottom: 10,
                                                        titleFontColor: '#6e707e',
                                                        titleFontSize: 14,
                                                        borderColor: '#dddfeb',
                                                        borderWidth: 1,
                                                        xPadding: 15,
                                                        yPadding: 15,
                                                        displayColors: false,
                                                        intersect: false,
                                                        mode: 'index',
                                                        caretPadding: 10,
                                                        callbacks: {
                                                            label: function (tooltipItem, chart) {
                                                                var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                                                                return datasetLabel + ': ' + number_format(tooltipItem.yLabel) + ' VNĐ';
                                                            }
                                                        }
                                                    }
                                                }
                                            });
        </script>
    </body>
</html>
