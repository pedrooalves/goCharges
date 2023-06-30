<!DOCTYPE html>
<%@ page import="gocharges.payment.enums.PaymentStatus"%>
<%@ page import="gocharges.payment.enums.PaymentBillingType"%>
<html lang="en" xmlns:g="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main"/>
    <title>Listagem de pagadores</title>

    <asset:javascript src="tableController.js"/>
    <asset:javascript src="goCharges.js"/>
</head>
<body>
    <div class="container col-12 js-container" style="height: 100%">
        <div class="card-body row">
            <div class="col 6">
                <div class="mt-3 mb-1 p-2 px-4 bg-secondary text-white rounded domain-title">
                    <h1>Pagadores</h1>

                    <a class="d-flex justify-content-center text-decoration-none" href="${createLink(controller:'payer', action:'create')}">
                        <button class="btn btn-gogreen">Adicionar pagador</button>
                    </a>
                </div>

                <nav class="navbar navbar-expand navbar-light bg-light col mb-3">
                    <g:form class="col filterList" url="[controller: 'payer', action: 'index']" method="POST">
                        <input class="col" name="name[ilike]" value="" placeholder="Nome">

                        <input class="col filter" name="email[ilike]" value="" placeholder="E-mail">

                        <input class="col filter" name="cpfCnpj[like]" value="" placeholder="CPF / CNPJ">

                        <input class="col filter" name="mobilePhone[like]" value="" placeholder="Celular">

                        <select class="col filter" name="deletedOnly">
                            <option value="">Exibir somente pagadores ativos</option>
                            <option value="true">Exibir somente pagadores inativos</option>
                            <option value="false">Exibir todos os pagadores</option>
                        </select><br/>

                        <button class="btn btn-outline-primary ml-3">Buscar</button></a>
                    </g:form>
                </nav>

                <div class="js-table">
                    <g:if test="${payerList}">
                        <g:render template="/payer/templates/table" model="${[payerList: payerList]}"/>
                    </g:if>
                    <g:else>
                        <g:render template="/payer/templates/emptyState"/>
                    </g:else>
                </div>
            </div>
        </div>
    </div>
</body>
</html>