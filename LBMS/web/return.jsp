<%--
    Document   : return
    Created on : Mar 1, 2017, 10:43:48 AM
    Author     : LittleBird
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.time.Instant"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="com.rasha.model.Borrow"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>Return Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:if test="${sessionScope.username != null && sessionScope.username !=''}">
        <center>
            <div id = "box">
                <h3>Return Books </h3>
                <c:if test="${flag}"><font color="green"><b>*** Book Return Sucessfull!!!</b></font><br/><br/> </c:if>

                    <input type="text" name="searchbox" placeholder="Enter to search..."/>
                    <input type="submit" name ="searchbutton" value="Search"/><br/><br/>

                    <table border="1" style="width: 100%">
                        <thead>
                            <tr>
                                <th>Borrow Id</th>
                                <th>Title</th>
                                <th>Author Name</th>
                                <th>Student Id</th>
                                <th>Student Name</th>
                                <th>Borrow Date</th>
                                <th>Deadline</th>
                                <th>Fine</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="br" items="${allBr}">
                            <tr>
                                <td><c:out value="${br.id}"/></td>
                                <td><c:out value="${br.title}"/></td>
                                <td><c:out value="${br.authorName}"/></td>
                                <td><c:out value="${br.studentId}"/></td>
                                <td><c:out value="${br.studentName}"/></td>
                                <td><c:out value="${br.borrowDate}"/></td>
                                <td><c:out value="${br.returnDate}"/></td>

                                <c:set var="borrowDate" value="${br.borrowDate}"/>
                                <c:set var="returnDate" value="${br.returnDate}"/>

                                <td>
                                    <%
                                        Date borrowDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(pageContext.getAttribute("borrowDate").toString());
                                        Date returnDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(pageContext.getAttribute("returnDate").toString());
                                        Date currentDate = new Date();

                                        int diffInDays = (int) ((currentDate.getTime() - borrowDate.getTime()) / (1000 * 60 * 60 * 24));
                                        pageContext.setAttribute("diffInDays", diffInDays);

                                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                        Calendar calobj = Calendar.getInstance();
                                        String returnedDate = df.format(calobj.getTime());
                                        pageContext.setAttribute("returnDate", returnedDate);
                                    %>
                                    <c:choose>
                                        <c:when test="${diffInDays > 7}">
                                            <c:set var="fine" value="${(diffInDays - 7) * 5}"/>                                    
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="fine" value="${0}"/>                                    
                                        </c:otherwise>
                                    </c:choose>
                                    <c:out value="${fine}"/>
                                </td>
                                <td><a href="ReturnController?id=${br.id}&fine=${fine}&returndate=${returnDate}">Return</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>   
            </div>
        </center>
    </c:if>
</body>
</html>
