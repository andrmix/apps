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
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/toolbar.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src='<c:url value="/js/calendar_ru.js"/>'></script>
        <title>Решение</title>
    </head>
    <body>
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'>
                <div class="heada">${user.name}
                    (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    / ${user.depart.name} / ***Должность***
                </div>
                <div class="headb">/ Закрытые обращения</div>
            </div>
        </div>
        <div id="sidebar">
            <div class="sidebar_el">
                    <a href='<c:url value="/user/new_incident"/>'>
                        <div class="u_icon"></div>
                        Новое обращение
                    </a>
                </div>
                <div class="sidebar_el">
                    <a href='<c:url value="/user"/>'><div class="u_icon"></div>Активные обращения
                            <c:if test="${openIncidentsNew.size() gt 0}">
                                <span class="videlc">${openIncidentsNew.size()}</span>
                            </c:if>
                        </a></div>
                <div class="sidebar_el">
                    <a href='<c:url value="/user/closed_incidents"/>'><div class="u_iconv"></div><span class="videl">Закрытые обращения
                        <c:if test="${closedIncidentsNew.size() gt 0}">
                            <span class="count">${closedIncidentsNew.size()}</span>
                        </c:if>
                    </span></a>       
                </div>
        </div>
        <form action='<c:url value="${action}"/>' method="POST">
            <div id="content">
                <c:choose>
                    <c:when test="${tools == 1}">
                        <div id="toolbar">
                            <input type="submit" value="Скрыть фильтр" name="bToolsOff" class="plashka_color"/>
                            <table class="toolbar_table">
                                <tr>
                                    <td>
                                        <input placeholder="с" type="text" value="${dateb}" name="dateBegin" class="filter_date"
                                               onfocus="this.select();
                                                       lcs(this)"
                                               onclick="event.cancelBubble = true;
                                                       this.select();
                                                       lcs(this)">
                                        <span style="color: #FFFFFF">-</span>
                                        <input placeholder="по" type="text" value="${datee}" name="dateEnd" class="filter_date"
                                               onfocus="this.select();
                                                       lcs(this)"
                                               onclick="event.cancelBubble = true;
                                                       this.select();
                                                       lcs(this)">
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${openr == 1}">
                                                <div  class="radiob1"><input type="radio" name="group1" value="dOpen" checked> По дате создания</div>
                                                </c:when>
                                                <c:otherwise>
                                                <div  class="radiob1"><input type="radio" name="group1" value="dOpen"> По дате создания</div>
                                                </c:otherwise>
                                            </c:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 42%">
                                        <input type="submit" class="filter_off" value="Показать" name="dFilter"/>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${closer == 1}">
                                                <div  class="radiob2"><input type="radio" name="group1" value="dClose" class="radiob" checked> По дате закрытия</div>
                                                </c:when>
                                                <c:otherwise>
                                                <div  class="radiob2"><input type="radio" name="group1" value="dClose" class="radiob"> По дате закрытия</div>
                                                </c:otherwise>
                                            </c:choose>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Фильтр" name="bToolsOn" class="plashka_color"/>
                    </c:otherwise>
                </c:choose>

                <table class="incidents_tab">
                    <thead>
                        <tr>
                            <th><a href='<c:url value="/sort_by_name_closed"/>'>Заголовок обращения</a></th>
                            <th><a href='<c:url value="/sort_by_dateo_closed"/>'>Дата/Время создания</a></th>
                            <th><a href='<c:url value="/sort_by_status_closed"/>'>Статус</a></th>
                            <th><a href='<c:url value="/sort_by_spec_closed"/>'>Исполнитель</a></th>
                            <th><a href='<c:url value="/sort_by_datec_closed"/>'>Дата/Время закрытия</a></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${closedIncidents.isEmpty()}">
                        <td colspan="5" style="text-align: center;">Закрытых обращений нет</td>
                    </c:if>
                    <c:forEach var="incident" items="${closedIncidents}">
                        <c:choose>
                            <c:when test="${incident.new1 == 1 && incident.status.id == 8}">
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
