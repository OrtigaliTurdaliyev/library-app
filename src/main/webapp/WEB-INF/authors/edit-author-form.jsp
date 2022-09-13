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
    <title>MUALLIF TAHRIRLASH</title>
</head>
<body>

<form action="/authors/edit" method="post">
    <input type="text" name="id" hidden value="${muallif.id}">
    <label for="fullName">Ism Familyasi: </label>
    <input value="${muallif.fullName}" name="fullName" type="text" id="fullName">
    <hr>
    <label for="biography">Biografiyasi: </label>
    <input value="${muallif.biography}" name="biography" type="text" id="biography">

    <hr>
    <button type="submit">Saqlash</button>
</form>
<a href="/" class="btn btn-primary mt-4">Bosh saxifa</a></body>
</body>
</html>
