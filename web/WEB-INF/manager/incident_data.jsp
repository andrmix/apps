<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/header.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/sidebar.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/incident_data.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Решение</title>
    </head>
    <body>
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'>
                <div class="heada">${user.name}
                    (<a href='<c:url value="/logout"/>'>Выйти</a>)
                </div>
                <div class="headb">/ Обращения / Данные обращения</div>
            </div>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/manager"/>'>< Назад</a></p>
        </div>
        <form action='<c:url value="/manager/incident_data"/>' method="POST">
            <input type="hidden" name="id" value="${incident.id}"/>
            <input type="hidden" name="status" value="${incident.status.id}"/>
            <c:set var="una" scope="application" value="${un}"/>
            <div id="content">
                <div class="incident_data">
                    <table class="table_incident_data">
                        <thead>
                            <tr>
                                <th colspan="2">${incident.title}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>ИД обращения</td>
                                <td>${incident.id}</td>
                            </tr>
                            <tr>
                                <td>Дата и время создания</td>
                                <td>${incident.dateIncident} ${incident.timeIncident}</td>
                            </tr>
                            <tr>
                                <td>Тип обращения</td>
                                <td>${incident.typeIncident.name}</td>
                            </tr>
                            <tr>
                                <td>Текст обращения</td>
                                <td>${incident.text}</td>
                            </tr>
                            <tr>
                                <td>Статус</td>
                                <td>${incident.status.name}</td>
                            </tr>

                            <tr>
                                <td>Заявитель</td>
                                <td>${incident.zayavitel.name}</td>
                            </tr>
                            <tr>
                                <td>Исполнитель</td>
                                <td>${incident.specialist.name}</td>
                            </tr>
                            <c:if test="${incident.status.id == 4 || incident.status.id == 5}">
                                <tr>
                                    <td>Решение</td>
                                    <td>${incident.decision}</td>
                                </tr>
                                <tr>
                                    <td>Дата и время выполнения</td>
                                    <td>${incident.dateStatus} ${incident.timeStatus}</td>
                                </tr>
                                <tr>
                                    <td>Количество доработок</td>
                                    <c:choose>
                                        <c:when test="${incident.revisionCount ne null}">
                                            <td>${incident.revisionCount}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>0</td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:if>
                            <c:if test="${incident.status.id == 6}">
                                <tr>
                                    <td>Решение</td>
                                    <td>${incident.decision}</td>
                                </tr>
                                <tr>
                                    <td>Дата и время закрытия</td>
                                    <td>${incident.dateClose} ${incident.timeClose}</td>
                                </tr>
                                <tr>
                                    <td>Количество доработок</td>
                                    <c:choose>
                                        <c:when test="${incident.revisionCount ne null}">
                                            <td>${incident.revisionCount}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>0</td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:if>
                            <c:if test="${incident.status.id == 7 || incident.status.id == 8}">
                                <tr>
                                    <td>Причина отклонения/отмены</td>
                                    <td>${incident.decision}</td>
                                </tr>
                                <tr>
                                    <td>Дата и время отклонения/отмены</td>
                                    <td>${incident.dateClose} ${incident.timeClose}</td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>Вложение</td>
                                <c:choose>
                                    <c:when test="${incident.attachment ne null}">
                                        <td><img src='<c:url value="/screens/${incident.attachment}"/>' tabindex="0"></td>
                                        </c:when>
                                        <c:otherwise>
                                        <td>Нет</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </tbody>
                    </table>
                    <c:choose>
                        <c:when test="${commenta == 1}">
                            <div class="commentar">
                                <table class="commgo">
                                    <tr>
                                        <td style="width: 70%"><textarea placeholder="Укажите причину" name="textc" class="commEdit"/></textarea></td>
                                        <td style="width: 30%"><input type="submit" value="Готово" name="pDone" class="ibuttcomm"/></td>
                                    </tr>
                                </table>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${appoint == 1}">
                                    <select name="specId" class="sel_mal">
                                        <c:forEach var="specialist" items="${specialists}">
                                            <option value="${specialist.login}" selected><c:out value="${specialist.name}"/></option>
                                        </c:forEach>
                                    </select>
                                    <input type="submit" value="Назначить" name="Done" class="ibutt"/>
                                </c:when>
                                <c:when test="${done == 1}">
                                    <div class="commentar">
                                        <table class="commgo">
                                            <tr>
                                                <td style="width: 70%"><textarea placeholder="Решение" name="decision" class="commEdit"/></textarea></td>
                                                <td style="width: 30%"><input type="submit" value="Готово" name="gDone" class="ibuttcomm"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${incident.status.id == 1}">
                                        <input type="submit" value="Назначить" name="Appoint" class="ibutt"/>
                                        <input type="submit" value="Отклонить" name="Close" class="ibutt"/>
                                    </c:if>
                                    <c:if test="${incident.status.id == 2 || incident.status.id == 3}">
                                        <input type="submit" value="Переназначить" name="Appoint" class="ibutt"/>
                                    </c:if>
                                    <c:if test="${incident.status.id == 2 && incident.specialist.login == incident.manager.login}">
                                        <input type="submit" value="В работу" name="InWork" class="ibutt"/>
                                        <input type="submit" value="Отклонить" name="Close" class="ibutt"/>
                                    </c:if>
                                    <c:if test="${incident.status.id == 3 && incident.specialist.login == incident.manager.login}">
                                        <input type="submit" value="Выполнить" name="Doit" class="ibutt"/>
                                        <input type="submit" value="Отклонить" name="Close" class="ibutt"/>
                                    </c:if>
                                    <c:if test="${task == 1 && (incident.status.id == 2 || incident.status.id == 3)}">
                                        <input type="submit" value="Отменить" name="Close" class="ibutt"/>
                                    </c:if>
                                    <c:if test="${(task == 1 && incident.status.id == 4) || incident.status.id == 5}">
                                        <input type="submit" value="Согласовать" name="Agree" class="ibutt"/>
                                    </c:if>
                                    <c:if test="${incident.status.id == 8 || incident.status.id == 6}">
                                        <input type="submit" value="Удалить" name="Delete" class="ibutt"/>
                                    </c:if>
                                        <input type="submit" value="to mail" name="Send" class="ibutt"/>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${commento == 1}">
                            <br>
                            <input type="submit" value="Комментарии" name="bComm" class="plashka_on"/>
                            <input type="submit" value="История" name="bHist" class="plashka_off"/>
                            <table class="comments">
                                <tbody>
                                    <c:forEach var="comment" items="${comments}">
                                        <tr>
                                            <td style="width: 20%">${comment.usersLogin.name}</td>
                                            <td style="width: 10%">${comment.dateComment} - ${comment.timeComment}</td>
                                            <td style="width: 70%">${comment.text}</td>
                                        </tr>
                                    </c:forEach>
                            </table>
                            <c:if test="${incident.status.id == 3}">
                                <table class="commgo">
                                    <tr>
                                        <td style="width: 70%"><textarea placeholder="Комментировать..." name="textcomm" class="commEdit"/></textarea></td>
                                        <td style="width: 30%"><input type="submit" value="Отправить" name="bCommGo" class="ibuttcomm"/></td>
                                    </tr>
                                </table>
                            </c:if>
                        </c:when>
                        <c:when test="${ihistory == 1}">
                            <br>
                            <input type="submit" value="Комментарии" name="bComm" class="plashka_off"/>
                            <input type="submit" value="История" name="bHist" class="plashka_on"/>
                            <table class="comments">
                                <tbody>
                                    <c:forEach var="hist" items="${allhistory}">
                                        <tr>
                                            <td style="width: 20%">${hist.status.name}</td>
                                            <td style="width: 20%">${hist.dateAction} ${hist.timeAction}</td>
                                            <td style="width: 30%">${hist.actioner.name}</td>
                                            <td style="width: 30%">${hist.text}</td>
                                        </tr>
                                    </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <br>
                            <input type="submit" value="Комментарии" name="bComm" class="plashka_off"/>
                            <input type="submit" value="История" name="bHist" class="plashka_off"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form>
    </body>
</html>