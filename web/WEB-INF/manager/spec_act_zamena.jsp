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
        <title>Заявка на замену оборудования</title>
    </head>
    <body>
        <form action='<c:url value="/manager/spec_act_zamena"/>' method="POST">
            <input type="hidden" name="incaId" value="${inc_id}"/>
            <div class="noprint">
                <a href='<c:url value="/manager/incident_data?id=${inc_id}"/>'><div class="abutt"><img class="img_butt" src='<c:url value="/css/img/nazad.png"/>'>Назад</div></a>
                <a onclick="print()"><div class="abutt"><img class="img_butt" src='<c:url value="/css/img/print.png"/>'>Печать</div></a>
                <div class="zliniya"></div>
            </div>
            <div class="zagol">
                ЗАЯВКА НА ЗАМЕНУ ОБОРУДОВАНИЯ
            </div>
            <div class="gor_data"><div class="gorod">г. Омск</div><div class="data">${req.dateDoc}</div></div>
            <div class="komis">
                Комиссия в составе:
                <table class="table_komis">
                    <tbody>
                        <tr>
                            <td style="width: 33%" class="kfio">${req.komis1.name}</td>
                            <td style="width: 33%" class="kpost">${req.komis1.dpost.name}</td>
                            <td style="width: 34%" class="kdep">${req.komis1.depart.name}</td>
                        </tr>
                        <tr>
                            <td style="width: 33%" class="kfio">${req.komis2.name}</td>
                            <td style="width: 33%" class="kpost">${req.komis2.dpost.name}</td>
                            <td style="width: 34%" class="kdep">${req.komis2.depart.name}</td>
                        </tr>
                        <tr>
                            <td style="width: 33%" class="kfio">${req.specialist.name}</td>
                            <td style="width: 33%" class="kpost">${req.specialist.dpost.name}</td>
                            <td style="width: 34%" class="kdep">${req.specialist.depart.name}</td>
                        </tr>
                    </tbody>
                </table>
                определила, что устранение технического инцидента ИД ${inc_id} от ${inc_date}
                не представляется возможным по причине:
                <table class="table_komisa">
                    <tr>
                        <td class="kfio">${req.cause}</td>
                    </tr>
                </table>
                и установила необходимость в замене оборудования:
                <table class="table_komisa">
                    <tr>
                        <td class="kfio">${req.zamenaOut}</td>
                    </tr>
                </table>
                на оборудование со склада:
                <table class="table_komisa">
                    <tr>
                        <td class="kfio">${req.zamenaIn}</td>
                    </tr>
                </table>

                <table class="table_komis">
                    <tbody>
                        <tr>
                            <td style="width: 30%" class="kfio">${req.komis1.name}</td>
                            <td style="width: 50%" class="kpost">___________________</td>
                            <td style="width: 20%" class="kpost">___________________</td>
                        </tr>
                        <tr>
                            <td style="width: 30%" class="podp"></td>
                            <td style="width: 50%" class="podp">подпись</td>
                            <td style="width: 20%" class="podp">дата</td>
                        </tr>
                        <tr>
                            <td style="width: 30%" class="kfio">${req.komis2.name}</td>
                            <td style="width: 50%" class="kpost">___________________</td>
                            <td style="width: 20%" class="kpost">___________________</td>
                        </tr>
                        <tr>
                            <td style="width: 30%" class="podp"></td>
                            <td style="width: 50%" class="podp">подпись</td>
                            <td style="width: 20%" class="podp">дата</td>
                        </tr>
                        <tr>
                            <td style="width: 30%" class="kfio">${req.specialist.name}</td>
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
