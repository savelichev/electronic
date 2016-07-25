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
    <title>Add notebook</title>
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
        <form class="input-group" method="post" action="add-notebook">


            <input type="text" class="hidden" name="category" value="notebook">

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
                <label for="processor"><fmt:message key="processor" bundle="${messages}"/></label>
                <input type="text" class="form-control" id="processor" name="processor" placeholder="Processor">
            </div>

            <div class="form-group">
                <label for="ram"><fmt:message key="ram" bundle="${messages}"/></label>
                <input type="text" class="form-control" id="ram" name="ram" placeholder="RAM capacity">
            </div>

            <div class="form-group">
                <label for="hdd"><fmt:message key="hdd" bundle="${messages}"/></label>
                <input type="text" class="form-control" id="hdd" name="hdd" placeholder="HDD capacity">
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

</body>
</html>
