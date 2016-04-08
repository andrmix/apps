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
        <title>Решение</title>
    </head>
    <body>
        <form action='<c:url value="/manager/specialists"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                    <div class="heada">${user.name}
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                        / ${user.depart.name} / ${user.dpost.name}
                    </div><div class="headb">/ Сотрудники</div>
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
                                <a class="a_vyp" href='<c:url value="/manager/on_agreement"/>'><div class="u_icon_vyp">Выполненные задания
                                            <c:if test="${agreeIncidentsNew.size() gt 0}">
                                                <span class="count">${agreeIncidentsNew.size()}</span>
                                            </c:if>
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
                                    <div class="u_icon_spec_v"><span class="videl">Специалисты</span></div>
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
                            <th rowspan="2"><a href='<c:url value="/sort_by_fio_spec"/>'>ФИО специалиста</a></th>
                            <th rowspan="2">Активные обращения</th>
                            <th colspan="3">Завершенные обращения</th>
                        </tr>
                        <tr>
                            <th>За сегодня</th>
                            <th>За месяц</th>
                            <th>Всего</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${specialistList}">
                            <tr>
                                <td><a href='<c:url value="/manager/specialist_data?id=${user[5]}"/>'>${user[0]}</a></td>
                                <td><c:choose>
                                        <c:when test="${user[1] == null}">
                                            0
                                        </c:when>
                                        <c:otherwise>
                                            ${user[1]}
                                        </c:otherwise>
                                    </c:choose></td>
                                <td><c:choose>
                                        <c:when test="${user[2] == null}">
                                            0
                                        </c:when>
                                        <c:otherwise>
                                            ${user[2]}
                                        </c:otherwise>
                                    </c:choose></td>
                                <td><c:choose>
                                        <c:when test="${user[3] == null}">
                                            0
                                        </c:when>
                                        <c:otherwise>
                                            ${user[3]}
                                        </c:otherwise>
                                    </c:choose></td>
                                <td><c:choose>
                                        <c:when test="${user[4] == null}">
                                            0
                                        </c:when>
                                        <c:otherwise>
                                            ${user[4]}
                                        </c:otherwise>
                                    </c:choose></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>
