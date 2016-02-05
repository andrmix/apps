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
            <p><a href='<c:url value="/user/new_incident"/>'>Новое обращение</a></p>
            <p><a href='<c:url value="/user"/>'>Активные обращения
                    <c:if test="${openIncidentsNew.size() gt 0}">
                        <span class="count">${openIncidentsNew.size()}</span>
                    </c:if>
                </a></p>
            <p><a href='<c:url value="/user/closed_incidents"/>'><span class="videl">Закрытые обращения
                        <c:if test="${closedIncidentsNew.size() gt 0}">
                            <span class="videlc">${closedIncidentsNew.size()}</span>
                        </c:if>
                    </span></a></p>
        </div>
        <form action='<c:url value="/user/closed_incidents"/>' method="POST">
            <div id="content">
                <table class="incidents_tab">
                    <thead>
                        <tr>
                            <th><a href='<c:url value="/sort_by_name_closed"/>'>Заголовок обращения</a></th>
                            <th><a href='<c:url value="/sort_by_date_closed"/>'>Дата/Время</a></th>
                            <th><a href='<c:url value="/sort_by_status_closed"/>'>Статус</a></th>
                            <th><a href='<c:url value="/sort_by_spec_closed"/>'>Исполнитель</a></th>
                            <th>Дата/Время закрытия</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${closedIncidents.isEmpty()}">
                        <td colspan="5" style="text-align: center;">Закрытых инцидентов нет</td>
                    </c:if>
                    <c:forEach var="incident" items="${closedIncidents}">
                        <c:choose>
                            <c:when test="${incident.new1 == 1 && incident.status.id == 7}">
                                <tr class="vyd">
                                </c:when>
                                <c:otherwise>
                                <tr>
                                </c:otherwise>
                            </c:choose>
                            <td><a href='<c:url value="/user/user_incident?id=${incident.id}"/>'>${incident.title}</a></td>
                            <td>${incident.dateIncident} ${incident.timeIncident}</td>
                            <td>${incident.status.name}</td>
                            <td>${incident.specialist.name}</td>
                            <td>${incident.dateClose} ${incident.timeClose}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>
