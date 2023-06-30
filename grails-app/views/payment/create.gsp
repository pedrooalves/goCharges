<!DOCTYPE html>
<%@ page import="gocharges.payment.enums.PaymentBillingType"%>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Adicionar cobrança</title>
</head>
<body>
    <div class="container d-flex justify-center create-form main-container">
        <g:form class="card-body" name="paymentForm" url="[controller: 'payment', action: 'save']">
            <h1 class="h1">Nova cobrança</h1>

            <div class="form-group mb-4 d-flex justify-content-between">
                <div class="col-8 pl-0">
                    <label class="mb-2 fw-bold">Pagador</label>
                    <br>
                    <select class="form-select col" name="payerId">
                        <option type="text" value="">Nenhum selecionado</option>
                        <g:each var="payer" in="${payerList}">
                            <option type="text" value="${payer.id}">${payer.name}</option>
                        </g:each>
                    </select>
                </div>
                <div class="col-2 px-0 mr-3">
                    <a class="d-flex align-items-center justify-content-center text-center text-decoration-none btn btn-gogreen "
                       href="${createLink(controller:'payer', action:'create')}">
                        Novo Pagador
                    </a>
                </div>
            </div>

            <div class="form-group column my-4">
                <div class="col-8 pl-0">
                    <label class="mb-2 fw-bold">Forma de pagamento</label>
                    <br>
                    <g:select name="billingType" class="form-select col" data-constraint="select"
                              from="${PaymentBillingType.values()}" noSelection="${['': 'Formas de pagamento']}"
                              valueMessagePrefix="BillingType"/>
                </div>
            </div>

            <div class="form-group mb-3">
                <label class="mb-2">Data de vencimento</label>
                <input class="form-control" type="date" name="dueDate" value="" placeholder="dd/mm/aaaa"/><br>
            </div>

            <div class="form-group mb-3">
                <label class="mb-2">Valor</label>
                <input class="form-control" type="text" name="value" value=""/><br/>
            </div>

            <div class="d-flex justify-content-between">
                <a class="text-decoration-none btn btn-outline-secondary" href="/payment">
                    Cancelar
                </a>
                <input class="btn btn-gogreen" type="submit" value="Cadastrar"/>
            </div>
        </g:form>
    </div>
</body>
</html>