<!DOCTYPE html>
<%@ page import="shared.enums.State"%>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Adicionar pagador</title>
</head>
<body>
    <div class="container my-8 d-flex justify-center col-6 main-container js-payer-create">
        <g:render class="" template="/templates/personForm" model="[formTitle: 'Adicionar Pagador', controller: 'payer', action: 'create']"/>
    </div>
</body>
</html>