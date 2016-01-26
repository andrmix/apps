<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/header.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/sidebar.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/tables.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Решение</title>
    </head>
    <body>
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Закрытые обращения</div></div>
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
            <p><a href='<c:url value="/specialist/spec_closed_incidents"/>'><span class="videl">Закрытые обращения
                        <c:if test="${closedIncidents.size() gt 0}">
                            <span class="videlc">${closedIncidents.size()}</span>
                        </c:if>
                    </span></a></p>
            <p><a href='<c:url value="/specialist/statistic"/>'>Статистика</a></p>
        </div>
        <div id="content">
            <table class="incidents_tab">
                <thead>
                    <tr>
                        <th><a href='<c:url value="/sort_by_name"/>'>Заголовок инцидента</a></th>
                        <th><a href='<c:url value="/sort_by_date"/>'>Дата</a></th>
                        <th>Время</th>
                        <th><a href='<c:url value="/sort_by_spec"/>'>Заявитель</a></th>
                        <th>Дата закрытия</th>
                        <th>Время закрытия</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${closedIncidents.isEmpty()}">
                    <td colspan="6" style="text-align: center;">Закрытых инцидентов нет</td>
                </c:if>
                <c:forEach var="incident" items="${closedIncidents}">
                    <tr>
                        <td><a href='<c:url value="/specialist/spec_incident_data?id=${incident.id}"/>'>${incident.title}</a></td>
                        <td>${incident.dateIncident}</td>
                        <td>${incident.timeIncident}</td>
                        <td>${incident.zayavitel.name}</td>
                        <td>${incident.dateClose}</td>
                        <td>${incident.timeClose}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
