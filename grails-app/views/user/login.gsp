<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Login</title>
</head>
<body>
    <g:form action='${request.contextPath}/login/authenticate' method='POST' id='frmLogar' name='frmLogar'>
        <g:if test="${flash.message}">
            <div class="${flash.type == FlashMessageType.SUCCESS ? 'alert alert-success' : alert alert-danger}" role="alert">
                ${flash.message}
            </div>
        </g:if>

        <p>
            E-mail
            <input type='text' name='username' id='username' />
        </p>
        <p>
            Senha
            <input type='password' name='password' id='password' />
        </p>
        <input type="submit" value="Entrar" />
    </g:form>
</body>
</html>