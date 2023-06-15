<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main" />
    <title>Minha Conta</title>
</head>
<body>
    <div class="container d-flex justify-content-center mb-3">
        <div>
            <div class="card mb-3 mt-3 p-5 bg-gogreen text-center text-white">
                <h1 class="display-4">Editar dados</h1>
            </div>

            <div class="card">
                <g:form class="card-body mb-3" name="userForm" url="[controller: 'user', action: 'update']">
                    <div class="form-group mb-3">
                        <label class="mb-2 fw-bold">Alterar e-mail</label>
                        <input class="form-control" type="email" name="username" value="${user.username}" /><br/>
                    </div>

                    <div class="form-group mb-3">
                        <label class="mb-2">Senha atual</label>
                        <input class="form-control" type="password" name="currentPassword" value="" /><br/>
                    </div>

                    <div class="form-group mb-3">
                        <label class="mb-2">Nova senha</label>
                        <input class="form-control" type="password" name="password" value="" /><br >
                    </div>

                    <div class="form-group mb-3">
                        <label class="mb-2">Confirmar nova senha</label>
                        <input class="form-control" type="password" name="confirmPassword" value="" /><br/>
                    </div>

                    <div class="navbar d-flex justify-content-space-between">
                        <a href="/"><input href="/" class="btn btn-outline-secondary" type="button" name="buttonCancelar" value="Cancelar" /></a>
                        <button type="submit" name="id" value="${user.id}" class="btn bg-gogreen text-white ml-3">
                            Salvar
                        </button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</body>
</html>