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
        <form action='<c:url value="/admin/new_depart"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
            </div>
            <div id="sidebar">
                <p><a href='<c:url value="/admin"/>'>< Назад</a></p>
            </div>
            <div id="content">
                <table>
                    <tr>
                        <td>Название:</td><td><input type="text" name="nameDepart" value="${nameDepart}"/></td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <c:choose>
                            <c:when test="${editDepart == 1}">
                                <td><input type="submit" value="Изменить" name="Edit"/></td>
                                </c:when>    
                                <c:otherwise>
                                <td><input type="submit" value="Добавить" name="Add"/></td>
                                </c:otherwise>
                            </c:choose>
                        <td><input type="submit" value="Отмена" name="Cancel"/></td>
                    </tr>
                </table>
            </div>
        </form>
    </body>
</html>
