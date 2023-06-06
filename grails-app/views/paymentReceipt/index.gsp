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
            <div class="d-flex justify-content-around m-4">
                <asset:image src="gocharges.png" alt="goCharges Logo"/></a>

                <h1 class="font-weight-bold">
                    Comprovante <br>
                    de pagamento
                </h1>
            </div>
                <div class="border-top border-bottom p-3">
                    <p class="font-weight-bold mr-2">Método de pagamento:</p> <p>${info.payment.billingType}</p>
                    <p class="font-weight-bold mr-2">Valor:</p> <p>${info.payment.value}</p>
                    <p class="font-weight-bold mr-2">Data de vencimento:</p> <p>${info.payment.dueDate}</p>
                    <p class="font-weight-bold mr-2">Data de pagamento:</p> <p></p>
                </div>

                <div class="border-top border-bottom p-3">
                    <p class="font-weight-bold mr-2 mb-2">Dados do pagador</p>
                    <p class="font-weight-bold mr-2">Nome:</p> <p>${info.payer.name}</p>
                    <p class="font-weight-bold mr-2">CPF / CNPJ:</p> <p>${info.payer.cpfCnpj}</p>
                </div>

                <div class="border-top border-bottom p-3">
                    <p class="font-weight-bold mr-2 mb-2">Dados do recebedor</p>
                    <p class="font-weight-bold mr-2">Nome:</p> <p>${info.customer.name}</p>
                    <p class="font-weight-bold mr-2">CPF / CNPJ:</p> <p>${info.customer.cpfCnpj}</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>