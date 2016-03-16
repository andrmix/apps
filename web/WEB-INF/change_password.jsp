<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style_login.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Решение</title>
    </head>
    <body>
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_z_or.png"/>'><div class="resh">Решение</div>
        </div>
        <form action='<c:url value="/change_password"/>' method="POST">
            <div id="lb">
                <ul id="loginBox">
                    <li><input placeholder="Введите пароль" type="password" class="text" size="20" name="pass1" value=""></li>
                    <li><input placeholder="Подтвердите пароль" type="password" class="text1" size="20" name="pass2" value=""></li>
                    <li><input type="submit" class="submitc1" value="Сменить пароль" name="resetPass">
                    <input type="submit" class="submitc2" value="Отмена" name="cancel"></li>
                </ul>
                <c:if test="${no == 1}">
                    <script>alert('Пароли не совпадают!');</script>
                </c:if>
            </div>
        </form>
    </body>
</html>
