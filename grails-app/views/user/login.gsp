<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Entrar</title>
</head>
<body>
    <form action='${request.contextPath}/login/authenticate' method='POST' id='login' name='login'>
        <p>
            <label>E-mail</label>
            <input type='text' name='username' id='username' />
        </p>
        <p>
            <label>Senha</label>
            <input type='password' name='password' id='password' />
        </p>
        <input type="submit" value="Entrar" />
    </form>
</body>
</html>