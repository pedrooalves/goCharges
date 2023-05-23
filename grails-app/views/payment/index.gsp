<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Cadastro de Payment</title>
</head>
<body>
<div class="container col-12">

    <div class="card-body row">

        <g:form name="paymentForm" url="[controller: 'payment', action: 'save']">
            <g:if test="${validation != null && validation.type.equals('save')}">
                <div class="${validation.success ? 'alert alert-success' : 'alert alert-danger'}" role="alert">
                    ${validation.message}
                </div>
            </g:if>
            CPF/CNPJ do Pagador <input type="text" name="payerCpfCnpj" value="" /><br/>
            Tipo de Recebimento Aceito
            <select class="form-select" name="billingType">
                <option type="text" value="BOLETO">Boleto</option>
                <option type="text" value="DEBIT_CARD">Cartão de Débito</option>
                <option type="text" value="PIX">Pix</option>
            </select>
            <br>
            Data de Vencimento <input type="text" name="dueDate" value="" /><br >
            Valor <input type="text" name="value" value="" /><br/>

            <input type="button" name="buttonCancelar" value="Cancelar" />
            <input type="submit" name="buttonCadastro" value="Cadastrar" />
        </g:form>

        <div class="col 6">
            <div class="mt-3 mb-1 p-2 bg-secondary text-center text-white rounded">
                <h1>Cobranças</h1>
            </div>

            <g:if test="${validation != null && (validation.type.equals('delete') || validation.type.equals('update'))}">
                <div class="${validation.success ? 'alert alert-success' : 'alert alert-danger'}" role="alert">
                    ${validation.message}
                </div>
            </g:if>

            <div class="row col-12">
                <h1 class="col-3 fw-bold text-center">Tipo de Recebimento</h1>
                <h1 class="col fw-bold text-center">Valor</h1>
                <h1 class="col fw-bold text-center">Data de Vencimento</h1>
                <h1 class="col fw-bold text-center">Status</h1>
                <h1 class="col fw-bold text-center">Pagador</h1>

            </div>

            <g:each var="payment" in="${payments}">
                <ul class="list-group list-group-horizontal mb-1 mb-1">
                    <li class="custom-list-item col-3">${payment.billingType}</li>
                    <li class="custom-list-item col">${payment.value}</li>
                    <li class="custom-list-item col">${payment.dueDate}</li>
                    <li class="custom-list-item col">${payment.status}</li>
                    <li class="custom-list-item col">${payment.payerId}</li>
                </ul>
            </g:each>
        </div>
    </div>
</div>
</body>
</html>