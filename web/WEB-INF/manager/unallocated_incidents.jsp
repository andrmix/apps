<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Нераспределенные обращения</div></div>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/manager"/>'>Нераспределенные обращения
                    <c:if test="${unallocatedIncidents.size() gt 0}">
                        <span class="count">${unallocatedIncidents.size()}</span>
                    </c:if>
                </a></p>
            <p><a href='<c:url value="/manager/specialists"/>'>Специалисты</a></p>
            <p><a href='<c:url value="/manager/done_incidents"/>'>На согласование
                    <c:if test="${doneIncidents.size() gt 0}">
                        <span class="count">${doneIncidents.size()}</span>
                    </c:if>
                </a></p>
        </div>
        <div id="content">
            <c:forEach var="incident" items="${unallocatedIncidents}">
                <ul id="incident">
                    <li><a href='<c:url value="/manager/incident_data?id=${incident.id}"/>'>${incident.title}</a></li>
                    <li>${incident.text}</li>
                    <li>${incident.dateIncident} | ${incident.zayavitel.name}</li>
                    <li></li>
                </ul>
            </c:forEach>
        </div>
    </body>
</html>
