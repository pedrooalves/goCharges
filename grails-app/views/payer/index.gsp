<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Listagem de Payer</title>
    <asset:javascript src="alert.js" defer="true"/>
</head>
<body>
<g:form name="payerForm" url="[controller: 'payer', action: 'register']">
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