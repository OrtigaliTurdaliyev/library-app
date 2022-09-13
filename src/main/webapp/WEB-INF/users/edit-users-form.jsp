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
    <title>Users TAHRIRLASH</title>
</head>
<body>

<form action="/users/edit" method="post">
    <input type="text" name="id" hidden value="${users.id}">
    <label for="fullName">Full Name: </label>
    <input value="${users.fullName}" name="fullName" type="text" id="fullName">
    <hr>
    <label for="phoneNumber">Phone Number: </label>
    <input value="${users.phoneNumber}" name="phoneNumber" type="text" id="phoneNumber">
    <hr>
    <label for="password">Password: </label>
    <input value="${users.password}" name="password" type="text" id="password">
    <hr>

    <button type="submit">Saqlash</button>
</form>

</body>
</html>
