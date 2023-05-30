<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Login</title>
</head>
<body>
    <g:if test="${flash}">
        <div class="${flash.type = FlashMessageType.SUCCESS ? 'alert alert-success' : 'alert alert-danger'}" role="alert">
            ${flash.message}
        </div>
    </g:if>

    <g:form method='POST' id='signUp' name='signUp' url="[controller: 'user', action: 'save']">
        <p>
            E-mail
            <input type='email' name='username' id='username' value="" />
        </p>
        <p>
            Senha
            <input type='password' name='password' id='password' value="" />
        </p>
        <p>
            Confirmar Senha
            <input type='password' name='confirmPassword' id='confirmPassword' value="" />
        </p>
        <a href="/"><input class="btn btn-outline-secondary" type="button" name="cancelButton" value="Cancelar" /></a>
        <input class="btn bg-gogreen text-white" type="submit" value="Cadastrar" />
    </g:form>
</body>
</html>