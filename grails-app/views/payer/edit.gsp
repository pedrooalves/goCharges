<!DOCTYPE html>
<%@ page import="shared.enums.State"%>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Atualizar pagador</title>
</head>
<body>
    <div class="container d-flex justify-content-center mb-3 col-12">
        <div class="col-6">
            <div class="card mb-3 mt-3 p-3 bg-gogreen text-center text-white">
                <h1 class="display-4">Atualizar pagador</h1>
            </div>

            <div class="card">
                <g:form class="card-body mb-3" name="payerForm" url="[controller: 'payer', action: 'update']">
                    <div class="form-group mb-3">
                        <label class="mb-2 fw-bold">Nome</label>
                        <input class="form-control" type="text" name="name" placeholder="Ex: João da Silva"
                               value="${payer.name}"/><br/>
                    </div>

                    <div class="form-group mb-3">
                        <label class="mb-2">E-mail</label>
                        <input class="form-control" type="email" name="email" placeholder="Ex: joao.silva@email.com"
                               value="${payer.email}"/><br/>
                    </div>

                    <div class="form-group mb-3">
                        <label class="mb-2">CPF / CNPJ </label>
                        <input class="form-control" type="text" readonly name="cpfCnpj" value="${payer.cpfCnpj}"/><br>
                    </div>

                    <div class="form-group mb-3">
                        <label class="mb-2">Celular</label>
                        <input class="form-control" type="text" name="mobilePhone" placeholder="(00) 00000-0000"
                               value="${payer.mobilePhone}"/><br/>
                    </div>

                    <h4 class="font-weight-bold mb-2">Endereço</h4>

                    <div class="form-group">
                        <label class="mb-1">CEP</label>
                        <input class="form-control col-6" type="text" pattern="\d{5}-?\d{3}" name="postalCode"
                               placeholder="00000-000" value="${payer.postalCode}"/>
                    </div>

                    <div class="form-row mb-3">
                        <div class="col">
                            <label class="mb-1">Rua</label>
                            <input class="form-control" type="text" name="address" placeholder="Ex: Rua Maria de Souza"
                                   value="${payer.address}"/>
                        </div>
                        <div class="col">
                            <label class="mb-1">Número</label>
                            <input class="form-control" type="text" name="addressNumber" placeholder="00000"
                                   value="${payer.addressNumber}"/>
                        </div>
                    </div>

                    <div class="form-row mb-3">
                        <div class="col">
                            <label class="mb-1">Complemento</label>
                            <input class="form-control" type="text" name="complement"
                                   placeholder="Ex: Bloco 00, Apartamento 00" value="${payer.complement}"/>
                        </div>

                        <div class="col">
                            <label class="mb-1">Bairro</label>
                            <input class="form-control" type="text" name="province" placeholder="Ex: Jardim Campos Elíseos"
                                   value="${payer.province}"/>
                        </div>
                    </div>

                    <div class="form-row mb-3">
                        <div class="col">
                            <label class="mb-1">Cidade</label>
                            <input class="form-control" type="text" name="city" placeholder="Ex: Salvador"
                                   value="${payer.city}"/>
                        </div>

                        <div class="col">
                            <label for="state-select" class="mb-1">Estado</label> <br/>
                            <g:select name="state" id="state-select" class="form-select" data-constraint="select"
                                      from="${State.values()}" value="${payer.state}"
                                      noSelection="${['': 'Selecione um estado']}"
                                      optionValue="name"/>
                        </div>
                    </div>

                    <div class="navbar d-flex justify-content-space-between">
                        <a href="/payer" class="btn btn-outline-secondary" style="text-decoration:none" type="button">Cancelar</a>
                        <button type="submit" name="id" value="${payer.id}" class="btn bg-gogreen text-white ml-3">
                            Salvar
                        </button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</body>
</html>