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
            <div class="head_block"><img class="user_pic" src='<c:url value="/css/img/user.png"/>'>
                <div class="heada">${user.name}
                    (<a href='<c:url value="/logout"/>'>Выйти</a>)
                    / ${user.depart.name} / ${user.dpost.name}
                </div><div class="headb">/ Данные обращения</div>
            </div>
        </div>
        <div id="sidebar">
            <div class="sidebar_el">
                <a class="a_nazad" href='<c:url value="/specialist"/>'><div class="u_icon_nazad"></div>Назад</a>       
            </div>
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
                            </div>
                        </div>

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
                                        <div id="hexagon_ss">Решено</div>
                                        <div id="hexagon_pv">Завершен</div>
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

                        <c:if test="${zamenaP == 0}">
                            <c:if test="${reqs.size() gt 0}">
                                <c:forEach var="req" items="${reqs}">
                                    <div class="nazn"><img class="img_nazn" src='<c:url value="/css/img/docs.png"/>'><div class="ztext">Заявка на замену оборудования</div>
                                        <div class="zButtons_tab">
                                            <button type="submit" name="zOpen" class="ibuttz"/><img class="img_buttz" src='<c:url value="/css/img/open.png"/>'>Открыть</button>
                                            <c:if test="${incident.status.id == 3}">
                                                <button type="submit" name="zEdit" class="ibuttz"/><img class="img_buttz" src='<c:url value="/css/img/edit.png"/>'>Редактировать</button>
                                                <button type="submit" name="zDel" class="ibuttz"/><img class="img_buttz" src='<c:url value="/css/img/del.png"/>'>Удалить</button>
                                            </c:if>
                                        </div>
                                    </div>
                                    <input type="hidden" name="zId" value="${req.id}"/>
                                </c:forEach>
                            </c:if>
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

                            <c:when test="${incident.status.id == 7}"> 
                                <div class="dec_inc_otkl">
                                    <div class="man">${incident.specialist.name}</div>
                                    <div class="man_otd">${incident.specialist.depart.name} / ${incident.specialist.dpost.name}</div>
                                    <div class="dat_dec">${incident.dateClose} ${incident.timeClose}</div>
                                    ${incident.decision}
                                </div>
                            </c:when>
                        </c:choose>

                        <c:if test="${acts.size() gt 0}">
                            <c:forEach var="act" items="${acts}">
                                <div class="nazna"><img class="img_nazn" src='<c:url value="/css/img/docs.png"/>'><div class="ztext">Акт выполненных работ</div>
                                    <div class="zButtons_tab">
                                        <button type="submit" name="aOpen" class="ibuttz"/><img class="img_buttz" src='<c:url value="/css/img/open.png"/>'>Открыть</button>
                                    </div>
                                </div>
                                <input type="hidden" name="aId" value="${act.id}"/>
                            </c:forEach>
                        </c:if>

                        <c:choose>
                            <c:when test="${otmena == 1}">
                                <div class="commentar">
                                    <textarea placeholder="Укажите причину" name="textc" class="commEdit"/></textarea>
                                    <button type="submit" name="pDone" class="ibutt" onclick="return CancelInc(this.form)"/><img class="img_butt" src='<c:url value="/css/img/cancel.png"/>'>Отклонить</button>
                                    <input type="hidden" name="status" value="${incident.status.id}"/>
                                </div>
                            </c:when>
                            <c:when test="${zamenaP == 1}"> 
                                <div class="zamena_act">
                                    <div class="man">Заявка на замену оборудования</div>
                                    <div class="man_otd"></div>
                                    Член комиссии 1:
                                    <select name="komisId1" class="sel_komis">
                                        <c:forEach var="komis" items="${komises1}">
                                            <option value="${komis.login}" selected><c:out value="${komis.name}"/></option>
                                        </c:forEach>
                                    </select>
                                    <br>
                                    Член комиссии 2:
                                    <select name="komisId2" class="sel_komis">
                                        <c:forEach var="komis" items="${komises2}">
                                            <option value="${komis.login}" selected><c:out value="${komis.name}"/></option>
                                        </c:forEach>
                                    </select>
                                    <br>
                                    Причина замены:
                                    <br>
                                    <textarea placeholder="Причина замены" name="prich" class="editAddArea"/>${req.cause}</textarea>
                                    <br>
                                    Оборудование:
                                    <br>
                                    <textarea placeholder="Оборудование" name="hw_off" class="editAddArea"/>${req.zamenaOut}</textarea>
                                    <br>
                                    Оборудование на замену:
                                    <br>
                                    <textarea placeholder="Оборудование на замену" name="hw_on" class="editAddArea"/>${req.zamenaIn}</textarea>
                                    <br>
                                    <c:choose>
                                        <c:when test="${zEd == 0}">
                                            <button type="submit" name="zDone" class="ibutt" onclick="return SpecZamena(this.form)"/><img class="img_butt" src='<c:url value="/css/img/done.png"/>'>Генерация</button>
                                        </c:when>
                                        <c:when test="${zEd == 1}">
                                            <button type="submit" name="zEditDone" class="ibutt" onclick="return SpecZamena(this.form)"/><img class="img_butt" src='<c:url value="/css/img/done.png"/>'>Генерация</button>
                                            <input type="hidden" name="rId" value="${req.id}"/>
                                        </c:when>
                                    </c:choose>
                                    <button type="submit" name="CancelZ" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/cancel.png"/>'>Отмена</button>
                                </div>
                            </c:when>
                            <c:when test="${done == 1}">
                                <div class="commentar">
                                    <textarea placeholder="Решение" name="decision" class="commEdit"/></textarea>
                                    <button type="submit" name="Done" class="ibutt" onclick="return DoneInc(this.form)"/><img class="img_butt" src='<c:url value="/css/img/done.png"/>'>Готово</button>
                                    <div class="in_kb"><input type="checkbox" name="kb">Добавить в Базу Знаний</div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${incident.status.id == 2}">
                                        <button type="submit" name="InWork" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/in_work.png"/>'>В работу</button>
                                        <c:if test="${incident.zayavitel.login ne incident.manager.login}">
                                            <button type="submit" name="Close" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/cancel.png"/>'>Отклонить</button>
                                        </c:if>
                                    </c:when>

                                    <c:when test="${incident.status.id == 3}">
                                        <button type="submit" name="Doit" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/done.png"/>'>Выполнить</button>
                                        <c:if test="${reqs.size() == 0}">
                                            <button type="submit" name="Zamena" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/zamena.png"/>'>Замена оборудования</button>
                                        </c:if>
                                        <c:if test="${incident.zayavitel.login ne incident.manager.login}">
                                            <button type="submit" name="Close" class="ibutt"/><img class="img_butt" src='<c:url value="/css/img/cancel.png"/>'>Отклонить</button>
                                        </c:if>
                                    </c:when>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>

                        <div class="liniya"></div>

                        <c:if test="${incident.status.id gt 2}">
                            <c:choose>
                                <c:when test="${icomm == 1}">
                                    <div class="pan_chp">
                                        <input type="submit" value="" name="bComm" class="pan_mod_cv"/>
                                        <input type="submit" value="" name="bHist" class="pan_mod_h"/>
                                        <c:if test="${incident.status.id == 3}">
                                            <input type="submit" value="" name="bResh" class="pan_mod_b"/>
                                        </c:if>
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
                                                <c:choose>
                                                    <c:when test="${incident.status.id == 3}">
                                                        <textarea placeholder="Комментировать..." name="textcomm" class="commEdit_comm"/></textarea>
                                                        <button type="submit" name="bCommGo" class="ibutt" onclick="return Comment(this.form)"/><img class="img_butt" src='<c:url value="/css/img/comm_2.png"/>'>Отправить</button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        Сообщений нет
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </c:when>

                                <c:when test="${ihistory == 1}">
                                    <div class="pan_chp">
                                        <input type="submit" value="" name="bComm" class="pan_mod_c"/>
                                        <input type="submit" value="" name="bHist" class="pan_mod_hv"/>
                                        <c:if test="${incident.status.id == 3}">
                                            <input type="submit" value="" name="bResh" class="pan_mod_b"/>
                                        </c:if>
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

                                <c:when test="${iresh == 1}">
                                    <div class="pan_chp">
                                        <input type="submit" value="" name="bComm" class="pan_mod_c"/>
                                        <input type="submit" value="" name="bHist" class="pan_mod_h"/>
                                        <input type="submit" value="" name="bResh" class="pan_mod_bv"/>
                                    </div>
                                    <div class="comm_inc_mod">
                                        <c:choose>
                                            <c:when test="${rIncidents.size() > 0}">
                                                <table class="commgo">
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
                                    </div>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>