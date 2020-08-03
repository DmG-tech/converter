<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Конвертер</title>

    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap-grid.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap-reboot.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <div class="m-5 text-center">
        <h2>История пользователя ${history.get(0).user.username}</h2>
    </div>

    <c:choose>
        <c:when test="${isEmpty == 0}">
            <form method="post">
                <div class="form-group">
                    <div>
                        <label>Дата</label>
                    </div>
                    <div>
                        <input type="date" name="date">
                    </div>
                </div>

                <div class="form-group">
                    <button class="btn btn-primary" type="submit">Показать</button>
                </div>
            </form>
        </c:when>
    </c:choose>

    <div>
        <form action="${contextPath}/index">
            <button class="btn btn-primary">Вернуться на главную</button>
        </form>
    </div>


    <div class="row">
        <div class="m-5">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Исходная валюта</th>
                    <th scope="col">Целевая валюта</th>
                    <th scope="col">Исходная сумма</th>
                    <th scope="col">Результат</th>
                    <th scope="col">Дата</th>
                </tr>
                </thead>
                <c:choose>

                    <c:when test="${isEmpty == 0}">
                        <c:forEach items="${history}" var="conversion">
                            <tr>
                                <td>${conversion.fromCurrency.name} (${conversion.fromCurrency.charCode})</td>
                                <td>${conversion.toCurrency.name} (${conversion.toCurrency.charCode})</td>
                                <td>${conversion.inputValue}</td>
                                <td>${conversion.resultValue}</td>
                                <td>${conversion.date}</td>
                            </tr>
                        </c:forEach>
                    </c:when>

                    <c:when test="${isEmpty == 1}">
                        <tr>
                            <td colspan="5">Нет истории операций</td>
                        </tr>
                    </c:when>

                </c:choose>
            </table>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>