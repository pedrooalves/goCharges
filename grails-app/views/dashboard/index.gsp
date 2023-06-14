<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Cadastro de Customer</title>
</head>
<body>
    <div class="container px-4">
        <h1 class="font-weight-bold display-4 text-center mb-6">Olá, ${userName}</h1>

        <div class="d-flex justify-content-around " style="margin: 100px 0px">
            <div class="card d-flex justify-content-around" style="width: 20rem;">
                <div class="card-header" style="height: 15%">
                    <h2 class="font-weight-bold">Cobranças</h2>
                </div>
                <div>
                    <a href="/payment/index" class="text-decoration-none text-reset">
                        <div class="card-body text-center border m-2" style="height: 6rem;">
                            <p class="mb-3 text-warning" style="font-size: 3rem;">${accountInfo.pendingPaymentCount}</p>
                            <p>Pendentes</p>
                        </div>
                    </a>
                    <a href="/payment/index" class="text-decoration-none text-reset">
                        <div class="card-body text-center border m-2" style="height: 6rem;">
                            <p class="mb-3 text-success" style="font-size: 3rem;">${accountInfo.receivedPaymentCount}</p>
                            <p>Recebidas</p>
                        </div>
                    </a>
                    <a href="/payment/index" class="text-decoration-none text-reset">
                        <div class="card-body text-center border m-2" style="height: 6rem;">
                            <p class="mb-3 text-danger" style="font-size: 3rem;">${accountInfo.overduePaymentCount}</p>
                            <p>Vencidas</p>
                        </div>
                    </a>
                </div>
            </div>

            <div class="card d-flex justify-content-around" style="width: 20rem;">
                <div class="card-header" style="height: 15%">
                    <h2 class="font-weight-bold">Clientes</h2>
                </div>
                <a href="/payer/index" class="text-decoration-none text-reset">
                    <div class="card-body d-flex flex-column justify-content-center text-center border m-2" style="height: 9rem">
                        <p class="mb-3 text-primary" style="font-size: 3rem;">${accountInfo.activePayerCount}</p>
                        <p>Ativos</p>
                    </div>
                </a>
                <a href="/payer/index" class="text-decoration-none text-reset">
                    <div class="card-body d-flex flex-column    justify-content-center text-center border m-2" style="height: 9rem">
                        <p class="mb-3 text-danger" style="font-size: 3rem;">${accountInfo.overduePayerCount}</p>
                        <p>Inadimplentes</p>
                    </div>
                </a>
            </div>

            <div class="card d-flex justify-content-around" style="width: 20rem;">
                <div class="card-header" style="height: 15%">
                    <h2 class="font-weight-bold">Faturamento</h2>
                </div>
                <div>
                    <a href="/payment/index" class="text-decoration-none text-reset">
                        <div class="card-body text-center border m-2" style="height: 6rem;">
                            <p class="mb-3 text-warning" style="font-size: 2.5rem;">
                                <span style="font-size: 1.5rem">R$</span>
                                <formatTagLib:currencyWithoutMonetarySimbol value="${accountInfo.pendingRevenueAmount}"/>
                            </p>
                            <p>Pendente</p>
                        </div>
                    </a>
                    <a href="/payment/index" class="text-decoration-none text-reset">
                        <div class="card-body text-center  border m-2" style="height: 6rem;">
                            <p class="mb-3 text-success" style="font-size: 2.5rem;">
                                <span style="font-size: 1.5rem">R$</span>
                                <formatTagLib:currencyWithoutMonetarySimbol value="${accountInfo.receivedRevenueAmount}"/>
                            </p>
                            <p>Confirmado</p>
                        </div>
                    </a>
                    <a href="/payment/index" class="text-decoration-none text-reset">
                        <div class="card-body text-center border m-2" style="height: 6rem;">
                            <p class="mb-3 text-danger" style="font-size: 2.5rem;">
                                <span style="font-size: 1.5rem">R$</span>
                                <formatTagLib:currencyWithoutMonetarySimbol value="${accountInfo.receivedRevenueAmount}"/>
                            </p>
                            <p>Recebido</p>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>