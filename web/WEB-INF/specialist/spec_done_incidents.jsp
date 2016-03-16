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
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'>
                <div class="heada">${user.name}
                    (<a href='<c:url value="/logout"/>'>Выйти</a>)
                </div><div class="headb">/ Выполненные обращения</div>
            </div>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/specialist"/>'>Активные обращения 
                    <c:if test="${openIncidentsNew.size() gt 0}">
                        <span class="count">${openIncidentsNew.size()}</span>
                    </c:if>
                </a></p>
            <p><a href='<c:url value="/specialist/spec_done_incidents"/>'><span class="videl">Выполненные обращения 
                    </span></a></p>
            <p><a href='<c:url value="/specialist/spec_closed_incidents"/>'>Закрытые обращения</a></p>
            <p><a href='<c:url value="/specialist/statistic"/>'>Статистика</a></p>
        </div>
        <div id="content">
            <table class="incidents_tab">
                <thead>
                    <tr>
                        <th><a href='<c:url value="/specialist/sort_by_name_done"/>'>Заголовок обращения</a></th>
                        <th><a href='<c:url value="/specialist/sort_by_dateo_done"/>'>Дата/Время создания</a></th>
                        <th><a href='<c:url value="/specialist/sort_by_zay_done"/>'>Заявитель</a></th>
                        <th><a href='<c:url value="/specialist/sort_by_status_done"/>'>Статус</a></th>
                        <th><a href='<c:url value="/specialist/sort_by_dated_done"/>'>Дата/Время выполнения</a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${doneIncidents.isEmpty()}">
                    <td colspan="5" style="text-align: center;">Выполненных обращений нет</td>
                </c:if>
                <c:forEach var="incident" items="${doneIncidents}">
                    <tr>
                        <td><a href='<c:url value="/specialist/spec_incident_data?id=${incident.id}"/>'>${incident.title}</a></td>
                        <td>${incident.dateIncident} ${incident.timeIncident}</td>
                        <td>${incident.zayavitel.name}</td>
                        <td>${incident.status.name}</td>
                        <td>${incident.dateStatus} ${incident.timeStatus}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
