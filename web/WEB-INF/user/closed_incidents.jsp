<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/header.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/sidebar.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/table.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Решение</title>
    </head>
    <body>
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Закрытые обращения</div></div>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/user/new_incident"/>'>Новое обращение</a></p>
            <p><a href='<c:url value="/user"/>'>Активные обращения
                    <c:if test="${openIncidents.size() gt 0}">
                        <span class="count">${openIncidents.size()}</span>
                    </c:if>
                </a></p>
            <p><a href='<c:url value="/user/closed_incidents"/>'><span class="videl">Закрытые обращения
                        <c:if test="${closedIncidents.size() gt 0}">
                            <span class="videlc">${closedIncidents.size()}</span>
                        </c:if>
                    </span></a></p>
        </div>
        <div id="content">
            <table class="incidents_tab">
                <thead>
                    <tr>
                        <th>Заголовок инцидента</th>
                        <th>Дата</th>
                        <th>Статус</th>
                        <th>Исполнитель</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="incident" items="${closedIncidents}">
                        <tr>
                            <td><a href='<c:url value="/user/user_incident?id=${incident.id}"/>'>${incident.title}</a></td>
                            <td>${incident.dateIncident}</td>
                            <td>${incident.status.name}</td>
                            <td>${incident.specialist.name}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
