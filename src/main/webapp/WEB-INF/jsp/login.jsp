<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Вход</title>

    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap-grid.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap-reboot.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <div class="row">

        <div class="col-6 offset-3">

            <div class="text-center">
                <h1>Авторизация</h1>
            </div>


            <form method="POST" action="${contextPath}/login">

                <div class="form-group ${error != null ? 'has-error' : ''}">

                    <div class="form-group">
                        <label>Логин</label>
                        <span>${message}</span>
                        <input name="username" type="text" class="form-control" placeholder="Username"
                               autofocus="true"/>
                    </div>

                    <div class="form-group">
                        <label>Пароль</label>
                        <input name="password" type="password" class="form-control" placeholder="Password"/>
                        <span>${error}</span>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col text-center">
                                <button class="btn btn-primary" type="submit"><spring:message code="button.logIn"/></button>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <h4 class="text-center"><a href="${contextPath}/registration"><spring:message code="button.reg"/></a></h4>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                </div>

            </form>

        </div>

    </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
