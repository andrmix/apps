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
        <script src='<c:url value="/js/checkOnNull.js"/>'></script>
        <title>Решение</title>
    </head>
    <body>
        <form action='<c:url value="/admin/new_typeincident"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'>
                    <div class="heada">${usera.name} 
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    </div><div class="headb">/ Типы инцидентов / Новый тип инцидентов</div>
                </div>
            </div>
            <div id="sidebar">
                <p><a href='<c:url value="/admin/typesincident"/>'>< Назад</a></p>
            </div>
            <div id="content">
                <input type="hidden" name="id" value="${typeIncident.id}"/>
                <div class="addEdit">
                    <ul>
                        <li><input placeholder="Название" type="text" name="nameTypeIncident" class="editAddEdit" value="${nameTypeIncident}"/></li>
                        <li>
                            <c:choose>
                                <c:when test="${editTypeIncident == 1}">
                                    <input type="submit" value="Изменить" name="Edit" class="ibutt" onclick="return AdminTI(this.form)"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" value="Добавить" name="Add" class="ibutt" onclick="return AdminTI(this.form)"/>
                                </c:otherwise>
                            </c:choose>
                            <input type="submit" value="Отмена" name="Cancel" class="ibutt"/>
                        </li>
                    </ul>
                </div>
            </div>
        </form>
    </body>
</html>
