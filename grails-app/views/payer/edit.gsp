<!DOCTYPE html>
<%@ page import="shared.enums.State"%>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Atualizar pagador</title>
</head>
<body>
    <div class="container my-8 d-flex justify-center col-6 main-container">
        <g:render class="" template="/templates/personForm" model='[formTitle: "Atualizar Pagador", controller: "payer", action: "update", payerId: "${person.id}"]'/>
    </div>
</body>
</html>