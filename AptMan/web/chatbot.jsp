<%-- 
    Document   : chatbot
    Created on : Oct 22, 2024, 10:35:50 AM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
        <df-messenger
        intent="WELCOME"
        chat-title="BOTtest1"
        agent-id="6708e5b6-3bfa-416e-adc7-1fea6c2d914a"
        language-code="en"
        ></df-messenger>
    </body>
</html>
