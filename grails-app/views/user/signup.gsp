<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Criar conta</title>
</head>
<body>
    <div class="container my-5" style="width: 20%;">
        <g:form method='POST' id='signUp' name='signUp' url="[controller: 'user', action: 'save']">

            <h1 class="mb-3" style="font-size: 2rem;">Criar conta</h1>

            <div class="form-group">
                <label>Email</label>
                <input class="form-control" type="text" id="username" name="username"/>
            </div>

            <div class="form-group">
                <label>Senha</label>
                <input class="form-control" type="password" id="password" name="password"/>
            </div>

            <div class="form-group">
                <label>Confirmar senha</label>
                <input class="form-control" type="password" id="confirmPassword" name="confirmPassword"/>
            </div>

            <div>
                <a href="/">
                    <input class="btn btn-outline-secondary" type="button" name="cancelButton" value="Cancelar"/>
                </a>

                <input class="btn bg-gogreen text-white" type="submit" value="Cadastrar"/>
            </div>
        </g:form>
    </div>
</body>
</html>