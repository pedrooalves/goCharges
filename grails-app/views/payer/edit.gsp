<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Listagem de Payer</title>
</head>
<body>

<div class="container d-flex justify-content-center mb-3">
    <div>
        <div class="card mb-3 mt-3 p-5 bg-gogreen text-center text-white">
            <h1 class="display-4">Editar Pagador</h1>
        </div>

        <g:if test="${flash?.message}">
            <div class="${flash.type.toString() == 'SUCCESS' ? 'alert alert-success' : 'alert alert-danger'}" role="alert">
                ${flash.message}
            </div>
        </g:if>

        <div class="card">
            <g:form class="card-body mb-3" name="payerForm" url="[controller: 'payer', action: 'update']">
                <div class="form-group mb-3">
                    <label class="mb-2 fw-bold">Nome</label>
                    <input class="form-control" type="text" name="name" value="${payer.name}" /><br/>
                </div>

                <div class="form-group mb-3">
                    <label class="mb-2">E-mail</label>
                    <input class="form-control" type="email" name="email" value="${payer.email}" /><br/>
                </div>

                <div class="form-group mb-3">
                    <label class="mb-2">CPF / CNPJ </label>
                    <input class="form-control" type="text" readonly name="cpfCnpj" value="${payer.cpfCnpj}" /><br >
                </div>

                <div class="form-group mb-3">
                    <label class="mb-2">Celular</label>
                    <input class="form-control" type="text" name="mobilePhone" value="${payer.mobilePhone}" /><br/>
                </div>

                <div class="form-group mb-3">
                    <label class="mb-2">Endereco </label>
                    <input class="form-control" type="text" name="address" value="${payer.address}" /><br/>
                </div>

                <div class="navbar d-flex justify-content-space-between">
                    <a href="/payer"><input href="/payer" class="btn btn-outline-secondary" type="button" name="buttonCancelar" value="Cancelar" /></a>
                    <button type="submit" name="id" value="${payer.id}" class="btn bg-gogreen text-white ml-3">
                        Salvar
                    </button>
                </div>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>