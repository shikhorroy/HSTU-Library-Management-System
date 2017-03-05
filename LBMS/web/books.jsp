<%-- 
    Document   : books
    Created on : Feb 26, 2017, 3:05:05 PM
    Author     : LittleBird
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>Books Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:if test="${sessionScope.username != null && sessionScope.username !=''}">
        <center>
            <div id = "box">

                <h3>Welcome to Books Page </h3>
                <ul>                
                    <li><a href="newbook.jsp">Add New Book</a></li>
                </ul>

                <!--<input type="text" name="searchbox" placeholder="Enter to search..."/>
                <input type="submit" name ="searchbutton" value="Search"/><br/><br/>-->

                <c:if scope="request" test="${exists == 'yes'}" var="reg">
                    <font color="red"><b>*** Id should be unique!!</b></font><br/><br/>
                </c:if>
                <c:if scope="request" test="${flag == 'reg'}" var="reg">
                    <font color="green"><b>*** New Book Added!!</b></font><br/><br/>
                </c:if>
                <c:if scope="request" test="${flag == 'update'}" var="upd">
                    <font color="green"><b>*** Update Successful!!</b></font><br/><br/>
                </c:if>

                <table border="1" style="width: 100%">
                    <thead>
                        <tr>
                            <th>ISBN NO.</th>
                            <th>Title</th>
                            <th>Author Name</th>
                            <th>Category</th>
                            <th>Self NO.</th>                    
                            <th colspan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${allBooks}" var="book">
                            <tr <c:if test="${isbn == book.isbn}"> style="background-color: gainsboro" </c:if>>
                                <td><c:out value="${book.isbn}"/></td>
                                <td><c:out value="${book.bookname}"/></td>
                                <td><c:out value="${book.authorname}"/></td>
                                <td><c:out value="${book.category}"/></td>
                                <td><c:out value="${book.selfno}"/></td>
                                <td><a href="BooksController?action=update&isbn=${book.isbn}">Update</a></td>
                                <td><a href="BooksController?action=delete&isbn=${book.isbn}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </center>
    </c:if>
</body>
</html>
