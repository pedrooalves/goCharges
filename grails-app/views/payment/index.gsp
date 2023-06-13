<!DOCTYPE html>
<%@ page import="gocharges.payment.enums.PaymentStatus" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Cadastro de Payment</title>
</head>
<body>
<div class="container col-12">
    <div class="card-body row">
        <g:if test="${showNewPaymentForm == true}">
            <div class="col-3">
                <div class="card mb-3 mt-3 p-1 bg-gogreen text-center text-white">
                    <h1 class="display-4">Nova Cobrança</h1>
                </div>

                <g:form class="card-body mb-3" name="paymentForm" url="[controller: 'payment', action: 'save']">
                    <div class="form-group mb-3">
                        <label for="payer-select" class="mb-2 fw-bold">Pagador</label>
                        <select class="form-select" id="payer-select" name="payerCpfCnpj">
                            <option value="">Nenhum selecionado</option>
                            <g:each var="payer" in="${payerList}">
                                <option value="${payer.cpfCnpj}">${payer.name}</option>
                            </g:each>
                        </select><br/>
                    </div>

                    <div class="form-group column mb-3">
                        <label for="billingType-select" class="mb-2 fw-bold">Tipo de Recebimento Aceito</label>
                        <select class="form-select" id="billingType-select" name="billingType">
                            <option value="">Nenhum selecionado</option>
                            <option value="BANK_SLIP">Boleto</option>
                            <option value="DEBIT_CARD">Cartão de Débito</option>
                            <option value="PIX">Pix</option>
                        </select><br/>
                    </div>

                    <div class="form-group mb-3">
                        <label for="dueDate" class="mb-2">Data de Vencimento</label>
                        <input class="form-control" id="dueDate" type="date" name="dueDate" value=""
                               placeholder="dd/mm/aaaa"/><br>
                    </div>

                    <div class="form-group mb-3">
                        <label for="value" class="mb-2">Valor </label>
                        <input class="form-control" id="value" type="text" name="value" value=""/><br/>
                    </div>

                    <div class="navbar d-flex justify-content-space-between">
                        <a href="${createLink(action:'index', controller:'payment')}"><input
                                class="btn btn-outline-secondary" type="button" name="buttonCancelar" value="Cancelar"/></a>
                        <input class="btn bg-gogreen text-white" type="submit" name="buttonRegister" value="Cadastrar"/>
                    </div>
                </g:form>
            </div>
        </g:if>

        <div class="col 6">
            <div class="mt-3 mb-1 p-2 bg-secondary text-center text-white rounded">
                <h1>Cobranças</h1>
            </div>

            <a href="${createLink(action:'showForm', controller:'payment')}">
                <button class="btn btn-outline-primary mb-2">Novo</button>
            </a>

            <div class="row col-11">
                <h1 class="col-3 fw-bold text-center">Tipo de Recebimento</h1>
                <h1 class="col fw-bold text-center">Valor</h1>
                <h1 class="col fw-bold text-center">Data de Vencimento</h1>
                <h1 class="col fw-bold text-center">Status</h1>
                <h1 class="col fw-bold text-center">Pagador</h1>
            </div>

            <g:each var="payment" in="${paymentList}">
                <ul class="list-group list-group-horizontal mb-1 mb-1">
                    <li class="custom-list-item col-3">${payment.billingType}</li>
                    <li class="custom-list-item col">${payment.value}</li>
                    <li class="custom-list-item col">
                        <FormatTagLib:brazilDateNotation date="${payment.dueDate}"/>
                    </li>
                    <li class="custom-list-item col">${payment.status}</li>
                    <li class="custom-list-item col">${payment.payer.name}</li>

                    <g:if test="${payment.status == PaymentStatus.PENDING}">
                        <g:form name="confirmButton" url="[controller: 'payment', action: 'confirm']" method="POST">
                            <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-dark ml-3">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-check-lg" viewBox="0 0 16 16">
                                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"/>
                                </svg>
                            </button>
                        </g:form>
                    </g:if>

                    <g:form name="updateButton" url="[controller: 'payment', action: 'edit']" method="POST">
                        <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-dark ml-3">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-pencil" viewBox="0 0 16 16">
                                <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                            </svg>
                        </button>
                    </g:form>

                    <g:if test="${payment.status == PaymentStatus.RECEIVED}">
                        <g:form name="receiptButton" url="[controller: 'paymentReceipt', action: 'index']" method="GET">
                            <button type="submit" name="publicId" value="${payment.publicId}"
                                    class="btn btn-outline-dark ml-3">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-receipt" viewBox="0 0 16 16">
                                    <path d="M1.92.506a.5.5 0 0 1 .434.14L3 1.293l.646-.647a.5.5 0 0 1 .708 0L5 1.293l.646-.647a.5.5 0 0 1 .708 0L7 1.293l.646-.647a.5.5 0 0 1 .708 0L9 1.293l.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .801.13l.5 1A.5.5 0 0 1 15 2v12a.5.5 0 0 1-.053.224l-.5 1a.5.5 0 0 1-.8.13L13 14.707l-.646.647a.5.5 0 0 1-.708 0L11 14.707l-.646.647a.5.5 0 0 1-.708 0L9 14.707l-.646.647a.5.5 0 0 1-.708 0L7 14.707l-.646.647a.5.5 0 0 1-.708 0L5 14.707l-.646.647a.5.5 0 0 1-.708 0L3 14.707l-.646.647a.5.5 0 0 1-.801-.13l-.5-1A.5.5 0 0 1 1 14V2a.5.5 0 0 1 .053-.224l.5-1a.5.5 0 0 1 .367-.27zm.217 1.338L2 2.118v11.764l.137.274.51-.51a.5.5 0 0 1 .707 0l.646.647.646-.646a.5.5 0 0 1 .708 0l.646.646.646-.646a.5.5 0 0 1 .708 0l.646.646.646-.646a.5.5 0 0 1 .708 0l.646.646.646-.646a.5.5 0 0 1 .708 0l.646.646.646-.646a.5.5 0 0 1 .708 0l.509.509.137-.274V2.118l-.137-.274-.51.51a.5.5 0 0 1-.707 0L12 1.707l-.646.647a.5.5 0 0 1-.708 0L10 1.707l-.646.647a.5.5 0 0 1-.708 0L8 1.707l-.646.647a.5.5 0 0 1-.708 0L6 1.707l-.646.647a.5.5 0 0 1-.708 0L4 1.707l-.646.647a.5.5 0 0 1-.708 0l-.509-.51z"/>
                                    <path d="M3 4.5a.5.5 0 0 1 .5-.5h6a.5.5 0 1 1 0 1h-6a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h6a.5.5 0 1 1 0 1h-6a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h6a.5.5 0 1 1 0 1h-6a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5zm8-6a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5z"/>
                                </svg>
                            </button>
                        </g:form>
                    </g:if>

                    <g:form name="deleteButton" url="[controller: 'payment', action: 'delete']" method="POST">
                        <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-danger ml-3">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-trash3" viewBox="0 0 16 16">
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