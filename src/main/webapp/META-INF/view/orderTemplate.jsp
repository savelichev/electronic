<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title><fmt:message key="approve_an_order" bundle="${messages}"/></title>

    <link href="../resources/css/bootstrap.css" rel="stylesheet">
    <link href="../resources/css/style.css" rel="stylesheet">
    <link href="../resources/css/font-awesome.css" rel="stylesheet">

</head>
<body>

<!--top navigation bar-->
<jsp:include page="navigationbar.jsp"/>

<div class="container">

    <c:forEach var="orderItem" items="${orderTemplate.orderItems}">

        <div class="container">

            <div class="col-md-6 col-lg-3">
                <div>
                    <c:out value="${orderItem.title}"/><c:out value="${orderItem.productArticle}"/>
                </div>

            </div>

            <div class="col-md-3">
                <div class="col-md-4 col-lg-4">
                    <c:out value="${orderItem.price}"/><fmt:message key="currency" bundle="${messages}"/>
                </div>

                <div class="col-md-4 col-lg-4">
                    <c:out value="${orderItem.amount}"/>
                </div>


                <div class="col-md-4 col-lg-4">
                    <c:out value="${orderItem.itemCost}"/>
                </div>
            </div>


        </div>
        <br/>

    </c:forEach>

    <div>
        <h3>
            <fmt:message key="total_cost" bundle="${messages}"/>:
            ${orderTemplate.orderCost}
            <fmt:message key="currency" bundle="${messages}"/>
        </h3>
    </div>

</div>


<div class="container">
    <form action="approveOrder" method="post">
        <div class="form-group">
            <label for="buyerName">
                <fmt:message key="buyer_first_name" bundle="${messages}"/>
                <fmt:message key="buyer_last_name" bundle="${messages}"/>
            </label>
            <input type="text" class="form-control" id="buyerName" name="buyerName"
                   value="${user.firstName} ${user.lastName}"/>
        </div>

        <div class="form-group">
            <label for="buyerCellNumber"><fmt:message key="cell_number" bundle="${messages}"/></label>
            <input type="text" class="form-control" id="buyerCellNumber" name="buyerCellNumber"
                   value="${user.cellNumber}"/>
        </div>

        <div class="form-group">
            <label for="address"><fmt:message key="delivery_address" bundle="${messages}"/></label>
            <input type="text" class="form-control" id="address" name="address" value="${orderTemplate.address}">
        </div>
        <div class="form-group">
            <label for="comment"><fmt:message key="comment_to_order" bundle="${messages}"/></label>
            <input type="text" class="form-control" id="comment" name="comment" placeholder="Comment">
        </div>
        <div>
            <button class="btn btn-primary" type="submit">
                <fmt:message key="approve_an_order" bundle="${messages}"/>
            </button>
        </div>
    </form>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../resources/js/bootstrap.js"></script>
</body>
</html>