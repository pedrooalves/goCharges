<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Cadastro de Customer</title>
</head>
<body>
    <div class="container my-3 col-12 row justify-content-between">
        <section class="col-4">
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
                    <div class="${validation.success ? 'alert alert-success' : 'alert alert-danger'}" role="alert">
                        ${validation.message}
                    </div>
                </g:if>
                <input type="submit" name="buttonCadastro" value="Cadastrar" />
                <input type="button" name="buttonCancelar" value="Cancelar" />
            </g:form>
        </section>

        <section class="col-6">
            <div class="card-body">

                <div class="mt-3 mb-1 p-2 bg-secondary text-center text-white rounded">
                    <h1>Customers</h1>
                </div>

                <div class="row col-11">
                    <h1 class="col fw-bold text-center">Nome</h1>
                    <h1 class="col fw-bold text-center">E-mail</h1>
                    <h1 class="col fw-bold text-center">Cpf ou Cnpj</h1>
                    <h1 class="col fw-bold text-center">Celular</h1>
                    <h1 class="col fw-bold text-center">Endereço</h1>
                </div>

                <g:each var="customer" in="${customers}">
                    <ul class="list-group list-group-horizontal mb-1 mb-1">
                        <li class="custom-list-item col">${customer.name}</li>
                        <li class="custom-list-item col">${customer.email}</li>
                        <li class="custom-list-item col">${customer.mobilePhone}</li>
                        <li class="custom-list-item col">${customer.CpfCnpj}</li>
                        <li class="custom-list-item col">${customer.address}</li>

                        <g:form name="customerUpdate" url="[controller: 'customer', action: 'edit']" method="GET">
                            <button type="submit" name="id" value="Editar" class="btn btn-outline-dark ml-3">
                                <asset:image src="pencil.svg"/>
                            </button>
                        </g:form>

                        <g:form name="customerDelete" url="[controller: 'customer', action: 'delete']" method="DELETE">
                            <button type="submit" name="id" value="${customer.id}" class="btn btn-outline-danger ml-3">
                                <asset:image src="trash.svg"/>
                            </button>
                        </g:form>
                    </ul>
                </g:each>
            </div>
        </section>
    </div>
</body>
</html>