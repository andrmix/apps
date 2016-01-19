<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Решение</title>
    </head>
    <body>
        <form action='<c:url value="/admin/typesincident"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Типы инцидентов</div></div>
            </div>
            <div id="sidebar">
                <p><a href='<c:url value="/admin"/>'>Сотрудники</a></p>
                <p><a href='<c:url value="/admin/departs"/>'>Отделы</a></p>
                <p><a href='<c:url value="/admin/typesincident"/>'>Типы инцидентов</a></p>
            </div>
            <div id="content">
                <div>
                    <ul id="toolbar">
                        <li><ul id="departSearch"><li><input placeholder="Поиск" type="text" class="search" size="20" name="Search"></li>
                                <li><input type="submit" class="filter_off1" value="Поиск" name="Searchb"/></li></ul></li>
                        <li><a href='<c:url value="/admin/new_typeincident"/>' class="newUser">Новый тип инцидента</a></li>
                    </ul>
                </div>
                <ul id="depart">
                    <c:forEach var="typeIncident" items="${typesIncidentList}">
                        <li><a href='<c:url value="/admin/typeincident_data?id=${typeIncident.id}"/>'>${typeIncident.name}</a></li>
                        </c:forEach>
                </ul>
            </div>
        </form>
    </body>
</html>
