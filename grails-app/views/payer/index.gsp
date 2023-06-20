<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <title>Listagem de pagadores</title>
    <asset:javascript src="goCharges.js" />
</head>
<body>
    <div class="container col-12" style="height: 100%">
        <div class="card-body row">
            <div class="col 6">
                <div class="mt-3 mb-1 p-2 px-4 bg-secondary text-white rounded d-flex justify-content-center align-items-center">
                    <h1>Pagadores</h1>
                </div>

                <nav class="navbar navbar-expand navbar-light bg-light col mb-3">
                        <g:form class="d-flex justify-content-center col" url="[controller: 'payer', action: 'index']" method="POST">
                            <select name="deletedOnly">
                                <option type="text" value="">Exibir somente pagadores ativos</option>
                                <option type="text" value="true">Exibir somente pagadores inativos</option>
                                <option type="text" value="false">Exibir todos os pagadores</option>
                            </select><br/>
                            <button class="btn btn-outline-primary ml-3">Buscar</button></a>
                        </g:form>
                </nav>

                <div class="navbar navbar-expand navbar-secondary d-flex justify-content-end col mb-3">
                    <a href="${createLink(action:'create', controller:'payer')}">
                        <button class="btn btn-outline-primary mb-2">Adicionar pagador</button>
                    </a>
                </div>

                <div class="row col-11">
                    <h1 class="col-3 fw-bold text-center">Nome</h1>
                    <h1 class="col fw-bold text-center">E-mail</h1>
                    <h1 class="col fw-bold text-center">Cpf ou Cnpj</h1>
                    <h1 class="col fw-bold text-center">Celular</h1>
                    <h1 class="col fw-bold text-center">Endereço</h1>
                </div>

                <g:each var="payer" in="${payerList}">
                    <ul class="list-group list-group-horizontal mb-1 mb-1">
                        <li class="custom-list-item col-3">${payer.name}</li>
                        <li class="custom-list-item col">${payer.email}</li>
                        <li class="custom-list-item col">${payer.cpfCnpj}</li>
                        <li class="custom-list-item col">${payer.mobilePhone}</li>
                        <li class="custom-list-item col">${payer.address}</li>

                        <g:form name="updateButton" url="[controller: 'payer', action: 'edit']" method="POST">
                            <button type="submit" name="id" value="${payer.id}" class="btn btn-outline-dark ml-3">
                                <asset:image src="pencil.svg"/>
                            </button>
                        </g:form>

                        <g:if test="${payer.canDelete()}">
                            <g:form name="deleteButton" url="[controller: 'payer', action: 'delete']" method="POST">
                                <button type="submit" name="id" value="${payer.id}" class="btn btn-outline-danger ml-3">
                                    <asset:image src="trash.svg"/>
                                </button>
                            </g:form>
                        </g:if>
                        <g:else>
                            <g:form name="restoreButton" url="[controller: 'payer', action: 'restore']" method="POST">
                                <button type="submit" name="id" value="${payer.id}" class="btn btn-outline-primary ml-3">
                                    <asset:image src="restore.svg"/>
                                </button>
                            </g:form>
                        </g:else>
                    </ul>
                </g:each>
            </div>
        </div>
    </div>
</body>
</html>