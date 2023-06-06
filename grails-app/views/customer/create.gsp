<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Dados comerciais</title>
</head>
<body>
    <div class="container my-8 d-flex justify-center w-50">

        <g:form class="card-body" name="customerForm" url="[controller: 'customer', action: 'update']">
            <h1 class="display-4">Dados Comerciais</h1>

            <g:if test="${validation != null}">
                <div class="${validation.success ? 'alert alert-success' : 'alert alert-danger'}" role="alert">
                    ${validation.message}
                </div>
            </g:if>

            <div class="form-group">
                <label>Nome</label>
                <input class="form-control" type="text" name="name" value="" />
            </div>

            <div class="form-group">
                <label>E-mail</label>
                <input class="form-control" readonly type="email" name="email" value=${userEmail} />
            </div>

            <div class="form-group">
                <label>Celular</label>
                <input class="form-control" type="text" name="mobilePhone" value="" />
            </div>

            <div class="form-group">
                <label>CPF / CNPJ</label>
                <input class="form-control" type="text" name="cpfCnpj" value="" />
            </div>

            <h4 class="font-weight-bold mb-2">Endereço</h4>

            <div class="form-group">
                <label class="mb-1">CEP</label>
                <input class="form-control col-6" type="text" pattern="\d{5}-?\d{3}"  name="postalCode" placeholder="00000-000" value="" />
            </div>

            <div class="form-row mb-3">
                <div class="col">
                    <label class="mb-1">Rua</label>
                    <input class="form-control" type="text" name="address" placeholder="Ex: Rua Maria de Souza" value="" />
                </div>
                <div class="col">
                    <label class="mb-1">Número</label>
                    <input class="form-control" type="text" name="addressNumber" placeholder="00000" value="" />
                </div>
            </div>

            <div class="form-row mb-3">
                <div class="col">
                    <label class="mb-1">Complemento</label>
                    <input class="form-control" type="text" name="complement" placeholder="Ex: Bloco 00, Apartamento 00" value="" />
                </div>
                <div class="col">
                    <label class="mb-1">Bairro</label>
                    <input class="form-control" type="text" name="province" placeholder="Ex: Jardim Campos Elíseos" value="" />
                </div>
            </div>

            <div class="form-row mb-3">
                <div class="col">
                    <label class="mb-1">Cidade</label>
                    <input class="form-control" type="text" name="city" placeholder="Ex: Salvador" value="" />
                </div>
                <div class="col">
                    <label class="mb-1">Estado</label>
                    <input class="form-control" type="text" name="state" placeholder="Ex: Bahia" value="" />
                </div>
            </div>

            <input type="submit" name="buttonCadastro" value="Cadastrar" />
            <input type="button" name="buttonCancelar" value="Cancelar" />
        </g:form>
    </div>
    </div>
</body>
</html>