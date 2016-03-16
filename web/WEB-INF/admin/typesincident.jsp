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
        <form action='<c:url value="/admin/typesincident"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'>
                    <div class="heada">${usera.name} 
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    </div><div class="headb">/ Типы инцидентов</div>
                </div>
            </div>
            <div id="sidebar">
                <p><a href='<c:url value="/admin"/>'>Сотрудники</a></p>
                <p><a href='<c:url value="/admin/departs"/>'>Отделы</a></p>
                <p><a href='<c:url value="/admin/typesincident"/>'><span class="videl">Типы инцидентов</span></a></p>
            </div>
            <div id="content">
                <c:choose>
                    <c:when test="${tools == 1}">
                        <div id="toolbar">
                            <input type="submit" value="Скрыть инструменты" name="bToolsOff" class="plashka_color"/>
                            <table class="toolbar_table">
                                <tr>
                                    <td>
                                        <input placeholder="Поиск" type="text" class="search" size="20" name="Search">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="submit" class="filter_off" value="Поиск" name="Searchb"/>
                                    </td>
                                    <td><a href='<c:url value="/admin/new_typeincident"/>' class="newUser">
                                            <img class="addUserPic" src='<c:url value="/img/userplus.png"/>'>
                                            Новый тип инцидента
                                        </a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Инструменты" name="bToolsOn" class="plashka_color"/>
                    </c:otherwise>
                </c:choose>

                <table class="incidents_tab">
                    <thead>
                        <tr>
                            <th><a href='<c:url value="/sort_by_name_typeincident"/>'>Название типа инцидента</a></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${typesIncidentList.isEmpty()}">
                        <td colspan="1" style="text-align: center;">Список типов инцидентов пуст</td>
                    </c:if>
                    <c:forEach var="typeIncident" items="${typesIncidentList}">
                        <tr>
                            <td><a href='<c:url value="/admin/typeincident_data?id=${typeIncident.id}"/>'>${typeIncident.name}</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>
