<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/docs.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/incident_data.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/tables.css"/>'>
        <link rel="stylesheet" media="print" type="text/css" href='<c:url value="/css/print.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Статистика сотрудника</title>
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
        <form action='<c:url value="/manager/stat_one_print"/>' method="POST">
            <div class="noprint">
                <a href='<c:url value="/manager/specialists"/>'><div class="abutt"><img class="img_butt" src='<c:url value="/css/img/nazad.png"/>'>Назад</div></a>
                <a onclick="print()"><div class="abutt"><img class="img_butt" src='<c:url value="/css/img/print.png"/>'>Печать</div></a>
                <div class="zliniya"></div>
            </div>
            <div class="zagol">
                СТАТИСТИКА РАБОТЫ СОТРУДНИКА
                <br>${specname}
                <br>НА ${dToday}
            </div>
            <table class="table_statistic_p">
                <tbody>
                    <c:forEach var="stat" items="${statList}">
                        <tr>
                            <td class="tab_title_p" style="width: 30%">Активные обращения</td>
                            <td class="tab_title_p">Всего</td>
                            <td class="tab_pole_p"><c:choose>
                                    <c:when test="${stat[1] == null}">
                                        0
                                    </c:when>
                                    <c:otherwise>
                                        ${stat[1]}
                                    </c:otherwise>
                                </c:choose></td>
                        </tr>
                        <tr>
                            <td rowspan="3"  class="tab_title_p">Завершенные обращения</td>
                            <td class="tab_title_p">За сегодня</td>
                            <td class="tab_pole_p"><c:choose>
                                    <c:when test="${stat[2] == null}">
                                        0
                                    </c:when>
                                    <c:otherwise>
                                        ${stat[2]}
                                    </c:otherwise>
                                </c:choose></td>
                        </tr>
                        <tr>
                            <td class="tab_title_p">За месяц</td>
                            <td class="tab_pole_p"><c:choose>
                                    <c:when test="${stat[3] == null}">
                                        0
                                    </c:when>
                                    <c:otherwise>
                                        ${stat[3]}
                                    </c:otherwise>
                                </c:choose></td>
                        </tr>
                        <tr>
                            <td class="tab_title_p">Всего</td>
                            <td class="tab_pole_p"><c:choose>
                                    <c:when test="${stat[4] == null}">
                                        0
                                    </c:when>
                                    <c:otherwise>
                                        ${stat[4]}
                                    </c:otherwise>
                                </c:choose></td>
                        </tr>
                        <tr>
                            <td rowspan="3" class="tab_title_p">Отклоненные обращения</td>
                            <td class="tab_title_p">За сегодня</td>
                            <td class="tab_pole_p"><c:choose>
                                    <c:when test="${stat[5] == null}">
                                        0
                                    </c:when>
                                    <c:otherwise>
                                        ${stat[5]}
                                    </c:otherwise>
                                </c:choose></td>
                        </tr>
                        <tr>
                            <td class="tab_title_p">За месяц</td>
                            <td class="tab_pole_p"><c:choose>
                                    <c:when test="${stat[6] == null}">
                                        0
                                    </c:when>
                                    <c:otherwise>
                                        ${stat[6]}
                                    </c:otherwise>
                                </c:choose></td>
                        </tr>
                        <tr>
                            <td class="tab_title_p">Всего</td>
                            <td class="tab_pole_p"><c:choose>
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

            <div class="blo_cha">       
                <div class="naznacpo"><img class="img_nazn" src='<c:url value="/css/img/chart_s.png"/>'><div class="ztext">За месяц
                        <c:choose>
                            <c:when test="${mon == 1}">
                                Январь
                            </c:when> 
                            <c:when test="${mon == 2}">
                                Февраль
                            </c:when> 
                            <c:when test="${mon == 3}">
                                Март
                            </c:when> 
                            <c:when test="${mon == 4}">
                                Апрель
                            </c:when> 
                            <c:when test="${mon == 5}">
                                Май
                            </c:when> 
                            <c:when test="${mon == 6}">
                                Июнь
                            </c:when> 
                            <c:when test="${mon == 7}">
                                Июль
                            </c:when> 
                            <c:when test="${mon == 8}">
                                Август
                            </c:when> 
                            <c:when test="${mon == 9}">
                                Сентябрь
                            </c:when> 
                            <c:when test="${mon == 10}">
                                Октябрь
                            </c:when> 
                            <c:when test="${mon == 11}">
                                Ноябрь
                            </c:when> 
                            <c:when test="${mon == 12}">
                                Декабрь
                            </c:when> 
                        </c:choose>
                        ${syear} года
                    </div></div>
                <div>
                    <div id="aira" style="width: 550px; height: 400px;"></div>
                </div></div>
            <div class="blo_cha">
                <div class="naznacpo"><img class="img_nazn" src='<c:url value="/css/img/chart_s.png"/>'><div class="ztext">За все время</div></div>
                <div id="airb" style="width: 550px; height: 400px;"></div></div>
        </form>
    </body>
</html>
