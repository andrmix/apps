<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/style_login.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Решение</title>
    </head>
    <body>
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_z_or.png"/>'><div class="resh">Решение</div>
        </div>
        <form action="j_security_check" method="POST">
            <div id="lb">
                <ul id="loginBox">
                    <li><input placeholder="Введите логин" type="text" class="text" size="20" name="j_username" value="moder"></li>
                    <li><input placeholder="Введите пароль" type="password" class="text1" size="20" name="j_password" value="moder"></li>
                    <li><input type="submit" class="submit" value="Вход"></li>
                </ul>
            </div>
        </form>
    </body>
</html>
