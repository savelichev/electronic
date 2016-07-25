<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages" var="messages"/>

<html lang="${language}">
<head>
    <title><fmt:message key="user_page" bundle="${messages}"/></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../../resources/css/bootstrap.css" rel="stylesheet">
    <link href="../../resources/css/style.css" rel="stylesheet">
    <link href="../../resources/css/font-awesome.css" rel="stylesheet">
</head>
<body>
<!--top navigation bar-->
<jsp:include page="../navigationbar.jsp"/>

<div class="container">
    <a href="cart"><fmt:message key="cart" bundle="${messages}"/></a>
    <br/>
    <a href="orders"><fmt:message key="orders" bundle="${messages}"/></a>
</div>


</body>
</html>
