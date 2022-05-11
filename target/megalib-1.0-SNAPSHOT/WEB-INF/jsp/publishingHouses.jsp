<%--
  Created by IntelliJ IDEA.
  User: muhammad
  Date: 03.05.2022
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Publishing Houses</title>
</head>
<body>
    <h1>Издательства:</h1>
    <c:forEach var="publishingHouse" items="${requestScope.publishingHouses}">
        <div>
            <p>${publishingHouse.naming}</p>
            <p>${publishingHouse.description}</p>
        </div>
        <hr>
    </c:forEach>
</body>
</html>
