<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Cadastro de Customer</title>
</head>
<body>
    <div class="container my-3">
        <h1 class="display-4">Cadastrar</h1>

        <g:form class="card-body" name="customerForm" url="[controller: 'customer', action: 'save']">

            <div class="form-group">
                <label>Nome</label> <input class="form-control" type="text" name="name" value="" />
            </div>

            <div class="form-group">
                <label>Email</label> <input class="form-control" type="email" name="email" value="" />
            </div>

            <div class="form-group">
                <label>Celular</label> <input class="form-control" type="text" name="mobilePhone" value="" />
            </div>

            <div class="form-group">
                <label>CPF / CNPJ</label> <input class="form-control" type="text" name="cpfCnpj" value="" />
            </div>

            <div class="form-group">
                <label>Endereço</label> <input class="form-control" type="text" name="address" value="" />
            </div>

            <g:if test="${validation != null}">
                <div class="alert alert-danger" role="alert">
                    ${validation.message}
                </div>
            </g:if>

            <input type="submit" name="buttonCadastro" value="Cadastrar" />
            <input type="button" name="buttonCancelar" value="Cancelar" />
        </g:form>
    </div>
</body>
</html>