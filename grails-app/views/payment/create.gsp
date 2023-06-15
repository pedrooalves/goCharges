<!DOCTYPE html>
<%@ page import="gocharges.payment.enums.PaymentBillingType"%>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Adicionar cobrança</title>
</head>
<body>
    <div class="container my-8 d-flex justify-center w-25">
        <g:form class="card-body" name="paymentForm" url="[controller: 'payment', action: 'save']">
            <h1 class="display-4">Nova cobrança</h1>

            <div class="form-group mb-4 d-flex justify-content-between">
                <div>
                    <label class="mb-2 fw-bold">Pagador</label>
                    <select class="form-select" name="payerId">
                        <option type="text" value="">Nenhum selecionado</option>
                        <g:each var="payer" in="${payerList}">
                            <option type="text" value="${payer.id}">${payer.name}</option>
                        </g:each>
                    </select>
                </div>

                <a class="d-flex align-items-center text-decoration-none btn btn-primary"
                   href="${createLink(controller:'payer', action:'create')}">
                    Novo Pagador
                </a>
            </div>

            <div class="form-group column my-4">
                <label class="mb-2 fw-bold">Forma de pagamento</label>
                <g:select name="billingType" class="form-select" data-constraint="select"
                          from="${PaymentBillingType.values()}" noSelection="${['': 'Formas de pagamento']}"
                          valueMessagePrefix="BillingType"
                />
            </div>

            <div class="form-group mb-3">
                <label class="mb-2">Data de vencimento</label>
                <input class="form-control" type="date" name="dueDate" value="" placeholder="dd/mm/aaaa"/><br>
            </div>

            <div class="form-group mb-3">
                <label class="mb-2">Valor</label>
                <input class="form-control" type="text" name="value" value=""/><br/>
            </div>

            <div class="navbar d-flex justify-content-space-between">
                <a href="/payment" class="text-decoration-none btn btn-outline-secondary" type="button">Cancelar</a>
                <button type="submit" class="btn bg-gogreen text-white ml-3">Cadastrar</button>
            </div>
        </g:form>
    </div>
</body>
</html>