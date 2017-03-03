<%-- 
    Document   : logout
    Created on : Mar 2, 2017, 3:00:51 AM
    Author     : LittleBird
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Page</title>
    </head>
    <body>
        <%
            session.setAttribute("username", null);
            session.invalidate();
            response.sendRedirect("login.jsp");
        %>
    </body>
</html>
