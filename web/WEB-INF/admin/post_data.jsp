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
        <form action='<c:url value="/admin/post_data"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                    <div class="heada">${usera.name} 
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    </div><div class="headb">/ Должности / Данные должности</div>
                </div>
            </div>
            <div id="sidebar">
                <div class="sidebar_el">
                    <a class="a_nazad" href='<c:url value="/admin/dposts"/>'><div class="u_icon_nazad"></div>Назад</a>
                </div>
            </div>
            <input type="hidden" name="id" value="${dpost.id}"/>
            <div id="content">
                <div class="incident_data">
                    <table class="table_incident_data">
                        <thead>
                            <tr>
                                <th colspan="2">Должность</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="width: 30%">Название</td>
                                <td style="width: 70%">${dpost.name}</td>
                            </tr>
                        </tbody>
                    </table>
                    <button type="submit" name="Edit" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/edit.png"/>'>Редактировать</button>
                    <button type="submit" name="Delete" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/del.png"/>'>Удалить</button>
                </div>
            </div>
        </form>
    </body>
</html>
