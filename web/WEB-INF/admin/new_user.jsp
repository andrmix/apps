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
        <form action='<c:url value="/admin/new_user"/>' method="POST">
            <div id="header">
                <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
            </div>
            <div id="sidebar">
                <p><a href='<c:url value="/admin"/>'>< Назад</a></p>
            </div>
            <div id="content">
                <table>
                    <tr>
                        <td>Логин:</td><td><input type="text" name="login" value="${login}"/></td>
                    </tr>
                    <tr>
                        <td>Пароль:</td><td><input type="text" name="pass" value="${pass}"/></td>
                    </tr>
                    <tr>
                        <td>ФИО:</td><td><input type="text" name="fio" value="${fio}"/></td>
                    </tr>
                    <tr>
                        <td>E-mail:</td><td><input type="text" name="email" value="${email}"/></td>
                    </tr>
                    <tr>
                        <td>Отдел:</td>
                        <td>
                            <select name="departId">
                                <c:forEach var="depart" items="${departs}">
                                    <option value="${depart.id}" selected><c:out value="${depart.name}"/></option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Роль:</td>
                        <td>
                            <select name="role">
                                <c:choose>
                                    <c:when test="${role == 'user'}">
                                        <option value="user" selected><c:out value="Пользователь"/></option>
                                        <option value="specialist"><c:out value="Специалист"/></option>
                                        <option value="rukovoditel"><c:out value="Руководитель"/></option>
                                    </c:when>
                                    <c:when test="${role == 'specialist'}">
                                        <option value="user"><c:out value="Пользователь"/></option>
                                        <option value="specialist" selected><c:out value="Специалист"/></option>
                                        <option value="rukovoditel"><c:out value="Руководитель"/></option>
                                    </c:when>
                                    <c:when test="${role == 'rukovoditel'}">
                                        <option value="user"><c:out value="Пользователь"/></option>
                                        <option value="specialist"><c:out value="Специалист"/></option>
                                        <option value="rukovoditel" selected><c:out value="Руководитель"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="user"><c:out value="Пользователь"/></option>
                                        <option value="specialist"><c:out value="Специалист"/></option>
                                        <option value="rukovoditel" selected><c:out value="Руководитель"/></option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <c:choose>
                            <c:when test="${editUser == 1}">
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
