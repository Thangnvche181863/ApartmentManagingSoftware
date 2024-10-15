<%-- 
    Document   : verify
    Created on : Oct 15, 2024, 12:15:07 AM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Verify Code</title>
        <!-- Include Tailwind CSS CDN -->
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body>
        <section class="bg-gray-50 dark:bg-gray-900">
            <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
                <div class="w-full p-6 bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md dark:bg-gray-800 dark:border-gray-700 sm:p-8">
                    <h2 class="mb-1 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                        Nhập mã xác thực
                    </h2>
                    <% if (request.getAttribute("errVerify") != null) { %>
                    <div class="alert alert-danger">
                        <%= request.getAttribute("errVerify") %>
                    </div>
                    <% } %>

                    <% if (request.getAttribute("changemailerr") != null) { %>
                    <div class="alert alert-danger">
                        <%= request.getAttribute("changemailerr") %>
                    </div>
                    <% } %>

                    <% if (request.getAttribute("changemailSuccess") != null) { %>
                    <div class="alert alert-success">
                        <%= request.getAttribute("changemailSuccess") %>
                    </div>
                    <% } %>

                    <form class="mt-4 space-y-4 lg:mt-5 md:space-y-5" action="verify" method="POST">
                        <div>
                            <label for="vericode" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Mã xác thực</label>
                            <input type="text" name="vericode" id="vericode" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required="">
                        </div>
                        <button type="submit" class="w-full text-white bg-gray-500 hover:bg-gray-600 focus:ring-4 focus:outline-none focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-gray-500 dark:hover:bg-gray-600 dark:focus:ring-gray-800">Xác nhận</button>
                    </form>
                    <a href="userhome">
                        <button>Back to home</button>
                    </a>
                </div>
            </div>
        </section>
        <script>
            tailwind.config = {
                darkMode: 'class',
                theme: {
                    extend: {
                        colors: {
                            primary: {"50": "#eff6ff", "100": "#dbeafe", "200": "#bfdbfe", "300": "#93c5fd", "400": "#60a5fa", "500": "#3b82f6", "600": "#2563eb", "700": "#1d4ed8", "800": "#1e40af", "900": "#1e3a8a", "950": "#172554"}
                        }
                    },
                    fontFamily: {
                        'body': [
                            'Inter',
                            'ui-sans-serif',
                            'system-ui',
                            '-apple-system',
                            'system-ui',
                            'Segoe UI',
                            'Roboto',
                            'Helvetica Neue',
                            'Arial',
                            'Noto Sans',
                            'sans-serif',
                            'Apple Color Emoji',
                            'Segoe UI Emoji',
                            'Segoe UI Symbol',
                            'Noto Color Emoji'
                        ],
                        'sans': [
                            'Inter',
                            'ui-sans-serif',
                            'system-ui',
                            '-apple-system',
                            'system-ui',
                            'Segoe UI',
                            'Roboto',
                            'Helvetica Neue',
                            'Arial',
                            'Noto Sans',
                            'sans-serif',
                            'Apple Color Emoji',
                            'Segoe UI Emoji',
                            'Segoe UI Symbol',
                            'Noto Color Emoji'
                        ]
                    }
                }
            };
        </script>            
    </body>
</html>
