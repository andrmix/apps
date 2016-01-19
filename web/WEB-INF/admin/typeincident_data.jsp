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
        <form action='<c:url value="/admin/typeincident_data"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Типы инцидентов / Данные типа инцидентов</div></div>
            </div>
            <div id="sidebar">
                <p><a href='<c:url value="/admin/typesincident"/>'>< Назад</a></p>
            </div>
            <input type="hidden" name="id" value="${typeIncident.id}"/>
            <div id="content">
                <ul id="depart">
                    <li>${typeIncident.name}</li>
                </ul>
                <input type="submit" value="Редактировать" name="Edit" id="butt"/>
                <input type="submit" value="Удалить" name="Delete" id="butt"/>
                <input type="submit" value="Отмена" name="Cancel" id="butt"/>
            </div>
        </form>
    </body>
</html>
