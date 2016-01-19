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
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'><div class="heada">${user.name} (<a href='<c:url value="/logout"/>'>Выйти</a>)</div><div class="headb">/ Выполненные обращения / Данные обращения</div></div>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/specialist/spec_done_incidents"/>'>< Назад</a></p>
        </div>
        <form action='<c:url value="/specialist/spec_done_incident_data"/>' method="POST">
            <input type="hidden" name="id" value="${incident.id}"/>
            <div id="content">
                <ul id="incident">
                    <li>${incident.title}</li>
                    <li>ID инцидента: ${incident.id} | ${incident.dateIncident} | ${incident.zayavitel.name}</li>
                    <li>${incident.text}</li>
                    <li class="tem"><ul><li class="inner_tem"><b>Решение:</b></li>
                    <li class="inner_tem">${incident.decision}</li></ul></li>
                    <li>Дата выполнения: ${incident.dateDone}</li>
                    <li></li>
                </ul>
            </div>
        </form>
    </body>
</html>