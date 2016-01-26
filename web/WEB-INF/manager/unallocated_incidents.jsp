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
            <p><a href='<c:url value="/manager"/>'><span class="videl">Нераспределенные обращения
                        <c:if test="${unallocatedIncidents.size() gt 0}">
                            <span class="videlc">${unallocatedIncidents.size()}</span>
                        </c:if>
                    </span></a></p>
            <p><a href='<c:url value="/manager/allocated"/>'>Распределенные обращения
                        <c:if test="${allocatedIncidents.size() gt 0}">
                            <span class="count">${allocatedIncidents.size()}</span>
                        </c:if>
                    </a></p>
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
                        <th><a href='<c:url value="/sort_by_name_un"/>'>Заголовок инцидента</a></th>
                        <th><a href='<c:url value="/sort_by_date_un"/>'>Дата</a></th>
                        <th>Время</th>
                        <th><a href='<c:url value="/sort_by_status_un"/>'>Статус</a></th>
                        <th><a href='<c:url value="/sort_by_zay_un"/>'>Заявитель</a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${unallocatedIncidents.isEmpty()}">
                    <td colspan="6" style="text-align: center;">Нераспределенных инцидентов нет</td>
                </c:if>
                    <c:forEach var="incident" items="${unallocatedIncidents}">
                        <tr>
                            <td><a href='<c:url value="/manager/incident_data?id=${incident.id}&un=da"/>'>${incident.title}</a></td>
                            <td>${incident.dateIncident}</td>
                            <td>${incident.timeIncident}</td>
                            <td>${incident.status.name}</td>
                            <td>${incident.zayavitel.name}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
