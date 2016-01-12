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
                <p><a href='<c:url value="/admin"/>'>Сотрудники</a></p>
                <p><a href='<c:url value="/admin/departs"/>'>Отделы</a></p>
                <p><a href='<c:url value="/admin/typesincident"/>'>Типы инцидентов</a></p>
            </div>
            <div id="content">
                <div>
                    <ul id="toolbar">
                        <li><ul id="departFilter">
                                <li><select name="departId" class="sel">
                                        <c:forEach var="depart" items="${departs}">
                                            <option value="${depart.id}" selected><c:out value="${depart.name}"/></option>
                                        </c:forEach>
                                    </select></li>
                                <li><c:choose>
                                        <c:when test="${filtr == 0}">
                                            <input type="submit" class="filter_off" value="Фильтр" name="Filteroff"/>
                                        </c:when>    
                                        <c:otherwise>
                                            <input type="submit" class="filter_on" value="Фильтр" name="Filteron"/>
                                        </c:otherwise>
                                    </c:choose></li>
                            </ul></li>
                        <li><ul id="departSearch"><li><input placeholder="Поиск" type="text" class="search" size="20" name="Search"></li>
                                <li><input type="submit" class="filter_off1" value="Поиск" name="Searchb"/></li></ul></li>
                        <li><a href='<c:url value="/admin/new_user"/>' class="newUser">Новый сотрудник</a></li>
                    </ul>
                </div>
                <c:forEach var="user" items="${userList}">
                    <ul id="incident">
                        <li><a href='<c:url value="/admin/user_data?login=${user.login}"/>'>${user.name}</a></li>
                        <li>${user.depart.name}</li>
                        <li>
                            <span class="date-article">Логин: ${user.login}</span>
                            <span class="date-article">E-mail: ${user.email}</span>
                        </li>
                        <li></li>
                    </ul>
                </c:forEach>
            </div>
        </form>
    </body>
</html>
