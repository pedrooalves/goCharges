<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Login</title>
</head>
<body>
    <g:form method='POST' id='signUp' name='signUp' url="[controller: 'user', action: 'save']">
        <g:if test="${validation && (validation.type == 'delete' || validation.type == 'update' ||
                    validation.type == 'save')}">
            <div class="${validation.success ? 'alert alert-success' : 'alert alert-danger'}" role="alert">
                ${validation.message}
            </div>
        </g:if>

        <div>
            <label>E-mail</label>
            <input type='email' name='username' id='username' value=""/>
        </div>

        <div>
            <label>Senha</label>
            <input type='password' name='password' id='password' value=""/>
        </div>

        <div>
            <label>Confirmar Senha</label>
            <input type='password' name='confirmPassword' id='confirmPassword' value=""/>
        </div>

        <div>
            <a href="/"><input class="btn btn-outline-secondary" type="button" name="cancelButton" value="Cancelar"/></a>
            <input class="btn bg-gogreen text-white" type="submit" value="Cadastrar"/>
        </div>
    </g:form>
</body>
</html>