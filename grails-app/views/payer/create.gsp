<!DOCTYPE html>
<%@ page import="shared.enums.State"%>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Adicionar pagador</title>
</head>
<body>
    <div class="container my-8 d-flex justify-center col-6 main-container">
        <g:render template="/payer/templates/create/form" model="[formTitle: 'Adicionar Pagador', controller: 'payer', action: 'save']"/>
    </div>
</body>
</html>