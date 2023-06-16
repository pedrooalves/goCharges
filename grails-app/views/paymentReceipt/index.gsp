<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>
    <title>Comprovante</title>
</head>
<body>
    <div class="container d-flex justify-content-center mt-5">
        <div class="border bg-light w-50">
            <div class="d-flex justify-content-between px-4 m-4">
                <asset:image src="gocharges.png" alt="goCharges Logo"/>
                </a>
                <h1 class="font-weight-bold"> Comprovante de pagamento </h1>
            </div>

            <div class="border-top border-bottom p-3">
                <p class="font-weight-bold mr-2">Forma de pagamento:</p>
                <p class="mb-2">
                    <formatTagLib:billingType billingType="${payment.billingType}"/>
                </p>

                <p class="font-weight-bold mr-2">Valor:</p>
                <p class="mb-2">R$ <formatTagLib:currencyWithoutMonetarySimbol value="${payment.value}"/></p>

                <p class="font-weight-bold mr-2">Data de vencimento:</p>
                <p class="mb-2">
                    <formatTagLib:brazilDate date="${payment.dueDate}"/>
                </p>

                <p class="font-weight-bold mr-2">Data de pagamento:</p>
                <p class="mb-2"><formatTagLib:brazilDate date="${payment.paymentDate}"/></p>
            </div>

            <div class="border-top border-bottom p-3">
                <p class="font-weight-bold mr-2 mb-3">Dados do pagador</p>

                <p class="font-weight-bold mr-2">Nome:</p>
                <p class="mb-2">${payment.payer.name}</p>

                <p class="font-weight-bold mr-2">CPF / CNPJ:</p>
                <p>${payment.payer.cpfCnpj}</p>
            </div>

            <div class="border-top border-bottom p-3">
                <p class="font-weight-bold mr-2 mb-3">Dados do recebedor</p>

                <p class="font-weight-bold mr-2">Nome:</p>
                <p class="mb-2">${payment.customer.name}</p>

                <p class="font-weight-bold mr-2">CPF / CNPJ:</p>
                <p>${payment.customer.cpfCnpj}</p>
            </div>
        </div>
    </div>
</body>
</html>