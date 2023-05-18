<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Listagem de Customer</title>
</head>
<body>

    <g:each var="${customer}" in="${customers}" >
        ${customer.name}
        ${customer.email}
    </g:each>
</body>
</html>