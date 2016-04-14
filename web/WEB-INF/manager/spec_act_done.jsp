<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/docs.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/css/incident_data.css"/>'>
        <link rel="stylesheet" media="print" type="text/css" href='<c:url value="/css/print.css"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Акт выполненных работ</title>
    </head>
    <body>
        <form action='<c:url value="/manager/spec_act_done"/>' method="POST">
            <input type="hidden" name="incaId" value="${inc_id}"/>
            <div class="noprint">
                <a href='<c:url value="/manager/incident_data?id=${inc_id}"/>'><div class="abutt"><img class="img_butt" src='<c:url value="/css/img/nazad.png"/>'>Назад</div></a>
                <a onclick="print()"><div class="abutt"><img class="img_butt" src='<c:url value="/css/img/print.png"/>'>Печать</div></a>
                <div class="zliniya"></div>
            </div>
            <div class="zagol">
                АКТ ВЫПОЛНЕННЫХ РАБОТ
            </div>
            <div class="gor_data"><div class="gorod">г. Омск</div><div class="data">${act.dateDoc}</div></div>
            <div class="komis">
                Я, руководитель отдела информационного и технического обеспечения ${act.specialist.name}<br>
                составил настоящим акт о том, что в соответствии с поступившей в отдел заявки от сотрудника ${act.komis1.name}<br>
                в виде электронного обращения ИД ${inc_id} от ${inc_date} выполнены и приняты работы по устранению технического инцидента.<br>

                <br>
                Решение инцидента:
                <table class="table_komiso">
                    <tr>
                        <td class="kfio">${act.cause}</td>
                    </tr>
                </table>

                Работы произвел:
                <table class="table_komiso">
                    <tbody>
                        <tr>
                            <td style="width: 30%" class="kfio">${act.specialist.name}</td>
                            <td style="width: 50%" class="kpost">___________________</td>
                            <td style="width: 20%" class="kpost">___________________</td>
                        </tr>
                        <tr>
                            <td style="width: 30%" class="podp"></td>
                            <td style="width: 50%" class="podp">подпись</td>
                            <td style="width: 20%" class="podp">дата</td>
                        </tr>
                    </tbody>
                </table>

                Заявитель:
                <table class="table_komiso">
                    <tbody>
                        <tr>
                            <td style="width: 30%" class="kfio">${act.komis1.name}</td>
                            <td style="width: 50%" class="kpost">___________________</td>
                            <td style="width: 20%" class="kpost">___________________</td>
                        </tr>
                        <tr>
                            <td style="width: 30%" class="podp"></td>
                            <td style="width: 50%" class="podp">подпись</td>
                            <td style="width: 20%" class="podp">дата</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>
