<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Обращения / Данные обращения</div></div>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/user"/>'>< Назад</a></p>
        </div>
        <form action='<c:url value="/user/user_incident"/>' method="POST">
            <input type="hidden" name="id" value="${incident.id}"/>
            <div id="content">
                <div class="incident_data">
                    <table class="table_incident_data">
                        <thead>
                            <tr>
                                <th colspan="2">${incident.title}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>ID инцидента</td>
                                <td>${incident.id}</td>
                            </tr>
                            <tr>
                                <td>Тип инцидента</td>
                                <td>${incident.typeIncident.name}</td>
                            </tr>
                            <tr>
                                <td>Текст обращения</td>
                                <td>${incident.text}</td>
                            </tr>
                            <tr>
                                <td>Дата</td>
                                <td>${incident.dateIncident}</td>
                            </tr>
                            <tr>
                                <td>Статус</td>
                                <td>${incident.status.name}</td>
                            </tr>
                            <c:if test="${incident.status.id == 3 || incident.status.id == 4}">
                                <tr>
                                    <td>Решение</td>
                                    <td>${incident.decision}</td>
                                </tr>
                                <tr>
                                    <td>Дата выполнения</td>
                                    <td>${incident.dateDone}</td>
                                </tr>
                            </c:if>
                            <c:if test="${incident.status.id == 4}">
                                <tr>
                                    <td>Дата закрытия</td>
                                    <td>${incident.dateClose}</td>
                                </tr>
                            </c:if>
                            <c:if test="${incident.status.id == 2 || incident.status.id == 7}">
                                <tr>
                                    <td>Причина</td>
                                    <td>${incident.decision}</td>
                                </tr>
                                <tr>
                                    <td>Дата отклонения/отмены</td>
                                    <td>${incident.dateClose}</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                    <c:choose>
                        <c:when test="${commenta == 1}">
                            <div class="comment">
                                <textarea placeholder="Укажите причину" name="textc" class="editAddArea"/></textarea><br>
                                <input type="submit" value="Готово" name="Done" class="ibutt"/>
                                <input type="hidden" name="status" value="${incident.status.id}"/>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${incident.status.id == 1 || incident.status.id == 6}">
                                <input type="submit" value="Изменить" name="Edit" class="ibutt"/>
                                <input type="submit" value="Отменить обращение" name="Close" class="ibutt"/>
                            </c:if>
                            <c:if test="${incident.status.id == 3}">
                                <input type="submit" value="Подтвердить" name="Accept" class="ibutt"/>
                                <input type="submit" value="Вернуть на доработку" name="NoAccept" class="ibutt"/>
                            </c:if>
                            <c:if test="${incident.status.id == 5}">
                                <input type="submit" value="Оставить комментарий" name="Comment" class="ibutt"/>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form>
    </body>
</html>