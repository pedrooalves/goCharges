<!DOCTYPE html>
<%@ page import="shared.enums.State"%>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Listagem de Payer</title>
</head>
<body>
    <div class="container d-flex justify-content-center mb-3 col-12">
        <div class="col-6">
            <div class="card mb-3 mt-3 p-3 bg-gogreen text-center text-white">
                <h1 class="display-4">Editar Pagador</h1>
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

                    <div class="js-person-address-form">
                        <h4 class="font-weight-bold mb-2">Endereço</h4>

                        <div class="form-row mb-2">
                            <div class="col">
                                <label class="mb-1">CEP</label>
                                <input class="form-control js-postal-code" type="text" pattern="\d{5}-?\d{3}" name="postalCode"
                                       placeholder="00000-000" value="${payer.postalCode}"/>
                            </div>
                            <div class="d-flex align-items-end col">
                                <a href="https://buscacepinter.correios.com.br/" target="_blank" class="btn btn-outline-dark"
                                   style="text-decoration: none;"> Não sei meu CEP </a>
                            </div>
                        </div>

                        <div class="form-row ml-1">
                            <span class="form-row js-postal-code-warning text-danger"></span>
                        </div>

                        <div class="form-row mb-3">
                            <div class="col">
                                <label class="mb-1">Rua</label>
                                <input class="form-control js-address js-set" type="text" name="address"
                                       placeholder="Ex: Rua Maria de Souza" value="${payer.address}"/>
                            </div>
                            <div class="col">
                                <label class="mb-1">Número</label>
                                <input class="form-control" type="text" name="addressNumber" placeholder="00000" value="${payer.addressNumber}"/>
                            </div>
                        </div>

                        <div class="form-row mb-3">
                            <div class="col">
                                <label class="mb-1">Complemento</label>
                                <input class="form-control" type="text" name="complement" placeholder="Ex: Bloco 00, Apartamento 00"
                                       value=""/>
                            </div>
                            <div class="col">
                                <label class="mb-1">Bairro</label>
                                <input class="form-control js-province js-set" type="text" name="province" placeholder="Ex: Jardim Campos Elíseos"
                                       value="${payer.province}"/>
                            </div>
                        </div>

                        <div class="form-row mb-3">
                            <div class="col">
                                <label class="mb-1">Cidade</label>
                                <input class="form-control js-city js-set" type="text" name="city" placeholder="Ex: Salvador" value="${payer.city}"/>
                            </div>
                            <div class="col">
                                <label class="mb-1">Estado</label> <br/>
                                <g:select class="form-select js-state js-set" name="state" data-constraint="select" value="${payer.state}"
                                          from="${State.values()}" noSelection="${['': 'Selecione um estado']}" optionValue="name"/>
                            </div>
                        </div>
                    </div>

                    <div class="navbar d-flex justify-content-space-between">
                        <a href="/payer" class="text-decoration-none btn btn-outline-secondary" type="button">Cancelar</a>
                        <button type="submit" name="id" value="${payer.id}" class="btn bg-gogreen text-white ml-3">
                            Salvar
                        </button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
    <asset:javascript src="postal-code-utils.js"/>
</body>
</html>