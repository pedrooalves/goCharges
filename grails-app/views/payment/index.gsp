<!DOCTYPE html>
<%@ page import="gocharges.payment.enums.PaymentStatus"%>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Cadastro de Payment</title>
    <asset:javascript src="modalController.js"/>
</head>
<body>
    <div class="container col-12 js-payment-list-container">
        <div class="card-body row">
            <div class="col">
                <g:render template="/modal/templates/confirmReceivedInCashModal"/>

                <div class="mt-3 mb-1 p-2 px-4 bg-secondary text-center text-white rounded d-flex justify-content-between">
                    <h1>Cobranças</h1>

                    <a class="d-flex align-items-center text-decoration-none"
                       href="${createLink(controller:'payment', action:'create')}">
                        <button class="btn btn-primary">Nova Cobrança</button>
                    </a>
                </div>

                <div class="row col-11">
                    <h1 class="col-3 fw-bold text-center">Forma de pagamento</h1>
                    <h1 class="col fw-bold text-center">Valor</h1>
                    <h1 class="col fw-bold text-center">Data de Vencimento</h1>
                    <h1 class="col fw-bold text-center">Status</h1>
                    <h1 class="col fw-bold text-center">Pagador</h1>
                </div>

                <g:each var="payment" in="${paymentList}">
                    <ul class="list-group list-group-horizontal mb-1 mb-1 justify-content-between">
                        <li class="custom-list-item col-3">${payment.billingType}</li>
                        <li class="custom-list-item col">${payment.value}</li>
                        <li class="custom-list-item col">
                            <formatTagLib:brazilDate date="${payment.dueDate}"/>
                        </li>
                        <li class="custom-list-item col">${payment.status}</li>
                        <li class="custom-list-item col">${payment.payer.name}</li>

                        <g:if test="${payment.status == PaymentStatus.PENDING}">
                            <button class="btn btn-outline-dark ml-3 js-btn-confirm-received-in-cash" type="button" value="${payment.id}">
                                <asset:image src="cash-stack.svg"/>
                            </button>
                        </g:if>

                        <g:form name="updateButton" url="[controller: 'payment', action: 'edit']" method="PUT">
                            <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-dark ml-3">
                                <asset:image src="pencil.svg"/>
                            </button>
                        </g:form>

                        <g:if test="${payment.status == PaymentStatus.RECEIVED}">
                            <g:form name="receiptButton" url="[controller: 'paymentReceipt', action: 'index']" method="GET">
                                <button type="submit" name="publicId" value="${payment.publicId}"
                                        class="btn btn-outline-dark ml-3">
                                    <asset:image src="receipt.svg"/>
                                </button>
                            </g:form>
                        </g:if>

                        <g:form name="deleteButton" url="[controller: 'payment', action: 'delete']" method="DELETE">
                            <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-danger ml-3">
                                <asset:image src="trash.svg"/>
                            </button>
                        </g:form>
                    </ul>
                </g:each>
            </div>
        </div>
    </div>
</body>
</html>