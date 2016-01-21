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
            <p><a href='<c:url value="/manager"/>'>< Назад</a></p>
        </div>
        <form action='<c:url value="/manager/incident_data"/>' method="POST">
            <input type="hidden" name="id" value="${incident.id}"/>
            <c:set var="una" scope="application" value="${un}"/>
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
                            <tr>
                                <td>Заявитель</td>
                                <td>${incident.zayavitel.name}</td>
                            </tr>
                        </tbody>
                    </table>
                    <c:choose>
                        <c:when test="${commenta == 1}">
                            <div class="commentar">
                                <table class="commgo">
                                    <tr>
                                        <td style="width: 70%"><textarea placeholder="Укажите причину" name="textc" class="commEdit"/></textarea></td>
                                        <td style="width: 30%"><input type="submit" value="Готово" name="pDone" class="ibuttcomm"/></td>
                                    </tr>
                                </table>
                                <input type="hidden" name="status" value="${incident.status.id}"/>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${appoint == 1}">
                                    <select name="specId" class="sel_mal">
                                        <c:forEach var="specialist" items="${specialists}">
                                            <option value="${specialist.login}" selected><c:out value="${specialist.name}"/></option>
                                        </c:forEach>
                                    </select>
                                    <input type="submit" value="Назначить" name="Done" class="ibutt"/>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${una.equals('da')}">
                                        <input type="submit" value="Назначить" name="Appoint" class="ibutt"/>
                                    </c:if>
                                    <c:if test="${una.equals('no')}">
                                        <input type="submit" value="Переназначить" name="Appoint" class="ibutt"/>
                                    </c:if>
                                    <input type="submit" value="Отклонить" name="Close" class="ibutt"/>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form>
    </body>
</html>