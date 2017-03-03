<%--
    Document   : home
    Created on : Feb 22, 2017, 2:37:16 PM
    Author     : LittleBird
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>Home</title>
    </head>

    <body>

        <%@include file="header.jsp" %>
        <c:if test="${sessionScope.username != null && sessionScope.username !=''}">
        <center>
            <div id = "box">
                <h2>Welcome !!!</h2>
                <article>
                    <h3>Activities and Facilities:</h3>
                    <p>
                        HSTU Library is a well furnished multi storied building. It is doing its daily activities by different sections, such as: Acquisition section, Circulation section, Processing section and Reference section which are situated at different location in the Library Building. Administrative section of the library does the administrative activities.
                        Acquisition Section is always working hard to collect the all kind of required documents of the users. Recently it has collected a great deal of different types of books for different faculties of this university.
                        Circulation section sets on the ground floor of the library building. All the researchers, students and teachers of this university may borrow their required books as per rule from the Circulation Section for reading the book outside of the library or home. Only those books that are more than 2 copies are available for loan to students/ teachers/ researchers.
                        Processing section is doing an important job. It prepares the catalogue and classification all kind of documents by using the AACR-2 code and D.D.C. scheme. It is located on the ground floor of the library building.
                        Reference section is another important section. It is situated at the first floor and second floor of the building. Teachers, researchers and students may read the rare documents sitting in the Reference Section. It also provides photocopy facility against a nominal fee. Another important service of this section is current awareness service. It regularly sends the list of current received documents to the faculties. Thesis, 73 titled home and abroad journals and 15 Daily newspapers are kept at the 2nd floor of the building. All kind of users can read the journal, daily newspaper and thesis here. There is a cyber center in the Library. All users may use the cyber center against a nominal fee.
                    </p>
                    <h3>Mission:</h3>
                    <p>
                        The Library’s mission is to provide comprehensive resources and services in support of the research, teaching and learning needs of the University community.
                    </p>
                    <h3>Vision:</h3>
                    <p>
                        Build up a world-class Knowledge Resource Centre and provide innovative services and collections to the research, teaching and learning communities by using latest technology.
                    </p>
                    <h3>Library Hour:</h3>
                    <p>
                        The Library shall be opened from 8:00 am to 7:00 pm. It shall be wholly closed on university holidays.
                    </p>
                </article>
            </div>
        </center>
    </c:if>
</body>
</html>
