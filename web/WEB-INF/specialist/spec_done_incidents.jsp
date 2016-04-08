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
            <div class="head_block"><img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                <div class="heada">${user.name}
                    (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    / ${user.depart.name} / ${user.dpost.name}
                </div><div class="headb">/ Выполненные обращения</div>
            </div>
        </div>
        <div id="sidebar">
                <div class="sidebar_el">
                    <a class="a_act_inc" href='<c:url value="/specialist"/>'><div class="u_icon_act_inc"></div>Активные обращения
                            <c:if test="${openIncidentsNew.size() gt 0}">
                                <span class="videlc">${openIncidentsNew.size()}</span>
                            </c:if>
                    </a>
                </div>
                <div class="sidebar_el">
                    <a class="a_clo_inc" href='<c:url value="/specialist/spec_done_incidents"/>'>
                        <div class="u_icon_clo_inc_v"></div>
                        <span class="videl">Выполненные обращения</span>
                    </a>
                </div>
                <div class="sidebar_el">
                    <a class="a_clos_inc" href='<c:url value="/specialist/spec_closed_incidents"/>'>
                        <div class="u_icon_clos_inc"></div>
                        Закрытые обращения
                    </a>
                </div>
                <div class="sidebar_el">
                    <a class="a_stat" href='<c:url value="/specialist/statistic"/>'>
                        <div class="u_icon_stat"></div>
                        Статистика
                    </a>
                </div>  
            </div>
        <div id="content">
            <table class="incidents_tab">
                <thead>
                    <tr>
                        <th><a href='<c:url value="/specialist/sort_by_name_done"/>'>Заголовок обращения</a></th>
                        <th><a href='<c:url value="/specialist/sort_by_dateo_done"/>'>Дата/Время создания</a></th>
                        <th><a href='<c:url value="/specialist/sort_by_zay_done"/>'>Заявитель</a></th>
                        <th><a href='<c:url value="/specialist/sort_by_dated_done"/>'>Дата/Время выполнения</a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${doneIncidents.isEmpty()}">
                    <td colspan="4" style="text-align: center;">Выполненных обращений нет</td>
                </c:if>
                <c:forEach var="incident" items="${doneIncidents}">
                    <tr>
                        <td><a href='<c:url value="/specialist/spec_incident_data?id=${incident.id}"/>'>${incident.title}</a></td>
                        <td>${incident.dateIncident} ${incident.timeIncident}</td>
                        <td>${incident.zayavitel.name}</td>
                        <td>${incident.dateDone} ${incident.timeDone}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
