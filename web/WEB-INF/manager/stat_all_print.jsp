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
        <title>Статистика ОИиТО</title>
        <script src="https://www.google.com/jsapi"></script>
        <script>
            google.load("visualization", "1", {packages: ["corechart"]});
            google.setOnLoadCallback(drawChart);
            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Тип обращения', 'Количество'],
            <c:forEach var="typi" items="${typiList}">
                    ['${typi[1]}', ${typi[2]}],
            </c:forEach>
                ]);
                var options = {
                    title: 'Типы обращения',
                    is3D: true,
                    pieResidueSliceLabel: 'Остальное'
                };
                var chart = new google.visualization.PieChart(document.getElementById('air'));
                chart.draw(data, options);
            }
            google.setOnLoadCallback(drawChartV);
            function drawChartV() {
                var data = google.visualization.arrayToDataTable([
                    ['Специалист', 'Показатель'],
            <c:forEach var="useri" items="${specialistList}">
                    ['${useri[0]}', ${useri[4]}],
            </c:forEach>
                ]);
                var options = {
                    title: 'Специалисты',
                    is3D: true,
                    hAxis: {title: 'Специалисты'},
                    vAxis: {title: 'Показатель'}
                };
                var chart = new google.visualization.ColumnChart(document.getElementById('oil'));
                chart.draw(data, options);
            }

            google.load("visualization", "1", {packages: ["corechart"]});
            google.setOnLoadCallback(drawChartM);
            function drawChartM() {
                var data = google.visualization.arrayToDataTable([
                    ['Тип обращения', 'Количество'],
            <c:forEach var="typim" items="${typimList}">
                    ['${typim[1]}', ${typim[2]}],
            </c:forEach>
                ]);
                var options = {
                    title: 'Типы обращения',
                    is3D: true,
                    pieResidueSliceLabel: 'Остальное'
                };
                var chart = new google.visualization.PieChart(document.getElementById('airm'));
                chart.draw(data, options);
            }
            google.setOnLoadCallback(drawChartVM);
            function drawChartVM() {
                var data = google.visualization.arrayToDataTable([
                    ['Специалист', 'Показатель'],
            <c:forEach var="userim" items="${specialistmList}">
                    ['${userim[0]}', ${userim[1]}],
            </c:forEach>
                ]);
                var options = {
                    title: 'Специалисты',
                    is3D: true,
                    hAxis: {title: 'Специалисты'},
                    vAxis: {title: 'Показатель'}
                };
                var chart = new google.visualization.ColumnChart(document.getElementById('oilm'));
                chart.draw(data, options);
            }
        </script>
    </head>
    <body>
        <form action='<c:url value="/manager/stat_all_print"/>' method="POST">
            <div class="noprint">
                <a href='<c:url value="/manager/specialists"/>'><div class="abutt"><img class="img_butt" src='<c:url value="/css/img/nazad.png"/>'>Назад</div></a>
                <a onclick="print()"><div class="abutt"><img class="img_butt" src='<c:url value="/css/img/print.png"/>'>Печать</div></a>
                <div class="zliniya"></div>
            </div>
            <div class="zagol">
                СТАТИСТИКА РАБОТЫ ОТДЕЛА ИНФОРМАЦИОННОГО И ТЕХНИЧЕСКОГО ОБЕСПЕЧЕНИЯ НА ${dToday}
            </div>
            <table class="incidents_tab_p">
                <thead>
                    <tr>
                        <th rowspan="2">ФИО специалиста</th>
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
                            <td>${user[0]}</td>
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

            <div class="naznacp"><img class="img_nazn" src='<c:url value="/css/img/chart_s.png"/>'><div class="ztext">За месяц
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
                <div id="airm" style="width: 550px; height: 400px;"></div>
                <div id="oilm" style="width: 400px; height: 400px;"></div>
            </div>
            <div class="naznacp"><img class="img_nazn" src='<c:url value="/css/img/chart_s.png"/>'><div class="ztext">За все время</div></div>
            <div id="air" style="width: 550px; height: 400px;"></div>
            <div id="oil" style="width: 400px; height: 400px;"></div>
        </form>
    </body>
</html>
