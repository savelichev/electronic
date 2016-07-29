<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="utbl" uri="WEB-INF/userTable" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages" var="messages"/>

<html lang="${language}">
<head>
    <title><fmt:message key="all_users" bundle="${messages}"/></title>
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
    <div align="center">
        <form action="show-user-by-email" method="get">
            <fmt:message key="get_user_by_email" bundle="${messages}"/>
            <label>
                <input type="email" name="userEmail" placeholder="Email"/>
            </label>
            <button type="submit"><fmt:message key="show" bundle="${messages}"/></button>
        </form>
    </div>

    <utbl:buildUserTable users="${users}" language="${language}"/>
    <%--<div>--%>
        <%--<table border="1" align="center">--%>
            <%--<tr>--%>
                <%--<th></th>--%>
                <%--<th></th>--%>
                <%--<th><fmt:message key="email_address" bundle="${messages}"/></th>--%>
                <%--<th><fmt:message key="login" bundle="${messages}"/></th>--%>
                <%--<th><fmt:message key="cell_number" bundle="${messages}"/></th>--%>
                <%--<th><fmt:message key="firstname" bundle="${messages}"/></th>--%>
                <%--<th><fmt:message key="lastname" bundle="${messages}"/></th>--%>
                <%--<th><fmt:message key="block_status" bundle="${messages}"/></th>--%>
                <%--<th><fmt:message key="role" bundle="${messages}"/></th>--%>
            <%--</tr>--%>
            <%--<c:forEach var="user" items="${users}">--%>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--<div>--%>
                            <%--<form action="block-user" method="post">--%>

                                    <%--<input type="hidden" name="userEmail" value="${user.email}"/>--%>

                                <%--<button type="submit"><fmt:message key="block" bundle="${messages}"/> </button>--%>
                            <%--</form>--%>
                        <%--</div>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--<div>--%>
                            <%--<form action="unblock-user" method="post">--%>
                                <%--<label>--%>
                                    <%--<input hidden name="userEmail" value="${user.email}"/>--%>
                                <%--</label>--%>
                                <%--<button type="submit"><fmt:message key="unblock" bundle="${messages}"/> </button>--%>
                            <%--</form>--%>
                        <%--</div>--%>
                    <%--</td>--%>
                    <%--<td>${user.email}</td>--%>
                    <%--<td>${user.login}</td>--%>
                    <%--<td>${user.cellNumber}</td>--%>
                    <%--<td>${user.firstName}</td>--%>
                    <%--<td>${user.lastName}</td>--%>
                    <%--<td>${user.blocked}</td>--%>
                    <%--<td>${user.role}</td>--%>


                <%--</tr>--%>
            <%--</c:forEach>--%>
        <%--</table>--%>
    <%--</div>--%>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
</body>
</html>
