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
        <form action='<c:url value="/user/new_incident"/>' method="POST">
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/user"/>'>< Назад</a></p>
        </div>
            <div id="content">
                <table>
                <tr>
                    <td>Тип инцидента:</td>
                    <td>
                        <select name="typId">
                            <c:forEach var="typ" items="${typs}">
                                <option value="${typ.id}" selected><c:out value="${typ.name}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Title:</td><td><input type="text" name="title"/></td>
                </tr>
                <tr>
                    <td>Text:</td><td><input type="text" name="texti"/></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><input type="submit" value="Add" name="Add"/></td>
                    <td><input type="submit" value="Cancel" name="Cancel"/></td>
                </tr>
            </table>
            </div>
        </form>
    </body>
</html>
