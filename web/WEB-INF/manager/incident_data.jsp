<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Обращения / Данные обращения</div></div>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/manager"/>'>< Назад</a></p>
        </div>
        <form action='<c:url value="/manager/incident_data"/>' method="POST">
            <input type="hidden" name="id" value="${incident.id}"/>
            <div id="content">
                <ul id="incident">
                    <li>${incident.title}</li>
                    <li>ID инцидента: ${incident.id} | ${incident.dateIncident} | ${incident.zayavitel.name}</li>
                    <li>${incident.text}</li>
                    <li>
                        <input type="submit" value="Отменить" name="Close" class="ibutt"/>
                        <c:choose>
                            <c:when test="${appoint == 1}">
                                <select name="specId" class="sel_mal">
                                    <c:forEach var="specialist" items="${specialists}">
                                        <option value="${specialist.login}" selected><c:out value="${specialist.name}"/></option>
                                    </c:forEach>
                                </select>
                                <input type="submit" value="Готово" name="Done" class="ibutt"/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Назначить" name="Appoint" class="ibutt"/>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
        </form>
    </body>
</html>