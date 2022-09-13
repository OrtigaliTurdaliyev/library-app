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
    <title>Tillarni RO'YXATI</title>
</head>
<body>

<button><a href="/languages/get-form">+ new language</a></button>


<c:forEach items="${tillar}" var="til" varStatus="loop">
    <h1>${til.name}</h1>
    <button><a href="/languages/edit/${til.id}">Edit</a></button>
    <button><a href="/languages/delete/${til.id}">Delete</a></button>
    <hr>
</c:forEach>


</body>
</html>
