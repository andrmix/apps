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
        <form action='<c:url value="/admin/user_data"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                    <div class="heada">${usera.name}
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    </div><div class="headb">/ Сотрудники / Данные сотрудника</div>
                </div>
            </div>
            <div id="sidebar">
                <div class="sidebar_el">
                    <a class="a_nazad" href='<c:url value="/admin"/>'><div class="u_icon_nazad"></div>Назад</a>
                </div>
            </div>
            <input type="hidden" name="login" value="${user.login}"/>
            <div id="content">
                <div class="incident_data">
                    <table class="table_incident_data">
                        <thead>
                            <tr>
                                <th colspan="2">${user.name}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Логин</td>
                                <td>${user.login}</td>
                            </tr>
                            <tr>
                                <td>E-mail</td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td>Отдел</td>
                                <td>${user.depart.name}</td>
                            </tr>
                            <tr>
                                <td>Должность</td>
                                <td>${user.dpost.name}</td>
                            </tr>
                        </tbody>
                    </table>
                    <button type="submit" name="Edit" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/edit.png"/>'>Редактировать</button>
                    <button type="submit" name="Delete" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/del.png"/>'>Удалить</button>
                    <button type="submit" name="resetPass" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/zamena.png"/>'>Сброс пароля</button>
                    <c:choose>
                        <c:when test="${user.changePassword == 2}">
                            <button type="submit" name="unblockUser" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/unblock.png"/>'>Разблокировать</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" name="blockUser" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/block.png"/>'>Заблокировать</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form>
    </body>
</html>
