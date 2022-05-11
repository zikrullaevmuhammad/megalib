<%--
  Created by IntelliJ IDEA.
  User: muhammad
  Date: 03.05.2022
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Authors</title>
</head>
<body>
    <h1>Авторы</h1>
    <c:forEach var="author" items="${requestScope.authors}">
        <div>
            <p>${author.naming}</p>
            <p>${author.about}</p>
        </div>
        <hr>
    </c:forEach>
</body>
</html>
