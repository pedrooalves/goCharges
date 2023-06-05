<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Cadastro de Customer</title>
</head>
<body>
<div class="container px-4">

    <h1 class="font-weight-bold display-4 text-center mb-6">Olá, ${userName}</h1>

    <div class="d-flex justify-content-around">
        <div class="card d-flex justify-content-around" style="width: 18rem;">
            <a href="/payer/index" class="text-decoration-none text-reset">
                <div class="card-header">
                    <h2 class="font-weight-bold">Clientes</h2>
                </div>
                <div class="card-body d-flex justify-content-center align-items-center" style="height: 18rem">
                    <p class="text-center" style="font-size: 5rem;">${info.payerCount}</p>
                </div>
            </a>
        </div>

        <div class="card d-flex justify-content-around" style="width: 18rem;">
            <div class="card-header">
                <h2 class="font-weight-bold">Cobranças</h2>
            </div>
            <div>
                <a href="/payment/index" class="text-decoration-none text-reset">
                    <div class="card-body text-center border m-2" style="height: 6rem;">
                        <p class="mb-3" style="font-size: 3rem;">${info.pendingPaymentCount}</p>
                        <p>Pendentes</p>
                    </div>
                </a>
                <a href="/payment/index" class="text-decoration-none text-reset">
                    <div class="card-body text-center border m-2" style="height: 6rem;">
                        <p class="mb-3" style="font-size: 3rem;">${info.receivedPaymentCount}</p>
                        <p>Recebidas</p>
                    </div>
                </a>
                <a href="/payment/index" class="text-decoration-none text-reset">
                    <div class="card-body text-center border m-2" style="height: 6rem;">
                        <p class="mb-3" style="font-size: 3rem;">${info.overduePaymentCount}</p>
                        <p>Vencidas</p>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>