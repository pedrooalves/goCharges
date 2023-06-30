<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Detalhes do pagador</title>
</head>
<body>
    <div class="container d-flex justify-content-start default-details mb-3 main-container">
        <div class="col-12 pt-5">
            <div class="border rounded col">
                <div class="row d-flex justify-content-center rounded border">
                    <span class="h2"><p class="text-center">Detalhes do pagador</p></span>
                </div>
                <div class="d-flex details-card">
                    <div class="details-info">
                        <div>
                            <div class="pt-3">
                                <p class="font-weight-bold mr-2">Nome</p>
                                <p class="mb-2">${payer.name}</p>

                                <p class="font-weight-bold mr-2">Email</p>
                                <p class="mb-2">${payer.email}</p>

                                <p class="font-weight-bold mr-2">Telefone</p>
                                <p class="mb-2">${payer.mobilePhone}</p>

                                <p class="font-weight-bold mr-2">CPF / CNPJ</p>
                                <p class="mb-2">${payer.cpfCnpj}</p>

                                <p class="font-weight-bold mr-2">Criado em</p>
                                <p class="mb-2">
                                    <formatTagLib:brazilDate date="${payer.dateCreated}"/>
                                    às
                                    <formatTagLib:time date="${payer.dateCreated}"/>
                                </p>
                            </div>
                        </div>
                        <div>
                            <div class="pt-3">
                                <p class="font-weight-bold mr-2">Endereço</p>
                                <p class="mb-2">${payer.address}, ${payer.addressNumber}</p>

                                <p class="font-weight-bold mr-2">Cidade</p>
                                <p class="mb-2">${payer.province}</p>

                                <p class="font-weight-bold mr-2">Estado</p>
                                <p class="mb-2">${payer.state.name}</p>

                                <p class="font-weight-bold mr-2">CEP</p>
                                <p class="mb-2">${payer.postalCode}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>