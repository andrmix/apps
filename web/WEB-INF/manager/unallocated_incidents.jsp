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
        <form action='<c:url value="/manager"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'>
                    <div class="heada">${user.name}
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    </div><div class="headb">/ Нераспределенные обращения</div>
                </div>
            </div>
            <div id="sidebar">
                <c:choose>
                    <c:when test="${ismoder == 1}">
                        <input type="submit" value="[ - ] Руководитель" name="rolemoder" class="ibuttav"/>
                        <div id="pan_moder">
                            <p><a href='<c:url value="/manager/new_task"/>'>Новое задание</a></p>
                            <p><a href='<c:url value="/manager"/>'><span class="videl">Нераспределенные обращения
                                        <c:if test="${unallocatedIncidentsNew.size() gt 0}">
                                            <span class="videlc">${unallocatedIncidentsNew.size()}</span>
                                        </c:if>
                                    </span></a></p>
                            <p><a href='<c:url value="/manager/allocated"/>'>Распределенные обращения</a></p>
                            <p><a href='<c:url value="/manager/on_agreement"/>'>На согласование
                                    <c:if test="${agreeIncidentsNew.size() gt 0}">
                                        <span class="count">${agreeIncidentsNew.size()}</span>
                                    </c:if>
                                </a></p>
                            <p><a href='<c:url value="/manager/closed"/>'>Архив обращений</a></p>
                            <p><a href='<c:url value="/manager/specialists"/>'>Специалисты</a></p>
                            <p><a href='<c:url value="/manager/manager_tools"/>'>Настройки</a></p>
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
                            <th><a href='<c:url value="/sort_by_name_un"/>'>Заголовок обращения</a></th>
                            <th><a href='<c:url value="/sort_by_date_un"/>'>Дата/Время создания</a></th>
                            <th><a href='<c:url value="/sort_by_zay_un"/>'>Заявитель</a></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${unallocatedIncidents.isEmpty()}">
                        <td colspan="3" style="text-align: center;">Нераспределенных обращений нет</td>
                    </c:if>
                    <c:forEach var="incident" items="${unallocatedIncidents}">
                        <c:choose>
                            <c:when test="${incident.new1 == 1}">
                                <tr class="vyd">
                                </c:when>
                                <c:otherwise>
                                <tr> 
                                </c:otherwise>
                            </c:choose>
                            <td><a href='<c:url value="/manager/incident_data?id=${incident.id}"/>'>${incident.title}</a></td>
                            <td>${incident.dateIncident} ${incident.timeIncident}</td>
                            <td>${incident.zayavitel.name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>
