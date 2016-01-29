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
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Нераспределенные обращения</div></div>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/manager"/>'>Нераспределенные обращения
                    <c:if test="${unallocatedIncidentsNew.size() gt 0}">
                        <span class="count">${unallocatedIncidentsNew.size()}</span>
                    </c:if>
                </a></p>
            <p><a href='<c:url value="/manager/allocated"/>'><span class="videl">Распределенные обращения
                    </span></a></p>
            <p><a href='<c:url value="/manager/specialists"/>'>Специалисты</a></p>
            <p><a href='<c:url value="/manager/done_incidents"/>'>На согласование
                    <c:if test="${doneIncidents.size() gt 0}">
                        <span class="count">${doneIncidents.size()}</span>
                    </c:if>
                </a></p>
        </div>
        <div id="content">
            <table class="incidents_tab">
                <thead>
                    <tr>
                        <th><a href='<c:url value="/sort_by_name_allo"/>'>Заголовок инцидента</a></th>
                        <th><a href='<c:url value="/sort_by_date_allo"/>'>Дата</a></th>
                        <th>Время</th>
                        <th><a href='<c:url value="/sort_by_status_allo"/>'>Статус</a></th>
                        <th><a href='<c:url value="/sort_by_zay_allo"/>'>Заявитель</a></th>
                        <th><a href='<c:url value="/sort_by_spec_allo"/>'>Специалист</a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${allocatedIncidents.isEmpty()}">
                    <td colspan="6" style="text-align: center;">Распределенных инцидентов нет</td>
                </c:if>
                <c:forEach var="incident" items="${allocatedIncidents}">
                    <tr>
                        <td><a href='<c:url value="/manager/incident_data?id=${incident.id}&un=no"/>'>${incident.title}</a></td>
                        <td>${incident.dateIncident}</td>
                        <td>${incident.timeIncident}</td>
                        <td>${incident.status.name}</td>
                        <td>${incident.zayavitel.name}</td>
                        <td>${incident.specialist.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
