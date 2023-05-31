<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Cadastro de Customer</title>
</head>
<body>
<div class="container py-4 px-4">

    <div class="d-flex justify-content-around">
        <div class="card mr-8" style="width: 18rem;">
            <a href="/payer/index" class="text-decoration-none text-reset">
                <div class="card-header">
                    <h2 class="font-weight-bold">Clientes</h2>
                </div>
                <div class="card-body d-flex justify-content-center align-items-center" style="height: 12rem;">
                    <p class="text-center" style="font-size: 5rem;">${payerCount}</p>
                </div>
            </a>
        </div>

        <div class="card" style="width: 18rem;">
            <a href="/payment/index" class="text-decoration-none text-reset">
                <div class="card-header">
                    <h2 class="font-weight-bold">Cobranças</h2>
                </div>

                <div class="card-body text-center" style="height: 6rem;">
                    <p class="mb-3" style="font-size: 3rem;">${paymentsByStatus.pending}</p>
                    <p>Pendentes</p>
                </div>
                <div class="card-body text-center" style="height: 6rem;">
                    <p class="mb-3" style="font-size: 3rem;">${paymentsByStatus.received}</p>
                    <p>Recebidas</p>
                </div>
            </a>
        </div>
    </div>
</div>
</body>
</html>