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
                <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'>
                    <div class="heada">${usera.name}
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    </div><div class="headb">/ Сотрудники / Данные сотрудника</div>
                </div>
            </div>
            <div id="sidebar">
                <p><a href='<c:url value="/admin"/>'>< Назад</a></p>
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
                        </tbody>
                    </table>
                    <input type="submit" value="Редактировать" name="Edit" class="ibutt"/>
                    <input type="submit" value="Удалить" name="Delete" class="ibutt"/>
                    <input type="submit" value="Сброс пароля" name="resetPass" class="ibutt"/>
                    <c:choose>
                        <c:when test="${user.changePassword == 2}">
                            <input type="submit" value="Разблокировать" name="unblockUser" class="ibutt"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Заблокировать" name="blockUser" class="ibutt"/>
                        </c:otherwise>
                    </c:choose>
                    <input type="submit" value="Отмена" name="Cancel" class="ibutt"/>
                </div>
            </div>
        </form>
    </body>
</html>
