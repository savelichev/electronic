<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="cart" uri="WEB-INF/cartAdd" %>--%>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages" var="messages"/>


<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Electronic</title>

    <link href="../../resources/css/bootstrap.css" rel="stylesheet">
    <link href="../../resources/css/style.css" rel="stylesheet">
    <link href="../../resources/css/font-awesome.css" rel="stylesheet">

</head>
<body>

<!--top navigation bar-->
<jsp:include page="../navigationbar.jsp"/>
<c:set var="role" value="${sessionScope.user.role}"/>
<c:if test="${role=='ADMIN'}">
    <div class="container">
        <a href="add-notebook"><fmt:message key="add_notebook" bundle="${messages}"/></a>
    </div>
</c:if>

<div class="container">

    <div class="col-md-2">Подбор по параметрам</div>
    <div class="col-md-10">
        <div>

            <c:forEach items="${notebooks}" var="notebook">

                <div class="prod_border">
                    <div class="col-md-3">
                        <img class="img-responsive"
                             src="/resources/notebook_images/${notebook.producer}_${notebook.model}_1.jpg">
                    </div>
                    <div class="col-md-8">
                        <div>

                            <h5><c:out value="${notebook.producer}"/> <c:out value="${notebook.model}"/></h5>

                        </div>
                        <div>
                            <p><c:out value="${notebook.price}"/><fmt:message key="currency" bundle="${messages}"/></p>
                            <p><c:out value="${notebook.description}"/></p>
                        </div>
                    </div>

                    <div class="col-md-1">
                        <form method="post" action="cartAddProduct?productArticle=${notebook.article}">
                            <button type="submit" class="btn btn-primary buy_button">
                                <fmt:message key="buy" bundle="${messages}"/>
                            </button>
                        </form>
                    </div>

                    <c:if test="${role=='ADMIN'}">
                        <div class="col-md-1">
                            <form method="post" action="deleteNotebook?notebookArticle=${notebook.article}">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="delete" bundle="${messages}"/>
                                </button>
                            </form>
                        </div>
                    </c:if>

                </div>

                <br/>

            </c:forEach>

        </div>
    </div>

</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../resources/js/bootstrap.js"></script>
</body>
</html>