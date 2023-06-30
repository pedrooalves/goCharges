<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Detalhes da Cobrança</title>
</head>
<body>
    <div class="container d-flex justify-content-start default-details mb-3 main-container">
        <div class="col-12 pt-5">
            <div class="border rounded col">
                <div class="row d-flex justify-content-center rounded border">
                    <span class="h2"><p class="text-center">Detalhes da cobrança</p></span>
                </div>
                <div class="d-flex details-card">
                    <div class="details-info">
                        <div class="pt-3">
                            <label class="font-weight-bold mr-2">Situação</label>
                            <p class="mb-2">${payment.status.name}</p>

                            <label class="font-weight-bold mr-2">Criada em:</label>
                            <p class="mb-2">
                                <formatTagLib:brazilDate date="${payment.dateCreated}"/>
                            </p>

                            <g:if test="${payment.isPaid()}">
                                <label class="font-weight-bold mr-2">${payment.status.name} em:</label>
                                <p class="mb-2"><formatTagLib:brazilDate date="${payment.paymentDate}"/></p>
                            </g:if>

                            <label class="font-weight-bold mr-2">Cliente:</label>
                            <p class="mb-2">${payment.payer.name}</p>
                        </div>
                        <div class="pt-3">
                            <label class="font-weight-bold mr-2">Valor:</label>
                            <p class="mb-2">R$ <formatTagLib:currencyWithoutMonetarySimbol value="${payment.value}"/></p>

                            <label class="font-weight-bold mr-2">Vencimento:</label>
                            <p class="mb-2">
                                <formatTagLib:brazilDate date="${payment.dueDate}"/>
                            </p>

                            <label class="font-weight-bold mr-2">Forma de pagamento:</label>
                            <p class="mb-2">${payment.billingType.name}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>