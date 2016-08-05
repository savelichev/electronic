<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages" var="messages"/>

<html>
<head>
    <title><fmt:message key="add_phone" bundle="${messages}"/></title>
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
    <div class="col-md-4 container" id="addnotebook">
        <form class="input-group" method="post" action="add-phone">


            <input type="text" class="hidden" name="category" value="phone">

            <div class="form-group">
                <label for="producer"><fmt:message key="producer" bundle="${messages}"/></label>
                <input type="text" class="form-control" id="producer" name="producer" placeholder="Producer">
            </div>

            <div class="form-group">
                <label for="model"><fmt:message key="model" bundle="${messages}"/></label>
                <input type="text" class="form-control" id="model" name="model" placeholder="Model">
            </div>

            <div class="form-group">
                <label for="price"><fmt:message key="price" bundle="${messages}"/></label>
                <input type="text" class="form-control" id="price" name="price" placeholder="Price">
            </div>

            <div class="form-group">
                <label for="description"><fmt:message key="description" bundle="${messages}"/></label>
                <input type="text" class="form-control" id="description" name="description" placeholder="Description">
            </div>

            <div class="form-group">
                <label for="displayDiagonal"><fmt:message key="display_diagonal" bundle="${messages}"/></label>
                <input type="text" class="form-control" id="displayDiagonal" name="displayDiagonal"
                       placeholder="Display diagonal">
            </div>

            <div class="form-group">
                <label for="os"><fmt:message key="operation_system" bundle="${messages}"/></label>
                <input type="text" class="form-control" id="os" name="os" placeholder="Operation System">
            </div>

            <div class="form-group">
                <label for="mainCamera"><fmt:message key="main_camera" bundle="${messages}"/></label>
                <input type="text" class="form-control" id="mainCamera" name="mainCamera" placeholder="Main camera">
            </div>

            <div class="form-group">
                <label for="batteryCapacity"><fmt:message key="battery_capacity" bundle="${messages}"/></label>
                <input type="text" class="form-control" id="batteryCapacity" name="batteryCapacity"
                       placeholder="Battery capacity">
            </div>

            <div class="form-group">
                <label for="imageRef"><fmt:message key="image_ref" bundle="${messages}"/></label>
                <input type="text" class="form-control" id="imageRef" name="imageRef" placeholder="Image reference">
            </div>


            <br/>
            <div>
                <button type="submit" class="btn btn-primary"><fmt:message key="add" bundle="${messages}"/></button>
            </div>
        </form>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
</body>
</html>
