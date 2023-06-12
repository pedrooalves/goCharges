<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Nova cobrança</title>
</head>
<body>
<div class="container my-8 d-flex justify-center w-50">
    <g:form class="card-body" name="paymentForm" url="[controller: 'payment', action: 'save']">
        <h1 class="display-4">Nova Cobrança</h1>

        <div class="form-group mb-3">
            <label class="mb-2 fw-bold">CPF/CNPJ do Pagador</label>
            <input class="form-control" type="text" name="payerCpfCnpj" value=""/><br/>
        </div>

        <div class="form-group column mb-3">
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

        <input type="submit" name="buttonCadastro" value="Cadastrar"/>
        <input type="button" name="buttonCancelar" value="Cancelar"/>
    </g:form>
</div>
</body>
</html>