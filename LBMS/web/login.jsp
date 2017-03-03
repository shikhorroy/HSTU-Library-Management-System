<%-- 
    Document   : index
    Created on : Feb 22, 2017, 2:36:25 PM
    Author     : LittleBird
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href = "css/login.css">

        <title>Login</title>
    </head>
    <body>
        <div class = "login_box">
            <h1>Admin</h1>
            <h2>Login...</h2>
            <form action="Login" method="post">
                <span>Username: </span><input type="text" name="username"/><br/>
                <span>Password: </span><input type="password" name="password"/><br/>
                <input type="submit" value="Login"/>
            </form><br/>
            <center>
                <c:if test="${not empty errMsg}">                    
                    <b>[ N.B.</b><font color="red"> <c:out value="${errMsg}"/></font><b> ]</b>
                </c:if>
            </center>
        </div>
    </body>
</html>
