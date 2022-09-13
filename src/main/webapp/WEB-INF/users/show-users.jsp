<%--
  Created by IntelliJ IDEA.
  User: Ortig'ali
  Date: 9/13/2022
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users ro'yxati</title>
</head>
<body>
<h1>======USERS======</h1>

<div class="container text-center">

    <h1>======= USERS ========</h1>

    <a class="btn btn-outline-primary my-4" href="/users">+ Add new author</a>
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
                            <a href="/users/${user.id}">${user.fullName}</a>
                        </td>
                        <td>${user.phoneNumber}</td>
                        <td>
                            <a class="btn btn-warning" href="/users/edit?id=${user.id}">EDIT</a>
                            <a class="btn btn-danger" href="/users/delete/${user.id}">DELETE</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>
    </div>
</div>


</body>
</html>
