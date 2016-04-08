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
        <form action='<c:url value="/specialist/spec_incidents"/>' method="POST">
            <c:if test="${blo == 1}">
                <script>
                    alert('Ваша учетная запись заблокирована!');
                    document.forms[0].action = '<c:url value="/logout"/>';
                    document.forms[0].method = "post"; // "get"
                    document.forms[0].submit();
                </script>
            </c:if>
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                    <div class="heada">${user.name} 
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                        / ${user.depart.name} / ${user.dpost.name}
                    </div><div class="headb">/ Активные обращения</div>
                </div>
            </div>
            <div id="sidebar">
                <div class="sidebar_el">
                    <a class="a_act_inc" href='<c:url value="/specialist"/>'><div class="u_icon_act_inc_v"></div><span class="videl">Активные обращения
                            <c:if test="${openIncidentsNew.size() gt 0}">
                                <span class="videlc">${openIncidentsNew.size()}</span>
                            </c:if>
                        </span>
                    </a>
                </div>
                <div class="sidebar_el">
                    <a class="a_clo_inc" href='<c:url value="/specialist/spec_done_incidents"/>'>
                        <div class="u_icon_clo_inc"></div>
                        Выполненные обращения
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
                            <th><a href='<c:url value="/specialist/sort_by_name_open"/>'>Заголовок обращения</a></th>
                            <th><a href='<c:url value="/specialist/sort_by_date_open"/>'>Дата/Время создания</a></th>
                            <th><a href='<c:url value="/specialist/sort_by_status_open"/>'>Статус</a></th>
                            <th><a href='<c:url value="/specialist/sort_by_zay_open"/>'>Заявитель</a></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${openIncidents.isEmpty()}">
                        <td colspan="4" style="text-align: center;">Активных обращений нет</td>
                    </c:if>
                    <c:forEach var="incident" items="${openIncidents}">
                        <c:choose>
                            <c:when test="${incident.new1 == 1}">
                                <tr class="vyd">
                                </c:when>
                                <c:otherwise>
                                <tr> 
                                </c:otherwise>
                            </c:choose>
                            <td><a href='<c:url value="/specialist/spec_incident_data?id=${incident.id}"/>'>${incident.title}</a></td>
                            <td>${incident.dateIncident} ${incident.timeIncident}</td>
                            <td>${incident.status.name}</td>
                            <td>${incident.zayavitel.name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>
