<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

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
    <title>Electronic</title>

    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/font-awesome.css" rel="stylesheet">

</head>
<body>

<!--top navigation bar-->
<jsp:include page="view/navigationbar.jsp"/>

<%--main menu--%>
<div class="container">
    <div class="col-md-12">
        <div class="col-md-2">
            <a href="audio">
                <img alt="tv" src="resources/images/category_audio-video-auto.png">
                <p class="text-center">
                    <fmt:message key="audio" bundle="${messages}"/>
                </p>
            </a>
        </div>
        <div class="col-md-2">
            <a href="cameras">
                <img src="resources/images/category_photo-video.png">
                <p class="text-center">
                    <fmt:message key="cameras" bundle="${messages}"/>
                </p>
            </a>
        </div>
        <div class="col-md-2">
            <a href="tv">
                <img src="resources/images/category_tv.png">
                <p class="text-center">
                    <fmt:message key="tv" bundle="${messages}"/>
                </p>
            </a>
        </div>
        <div class="col-md-2">
            <a href="notebooks">
                <img src="resources/images/catgory_computer.png">
                <p class="text-center">
                    <fmt:message key="notebooks" bundle="${messages}"/>
                </p>
            </a>
        </div>
        <div class="col-md-2">
            <a href="phones">
                <img src="resources/images/category_phone.png">
                <p class="text-center">
                    <fmt:message key="phones" bundle="${messages}"/>
                </p>
            </a>
        </div>
        <div class="col-md-2">
            <a href="accessories">
                <img src="resources/images/category_accessories.png">
                <p class="text-center">
                    <fmt:message key="accessories" bundle="${messages}"/>
                </p>
            </a>
        </div>
    </div>
</div>

<br>
<%--slider--%>
<div class="container">
    <div class="col-md-12">
        <!--slider-->
        <div id="carousel" class="carousel slide">
            <!--slide indicators-->
            <ol class="carousel-indicators">
                <li class="active" data-target="#carousel" data-slide="0"></li>
                <li data-target="#carousel" data-slide="1"></li>
                <li data-target="#carousel" data-slide="2"></li>
            </ol>
            <!--slides-->
            <div class="carousel-inner">
                <div class="item active">
                    <img class="center-block" src="resources/images/slider_tv.png" alt="">
                </div>
                <div class="item">
                    <img class="center-block" src="resources/images/sleder_cell.png" alt="">
                </div>
                <div class="item">
                    <img class="center-block" src="resources/images/slider_notebook.png" alt="">
                </div>
            </div>
            <!--Arrows for switch slide-->
            <a href="#carousel" class="left carousel-control" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a href="#carousel" class="right carousel-control" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.js"></script>
</body>
</html>