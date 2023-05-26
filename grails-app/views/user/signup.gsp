<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Login</title>
</head>
<body>
<g:form method='POST' id='signUp' name='signUp' url="[controller: 'user', action: 'save']">
    <p>
        E-mail
        <input type='email' name='email' id='username' />
    </p>
    <p>
        Senha
        <input type='password' name='password' id='password' />
    </p>
    <a href="/"><input class="btn btn-outline-secondary" type="button" name="cancelButton" value="Cancelar" /></a>
    <input class="btn bg-gogreen text-white" type="submit" value="Entrar" />
</g:form>
</body>
</html>