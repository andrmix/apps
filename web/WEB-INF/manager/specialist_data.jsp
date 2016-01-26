<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Статистика</div></div>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/manager/specialists"/>'>< Назад</a></p>
        </div>
        <form action='<c:url value="/manager/specialist_data"/>' method="POST">
            <div id="content">
                <div class="incident_data">
                    <table class="table_statistic">
                        <thead>
                            <tr>
                                <th colspan="4">Статистика</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="stat" items="${statList}">
                                <tr>
                                    <td rowspan="2" style="width: 30%" class="tab_title">Активные инциденты</td>
                                    <td class="tab_title" style="width: 30%">За сегодня</td>
                                    <td><c:choose>
                                            <c:when test="${stat[1] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[1]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td class="tab_title">Всего</td>
                                    <td><c:choose>
                                            <c:when test="${stat[2] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[2]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td rowspan="3" class="tab_title">Завершенные инциденты</td>
                                    <td class="tab_title">За сегодня</td>
                                    <td><c:choose>
                                            <c:when test="${stat[3] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[3]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td class="tab_title">За месяц</td>
                                    <td><c:choose>
                                            <c:when test="${stat[4] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[4]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td class="tab_title">Всего</td>
                                    <td><c:choose>
                                            <c:when test="${stat[5] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[5]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td rowspan="3" class="tab_title">Отклоненные инциденты</td>
                                    <td class="tab_title">За сегодня</td>
                                    <td><c:choose>
                                            <c:when test="${stat[6] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[6]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td class="tab_title">За месяц</td>
                                    <td><c:choose>
                                            <c:when test="${stat[7] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[7]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td class="tab_title">Всего</td>
                                    <td><c:choose>
                                            <c:when test="${stat[8] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[8]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <table class="table_statistic">
                        <thead>
                            <tr>
                                <th colspan="12"  class="podzag">Завершенные за год</th>
                            </tr>
                            <tr>
                                <th style="width: 17%">Январь</th>
                                <th style="width: 17%">Февраль</th>
                                <th style="width: 17%">Март</th>
                                <th style="width: 17%">Апрель</th>
                                <th style="width: 17%">Май</th>
                                <th style="width: 15%">Июнь</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <c:forEach var="stata" items="${statYearList1}">
                                    <td class="cell_center">
                                        ${stata}
                                    </td>
                                </c:forEach>
                            </tr>
                        </tbody>
                    </table>
                    <table class="table_statistic">
                        <thead>
                            <tr>
                                <th style="width: 17%">Июль</th>
                                <th style="width: 17%">Август</th>
                                <th style="width: 17%">Сентябрь</th>
                                <th style="width: 17%">Октябрь</th>
                                <th style="width: 17%">Ноябрь</th>
                                <th style="width: 15%">Декабрь</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <c:forEach var="stata" items="${statYearList2}">
                                    <td class="cell_center">
                                        ${stata}
                                    </td>
                                </c:forEach>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </body>
</html>