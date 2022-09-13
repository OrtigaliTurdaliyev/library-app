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
    <title>Til TAHRIRLASH</title>
</head>
<body>

<form action="/languages/edit" method="post">
    <input type="text" name="id" hidden value="${til.id}">
    <label for="name">Til nomi: </label>
    <input value="${til.lang}" name="lang" type="text" id="name">
    <hr>

    <button type="submit">Saqlash</button>
</form>

</body>
</html>
