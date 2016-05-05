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
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/incident_data.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Решение</title>
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
                <table class="incidents_tab_d">
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
                <div class="naznac"><img class="img_nazn" src='<c:url value="/css/img/docs.png"/>'><div class="ztext">За месяц
                        <select name="monId" class="sel_komisc">
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
                        <input type="text" name="years" class="pyear" value="2016"/> года
                        <button type="submit" name="Showc" class="ibuttzc"/><img class="img_buttz" src='<c:url value="/css/img/done.png"/>'>Показать</button>
                    </div></div>
                <div>
                    <div id="airm" style="width: 550px; height: 400px;"></div>
                    <div id="oilm" style="width: 450px; height: 400px;"></div>
                </div>
                <div class="naznac"><img class="img_nazn" src='<c:url value="/css/img/docs.png"/>'><div class="ztext">За все время</div></div>
                <div id="air" style="width: 550px; height: 400px;"></div>
                <div id="oil" style="width: 450px; height: 400px;"></div>
            </div>
        </form>
    </body>
</html>
