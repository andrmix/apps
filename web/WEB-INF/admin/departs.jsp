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
        <form action='<c:url value="/admin/departs"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                    <div class="heada">${usera.name}
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    </div><div class="headb">/ Отделы</div>
                </div>
            </div>
            <div id="sidebar">
                <div class="sidebar_el">
                    <a class="a_users" href='<c:url value="/admin"/>'>
                        <div class="u_icon_users"></div>
                        Сотрудники
                    </a>
                </div>
                <div class="sidebar_el">
                    <a class="a_departs" href='<c:url value="/admin/departs"/>'>
                        <div class="u_icon_departs_v"></div>
                        <span class="videl">Отделы</span>
                    </a>
                </div>
                <div class="sidebar_el">
                    <a class="a_dposts" href='<c:url value="/admin/dposts"/>'>
                        <div class="u_icon_dposts"></div>
                        Должности
                    </a>
                </div>
                <div class="sidebar_el">
                    <a class="a_typs" href='<c:url value="/admin/typesincident"/>'>
                        <div class="u_icon_typs"></div>
                        Типы инцидентов
                    </a>
                </div>
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
                                    <td><a href='<c:url value="/admin/new_depart"/>' class="newUser">
                                            <img class="addUserPic" src='<c:url value="/img/userplus.png"/>'>
                                            Новый отдел
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
                            <th><a href='<c:url value="/sort_by_name_depart"/>'>Название отдела</a></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${departList.isEmpty()}">
                        <td colspan="1" style="text-align: center;">Список отделов пуст</td>
                    </c:if>
                    <c:forEach var="depart" items="${departList}">
                        <tr>
                            <td><a href='<c:url value="/admin/depart_data?id=${depart.id}"/>'>${depart.name}</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>
