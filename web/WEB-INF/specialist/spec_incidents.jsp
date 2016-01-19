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
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Активные обращения</div></div>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/specialist"/>'>Активные обращения 
                    <c:if test="${openIncidents.size() gt 0}">
                        <span class="count">${openIncidents.size()}</span>
                    </c:if>
                </a></p>
            <p><a href='<c:url value="/specialist/spec_done_incidents"/>'>Выполненные обращения 
                    <c:if test="${doneIncidents.size() gt 0}">
                        <span class="count">${doneIncidents.size()}</span>
                    </c:if>
                </a></p>
            <p><a href='<c:url value="/specialist/closed_incidents"/>'>Закрытые обращения
                    <c:if test="${closedIncidents.size() gt 0}">
                        <span class="count">${closedIncidents.size()}</span>
                    </c:if>
                </a></p>
        </div>
        <div id="content">
            <c:forEach var="incident" items="${openIncidents}">
                <ul id="incident">
                    <li><a href='<c:url value="/specialist/spec_incident_data?id=${incident.id}"/>'>${incident.title}</a></li>
                    <li>${incident.text}</li>
                    <li>
                        <span class="date-article">Дата: ${incident.dateIncident} |</span>
                        <span class="date-article">Статус: ${incident.status.name} |</span>
                        <span class="date-article">Заявитель: ${incident.zayavitel.name}</span>
                    </li>
                    <li></li>
                </ul>
            </c:forEach>
        </div>
    </body>
</html>
