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
        <script src='<c:url value="/js/checkOnNull.js"/>'></script>
        <title>Решение</title>
    </head>
    <body>
        <div id="header">
            <img class="galka" src='<c:url value="/img/galka_white.png"/>'><h1>Решение</h1>
            <div class="head_block">
                <img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                <div class="heada">${user.name}
                    (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    / ${user.depart.name} / ${user.dpost.name}
                </div><div class="headb">/ Обращения / Данные обращения</div></div>
        </div>
        <div id="sidebar">
            <div class="sidebar_el">
                <a class="a_nazad" href='<c:url value="/user"/>'><div class="u_icon_nazad"></div>Назад</a>       
            </div>
        </div>
        <form action='<c:url value="/user/user_incident"/>' method="POST">
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
                            </div>
                        </div>

                        <div id="podla">
                            <div id="podl">
                                <c:choose>
                                    <c:when test="${incident.status.id == 1}">
                                        <div id="hexagon_n">Новый</div>
                                        <div id="hexagon_s">Назначен</div>
                                        <div id="hexagon_s">В работе</div>
                                        <div id="hexagon_s">Решено</div>
                                        <div id="hexagon_p">Завершен</div>
                                    </c:when>
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
                                        <div id="hexagon_ss">Решено</div>
                                        <div id="hexagon_pv">Завершен</div>
                                    </c:when>
                                    <c:when test="${incident.status.id == 6}">
                                        <div id="hexagon_ns">Новый</div>
                                        <div id="hexagon_so">Отменен</div>
                                        <div id="hexagon_s">В работе</div>
                                        <div id="hexagon_s">Решено</div>
                                        <div id="hexagon_p">Завершен</div>
                                    </c:when>
                                    <c:when test="${incident.status.id == 7}">
                                        <div id="hexagon_ns">Новый</div>
                                        <div id="hexagon_ss">Назначен</div>
                                        <div id="hexagon_so">Отклонен</div>
                                        <div id="hexagon_s">Решено</div>
                                        <div id="hexagon_p">Завершен</div>
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <c:choose>
                            <c:when test="${incident.attachment ne null}">
                                <div class="text_inc">
                                    <div class="man">${incident.zayavitel.name}</div>
                                    <div class="man_otd">${incident.zayavitel.depart.name} / ${incident.zayavitel.dpost.name}</div>
                                    ${incident.text}
                                    <img class="scree" src='<c:url value="/screens/${incident.attachment}"/>' tabindex="0">
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="text_inc">
                                    <div class="man">${incident.zayavitel.name}</div>
                                    <div class="man_otd">${incident.zayavitel.depart.name} / ${incident.zayavitel.dpost.name}</div>
                                    ${incident.text}
                                </div>
                            </c:otherwise>
                        </c:choose>

                        <c:if test="${incident.status.id > 1}">
                            <div class="nazn"><img class="img_nazn" src='<c:url value="/css/img/appoint.png"/>'><div class="ztext">${incident.specialist.name} (${incident.specialist.dpost.name})</div></div>
                            </c:if>

                        <c:choose>
                            <c:when test="${incident.status.id == 4}">       
                                <div class="dec_inc">
                                    <div class="man">${incident.specialist.name}</div>
                                    <div class="man_otd">${incident.specialist.depart.name} / ${incident.specialist.dpost.name}</div>
                                    <div class="dat_dec">${incident.dateDone} ${incident.timeDone}</div>
                                    ${incident.decision}
                                </div>
                            </c:when>

                            <c:when test="${incident.status.id == 5}"> 
                                <div class="dec_inc">
                                    <div class="man">${incident.specialist.name}</div>
                                    <div class="man_otd">${incident.specialist.depart.name} / ${incident.specialist.dpost.name}</div>
                                    <div class="dat_dec">${incident.dateClose} ${incident.timeClose}</div>
                                    ${incident.decision}
                                </div>
                            </c:when>

                            <c:when test="${incident.status.id == 6}"> 
                                <div class="dec_inc_otkl">
                                    <div class="man">${incident.zayavitel.name}</div>
                                    <div class="man_otd">${incident.zayavitel.depart.name} / ${incident.zayavitel.dpost.name}</div>
                                    <div class="dat_dec">${incident.dateClose} ${incident.timeClose}</div>
                                    ${incident.decision}
                                </div>
                            </c:when>

                            <c:when test="${incident.status.id == 7}"> 
                                <div class="dec_inc_otkl">
                                    <div class="man">${incident.specialist.name}</div>
                                    <div class="man_otd">${incident.specialist.depart.name} / ${incident.specialist.dpost.name}</div>
                                    <div class="dat_dec">${incident.dateClose} ${incident.timeClose}</div>
                                    ${incident.decision}
                                </div>
                            </c:when>
                        </c:choose>

                        <c:choose>
                            <c:when test="${commenta == 1}">
                                <div class="commentar">
                                    <textarea placeholder="Укажите причину" name="textc" class="commEdit"/></textarea>
                                    <button type="submit" name="Done" class="ibutt" onclick="return CancelInc(this.form)"/><img class="img_butt" src='<c:url value="/css/img/cancel.png"/>'>Отменить</button>
                                    <input type="hidden" name="status" value="${incident.status.id}"/>
                                </div>
                            </c:when>

                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${incident.status.id == 1 || incident.status.id == 2}">
                                        <c:if test="${incident.status.id == 1}"> 
                                            <button type="submit" name="Edit" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/edit.png"/>'>Изменить</button>
                                        </c:if>
                                        <button type="submit" name="Close" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/cancel.png"/>'>Отменить обращение</button>
                                    </c:when>
                                    <c:when test="${incident.status.id == 4}">
                                        <button type="submit" name="Accept" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/accept.png"/>'>Подтвердить</button>
                                        <button type="submit" name="NoAccept" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/accept_no.png"/>'>Вернуть на доработку</button>
                                    </c:when>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>

                        <div class="liniya"></div>

                        <c:if test="${incident.status.id gt 2}">
                            <c:choose>
                                <c:when test="${commento == 1}">
                                    <div class="pan_chp">
                                        <input type="submit" value="" name="bComm" class="pan_mod_cv"/>
                                        <input type="submit" value="" name="bHist" class="pan_mod_h"/>
                                    </div>
                                    <div class="comm_inc_mod">
                                        <c:choose>
                                            <c:when test="${comments.size() > 0}">
                                                <c:forEach var="comment" items="${comments}">
                                                    <div class="comm_inc">
                                                        <div class="man_comm">${comment.usersLogin.name}</div>
                                                        <div class="man_otd_comm">${comment.usersLogin.depart.name} / ${comment.usersLogin.dpost.name}</div>
                                                        <div class="dat_comm">${comment.dateComment} ${comment.timeComment}</div>
                                                        ${comment.text}
                                                    </div>
                                                    <div class="liniya_kor"></div>
                                                </c:forEach>

                                                <c:if test="${incident.status.id == 3}">
                                                    <textarea placeholder="Комментировать..." name="textcomm" class="commEdit_comm"/></textarea>
                                                    <button type="submit" name="bCommGo" class="ibutt" onclick="return Comment(this.form)"/><img class="img_butt" src='<c:url value="/css/img/comm_2.png"/>'>Отправить</button>
                                                </c:if>    
                                            </c:when>
                                            <c:otherwise>
                                                Сообщений нет
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </c:when>

                                <c:when test="${ihistory == 1}">
                                    <div class="pan_chp">
                                        <input type="submit" value="" name="bComm" class="pan_mod_c"/>
                                        <input type="submit" value="" name="bHist" class="pan_mod_hv"/>
                                    </div>
                                    <div class="comm_inc_mod">
                                        <table class="commgo">
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
                                    </div>
                                </c:when>
                            </c:choose>
                        </c:if>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>