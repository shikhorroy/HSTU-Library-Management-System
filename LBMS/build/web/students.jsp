<%--
    Document   : students
    Created on : Feb 22, 2017, 5:58:34 PM
    Author     : LittleBird
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>Students Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:if test="${sessionScope.username != null && sessionScope.username !=''}">
        <center>
            <div id = "box">
                <h3>Welcome to Students Page </h3>
                <ul>
                    <li><a href="newstudent.jsp">Student Registration</a></li>
                </ul>

                <input type="text" name="searchbox" placeholder="Enter to search..."/>
                <input type="submit" name ="searchbutton" value="Search"/><br/><br/>

                <c:if scope="request" test="${exists == 'yes'}" var="reg">
                    <font color="red"><b>*** Id should be unique!!</b></font><br/><br/>
                </c:if>
                <c:if scope="request" test="${flag == 'reg'}" var="reg">
                    <font color="green"><b>*** Registration Successful!!</b></font><br/><br/>
                </c:if>
                <c:if scope="request" test="${flag == 'update'}" var="upd">
                    <font color="green"><b>*** Update Successful!!</b></font><br/><br/>
                </c:if>

                <table border="1">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Phone No</th>
                            <th>Address</th>
                            <th>Gender</th>
                            <th>Marital Status</th>
                            <th>Department</th>
                            <th colspan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${allStudents}" var="student">
                            <tr <c:if test="${id == student.id}"> style="background-color: gainsboro" </c:if>>
                                <td><c:out value="${student.id}"/></td>
                                <td><c:out value="${student.firstName}"/></td>
                                <td><c:out value="${student.lastName}"/></td>
                                <td><c:out value="${student.email}"/></td>
                                <td><c:out value="${student.phoneNo}"/></td>
                                <td><c:out value="${student.address}"/></td>
                                <td><c:out value="${student.gender == 1 ? 'Male' : 'Female'}"/></td>
                                <td><c:out value="${student.ms == 1 ? 'Married' : 'Unmarried'}"/></td>
                                <td><c:out value="${student.dept}"/></td>
                                <td><a href="StudentsController?action=update&id=${student.id}">Update</a></td>
                                <td><a href="StudentsController?action=delete&id=${student.id}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </center>
    </c:if>
</body>
</html>
