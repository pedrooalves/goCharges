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
<div class="container my-8 d-flex justify-center">

    <div class="container w-50">
        <h1 class="display-4">Login</h1>

            <g:form class="card-body" name="customerForm" url="[controller: 'customer', action: 'register']">

                <div class="form-group">
                    <label>Email</label> <input class="form-control" type="email" name="email" value="" />
                </div>

                <div class="form-group">
                    <label>Senha</label> <input class="form-control" type="text" name="name" value="" />
                </div>

                <input type="submit" name="buttonCadastro" value="Cadastrar" />
                <input type="button" name="buttonCancelar" value="Cancelar" />
            </g:form>
    </div>
</div>
</body>
</html>