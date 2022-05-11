<%--
  Created by IntelliJ IDEA.
  User: muhammad
  Date: 03.05.2022
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
    <h1>Книги: </h1>
    <c:forEach var="book" items="${requestScope.books}">
        <div>
            <img src="${book.image}" alt="photo">
            <p>${book.naming}</p>
            <p>${book.description}</p>
        </div>
        <hr>
    </c:forEach>
</body>
</html>
