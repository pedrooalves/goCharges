<!DOCTYPE html>
<%@ page import="gocharges.payment.enums.PaymentBillingType"%>
<%@ page import="gocharges.payment.enums.PaymentStatus"%>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Listagem de cobranças</title>
    <asset:javascript src="modalController.js"/>
</head>
<body>
    <div class="container col-12 js-payment-list-container">
        <div class="card-body row">
            <div class="col">
                <g:render template="/modal/templates/confirmReceivedInCashModal"/>

                <div class="mt-3 mb-1 p-2 px-4 bg-secondary text-white rounded d-flex justify-content-center">
                    <h1>Cobranças</h1>

                    <a class="d-flex align-items-center text-decoration-none"
                       href="${createLink(controller:'payment', action:'create')}">
                        <button class="btn btn-gogreen">Nova Cobrança</button>
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

                <div class="row col-11">
                    <h1 class="col-3 fw-bold text-center">Forma de pagamento</h1>
                    <h1 class="col fw-bold text-center">Valor</h1>
                    <h1 class="col fw-bold text-center">Data de Vencimento</h1>
                    <h1 class="col fw-bold text-center">Situação</h1>
                    <h1 class="col fw-bold text-center">Pagador</h1>
                </div>

                <g:each var="payment" in="${paymentList}">
                    <ul class="list-group list-group-horizontal mb-1 mb-1 justify-content-between">
                        <li class="custom-list-item col-3">${payment.billingType.name}</li>
                        <li class="custom-list-item col">${payment.value}</li>
                        <li class="custom-list-item col"><formatTagLib:brazilDate date="${payment.dueDate}"/></li>
                        <li class="custom-list-item col">${payment.status.name}</li>
                        <li class="custom-list-item col">${payment.payer.name}</li>

                        <g:if test="${payment.canConfirm()}">
                            <g:form name="confirmButton" url="[controller: 'payment', action: 'confirmReceivedInCash']" method="POST">
                                <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-dark ml-3">
                                    <asset:image src="cash-stack.svg"/>
                                </button>
                            </g:form>
                        </g:if>

                        <g:form name="updateButton" url="[controller: 'payment', action: 'edit']" method="POST">
                            <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-dark ml-3">
                                <asset:image src="pencil.svg"/>
                            </button>
                        </g:form>

                        <g:if test="${payment.status == PaymentStatus.RECEIVED}">
                            <g:form name="receiptButton" url="[controller: 'paymentReceipt', action: 'index']" method="GET">
                                <button type="submit" name="publicId" value="${payment.publicId}" class="btn btn-outline-dark ml-3">
                                    <asset:image src="receipt.svg"/>
                                </button>
                            </g:form>
                        </g:if>

                        <g:if test="${payment.canDelete()}">
                            <g:form name="deleteButton" url="[controller: 'payment', action: 'delete']" method="POST">
                                <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-danger ml-3">
                                    <asset:image src="trash.svg"/>
                                </button>
                            </g:form>
                        </g:if>
                        <g:else>
                            <g:form name="restoreButton" url="[controller: 'payment', action: 'restore']" method="POST">
                                <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-primary ml-3">
                                    <asset:image src="restore.svg"/>
                                </button>
                            </g:form>
                        </g:else>
                    </ul>
                </g:each>
            </div>
        </div>
    </div>
</body>
</html>