<%--
  Created by IntelliJ IDEA.
  User: oybek
  Date: 9/9/2022
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book</title>
<%--  <jsp:include page="Css.jsp"></jsp:include>--%>
</head>
<body>
<h1>TITLE: ${book.title}</h1>
<h1>AUTHORS: <c:forEach items="${book.authorDtoList}" var="authors">
    <a href="/authors/${authors.authorId}">${authors.authorFullName}</a>
</c:forEach></h1>
<h1>LANGUAGE: ${book.languageName}</h1>
<h1>COUNT: ${book.count}</h1>
<h1>CATEGORIES: <c:forEach items="${book.categoryDtoList}" var="categories">
    <a href="/categories/${categories.id}">${categories.name}</a>
</c:forEach></h1></h1>
<h1>ISBN: ${book.isbn}</h1>
<h2>DESCRIPTION: ${book.description}</h2>
<a href="/" class="btn btn-primary mt-4">Main Menu</a></body>
</body>
</html>
