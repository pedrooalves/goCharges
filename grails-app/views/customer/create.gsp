<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Dados comerciais</title>
</head>
<body>
    <div class="container my-8 d-flex justify-center col-6 main-container js-customer-create">
        <g:render template="/templates/personForm" model="[formTitle: 'Dados Comerciais', controller: 'customer', action: 'update']"/>
    </div>
</body>
</html>