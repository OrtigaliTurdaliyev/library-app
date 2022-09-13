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
    <title>Book FORM</title>
<%--    <jsp:include page="Css.jsp"></jsp:include>--%>

</head>
<body>

<div class="container text-center">
    <h1 style="color: red">Bu isbn dagi kitob mavjud!!!</h1>
    <div class="row">
        <div class="col-md-6 offset-3">
            <form action="/books" method="post">
                    <div class="mb-3">
                        <label for="bookTitle" class="form-label">Title</label>
                        <input name="title" type="text" class="form-control" value="${book.title}" id="bookTitle"
                               required>
                    </div>

                    <div class="mb-3">
                        <label for="bookDescription" class="form-label">Description</label>
                        <input name="description" type="text" class="form-control" value="${book.description}"
                               id="bookDescription" required>
                    </div>
                    <div class="mb-3">
                        <label for="bookIsbn" class="form-label">ISBN</label>
                        <input name="isbn" type="number" class="form-control" value="${book.isbn}" id="bookIsbn"
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="bookCount" class="form-label">AMOUNT</label>
                        <input name="count" type="number" min="1" max="1000" class="form-control" value="${book.count}"
                               required id="bookCount">
                    </div>
                    <div class="form-group">
                        <label for="authorsIds">Authors:</label>
                        <select id="authorsIds"
                                class="selectpicker form-control"
                                aria-label="Please select authors"
                                data-live-search="true"
                                name="authorsIds"
                                required
                        >
                            <c:forEach items="${authorList}" var="val">
                                <option value="${val.authorId}">${val.authorFullName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="languagesId">Languages:</label>
                        <select id="languagesId"
                                class="selectpicker form-control"
                                aria-label="Please select language"
                                data-live-search="true"
                                name="languageId"
                                required
                        >
                            <c:forEach items="${languagesList}" var="lang">
                                <option value="${lang.id}">${lang.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="categoriesIds">Categories:</label>
                        <select id="categoriesIds"
                                class="selectpicker form-control"
                                aria-label="Please select authors"
                                data-live-search="true"
                                name="categoriesIds"
                                required
                        >
                            <c:forEach items="${categoriesList}" var="category">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
        </div>
    </div>
</div>
<a href="/" class="btn btn-primary mt-4">Main Menu</a></body>

</body>
</html>
