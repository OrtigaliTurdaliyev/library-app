<%--
  Created by IntelliJ IDEA.
  User: abror
  Date: 10/09/22
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CATEGORIES RO'YXATI</title>

    <style>
        .status-color {
            color: <c:choose> <c:when test="${status.success}" >green</c:when>
            <c:otherwise>red</c:otherwise></c:choose>
        }
    </style>
</head>
<body>

<h1 class="status-color">${status.message}</h1>


<button><a href="/categories/get-form">+ new author</a></button>


<c:forEach items="${categories}" var="categories" varStatus="loop">
    <h1>${categories.name}</h1>
    <p>${categories.description}</p>
    <button> <a href="/categories/edit/${categories.id}">Edit</a></button>
    <button> <a href="/categories/delete/${categories.id}">Delete</a></button>
    <hr>
</c:forEach>


</body>
</html>
