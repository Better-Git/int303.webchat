<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="./WEB-INF/header.jsp" %>

        <!-- JS -->
        <script src="https://code.getmdl.io/1.2.0/material.min.js" defer></script>

        <title>Test</title>
    </head>
    <body>
        <p>${requestScope.room}</p>
        <p>${requestScope.username}</p>
        <p>${requestScope.mk-private}</p>
        <p>${requestScope.password}</p>
    </body>
</html>