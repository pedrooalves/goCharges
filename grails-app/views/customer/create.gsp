<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Dados comerciais</title>
</head>
<body>
<g:if test="${validation != null}">
    <p>${validation.message}</p>
</g:if>
<div class="container my-8 d-flex justify-center w-50">
    <g:form class="card-body" name="customerForm" url="[controller: 'customer', action: 'update']">
        <h1 class="display-4">Dados Comerciais</h1>

        <div class="form-group">
            <label>Nome</label> <input class="form-control" type="text" name="name" value=""/>
        </div>

        <div class="form-group">
            <label>Email</label> <input class="form-control" readonly type="email" name="email" value=${userEmail}>
        </div>

        <div class="form-group">
            <label>Celular</label> <input class="form-control" type="text" name="mobilePhone" value=""/>
        </div>

        <div class="form-group">
            <label>CPF / CNPJ</label> <input class="form-control" type="text" name="cpfCnpj" value=""/>
        </div>

        <div class="form-group">
            <label>Endereço</label> <input class="form-control" type="text" name="address" value=""/>
        </div>

        <g:if test="${validation != null}">
            <div class="${validation.success ? 'alert alert-success' : 'alert alert-danger'}" role="alert">
                ${validation.message}
            </div>
        </g:if>
        <input type="submit" name="buttonCadastro" value="Cadastrar"/>
        <input type="button" name="buttonCancelar" value="Cancelar"/>
    </g:form>
</div>
</div>
</body>
</html>