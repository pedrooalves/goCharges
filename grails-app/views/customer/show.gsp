<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Detalhes da conta</title>
</head>
<body>
    <div class="container d-flex justify-content-start default-details mb-3 main-container">
        <div class="col-12 pt-5">
            <div class="border rounded col">
                <div class="row d-flex justify-content-center rounded border">
                    <span class="h2"><p class="text-center">Detalhes da conta</p></span>
                </div>
                <div class="d-flex details-card">
                    <div class="details-info">
                        <g:if test="${customer.status.isActive()}">
                            <div>
                                <div class="pt-3">
                                    <p class="font-weight-bold mr-2">Nome</p>
                                    <p class="mb-2">${customer.name}</p>

                                    <p class="font-weight-bold mr-2">Email</p>
                                    <p class="mb-2">${customer.email}</p>

                                    <p class="font-weight-bold mr-2">Telefone</p>
                                    <p class="mb-2">${customer.mobilePhone}</p>

                                    <p class="font-weight-bold mr-2">CPF / CNPJ</p>
                                    <p class="mb-2">${customer.cpfCnpj}</p>

                                    <p class="font-weight-bold mr-2">Criado em</p>
                                    <p class="mb-2">
                                        <formatTagLib:brazilDate date="${customer.dateCreated}"/>
                                        às
                                        <formatTagLib:time date="${customer.dateCreated}"/>
                                    </p>
                                </div>
                            </div>
                            <div>
                                <div class="pt-3">
                                    <p class="font-weight-bold mr-2">Status</p>
                                    <p class="mb-2">${customer.status.name}</p>

                                    <p class="font-weight-bold mr-2">Endereço</p>
                                    <p class="mb-2">${customer.address}, ${customer.addressNumber}</p>

                                    <p class="font-weight-bold mr-2">Cidade</p>
                                    <p class="mb-2">${customer.province}</p>

                                    <p class="font-weight-bold mr-2">Estado</p>
                                    <p class="mb-2">${customer.state?.name}</p>

                                    <p class="font-weight-bold mr-2">CEP</p>
                                    <p class="mb-2">${customer.postalCode}</p>
                                </div>
                            </div>
                        </g:if>
                        <g:else>
                            <div>
                                <p class="font-weight-bold mr-2 text-center">Status</p>
                                <p class="mb-2 text-center">${customer.status.name}</p>

                                <p class="font-weight-bold mr-2 text-center">Email</p>
                                <p class="mb-2 text-center">${customer.email}</p>

                                <p class="font-weight-bold mr-2 text-center">Criado em</p>
                                <p class="mb-2 text-center">
                                    <formatTagLib:brazilDate date="${customer.dateCreated}"/>
                                    às
                                    <formatTagLib:time date="${customer.dateCreated}"/>
                                </p>
                            </div>
                        </g:else>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>