<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages" var="messages"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="sign_in" bundle="${messages}"/></title>

    <link href="../../resources/css/bootstrap.css" rel="stylesheet">
    <link href="../../resources/css/style.css" rel="stylesheet">
    <link href="../../resources/css/font-awesome.css" rel="stylesheet">
</head>
<body>

<jsp:include page="../navigationbar.jsp"/>

<div class="container">

    <div class="col-md-4 container" id="authForm">
        <form class="input-group" method="post" action="sign-in">
            <div class="form-group">
                <label for="inputEmail"><fmt:message key="email_address" bundle="${messages}"/></label>
                <input type="email" class="form-control" id="inputEmail" name="email" placeholder="Email">
            </div>
            <div class="form-group">
                <label for="inputPassword"><fmt:message key="password" bundle="${messages}"/></label>
                <input type="password" class="form-control" id="inputPassword" name="password"
                       placeholder="Password">
            </div>
            <br/>
            <div>
                <button type="submit" class="btn btn-primary"><fmt:message key="sign_in" bundle="${messages}"/></button>
                <a class="btn-link" href="sign-up"><fmt:message key="sign_up" bundle="${messages}"/></a>
            </div>
            <c:set var="badData" value="${sessionScope.badData}"/>
            <c:if test="${badData==true}">
                  <p>Wrong email or password!</p>
            </c:if>
        </form>
    </div>


</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
</body>
</html>