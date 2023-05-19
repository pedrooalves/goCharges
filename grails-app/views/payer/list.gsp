<!doctype html>
<html lang="en">
<head>
    <meta name="layout" content="main" />
    <title>Listagem de Payer</title>
</head>
<body>
    <g:each var="payer" in="${payers}" >
        ${payer.name}
        ${payer.email}
        ${payer.mobilePhone}
        ${payer.cpfCnpj}
        ${payer.address}
    </g:each>
</body>
</html>