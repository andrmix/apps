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
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/statusbar.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Решение</title>
    </head>
    <body>
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
            <div class="head_block"><img class="user_pic" src='<c:url value="/img/user32.png"/>'>
                <div class="heada">${user.name}
                    (<a href='<c:url value="/logout"/>'>Выйти</a>)
                </div><div class="headb">/ Данные обращения</div>
            </div>
        </div>
        <div id="sidebar">
            <p><a href='<c:url value="/specialist"/>'>< Назад</a></p>
        </div>
        <form action='<c:url value="/specialist/spec_incident_data"/>' method="POST">
            <input type="hidden" name="id" value="${incident.id}"/>
            <div id="content">
                <div class="incident_data">




                    <div class="inc_data">
                        <div class="shapka">
                            <div class="id_inc">${incident.id}</div>
                            <div class="name_inc">${incident.title}</div>
                            <div class="data_inc">
                                <div class="dat_inc">${incident.dateIncident} ${incident.timeIncident}</div>
                                <div class="type_inc">${incident.typeIncident.name}</div>
                            </div></div>
                        <div id="podla">
                            <div id="podl">
                                <c:choose>
                                    <c:when test="${incident.status.id == 2}">
                                        <div id="hexagon_ns">Новый</div>
                                        <div id="hexagon_sv">Назначен</div>
                                        <div id="hexagon_s">В работе</div>
                                        <div id="hexagon_s">Решено</div>
                                        <div id="hexagon_p">Завершен</div>
                                    </c:when>
                                    <c:when test="${incident.status.id == 3}">
                                        <div id="hexagon_ns">Новый</div>
                                        <div id="hexagon_ss">Назначен</div>
                                        <div id="hexagon_sv">В работе</div>
                                        <div id="hexagon_s">Решено</div>
                                        <div id="hexagon_p">Завершен</div>
                                    </c:when>
                                    <c:when test="${incident.status.id == 4}">
                                        <div id="hexagon_ns">Новый</div>
                                        <div id="hexagon_ss">Назначен</div>
                                        <div id="hexagon_ss">В работе</div>
                                        <div id="hexagon_sv">Решено</div>
                                        <div id="hexagon_p">Завершен</div>
                                    </c:when>
                                    <c:when test="${incident.status.id == 5}">
                                        <div id="hexagon_ns">Новый</div>
                                        <div id="hexagon_ss">Назначен</div>
                                        <div id="hexagon_ss">В работе</div>
                                        <div id="hexagon_sv">Решено</div>
                                        <div id="hexagon_p">Завершен</div>
                                    </c:when>

                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose>
                            </div></div>

                        <c:choose>
                            <c:when test="${incident.attachment ne null}">
                                <div class="text_inc">
                                    <div class="man">${incident.zayavitel.name}</div>
                                    <div class="man_otd">${incident.zayavitel.depart.name}</div>
                                    ${incident.text}
                                    <img class="scree" src='<c:url value="/screens/${incident.attachment}"/>' tabindex="0"></div>
                                </c:when>
                                <c:otherwise>
                                <div class="text_inc">
                                    <div class="man">${incident.zayavitel.name}</div>
                                    <div class="man_otd">${incident.zayavitel.depart.name}</div>
                                    ${incident.text}</div>
                                </c:otherwise>
                            </c:choose>

                        <c:if test="${incident.status.id == 4 || incident.status.id == 5 || incident.status.id == 6 || incident.status.id == 8}">
                            <div class="dec_inc">
                                <div class="man">${incident.specialist.name}</div>
                                <div class="man_otd">${incident.specialist.depart.name}</div>
                                <div class="dat_dec">${incident.dateStatus} ${incident.timeStatus}</div>
                                ${incident.decision}
                            </div>
                        </c:if>

                        <c:choose>
                            <c:when test="${otmena == 1}">
                                <div class="commentar">
                                    <textarea placeholder="Укажите причину" name="textc" class="commEdit"/></textarea>
                                    <input type="submit" value="Отклонить" name="pDone" class="ibutt"/>
                                    <input type="hidden" name="status" value="${incident.status.id}"/>
                                </div>
                            </c:when>
                            <c:when test="${zamenaP == 1}"> 
                                <div class="commentar">
                                    <textarea placeholder="Укажите причину" name="textz" class="commEdit"/></textarea>
                                    <input type="submit" value="Готово" name="zDone" class="ibutt"/>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${done == 1}">
                                        <div class="commentar">
                                            <textarea placeholder="Решение" name="decision" class="commEdit"/></textarea>
                                            <input type="submit" value="Готово" name="Done" class="ibutt"/>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${incident.status.id == 2}">
                                            <input type="submit" value="В работу" name="InWork" class="ibutt"/>
                                            <c:if test="${incident.zayavitel.login ne incident.manager.login}">
                                                <input type="submit" value="Отклонить" name="Close" class="ibutt"/>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${incident.status.id == 3}">
                                            <input type="submit" value="Выполнить" name="Doit" class="ibutt"/>
                                            <c:if test="${incident.req == null}">
                                                <input type="submit" value="Замена оборудования" name="Zamena" class="ibutt"/>
                                            </c:if>
                                            <c:if test="${incident.zayavitel.login ne incident.manager.login}">
                                                <input type="submit" value="Отклонить" name="Close" class="ibutt"/>
                                            </c:if>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                        <div class="liniya"></div>

                        <div class="pan_chp">
                            <input type="submit" value="" name="bComm" class="pan_mod"/>
                            <input type="submit" value="" name="bHist" class="pan_mod"/>
                            <input type="submit" value="" name="bResh" class="pan_mod"/>
                        </div>

                        <div class="comm_inc_mod">
                            <c:forEach var="comment" items="${comments}">
                                <div class="comm_inc">

                                    <div class="man_comm">${comment.usersLogin.name}</div>
                                    <div class="man_otd_comm">${incident.zayavitel.depart.name}</div>


                                    <div class="dat_comm">${comment.dateComment} ${comment.timeComment}</div>
                                    ${comment.text}
                                </div>
                                <div class="liniya_kor"></div>
                            </c:forEach>
                        </div>

                        <c:if test="${incident.status.id == 3}">
                            <textarea placeholder="Комментировать..." name="textcomm" class="commEdit_comm"/></textarea>
                            <input type="submit" value="Отправить" name="bCommGo" class="ibutt_comm"/>
                        </c:if>    
                    </div>


















                    <table class="table_incident_data">
                        <tbody>



                            <c:if test="${incident.status.id == 4 || incident.status.id == 5}">

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
                        </tbody>
                    </table>


                    <c:choose>

                        <c:when test="${ihistory == 1}">
                            <br>
                            <input type="submit" value="История" name="bHist" class="plashka_on"/>
                            <input type="submit" value="Похожие обращения" name="bResh" class="plashka_off"/>
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
                        <c:when test="${iresh == 1}">
                            <br>
                            <input type="submit" value="История" name="bHist" class="plashka_off"/>
                            <input type="submit" value="Похожие обращения" name="bResh" class="plashka_on"/>
                            <c:choose>
                                <c:when test="${rIncidents.size() > 0}">
                                    <table class="comments">
                                        <tbody>
                                            <c:forEach var="inc" items="${rIncidents}">
                                                <tr>
                                                    <td style="width: 3%">${inc[0]}</td>
                                                    <td style="width: 15%">${inc[1]} ${inc[2]}</td>
                                                    <td style="width: 12%">${inc[3]}</td>
                                                    <td style="width: 35%">${inc[4]}</td>
                                                    <td style="width: 35%">${inc[5]}</td>
                                                </tr>
                                            </c:forEach>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    Похожих обращений нет
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <br>
                            <input type="submit" value="История" name="bHist" class="plashka_off"/>
                            <input type="submit" value="Похожие обращения" name="bResh" class="plashka_off"/>
                        </c:otherwise>
                    </c:choose>          
                </div>
            </div>
        </form>
    </body>
</html>