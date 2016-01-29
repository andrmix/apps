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
        <form action='<c:url value="/admin"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Сотрудники</div></div>
            </div>
            <div id="sidebar">
                <p><a href='<c:url value="/manager"/>'>Нераспределенные обращения
                        <c:if test="${unallocatedIncidentsNew.size() gt 0}">
                            <span class="count">${unallocatedIncidentsNew.size()}</span>
                        </c:if>
                    </a></p>
                <p><a href='<c:url value="/manager/allocated"/>'>Распределенные обращения</a></p>
                <p><a href='<c:url value="/manager/specialists"/>'><span class="videl">Специалисты</span></a></p>
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
