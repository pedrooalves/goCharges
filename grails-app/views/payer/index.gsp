<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main" />
    <title>Listagem de Payer</title>
</head>
<body>
    <div class="container col-12" style="height: 69.5vh">
        <div class="card-body row">
            <g:if test="${showNewPayerForm != false}">
                <div class="col-4 overflow-auto" style="height: 66.5vh">
                    <div class="card mb-3 mt-3 p-2 bg-gogreen text-center text-white">
                        <h1 class="display-4">Novo Pagador</h1>
                    </div>

                    <g:if test="${validation != null && validation.type.equals('save')}">
                        <div class="alert alert-danger" role="alert">
                            ${validation.message}
                        </div>
                    </g:if>

                    <g:form class="card-body mb-3" name="payerForm" url="[controller: 'payer', action: 'save']">
                        <div class="form-group mb-3">
                            <label class="mb-2 fw-bold">Nome</label>
                            <input class="form-control" type="text" name="name" placeholder="Ex: João da Silva" value="" /><br/>
                        </div>

                        <div class="form-group mb-3">
                            <label class="mb-2">E-mail</label>
                            <input class="form-control" type="email" name="email" placeholder="Ex: joao.silva@email.com" value="" /><br/>
                        </div>

                        <div class="form-group mb-3">
                            <label class="mb-2">CPF / CNPJ </label>
                            <input class="form-control" type="text" name="cpfCnpj" placeholder="000.000.000-00" value="" /><br >
                        </div>

                        <div class="form-group mb-3">
                            <label class="mb-2">Celular</label>
                            <input class="form-control" type="text" name="mobilePhone" pattern="(?\d{2})? ?\d{4-5}-?\d{3}-?\d{2}" placeholder="(00) 00000-0000" value="" /><br/>
                        </div>


                        <h4 class="font-weight-bold mb-2">Endereço</h4>

                        <div class="form-group">
                            <label class="mb-1">CEP</label>
                            <input class="form-control col-6" type="text" pattern="\d{5}-?\d{3}"  name="postalCode" placeholder="00000-000" value="" />
                        </div>

                        <div class="form-row mb-3">
                            <div class="col">
                                <label class="mb-1">Rua</label>
                                <input class="form-control" type="text" name="address" placeholder="Ex: Rua Maria de Souza" value="" />
                            </div>
                            <div class="col">
                                <label class="mb-1">Número</label>
                                <input class="form-control" type="text" name="addressNumber" placeholder="00000" value="" />
                            </div>
                        </div>

                        <div class="form-row mb-3">
                            <div class="col">
                                <label class="mb-1">Complemento</label>
                                <input class="form-control" type="text" name="complement" placeholder="Ex: Bloco 00, Apartamento 00" value="" />
                            </div>
                            <div class="col">
                                <label class="mb-1">Bairro</label>
                                <input class="form-control" type="text" name="province" placeholder="Ex: Jardim Campos Elíseos" value="" />
                            </div>
                        </div>

                        <div class="form-row mb-3">
                            <div class="col">
                                <label class="mb-1">Cidade</label>
                                <input class="form-control" type="text" name="city" placeholder="Ex: Salvador" value="" />
                            </div>
                            <div class="col">
                                <label class="mb-1">Estado</label>
                                <input class="form-control" type="text" name="state" placeholder="Ex: Bahia" value="" />
                            </div>
                        </div>

                        <div class="navbar d-flex justify-content-space-between">
                            <a href="${createLink(action:'index', controller:'payer')}"><input class="btn btn-outline-secondary" type="button" name="buttonCancelar" value="Cancelar" /></a>
                            <input class="btn bg-gogreen text-white" type="submit" name="buttonRegister" value="Cadastrar" />
                        </div>
                    </g:form>
                </div>
            </g:if>

            <div class="col 6">
                <div class="mt-3 mb-1 p-2 bg-secondary text-center text-white rounded">
                    <h1>Pagadores</h1>
                </div>
                <a href="${createLink(action:'showForm', controller:'payer')}"><button class="btn btn-outline-primary mb-2">Novo</button></a>

                <g:if test="${validation != null && (validation.type.equals('delete') || validation.type.equals('update') ||
                (validation.type.equals('save') && validation.success))}">
                    <div class="${validation.success ? 'alert alert-success' : 'alert alert-danger'}" role="alert">
                        ${validation.message}
                    </div>
                </g:if>

                <div class="row col-11">
                    <h1 class="col-3 fw-bold text-center">Nome</h1>
                    <h1 class="col fw-bold text-center">E-mail</h1>
                    <h1 class="col fw-bold text-center">Cpf ou Cnpj</h1>
                    <h1 class="col fw-bold text-center">Celular</h1>
                    <h1 class="col fw-bold text-center">Endereço</h1>

                </div>

                <g:each var="payer" in="${payers}">
                    <ul class="list-group list-group-horizontal mb-1 mb-1">
                        <li class="custom-list-item col-3">${payer.name}</li>
                        <li class="custom-list-item col">${payer.email}</li>
                        <li class="custom-list-item col">${payer.cpfCnpj}</li>
                        <li class="custom-list-item col">${payer.mobilePhone}</li>
                        <li class="custom-list-item col">${payer.address}</li>

                        <g:form name="updateButton" url="[controller: 'payer', action: 'edit']" method="POST">
                            <button type="submit" name="id" value="${payer.id}" class="btn btn-outline-dark ml-3">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                    <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                </svg>
                            </button>
                        </g:form>

                        <g:form name="deleteButton" url="[controller: 'payer', action: 'delete']" method="POST">
                            <button type="submit" name="id" value="${payer.id}" class="btn btn-outline-danger ml-3">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
                                    <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                                </svg>
                            </button>
                        </g:form>
                    </ul>
                </g:each>
            </div>
        </div>
    </div>
</body>
</html>