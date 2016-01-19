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
            <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
        </div>
            <form action="j_security_check" method="POST">
    <div id="lb">
        <ul id="loginBox">
            <li><input placeholder="Введите логин" type="text" class="text" size="20" name="j_username" value="i.ivanov"></li>
            <li><input placeholder="Введите пароль" type="password" class="text1" size="20" name="j_password" value="ivanov"></li>
            <li><input type="submit" class="submit" value="Авторизоваться"></li>
        </ul>
    </div>
</form>
    </body>
</html>
