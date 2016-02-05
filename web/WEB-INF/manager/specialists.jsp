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
                <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Сотрудники</div></div>
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
                        <p><a href='<c:url value="/manager/closed"/>'>Архив обращений</a></p>
                        <p><a href='<c:url value="/manager/specialists"/>'><span class="videl">Специалисты</span></a></p>
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
                            <th rowspan="2"><a href='<c:url value="/sort_by_fio_spec"/>'>ФИО специалиста</a></th>
                            <th colspan="2">Активные инциденты</th>
                            <th colspan="3">Завершенные инциденты</th>
                        </tr>
                        <tr>
                            <th>За сегодня</th>
                            <th>Всего</th>
                            <th>За сегодня</th>
                            <th>За месяц</th>
                            <th>Всего</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${specialistList}">
                            <tr>
                                <td><a href='<c:url value="/manager/specialist_data?id=${user[6]}"/>'>${user[0]}</a></td>
                                <td><c:choose>
                                        <c:when test="${user[2] == null}">
                                            0
                                        </c:when>
                                        <c:otherwise>
                                            ${user[2]}
                                        </c:otherwise>
                                    </c:choose></td>
                                <td><c:choose>
                                        <c:when test="${user[1] == null}">
                                            0
                                        </c:when>
                                        <c:otherwise>
                                            ${user[1]}
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
                                <td><c:choose>
                                        <c:when test="${user[5] == null}">
                                            0
                                        </c:when>
                                        <c:otherwise>
                                            ${user[5]}
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
