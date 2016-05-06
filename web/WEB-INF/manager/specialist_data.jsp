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
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/incident_data.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/toolbar.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Решение</title>
        <script src="https://www.google.com/jsapi"></script>
        <script>
            google.load("visualization", "1", {packages: ["corechart"]});
            google.setOnLoadCallback(drawChart);
            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Тип обращения', 'Количество'],
            <c:forEach var="typis" items="${typisList}">
                    ['${typis[1]}', ${typis[2]}],
            </c:forEach>
                ]);
                var options = {
                    title: 'Типы обращения',
                    is3D: true,
                    pieResidueSliceLabel: 'Остальное'
                };
                var chart = new google.visualization.PieChart(document.getElementById('aira'));
                chart.draw(data, options);
            }
            google.setOnLoadCallback(drawChartV);
            function drawChartV() {
                var data = google.visualization.arrayToDataTable([
                    ['Тип обращения', 'Количество'],
            <c:forEach var="typisc" items="${typiscList}">
                    ['${typisc[1]}', ${typisc[2]}],
            </c:forEach>
                ]);
                var options = {
                    title: 'Типы обращения',
                    is3D: true,
                    pieResidueSliceLabel: 'Остальное'
                };
                var chart = new google.visualization.PieChart(document.getElementById('airb'));
                chart.draw(data, options);
            }
        </script>
    </head>
    <body>
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
            <div class="head_block"><img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                <div class="heada">${user.name}
                    (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    / ${user.depart.name} / ${user.dpost.name}
                </div><div class="headb">/ Статистика</div>
            </div>
        </div>
        <div id="sidebar">
            <div class="sidebar_el">
                <a class="a_nazad" href='<c:url value="/manager/specialists"/>'><div class="u_icon_nazad"></div>Назад</a>       
            </div>
        </div>
        <form action='<c:url value="/manager/specialist_data"/>' method="POST">
            <div id="content">
                <input type="hidden" name="id" value="${speclogin}"/>
                <div class="incident_data">
                    <button type="submit" name="bPrintOne" class="plashka_print"/><img class="img_buttp" src='<c:url value="/css/img/print.png"/>'>Печать</button>
                    <table class="table_statistic">
                        <thead>
                            <tr>
                                <th colspan="4">Статистика - ${specname}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="stat" items="${statList}">
                                <tr>
                                    <td style="width: 30%" class="tab_title">Активные обращения</td>
                                    <td class="tab_title">Всего</td>
                                    <td class="tab_pole"><c:choose>
                                            <c:when test="${stat[1] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[1]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td rowspan="3" class="tab_title">Завершенные обращения</td>
                                    <td class="tab_title">За сегодня</td>
                                    <td class="tab_pole"><c:choose>
                                            <c:when test="${stat[2] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[2]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td class="tab_title">За месяц</td>
                                    <td class="tab_pole"><c:choose>
                                            <c:when test="${stat[3] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[3]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td class="tab_title">Всего</td>
                                    <td class="tab_pole"><c:choose>
                                            <c:when test="${stat[4] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[4]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td rowspan="3" class="tab_title">Отклоненные обращения</td>
                                    <td class="tab_title">За сегодня</td>
                                    <td class="tab_pole"><c:choose>
                                            <c:when test="${stat[5] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[5]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td class="tab_title">За месяц</td>
                                    <td class="tab_pole"><c:choose>
                                            <c:when test="${stat[6] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[6]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                                <tr>
                                    <td class="tab_title">Всего</td>
                                    <td class="tab_pole"><c:choose>
                                            <c:when test="${stat[7] == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${stat[7]}
                                            </c:otherwise>
                                        </c:choose></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="naznac"><img class="img_nazn" src='<c:url value="/css/img/chart_w.png"/>'><div class="ztext">За месяц
                            <select name="monc" class="sel_komisc">
                                <option value="1" selected>Январь</option>
                                <option value="2">Февраль</option>
                                <option value="3">Март</option>
                                <option value="4">Апрель</option>
                                <option value="5">Май</option>
                                <option value="6">Июнь</option>
                                <option value="7">Июль</option>
                                <option value="8">Август</option>
                                <option value="9">Сентябрь</option>
                                <option value="10">Октябрь</option>
                                <option value="11">Ноябрь</option>
                                <option value="12">Декабрь</option>
                            </select>
                            <input type="text" name="yearc" class="pyear" value="2016"/> года
                            <button type="submit" name="bcharts" class="ibuttzc"/><img class="img_buttz" src='<c:url value="/css/img/done.png"/>'>Показать</button>
                        </div></div>
                    <div id="aira" style="width: 550px; height: 400px;"></div>
                    <div class="naznac"><img class="img_nazn" src='<c:url value="/css/img/chart_w.png"/>'><div class="ztext">За все время</div></div>
                    <div id="airb" style="width: 550px; height: 400px;"></div>
                </div>
            </div>
        </form>
    </body>
</html>