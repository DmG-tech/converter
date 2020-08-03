<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Создание аккаунта</title>

  <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
  <link href="${contextPath}/resources/css/bootstrap-grid.css" rel="stylesheet">
  <link href="${contextPath}/resources/css/bootstrap-reboot.css" rel="stylesheet">
</head>

<body>

<div class="container">

  <div class="row">

    <div class="col-6 offset-3">

      <div class="text-center">
        <h1>Регистрация</h1>
      </div>

      <form:form method="POST" modelAttribute="userForm">

        <div class="form-group ${error != null ? 'has-error' : ''}">

          <div class="form-group ${status.error ? 'has-error' : ''}">
            <label>Логин</label>
            <spring:bind path="username">
                <form:input type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </spring:bind>
          </div>

          <div class="form-group ${status.error ? 'has-error' : ''}">
            <label>Пароль</label>
            <spring:bind path="password">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </spring:bind>
          </div>

          <div class="form-group">
            <div class="row">
              <div class="col text-center">
                <button class="btn btn-primary" type="submit"><spring:message code="button.reg"/></button>
              </div>
            </div>
          </div>

        </div>

      </form:form>

    </div>

  </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>