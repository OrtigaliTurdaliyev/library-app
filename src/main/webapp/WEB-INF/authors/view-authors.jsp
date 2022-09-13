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
    <title>MUALLIFLARNI RO'YXATI</title>

    <style>
        .status-color {
            color: <c:choose> <c:when test="${status.success}" >green</c:when>
            <c:otherwise>red</c:otherwise></c:choose>
        }
    </style>
</head>
<body>

<h1 class="status-color">${status.message}</h1>


<button><a href="/authors/get-form">+ new author</a></button>


<c:forEach items="${mualliflar}" var="muallif" varStatus="loop">
    <h1>${muallif.fullName}</h1>
    <p>${muallif.biography}</p>
    <button><a href="/authors/edit/${muallif.id}">Edit</a></button>
    <button><a href="/authors/delete/${muallif.id}">Delete</a></button>
    <hr>
</c:forEach>

<a href="/" class="btn btn-primary mt-4">Bosh saxifa</a></body>
</body>
</html>
