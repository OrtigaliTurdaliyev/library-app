<%--
  Created by IntelliJ IDEA.
  User: oybek
  Date: 9/10/2022
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--  <jsp:include page="Css.jsp"></jsp:include>--%>
    <title>Add Amount</title>
</head>
<body>
<div class="container text-center">
  <form action="/books/add" method="post">
<input type="hidden" value="${bookId}" name="id">
<label for="amount">
  <input type="number" min="1" max="100000" name="count" id="amount">
</label>
<button class="btn btn-outline-primary mt-4">Add</button>
  </form>
</div>
</body>
</html>
