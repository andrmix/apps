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
        <form action='<c:url value="/admin/new_user"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                    <div class="heada">${usera.name}
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    </div><div class="headb">/ Сотрудники / Новый сотрудник</div>
                </div>
            </div>
            <div id="sidebar">
                <div class="sidebar_el">
                    <a class="a_nazad" href='<c:url value="/admin"/>'><div class="u_icon_nazad"></div>Назад</a>
                </div>
            </div>
            <div id="content">
                <div class="addEdit">
                    <ul>
                        <li><input placeholder="Логин" type="text" name="login" class="editAddEdit" value="${login}"/></li>
                        <li><input placeholder="ФИО" type="text" name="fio" class="editAddEdit" value="${fio}"/></li>
                        <li><input placeholder="E-mail" type="text" name="email" class="editAddEdit" value="${email}"/></li>
                        <li>
                            <select name="departId" class="editAddSel">
                                <c:forEach var="depart" items="${departs}">
                                    <option value="${depart.id}" selected><c:out value="${depart.name}"/></option>
                                </c:forEach>
                                <c:if test="${edituser == 0}">
                                    <option disabled selected>Выберите отдел</option>
                                </c:if>
                            </select>
                        </li>
                        <li>
                            <select name="postId" class="editAddSel">
                                <c:forEach var="dpost" items="${posts}">
                                    <option value="${dpost.id}" selected><c:out value="${dpost.name}"/></option>
                                </c:forEach>
                                <c:if test="${edituser == 0}">
                                    <option disabled selected>Выберите должность</option>
                                </c:if>
                            </select>
                        </li>
                        <li>
                            <select name="role" class="editAddSel">
                                <c:choose>
                                    <c:when test="${role == 'user'}">
                                        <option value="user" selected><c:out value="Пользователь"/></option>
                                        <option value="specialist"><c:out value="Специалист"/></option>
                                        <option value="manager"><c:out value="Руководитель"/></option>
                                    </c:when>
                                    <c:when test="${role == 'specialist'}">
                                        <option value="user"><c:out value="Пользователь"/></option>
                                        <option value="specialist" selected><c:out value="Специалист"/></option>
                                        <option value="manager"><c:out value="Руководитель"/></option>
                                    </c:when>
                                    <c:when test="${role == 'manager'}">
                                        <option value="user"><c:out value="Пользователь"/></option>
                                        <option value="specialist"><c:out value="Специалист"/></option>
                                        <option value="manager" selected><c:out value="Руководитель"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="user" selected><c:out value="Пользователь"/></option>
                                        <option value="specialist"><c:out value="Специалист"/></option>
                                        <option value="manager"><c:out value="Руководитель"/></option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </li>
                        <li>
                            <c:choose>
                                <c:when test="${editUser == 1}">
                                    <button type="submit" name="Edit" class="ibutt" onclick="return AdminUser(this.form)"/><img class="img_butt" src='<c:url value="/css/img/done.png"/>'>Изменить</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" name="Add" class="ibutt" onclick="return AdminUser(this.form)"/><img class="img_butt" src='<c:url value="/css/img/done.png"/>'>Добавить</button>
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
