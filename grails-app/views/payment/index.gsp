<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Cadastro de Payment</title>
</head>
<body>
<div class="container col-12">

    <div class="card-body row">

        <g:if test="${showNewPaymentForm != false}">
            <div class="col-3">
                <div class="card mb-3 mt-3 p-1 bg-gogreen text-center text-white">
                    <h1 class="display-4">Nova Cobrança</h1>
                </div>

                <g:if test="${validation && validation.type == 'save'}">
                    <div class="${validation.success ? 'alert alert-success' : 'alert alert-danger'}" role="alert">
                        ${validation.message}
                    </div>
                </g:if>

                <g:form class="card-body mb-3" name="paymentForm" url="[controller: 'payment', action: 'save']">
                    <div class="form-group mb-3">
                        <label class="mb-2 fw-bold">CPF/CNPJ do Pagador</label>
                        <input class="form-control" type="text" name="payerCpfCnpj" value="" /><br/>
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
                        <input class="form-control" type="text" name="dueDate" value="" placeholder="dd/mm/aaaa" /><br >
                    </div>

                    <div class="form-group mb-3">
                        <label class="mb-2">Valor </label>
                        <input class="form-control" type="text" name="value" value="" /><br/>
                    </div>

                    <div class="navbar d-flex justify-content-space-between">
                        <a href="${createLink(action:'index', controller:'payment')}"><input class="btn btn-outline-secondary" type="button" name="buttonCancelar" value="Cancelar" /></a>
                        <input class="btn bg-gogreen text-white" type="submit" name="buttonRegister" value="Cadastrar" />
                    </div>
                </g:form>
            </div>
        </g:if>

        <div class="col 6">
            <div class="mt-3 mb-1 p-2 bg-secondary text-center text-white rounded">
                <h1>Cobranças</h1>
            </div>

            <a href="${createLink(action:'showForm', controller:'payment')}"><button class="btn btn-outline-primary mb-2">Novo</button></a>

            <g:if test="${validation && (validation.type == 'delete' || validation.type == 'update' ||
            (validation.type == 'save' && validation.success))}">
                <div class="${validation.success ? 'alert alert-success' : 'alert alert-danger'}" role="alert">
                    ${validation.message}
                </div>
            </g:if>

            <div class="row col-11">
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
                    <li class="custom-list-item col">${payment.payer.cpfCnpj}</li>

                    <g:form name="updateButton" url="[controller: 'payment', action: 'edit']" method="POST">
                        <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-dark ml-3">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                            </svg>
                        </button>
                    </g:form>

                    <g:form name="deleteButton" url="[controller: 'payment', action: 'delete']" method="POST">
                        <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-danger ml-3">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
                                <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                            </svg>
                        </button>
                    </g:form>
                </ul>
            </g:each>
        </div>
    </div>
</div>
</body>
</html>