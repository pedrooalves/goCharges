<!DOCTYPE html>
<%@ page import="gocharges.payment.enums.PaymentBillingType"%>
<%@ page import="gocharges.payment.enums.PaymentStatus"%>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Listagem de cobranças</title>

    <asset:javascript src="tableController.js"/>
    <asset:javascript src="modalController.js"/>
</head>
<body>
    <div class="container col-12 js-list-container js-container">
        <div class="card-body row">
            <div class="col">
                <g:render template="/modal/templates/confirmReceivedInCashModal"/>
                <g:render template="/modal/templates/confirmPaymentDeleteModal"/>

                <div class="mt-3 mb-1 p-2 px-4 bg-secondary text-white rounded domain-title">
                    <h1>Cobranças</h1>

                    <a class="d-flex justify-content-center text-decoration-none" href="${createLink(controller:'payment', action:'create')}">
                        <button class="btn btn-gogreen">Adicionar cobrança</button>
                    </a>
                </div>

                <nav class="navbar navbar-expand navbar-light bg-light col mb-3">
                    <g:form class="col filterList" name="status" url="[controller: 'payment', action: 'index']" method="POST">
                        <g:select class="col" name="billingType" data-constraint="select"
                                  from="${PaymentBillingType.values()}" noSelection="${['': 'Forma de pagamento']}"
                                  optionValue="name"/>

                        <g:select class="col filter" name="status" data-constraint="select"
                                  from="${PaymentStatus.values()}" noSelection="${['': 'Forma de pagamento']}"
                                  optionValue="name"/>

                        <select class="col filter" name="payerId">
                            <option value="">Selecione um pagador</option>
                            <g:each var="payer" in="${payerList}">
                                <option value="${payer.id}">${payer.name}</option>
                            </g:each>
                        </select><br/>

                        <select class="col filter" style="" name="deletedOnly">
                            <option value="">Exibir somente cobranças ativas</option>
                            <option value="true">Exibir somente cobranças inativas</option>
                            <option value="false">Exibir todas as cobranças</option>
                        </select><br/>

                        <button class="btn btn-outline-primary ml-3">Buscar</button>
                    </g:form>
                </nav>

                <div class="js-table">
                    <g:if test="${paymentList}">
                        <g:render template="/payment/templates/table" model="${[paymentList: paymentList]}"/>
                    </g:if>
                    <g:else>
                        <g:render template="/payment/templates/emptyState"/>
                    </g:else>
                </div>
            </div>
        </div>
    </div>
</body>
</html>