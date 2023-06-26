<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Atualizar dados</title>
</head>
<body>
<div class="container my-8 d-flex justify-center">
    <div class="container w-50">
        <h1 class="display-4">Atualizar dados</h1>

        <g:form class="card-body" name="customerForm" url="[controller: 'customer', action: 'update']" method="PUT">
            <div class="form-group">
                <label>Nome</label> <input class="form-control" type="text" name="name" value=${customer.name}>
            </div>

            <div class="form-group">
                <label>Email</label> <input class="form-control" type="email" name="email" value=${customer.email}>
            </div>

            <div class="form-group">
                <label>CPF / CNPJ</label> <input class="form-control" type="text" name="cpfCnpj"
                                                 value=${customer.cpfCnpj}>
            </div>

            <div class="form-group">
                <label>Celular</label> <input class="form-control" type="text" name="mobilePhone"
                                              value=${customer.mobilePhone}>
            </div>

            <div class="form-group">
                <label>Endereço</label> <input class="form-control" type="text" name="address"
                                               value=${customer.address}>
            </div>

            <button type="submit" name="id" value="Editar" class="btn bg-gogreen text-white ml-3">
                Editar
            </button>
            <input type="button" name="buttonCancelar" value="Cancelar"/>
        </g:form>
    </div>
</div>
</body>
</html>