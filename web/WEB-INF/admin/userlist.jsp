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
        <form action='<c:url value="/admin"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
            </div>
            <div id="sidebar">
                <p><a href='<c:url value="/admin/new_user"/>'>Новый сотрудник</a></p>
                <p><a href='<c:url value="/admin/new_depart"/>'>Новый отдел</a></p>
                <p><a href='<c:url value="/admin/new_typeincident"/>'>Новый тип инцидента</a></p>
                <p><a href='<c:url value="/admin"/>'>Общие списки</a></p>
            </div>
            <div id="content">
                <select name="departId" class="sel">
                    <c:forEach var="depart" items="${departs}">
                        <option value="${depart.id}" selected><c:out value="${depart.name}"/></option>
                    </c:forEach>
                </select>
                <c:choose>
                    <c:when test="${filtr == 0}">
                        <input type="submit" class="filter_off" value="Фильтр" name="Filteroff"/>
                    </c:when>    
                    <c:otherwise>
                        <input type="submit" class="filter_on" value="Фильтр" name="Filteron"/>
                    </c:otherwise>
                </c:choose>
                <input placeholder="Поиск" type="text" class="search" size="20" name="Search">
                <input type="submit" class="filter_off" value="Поиск" name="Searchb"/>
                <c:forEach var="user" items="${userList}">
                    <ul id="incident">
                        <li><a href='<c:url value="/admin/user_data?id=${user.login}"/>'>${user.name}</a></li>
                        <li>${user.depart.name}</li>
                        <li>
                            <span class="date-article">Дата: ${user.login}</span>
                            <span class="date-article">Статус: ${user.email}</span>
                        </li>
                        <li></li>
                    </ul>
                </c:forEach>
            </div>
        </form>
    </body>
</html>
