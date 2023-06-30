<!DOCTYPE html>
<%@ page import="gocharges.payment.enums.PaymentBillingType"%>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Atualizar cobrança</title>
</head>
<body>
    <div class="container my-8 d-flex justify-center w-25">
        <g:form class="card-body" name="payerForm" url="[controller: 'payment', action: 'update']">
            <h1 class="display-4">Atualizar cobrança</h1>

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
                <input class="form-control" type="date" name="dueDate" placeholder="dd/mm/aaaa"
                       value="${formatTagLib.isoDate(date: payment.dueDate)}"/><br>
            </div>

            <div class="form-group mb-3">
                <label class="mb-2">Valor</label>
                <input class="form-control" type="text" name="value" value="${payment.value}"/><br/>
            </div>

            <div class="d-flex justify-content-between">
                <a class="text-decoration-none btn btn-outline-secondary" href="/payment">
                    Cancelar
                </a>
                <button class="btn btn-gogreen" name="id" value="${payment.id}" type="submit" value="Cadastrar">Editar</button>
            </div>
        </g:form>
    </div>
</body>
</html>