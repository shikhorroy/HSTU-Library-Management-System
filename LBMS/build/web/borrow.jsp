<%--
    Document   : borrow
    Created on : Feb 26, 2017, 6:00:47 PM
    Author     : LittleBird
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>Borrow Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:if test="${sessionScope.username != null && sessionScope.username !=''}">
        <center>
            <div id = "box">
                <h1>Borrow Books!!!</h1>

                <c:if test="${flag}"><font color="green"><b>*** Borrow Sucessfull!!!</b></font><br/><br/> </c:if>

                    <form action="BorrowController">
                        <center>
                            <table>
                                <tbody>
                                    <tr>
                                        <td>Student Id: </td>
                                        <td>
                                            <input type="text" name="studentid" required placeholder="Put an Integer..." style="width: 350px"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>ISBN NO.: </td>
                                        <td>
                                            <input type="text" name="bookid" required style="width: 350px"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input type="submit" name="submit" value="Submit"/>                                
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </center>
                    </form>
                </div>
            </center>
    </c:if>
</body>
</html>
