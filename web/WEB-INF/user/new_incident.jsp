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
        <form action='<c:url value="/user/new_incident"/>' method="GET">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
                <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'>
                    <div class="heada">${user.name} 
                        (<a href='<c:url value="/logout"/>'>Выйти</a>)
                        / ${user.depart.name} / ***Должность***
                    </div><div class="headb">/ Обращения / Новое обращение</div>
                </div>
            </div>
            <div id="sidebar">
                <div class="sidebar_el">
                    <a href='<c:url value="/user"/>'><div class="u_icon"></div>Назад</a>       
                </div>
            </div>
            <div id="content">
                <input type="hidden" name="id" value="${incident.id}"/>
                <input type="hidden" name="editInc" value="${editincident}"/>
                <div class="addEdit">
                    <ul>
                        <li>
                            <select name="typId" class="editAddSel">
                                <c:forEach var="typ" items="${typs}">
                                    <option value="${typ.id}" selected><c:out value="${typ.name}"/></option>
                                </c:forEach>
                                <c:if test="${editincident == 0}">
                                    <option disabled selected>Выберите тип обращения</option>
                                </c:if>
                            </select>
                        </li>
                        <li>
                            <input placeholder="Введите заголовок" type="text" name="title" class="editAddEdit" value="${incident.title}"/>
                        </li>
                        <li>
                            <textarea placeholder="Введите текст обращения" name="texti" class="editAddArea"/>${incident.text}</textarea>
                        </li>
                        <li>
                            <input type="submit" value="Добавить вложение" name="addAttachment" class="ibutt"/>
                        </li>
                        <li>
                            <c:choose>
                                <c:when test="${editincident == 1}">
                                    <input type="submit" value="Изменить" name="Edit" class="ibutt" onclick="return UserIncident(this.form)"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" value="Добавить" name="Add" class="ibutt" onclick="return UserIncident(this.form)"/>
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