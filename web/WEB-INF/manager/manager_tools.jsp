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
                <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'>
                    <div class="heada">${user.name} 
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    </div><div class="headb">/ Настройки</div>
                </div>
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
                            <p><a href='<c:url value="/manager/specialists"/>'>Специалисты</a></p>
                            <p><a href='<c:url value="/manager/manager_tools"/>'><span class="videl">Настройки</span></a></p>
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
                <c:choose>
                    <c:when test="${user.changePassword == 2}">
                        <input type="submit" value="Вернуться" name="Back" class="ibutt"/>
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
                                    <input type="submit" value="Назначить" name="Done" class="ibutt"/>
                                </li>
                            </ul>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </body>
</html>