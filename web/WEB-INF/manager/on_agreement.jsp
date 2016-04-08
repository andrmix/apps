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
        <form action='<c:url value="/manager/on_agreement"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                    <div class="heada">${user.name} 
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                        / ${user.depart.name} / ${user.dpost.name}
                    </div><div class="headb">/ Нераспределенные обращения</div>
                </div>
            </div>
            <div id="sidebar">
                <c:choose>
                    <c:when test="${ismoder == 1}">
                        <input type="submit" value="[ - ] Руководитель" name="rolemoder" class="ibuttav"/>
                        <div id="pan_moder">
                            <div class="sidebar_el_m">
                                <a class="a_new_task" href='<c:url value="/manager/new_task"/>'><div class="u_icon_new_task">Новое задание</div></a>
                            </div>
                            <div class="sidebar_el_m">
                                <a class="a_unnal" href='<c:url value="/manager"/>'><div class="u_icon_unnal">Нераспределенные обращения
                                        <c:if test="${unallocatedIncidentsNew.size() gt 0}">
                                            <span class="videlc">${unallocatedIncidentsNew.size()}</span>
                                        </c:if>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar_el_m">
                                <a class="a_allo" href='<c:url value="/manager/allocated"/>'>
                                    <div class="u_icon_allo">Распределенные обращения</div>
                                </a>
                            </div> 
                            <div class="sidebar_el_m">
                                <a class="a_vyp" href='<c:url value="/manager/on_agreement"/>'><div class="u_icon_vyp_v"><span class="videl">Выполненные задания
                                            <c:if test="${agreeIncidentsNew.size() gt 0}">
                                                <span class="count">${agreeIncidentsNew.size()}</span>
                                            </c:if>
                                        </span>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar_el_m">
                                <a class="a_arc" href='<c:url value="/manager/closed"/>'>
                                    <div class="u_icon_arc">Архив обращений</div>
                                </a>
                            </div>
                            <div class="sidebar_el_m">
                                <a class="a_spec" href='<c:url value="/manager/specialists"/>'>
                                    <div class="u_icon_spec">Специалисты</div>
                                </a>
                            </div> 
                            <div class="sidebar_el_m">
                                <a class="a_tools" href='<c:url value="/manager/manager_tools"/>'>
                                    <div class="u_icon_tools">Настройки</div>
                                </a>
                            </div> 
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
                            <div class="sidebar_el_m">
                                <a class="a_act_inc_m" href='<c:url value="/manager/manager_incidents"/>'>
                                    <div class="u_icon_act_inc_m">Активные обращения 
                                        <c:if test="${openIncidentsManagerNew.size() gt 0}">
                                            <span class="count">${openIncidentsManagerNew.size()}</span>
                                        </c:if>
                                    </div> 
                                </a>
                            </div> 
                            <div class="sidebar_el_m">
                                <a class="a_vyp_inc_m" href='<c:url value="/manager/manager_done_incidents"/>'>
                                    <div class="u_icon_vyp_inc_m">Выполненные обращения</div>
                                </a>
                            </div>
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
                            <th><a href='<c:url value="/sort_by_name_m_agr"/>'>Заголовок обращения</a></th>
                            <th><a href='<c:url value="/sort_by_date_m_agr"/>'>Дата/Время создания</a></th>
                            <th><a href='<c:url value="/sort_by_status_m_agr"/>'>Статус</a></th>
                            <th><a href='<c:url value="/sort_by_zay_m_agr"/>'>Заявитель</a></th>
                            <th><a href='<c:url value="/sort_by_spec_m_agr"/>'>Специалист</a></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${agreeIncidents.isEmpty()}">
                        <td colspan="5" style="text-align: center;">Обращений на согласование нет</td>
                    </c:if>
                    <c:forEach var="incident" items="${agreeIncidents}">
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
