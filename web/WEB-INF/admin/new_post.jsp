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
        <form action='<c:url value="/admin/new_post"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                    <div class="heada">${usera.name}
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    </div><div class="headb">/ Должности / Новая должность</div>
                </div>
            </div>
            <div id="sidebar">
                <div class="sidebar_el">
                    <a class="a_nazad" href='<c:url value="/admin/dposts"/>'><div class="u_icon_nazad"></div>Назад</a>
                </div>
            </div>
            <div id="content">
                <input type="hidden" name="id" value="${dpost.id}"/>
                <div class="addEdit">
                    <ul>
                        <li><input placeholder="Название" type="text" name="namePost" class="editAddEdit" value="${namePost}"/></li>
                        <li>
                            <c:choose>
                                <c:when test="${editPost == 1}">
                                    <button type="submit" name="Edit" class="ibutt" onclick="return AdminPost(this.form)"/><img class="img_butt" src='<c:url value="/css/img/done.png"/>'>Изменить</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" name="Add" class="ibutt" onclick="return AdminPost(this.form)"/><img class="img_butt" src='<c:url value="/css/img/done.png"/>'>Добавить</button>
                                </c:otherwise>
                            </c:choose>
                            <button type="submit" name="Cancel" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/cancel.png"/>'>Отмена</button>
                        </li>
                    </ul>
                </div>
            </div>
        </form>
    </body>
</html>
