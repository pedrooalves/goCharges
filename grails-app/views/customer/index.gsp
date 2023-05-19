<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Cadastro de Customer</title>
</head>
<body>
    <g:if test="${validation != null}">
        <p>${validation.message}</p>
    </g:if>
    <g:form name="customerForm" url="[controller: 'customer', action: 'register']">
        Nome <input type="text" name="name" value="" /><br/>
        Email <input type="email" name="email" value="" /><br/>
        Celular <input type="text" name="mobilePhone" value="" /><br/>
        CPF / CNPJ <input type="text" name="cpfCnpj" value="" /><br >
        Endereco <input type="text" name="address" value="" /><br/>

        <input type="submit" name="buttonCadastro" value="Cadastrar" />
        <input type="button" name="buttonCancelar" value="Cancelar" />
    </g:form>
</body>
</html>