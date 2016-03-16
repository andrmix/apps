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
                <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'>
                    <div class="heada">${usera.name}
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    </div><div class="headb">/ Сотрудники</div>
                </div>
            </div>
            <div id="sidebar">
                <p><a href='<c:url value="/admin"/>'><span class="videl">Сотрудники</span></a></p>
                <p><a href='<c:url value="/admin/departs"/>'>Отделы</a></p>
                <p><a href='<c:url value="/admin/typesincident"/>'>Типы инцидентов</a></p>
            </div>
            <div id="content">
                <c:choose>
                    <c:when test="${tools == 1}">
                        <div id="toolbar">
                            <input type="submit" value="Скрыть инструменты" name="bToolsOff" class="plashka_color"/>
                            <table class="toolbar_table">
                                <tr>
                                    <td>
                                        <select name="departId" class="sel">
                                            <c:forEach var="depart" items="${departs}">
                                                <option value="${depart.id}" selected><c:out value="${depart.name}"/></option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input placeholder="Поиск" type="text" class="search" size="20" name="Search">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${filtr == 0}">
                                                <input type="submit" class="filter_off" value="Фильтр" name="Filteroff"/>
                                            </c:when>    
                                            <c:otherwise>
                                                <input type="submit" class="filter_on" value="Фильтр" name="Filteron"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <input type="submit" class="filter_off" value="Поиск" name="Searchb"/>
                                    </td>
                                    <td><a href='<c:url value="/admin/new_user"/>' class="newUser">
                                            <img class="addUserPic" src='<c:url value="/img/userplus.png"/>'>
                                            Новый сотрудник
                                        </a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Показать инструменты" name="bToolsOn" class="plashka_color"/>
                    </c:otherwise>
                </c:choose>

                <table class="incidents_tab">
                    <thead>
                        <tr>
                            <th><a href='<c:url value="/sort_by_fio"/>'>ФИО сотрудника</a></th>
                            <th><a href='<c:url value="/sort_by_login"/>'>Логин</a></th>
                            <th><a href='<c:url value="/sort_by_email"/>'>E-mail</a></th>
                            <th><a href='<c:url value="/sort_by_depart"/>'>Отдел</a></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${userList.isEmpty()}">
                        <td colspan="4" style="text-align: center;">Список пользователей пуст</td>
                    </c:if>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td><a href='<c:url value="/admin/user_data?login=${user.login}"/>'>${user.name}</a></td>
                            <td>${user.login}</td>
                            <td>${user.email}</td>
                            <td>${user.depart.name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>
