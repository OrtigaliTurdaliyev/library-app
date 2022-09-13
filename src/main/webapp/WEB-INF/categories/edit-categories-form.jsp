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
    <title>CATEGORY TAHRIRLASH</title>
</head>
<body>

<form action="/categories/edit" method="post">
    <input type="text" name="id" hidden value="${categories.id}">
    <label for="name">Name: </label>
    <input value="${categories.name}" name="name" type="text" id="name">
    <hr>
    <label for="description">Description: </label>
    <input value="${categories.description}" name="description" type="text" id="description">

    <hr>
    <button type="submit">Saqlash</button>
</form>

</body>
</html>
