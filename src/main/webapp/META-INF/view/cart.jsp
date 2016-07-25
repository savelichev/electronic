<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title><fmt:message key="cart" bundle="${messages}"/></title>

    <link href="../resources/css/bootstrap.css" rel="stylesheet">
    <link href="../resources/css/style.css" rel="stylesheet">
    <link href="../resources/css/font-awesome.css" rel="stylesheet">

</head>
<body>

<!--top navigation bar-->
<jsp:include page="navigationbar.jsp"/>

<div class="container">
    <c:forEach var="cartItem" items="${cart.cartItems}">
        <div class="container">

            <div class="col-md-3">
                <img class="img-responsive"
                     src="/resources/notebook_images/${cartItem.product.producer}_${cartItem.product.model}_1.jpg">
            </div>

            <div class="col-md-5">
                <div>
                    <h5><c:out value="${cartItem.product.producer}"/> <c:out value="${cartItem.product.model}"/></h5>
                </div>
                <div>
                    <p><c:out value="${cartItem.product.description}"/></p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="col-md-3">
                    <p class="cart_item_price">
                        <c:out value="${cartItem.product.price}"/><fmt:message key="currency"
                                                                               bundle="${messages}"/>
                    </p>
                </div>

                <div class="col-md-3">
                    <form method="post" action="cart-add-product">
                        <input type="hidden" name="productArticle" value="${cartItem.product.article}">
                        <button type="submit">+</button>
                    </form>
                    <c:out value="${cartItem.amount}"/>
                    <form method="post" action="cart-decrease-product">
                        <input type="hidden" name="productArticle" value="${cartItem.product.article}">
                        <button type="submit">-</button>
                    </form>
                </div>

                <div class="col-md-3">
                    <p class="cart_item_price">
                        <c:out value="${cartItem.cartItemCost}"/><fmt:message key="currency" bundle="${messages}"/>
                    </p>
                </div>

                <div class="col-md-3">
                    <form method="post" action="cart-remove-product">
                        <input type="hidden" name="productArticle" value="${cartItem.product.article}"/>
                        <button type="submit" class="btn-link">
                            <fmt:message key="remove" bundle="${messages}"/>
                        </button>
                    </form>
                </div>
            </div>

        </div>
        <br/>
    </c:forEach>
</div>

<div class="container">

    <div align="right">
        <div>
            <h3>
                <fmt:message key="total_cost" bundle="${messages}"/>:
                ${cart.cartCost}
                <fmt:message key="currency" bundle="${messages}"/>
            </h3>

        </div>
        <div class="container">
            <a class="btn btn-primary" href="build-order-template"><fmt:message key="make_an_order"
                                                                              bundle="${messages}"/> </a>
        </div>
    </div>


</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../resources/js/bootstrap.js"></script>
</body>
</html>