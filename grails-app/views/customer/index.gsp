<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <title>Listagem de clientes</title>
    <asset:javascript src="goCharges.js" />
</head>
<body>
<div class="container col-12" style="height: 100%">
    <div class="card-body row">
        <div class="col 6">
            <div class="mt-3 mb-1 p-2 px-4 bg-secondary text-white rounded d-flex justify-content-center align-items-center">
                <h1>Clientes</h1>
            </div>

            <nav class="navbar navbar-expand navbar-light bg-light col mb-3">
                <g:form class="d-flex justify-content-center col" url="[controller: 'customer', action: 'index']" method="POST">
                    <input class="col" name="name[ilike]" value="" placeholder="Nome">

                    <input class="col ml-3" name="email[ilike]" value="" placeholder="E-mail">

                    <input class="col ml-3" name="cpfCnpj[like]" value="" placeholder="CPF / CNPJ">

                    <input class="col ml-3" name="mobilePhone[like]" value="" placeholder="Celular">

                    <select class="ml-3" name="deletedOnly">
                        <option value="">Exibir somente clientes ativos</option>
                        <option value="true">Exibir somente clientes inativos</option>
                        <option value="false">Exibir todos os clientes</option>
                    </select><br/>

                    <button class="btn btn-outline-primary ml-3">Buscar</button></a>
                </g:form>
            </nav>

            <g:render template="/customer/templates/table" model="${[customerList: customerList]}"/>

        </div>
    </div>
</div>
</body>
</html>