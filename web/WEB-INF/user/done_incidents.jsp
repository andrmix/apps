<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Решение</title>
    </head>
    <body>
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/user/new_incident"/>'>Новое обращение</a></p>
            <p><a href='<c:url value="/user"/>'>Активные обращения ${openIncidents.size()}</a></p>
            <p><a href='<c:url value="/user/done_incidents"/>'>Выполненные обращения ${doneIncidents.size()}</a></p>
            <p><a href='<c:url value="/user/closed_incidents"/>'>Закрытые обращения ${closedIncidents.size()}</a></p>
        </div>
            <div id="content">
                <c:forEach var="incident" items="${doneIncidents}">
                        <ul id="incident">
                            <li><a href='<c:url value="/user/user_incident?id=${incident.id}"/>'>${incident.title}</a></li>
                            <li>${incident.text}</li>
                            <li>
                                <span class="date-article">Дата: ${incident.dateIncident}</span>
                                <span class="date-article">Статус: ${incident.status.name}</span>
                            </li>
                            <li></li>
                        </ul>
                </c:forEach>
            </div>
    </body>
</html>
