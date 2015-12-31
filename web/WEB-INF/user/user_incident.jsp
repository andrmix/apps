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
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/user"/>'>< Назад</a></p>
        </div>
        <form action='<c:url value="/user/user_incident"/>' method="POST">
            <input type="hidden" name="id" value="${incident.id}"/>
            <div id="content">
            <div id="incident">
                     <ul id="incident">
                            <li>${incident.title}</li>
                            <li>ID инцидента: ${incident.id} | ${incident.text}</li>
                            <li>
                                <span class="date-article">Дата: ${incident.dateIncident}</span>
                                <span class="date-article">Статус: ${incident.status.name}</span>
                            </li>
                            <li></li>
                    </ul>
                    <input type="submit" value="Close" name="Close"/>
                    <input type="submit" value="Cancel" name="Cancel"/>
            </div>
            </div>
       </form>
      </body>
</html>