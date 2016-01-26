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
                                <td>Время</td>
                                <td>${incident.timeIncident}</td>
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
                                <tr>
                                    <td>Время выполнения</td>
                                    <td>${incident.timeDone}</td>
                                </tr>
                                <tr>
                                    <td>Исполнитель</td>
                                    <td>${incident.specialist.name}</td>
                                </tr>
                            </c:if>
                            <c:if test="${incident.status.id == 4}">
                                <tr>
                                    <td>Дата закрытия</td>
                                    <td>${incident.dateClose}</td>
                                </tr>
                                <tr>
                                    <td>Время закрытия</td>
                                    <td>${incident.timeClose}</td>
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
                                <tr>
                                    <td>Время отклонения/отмены</td>
                                    <td>${incident.timeClose}</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                    <c:choose>
                        <c:when test="${commenta == 1}">
                            <div class="commentar">
                                <table class="commgo">
                                    <tr>
                                        <td style="width: 70%"><textarea placeholder="Укажите причину" name="textc" class="commEdit"/></textarea></td>
                                        <td style="width: 30%"><input type="submit" value="Готово" name="Done" class="ibuttcomm"/></td>
                                    </tr>
                                </table>
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
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${commento == 1}">
                            <table class="comments">
                                <thead>
                                    <tr>
                                        <th colspan="3">Комментарии</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="comment" items="${comments}">
                                        <tr>
                                            <td style="width: 20%">${comment.usersLogin.name}</td>
                                            <td style="width: 10%">${comment.dateComment} - ${comment.timeComment}</td>
                                            <td style="width: 70%">${comment.text}</td>
                                        </tr>
                                    </c:forEach>
                            </table>
                            <c:if test="${incident.status.id == 5}">
                            <table class="commgo">
                                <tr>
                                    <td style="width: 70%"><textarea placeholder="Комментировать..." name="textcomm" class="commEdit"/></textarea></td>
                                    <td style="width: 30%"><input type="submit" value="Отправить" name="bCommGo" class="ibuttcomm"/></td>
                                </tr>
                            </table>
                            </c:if>
                            <input type="submit" value="Скрыть комментарии" name="bCommOff" class="plashka"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Показать комментарии (${comments.size()})" name="bCommOn" class="plashka"/>
                        </c:otherwise>
                    </c:choose>           
                </div>
            </div>
        </form>
    </body>
</html>