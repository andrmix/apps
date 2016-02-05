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
        <form action='<c:url value="/manager/closed"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Закрытые обращения</div></div>
            </div>
            <div id="sidebar">
                <c:choose>
                    <c:when test="${ismoder == 1}">
                        <input type="submit" value="[ - ] Руководитель" name="rolemoder" class="ibuttav"/>
                        <div id="pan_moder">
                            <p><a href='<c:url value="/manager/new_task"/>'>Новое задание</a></p>
                            <p><a href='<c:url value="/manager"/>'>Нераспределенные обращения
                                    <c:if test="${unallocatedIncidentsNew.size() gt 0}">
                                        <span class="count">${unallocatedIncidentsNew.size()}</span>
                                    </c:if>
                                </a></p>
                            <p><a href='<c:url value="/manager/allocated"/>'>Распределенные обращения</a></p>
                            <p><a href='<c:url value="/manager/on_agreement"/>'>На согласование
                                    <c:if test="${agreeIncidentsNew.size() gt 0}">
                                        <span class="count">${agreeIncidentsNew.size()}</span>
                                    </c:if>
                                </a></p>
                            <p><a href='<c:url value="/manager/closed"/>'><span class="videl">Архив обращений</span></a></p>
                            <p><a href='<c:url value="/manager/specialists"/>'>Специалисты</a></p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="[+] Руководитель" name="rolemoder" class="ibutta"/>
                    </c:otherwise>
                </c:choose>
                <br>
                <c:choose>
                    <c:when test="${isspec == 1}">
                        <input type="submit" value="[ - ] Специалист" name="rolespec" class="ibuttav"/>
                        <div id="pan_moder">
                            <p><a href='<c:url value="/manager/manager_incidents"/>'>Активные обращения 
                                    <c:if test="${openIncidentsManagerNew.size() gt 0}">
                                        <span class="count">${openIncidentsManagerNew.size()}</span>
                                    </c:if>
                                </a></p>
                            <p><a href='<c:url value="/manager/manager_done_incidents"/>'>Выполненные обращения</a></p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="[+] Специалист" name="rolespec" class="ibutta"/>
                    </c:otherwise>
                </c:choose>
            </div>
            <div id="content">
                <table class="incidents_tab">
                    <thead>
                        <tr>
                            <th><a href='<c:url value="/sort_by_name_un"/>'>Заголовок инцидента</a></th>
                            <th><a href='<c:url value="/sort_by_date_un"/>'>Дата/Время</a></th>
                            <th><a href='<c:url value="/sort_by_status_un"/>'>Статус</a></th>
                            <th><a href='<c:url value="/sort_by_zay_un"/>'>Заявитель</a></th>
                            <th><a href='<c:url value="/sort_by_spec_allo"/>'>Специалист</a></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${closedIncidentsManager.isEmpty()}">
                        <td colspan="5" style="text-align: center;">Закрытых инцидентов нет</td>
                    </c:if>
                    <c:forEach var="incident" items="${closedIncidentsManager}">
                        <tr> 
                            <td><a href='<c:url value="/manager/incident_data?id=${incident.id}"/>'>${incident.title}</a></td>
                            <td>${incident.dateIncident} ${incident.timeIncident}</td>
                            <td>${incident.status.name}</td>
                            <td>${incident.zayavitel.name}</td>
                            <td>${incident.specialist.name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>
