<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book FORM</title>
<%--        <jsp:include page="Css.jsp"></jsp:include>--%>

</head>
<body>

<div class="container text-center">

    <c:if test="${book==null}">
        <h1>======= ADD NEW BOOK ========</h1>
    </c:if>
    <c:if test="${book!=null}">
        <h1>======= EDIT BOOK ========</h1>
    </c:if>


    <div class="row">
        <div class="col-md-6 offset-3">
            <c:if test="${book==null}">
            <form action="/books" method="post">
                </c:if>
                <c:if test="${book!=null}">
                <form action="/books/edit" method="post">
                    <input type="hidden" name="id" value="${book.id}">
                    </c:if>
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
                        <input name="isbn" type="number" class="form-control"
                        <c:if test="${book!=null}"> disabled value="${book.isbn}" </c:if> id="bookIsbn"
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="bookCount" class="form-label">AMOUNT</label>
                        <input name="count" type="number" min="1" max="1000" class="form-control" value="${book.count}"
                               required id="bookCount">
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="authorsIds">Authors:</label>
                        <select id="authorsIds"
                                class="selectpicker form-control"
                                aria-label="Please select authors"
                                data-live-search="true"
                                name="authorsIds"
                                required
                        >
                            <option disabled selected value="none">Choose Authors</option>
                            <c:forEach items="${authorList}" var="val">
                                <c:choose>
                                    <c:when test="${book.authorDtoList.contains(val)}">
                                        <option selected value="${val.authorId}">${val.authorFullName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${val.authorId}">${val.authorFullName}</option>
                                    </c:otherwise>
                                </c:choose>
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
                            <option disabled selected value="none">Choose Language</option>
                            <c:forEach items="${languagesList}" var="lang">
                                <c:choose>
                                    <c:when test="${book.languageId==lang.id}">
                                        <option selected value="${lang.id}">${lang.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${lang.id}">${lang.name}</option>
                                    </c:otherwise>
                                </c:choose>
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
                            <option disabled selected value="none">Choose Categories</option>
                            <c:forEach items="${categoriesList}" var="category">
                                <c:choose>
                                    <c:when test="${book.categoryDtoList.contains(category)}">
                                        <option selected value="${category.id}">${category.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${category.id}">${category.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>


                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
        </div>
    </div>
</div>
<a href="/" class="btn btn-primary mt-4">Bosh saxifa</a></body>

</body>
</html>
