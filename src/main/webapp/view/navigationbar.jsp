<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages" var="messages"/>
<c:set var="user" value="${sessionScope.user}"/>


<div class="navbar navbar-inverse navbar-static-top">
    <div class="container">

        <div class="navbar-header">

        </div>

        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-menu-button">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <div class="collapse navbar-collapse" id="navigation-menu-button">

            <ul class="nav navbar-nav">
                <li><a href="index"><fmt:message key="to_main" bundle="${messages}"/></a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <fmt:message key="catalog" bundle="${messages}"/><b class="caret"></b>
                </a>
                    <ul class="dropdown-menu">
                        <li><a href="audio"><fmt:message key="audio" bundle="${messages}"/></a></li>
                        <li><a href="cameras"><fmt:message key="cameras" bundle="${messages}"/></a></li>
                        <li><a href="tv"><fmt:message key="tv" bundle="${messages}"/></a></li>
                        <li><a href="notebooks"><fmt:message key="notebooks" bundle="${messages}"/></a></li>
                        <li><a href="phones"><fmt:message key="phones" bundle="${messages}"/></a></li>
                        <li class="divider"></li>
                        <li><a href="accessories"> <fmt:message key="accessories" bundle="${messages}"/> </a></li>
                    </ul>
                </li>
                <li><a href="delivery"><fmt:message key="delivery" bundle="${messages}"/></a></li>
                <li><a href="contacts"><fmt:message key="contacts" bundle="${messages}"/></a></li>
            </ul>

            <div class="navbar-btn navbar-right">
                <div class="btn-group">
                    <a href="?language=ru_RU" class="btn btn-primary">RU</a>
                    <a href="?language=en_US" class="btn btn-primary">EN</a>
                </div>
            </div>


            <div class="navbar-btn navbar-right">
                <div id="signin" style="display: none">
                    <a href="signIn" class="btn btn-primary">
                        <i class="fa fa-sign-in"></i> <fmt:message key="sign_in" bundle="${messages}"/>
                    </a>
                </div>

                <div id="signout" style="display: none">
                    <a href="signOut" class="btn btn-primary">
                        <i class="fa fa-sign-in"></i> <fmt:message key="sign_out" bundle="${messages}"/>
                    </a>
                </div>

                <script type="text/javascript">
                    if (${user.login==null}) {
                        document.getElementById('signin').style.display = 'block';
                        document.getElementById('signout').style.display = 'none';
                    } else {
                        document.getElementById('signin').style.display = 'none';
                        document.getElementById('signout').style.display = 'block';
                    }
                </script>
            </div>


            <div class="navbar-text navbar-right">
                <a class="navbar-link" href="user-page"> ${user.login}</a>
            </div>

            <div class="navbar-text navbar-right">
                <a class="navbar-link" href="cart">
                    <fmt:message key="cart" bundle="${messages}"/>
                </a>
            </div>


        </div>
    </div>
</div>

