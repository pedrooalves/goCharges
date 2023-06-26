<!DOCTYPE html>
<%@ page import="gocharges.payment.enums.PaymentStatus"%>
<%@ page import="gocharges.payment.enums.PaymentBillingType"%>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <title>Listagem de pagadores</title>
    <asset:javascript src="goCharges.js" />
</head>
<body>
    <div class="container col-12" style="height: 100%">
        <div class="card-body row">
            <div class="col 6">
                <div class="mt-3 mb-1 p-2 px-4 bg-secondary text-white rounded d-flex justify-content-between align-items-center">
                    <h1>Pagadores</h1>

                    <a class="d-flex justify-content-center text-decoration-none" href="${createLink(controller:'payer', action:'create')}">
                        <button class="btn btn-gogreen">Adicionar Pagador</button>
                    </a>
                </div>

                <nav class="navbar navbar-expand navbar-light bg-light col mb-3">
                    <g:form class="d-flex justify-content-center col" name="status" url="[controller: 'payment', action: 'index']" method="POST">
                        <g:select name="billingType" data-constraint="select"
                                  from="${PaymentBillingType.values()}" noSelection="${['': 'Forma de pagamento']}"
                                  optionValue="name"/>

                        <g:select class="ml-3" name="status" data-constraint="select"
                                  from="${PaymentStatus.values()}" noSelection="${['': 'Forma de pagamento']}"
                                  optionValue="name"/>

                        <select class="ml-3" name="payerId">
                            <option value="">Selecione um pagador</option>
                            <g:each var="payer" in="${payerList}">
                                <option value="${payer.id}">${payer.name}</option>
                            </g:each>
                        </select><br/>

                        <select class="ml-3" name="deletedOnly">
                            <option value="">Exibir somente cobranças ativas</option>
                            <option value="true">Exibir somente cobranças inativas</option>
                            <option value="false">Exibir todas as cobranças</option>
                        </select><br/>

                        <button class="btn btn-outline-primary ml-3">Buscar</button>
                    </g:form>
                </nav>

                <g:render template="/payer/templates/table" model="${[payerList: payerList]}"/>

            </div>
        </div>
    </div>
</body>
</html>