<%-- 
    Document   : serviceinfor
    Created on : Sep 25, 2024, 6:29:53 PM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&family=Inter:slnt,wght@-10..0,100..900&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link rel="stylesheet" href="lib/animate/animate.min.css"/>
        <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container" style="margin-top: 10px;">
            <div class="row"
                 style="border: 1px darkgrey solid; border-radius: 10px;width: 50%;margin: 0 auto;padding: 20px;">
                <div class="col-sm-12">
                    <h2 class="myclass">Register</h2>
                    <form action="#" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label>ID</label>
                            <input type="text"
                                   class="form-control" name="id"  readonly value="${service.serviceId}"><!-- comment -->
                        </div>
                        <div class="form-group">
                            <label>Name Service</label>
                            <input type="text"
                                   class="form-control" name="name" value="${service.name}"><!-- comment -->
                        </div>
                        <div class="form-group">
                            <label>Type Service</label>
                            <input type="text"
                                   class="form-control" name="name" value="${service.type}"><!-- comment -->
                        </div>
                        <div class="form-group">
                            <label>Fee Service</label>
                            <input type="number"
                                   class="form-control" name="name" value="${service.fee}"><!-- comment -->
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <input type="text"
                                   class="form-control" name="name" value="${service.description}"><!-- comment -->
                        </div>
                        <div class="form-group">
                            <label>URL img</label>
                            <input type="file"
                                   class="form-control" name="name" value="${service.img}"><!-- comment -->
                        </div>
                        <div class="form-group">
                            <label>URL icon</label>
                            <input type="text"
                                   class="form-control" name="name" value="${service.icon}"><!-- comment -->
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
