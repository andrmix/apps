<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/header.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/sidebar.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/incident_data.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Решение</title>
    </head>
    <body>
        <form action='<c:url value="/manager/manager_tools"/>' method="GET">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                    <div class="heada">${user.name} 
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                        / ${user.depart.name} / ${user.dpost.name}
                    </div><div class="headb">/ Настройки</div>
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
                                    <div class="u_icon_spec">Специалисты</div>
                                </a>
                            </div> 
                            <div class="sidebar_el_m">
                                <a class="a_tools" href='<c:url value="/manager/manager_tools"/>'>
                                    <div class="u_icon_tools_v"><span class="videl">Настройки</span></div>
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
                <c:choose>
                    <c:when test="${user.changePassword == 2}">
                        <div class="addEdit">
                            <ul>
                                <li>
                                    <button type="submit" name="Back" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/in_work.png"/>'>Вернуться</button>
                                </li>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="addEdit">
                            <ul>
                                <li>
                                    Назначить руководителем в мое отсутствие
                                </li>
                                <li>
                                    <select name="specId" class="editAddSel">
                                        <c:forEach var="specialist" items="${specialists}">
                                            <option value="${specialist.login}" selected><c:out value="${specialist.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </li>
                                <li>
                                    <button type="submit" name="Done" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/done.png"/>'>Назначить</button>
                                </li>
                                <li>
                                    <input type="checkbox">Учитывать меня при автораспределении
                                </li>
                            </ul>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </body>
</html>