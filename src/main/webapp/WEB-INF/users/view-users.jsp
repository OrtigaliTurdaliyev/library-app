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
    <title>Users RO'YXATI</title>
</head>
<body>



<div class="container text-center">

    <h1>======= USERS ========</h1>

    <button><a href="/users/get-form">+ New users</a></button>
    <div class="row">
        <div class="col-md-6 offset-3">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">FullName</th>
                    <th scope="col">Phone Number</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${users}" var="user" varStatus="loop">
                    <tr>

                        <th scope="row">${loop.index+1}</th>

                        <td>
                           <button> <a href="/users/${user.id}">${user.fullName}</a></button><hr>
                        </td>
                        <td>${user.phoneNumber}</td>
                        <td>
                           <button> <a class="btn btn-warning" href="/users/edit?id=${user.id}">EDIT</a></button>
                           <button> <a class="btn btn-danger" href="/users/delete/${user.id}">DELETE</a></button>
                            <hr>
                        </td>

                    </tr>

                </c:forEach>


                </tbody>
            </table>

        </div>
    </div>
</div>

<%--<c:forEach items="${users}" var="users" varStatus="loop">--%>
<%--   <button href="/users/by/${users.id}"><h1>${users.fullName}</h1></button>--%>
<%--    <button><a href="/users/edit/${users.id}">Edit</a></button>--%>
<%--    <button><a href="/users/delete/${users.id}">Delete</a></button>--%>
<%--    <hr>--%>
<%--</c:forEach>--%>


</body>
</html>
