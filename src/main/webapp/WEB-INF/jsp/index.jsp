<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Конвертер валют</title>

    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap-grid.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap-reboot.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <sec:authorize access="!isAuthenticated()">
        <div class="text-center m-2">
            <form action="${contextPath}/login">
                <button class="btn btn-primary"><spring:message code="button.logIn"/></button>
            </form>
        </div>
        <div class="text-center m-2">
            <form action="${contextPath}/registration">
                <button class="btn btn-primary"><spring:message code="button.reg"/></button>
            </form>
        </div>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">

        <div class="container">

            <div class="row">

                <div class="col-6 offset-3">

                    <div class="text-center m-3">
                        <h1>Конвертер валют</h1>
                    </div>

                    <div class="text-sm-center m-3">
                        <p>Текущий пользователь (${pageContext.request.userPrincipal.name})</p>
                    </div>

                    <form method="post">

                        <div class="form-row">

                            <div class="flex-row col-md-6">

                                <select class="custom-select" name="from">
                                    <c:forEach items="${currencies}" var="currency">
                                        <option value="${currency.charCode}">${currency.charCode} (${currency.name})</option>
                                    </c:forEach>
                                </select>

                                <div class="form-group text-center m-2">
                                    <input class="inputValue" name="inputValue" type="text" min="1" required
                                           placeholder="${conversion.inputValue}"/>
                                    <p class="font-weight-lighter">${conversion.fromCurrencyCharCode}</p>
                                </div>

                            </div>

                            <div class="flex-row col-md-6">

                                <select class="custom-select" name="to">
                                    <c:forEach items="${currencies}" var="currency">
                                        <option value="${currency.charCode}">${currency.charCode} (${currency.name})</option>
                                    </c:forEach>
                                </select>

                                <div class="form-group text-center m-2">
                                    <input name="resultValue" type="text" disabled="disabled"
                                           placeholder="${conversion.resultValue}"/>
                                    <p class="font-weight-lighter">${conversion.toCurrencyCharCode}</p>
                                </div>

                            </div>

                        </div>

                        <div class="form-group">

                            <div class="text-center">
                                <button class="btn btn-primary" type="submit"><spring:message
                                        code="button.convert"/></button>
                            </div>

                        </div>

                    </form>

                    <div class="col-6 offset-3">

                        <div class="row">

                            <div class="text-center mr-1">
                                </form>
                                <form action="${contextPath}/logout">
                                    <button class="btn btn-primary"><spring:message code="button.logout"/></button>
                                </form>
                            </div>

                            <div class="text-center ml-1">
                                </form>
                                <form action="${contextPath}/history">
                                    <button class="btn btn-primary"><spring:message code="button.history"/></button>
                                </form>
                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

        <%--Ограничение вводимых знаков--%>
        <script>
            document.querySelector('.inputValue').addEventListener('keyup', function(){
                this.value = this.value.replace(/[^\d^,^.]/g, '');
            });
        </script>

    </sec:authorize>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>