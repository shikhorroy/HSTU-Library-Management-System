<%--
    Document   : confirmborrow
    Created on : Feb 26, 2017, 6:24:51 PM
    Author     : LittleBird
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>Confirm Borrow Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:if test="${sessionScope.username != null && sessionScope.username !=''}">
        <center>
            <div id = "box">
                <h1>Confirm Borrow</h1>

                <form action="BorrowController">
                    <table>
                        <thead>
                            <tr><th><u>Student Info:</u></th></tr>
                        </thead>
                        <tbody>
                            <tr><td>Id:</td><td>${student.id}</td></tr>
                            <tr><td>Name:</td><td>${student.firstName} ${student.lastName}</td></tr>
                            <tr><td>E-mail:</td><td>${student.email}</td></tr>
                            <tr><td>Phone No:</td><td>${student.phoneNo}</td></tr>
                            <tr><td>Dept:</td><td>${student.dept}</td></tr>
                            <tr><td>Address:</td><td>${student.address}</td></tr>
                        </tbody>
                    </table>

                    <table>
                        <thead>
                            <tr><th><u>Book Info:</u></th></tr>
                        </thead>
                        <tbody>
                            <tr><td>ISBN:</td><td>${book.isbn}</td></tr>
                            <tr><td>Title:</td><td>${book.bookname}</td></tr>
                            <tr><td>Author(s):</td><td>${book.authorname}</td></tr>
                            <tr><td>Category:</td><td>${book.category}</td></tr>
                            <tr><td>Self No:</td><td>${book.selfno}</td></tr>
                        </tbody>
                    </table>

                    <table>
                        <thead>
                            <tr><th><u>Borrow Info:</u></th></tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Borrow Date: </td>
                                <td>
                                    <%
                                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                        Calendar calobj = Calendar.getInstance();
                                        String borrowdate = df.format(calobj.getTime());
                                        out.println(borrowdate);
                                    %>
                                </td>
                            </tr>
                            <tr>
                                <td>Return within: </td>
                                <td>
                                    <%
                                        calobj.add(Calendar.DATE, 7);
                                        String returndate = df.format(calobj.getTime());
                                        out.println(returndate);
                                    %>
                                </td>
                            </tr>  
                        </tbody>
                    </table>

                    <input type="hidden" name="borrowdate" value="<%=borrowdate%>" />
                    <input type="hidden" name="returndate" value="<%=returndate%>" />
                    <input type="hidden" name="studentid" value="${student.id}" />
                    <input type="hidden" name="bookid" value="${book.isbn}" />

                    <input type="submit" name="confirm" value="Confirm"/>
                    <input type="submit" name="notconfirm" value="Cancel"/>
                </form>
            </div>
        </center>
    </c:if>
</body>
</html>
