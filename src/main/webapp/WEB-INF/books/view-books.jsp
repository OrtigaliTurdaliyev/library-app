<%--
  Created by IntelliJ IDEA.
  User: abror
  Date: 08/09/22
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book List</title>
<%--    <jsp:include page="Css.jsp"></jsp:include>--%>

</head>
<body>

<div class="container text-center">

    <h1>======= BOOKS ========</h1>

    <a class="btn btn-outline-primary my-4" href="/books/get-form">+ Add new book</a>
    <div class="row">
        <div class="col-md-8 offset-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Title</th>
                    <th scope="col">Authors</th>
                    <th scope="col">Language</th>
                    <th scope="col">Amount</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${kitoblar}" var="bookDto" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.index+1}</th>
                        <td>
                            <a href="/books/${bookDto.id}">${bookDto.title}</a>
                        </td>
                        <td>
                            <c:forEach items="${bookDto.authorDtoList}" var="author">
                                <a href="/authors/${author.authorId}">${author.authorFullName}</a> |
                            </c:forEach>
                        </td>
                        <td>${bookDto.languageName}</td>
                        <td>${bookDto.count}</td>
                        <td>
                            <a href="/books/add-amount/${bookDto.id}" class="btn btn-outline-primary">+</a>
                            <a class="btn btn-warning" href="/books/edit?id=${bookDto.id}">EDIT</a>
                            <a class="btn btn-danger" href="/books/delete/${bookDto.id}">DELETE</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>
    </div>
</div>

<a href="/" class="btn btn-primary mt-4">Main Menu</a></body>
</body>
</html>
