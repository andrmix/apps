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
        <form action='<c:url value="/admin/departs"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
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
                        <li><a href='<c:url value="/admin/new_depart"/>' class="newUser">Новый отдел</a></li>
                    </ul>
                </div>
                <ul id="depart">
                    <c:forEach var="depart" items="${departList}">
                        <li><a href='<c:url value="/admin/depart_data?id=${depart.id}"/>'>${depart.name}</a></li>
                        </c:forEach>
                </ul>
            </div>
        </form>
    </body>
</html>
