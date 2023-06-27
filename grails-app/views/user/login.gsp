<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Acessar sua conta</title>
</head>
<body>
    <div class="container my-5 default-form">
        <form action='${request.contextPath}/login/authenticate' method='POST' id='login' name='login'>

            <h1 class="mb-3" style="font-size: 2rem;">Acessar sua conta</h1>

            <div class="form-group">
                <label>Email</label>
                <input class="form-control" type="text" id="username" name="username"/>
            </div>

            <div class="form-group">
                <label>Senha</label>
                <input class="form-control" type="password" id="password" name="password"/>
            </div>

            <div class="form-buttons">
                <a href="/">
                    <input class="btn btn-outline-secondary" type="button" name="cancelButton" value="Cancelar"/>
                </a>
                <input class="btn bg-gogreen text-white" type="submit" value="Entrar"/>
            </div>
        </form>
    </div>
</body>
</html>