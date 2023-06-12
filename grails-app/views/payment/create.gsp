<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Nova cobrança</title>
</head>
<body>
<div class="container my-8 d-flex justify-center w-25">
    <g:form class="card-body" name="paymentForm" url="[controller: 'payment', action: 'save']">
        <h1 class="display-4">Nova Cobrança</h1>

        <div class="form-group mb-4 d-flex justify-content-between">
            <div>
                <label class="mb-2 fw-bold">Pagador</label>

                <select class="form-select" name="payerCpfCnpj">
                    <option type="text" value="">Nenhum selecionado</option>
                    <g:each var="payer" in="${payerList}">
                        <option type="text" value="${payer.cpfCnpj}">${payer.name}</option>
                    </g:each>
                </select><br/>
            </div>

            <a class="d-flex align-items-center text-decoration-none btn btn-primary" href="${createLink(controller:'payer', action:'create')}">
                Novo Pagador
            </a>
        </div>

        <div class="form-group column my-4">
            <label class="mb-2 fw-bold">Tipo de Recebimento Aceito</label>
            <select class="form-select" name="billingType">
                <option type="text" value="BANK_SLIP">Boleto</option>
                <option type="text" value="DEBIT_CARD">Cartão de Débito</option>
                <option type="text" value="PIX">Pix</option>
            </select><br/>
        </div>

        <div class="form-group mb-3">
            <label class="mb-2">Data de Vencimento</label>
            <input class="form-control" type="date" name="dueDate" value="" placeholder="dd/mm/aaaa"/><br>
        </div>

        <div class="form-group mb-3">
            <label class="mb-2">Valor </label>
            <input class="form-control" type="text" name="value" value=""/><br/>
        </div>

        <input class="btn btn-primary" type="submit" name="buttonCadastro" value="Cadastrar"/>
        <a class="text-decoration-none btn btn-primary" href="${createLink(controller:'payment', action:'index')}">
            Cancelar
        </a>
    </g:form>
</div>
</body>
</html>