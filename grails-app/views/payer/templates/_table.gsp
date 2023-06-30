<table class="mb-0 js-table">
    <thead>
        <tr class="border border-dark">
            <th class="h4 text-center" scope="col">Nome</th>
            <th class="h4 text-center" scope="col">E-mail</th>
            <th class="h4 text-center" scope="col">CPF / CNPJ</th>
            <th class="h4 text-center" scope="col">Celular</th>
            <th class="h4 text-center" scope="col">Ações</th>
        </tr>
    </thead>
    <tbody class="js-body">
        <g:each var="payer" in="${payerList}">
            <tr class="border border-dark js-table-row" data-url="/payer/show/${payer.id}">
                <td class="border border-dark">${payer.name}</td>
                <td class="border border-dark">${payer.email}</td>
                <td class="border border-dark">${payer.cpfCnpj}</td>
                <td class="border border-dark">${payer.mobilePhone}</td>
                <td class="default-action-row">
                    <g:render template="/payer/templates/actions" model="${[payer: payer]}"/>
                </td>
            </tr>
        </g:each>
    </tbody>
</table>
<nav>
    <ul class="d-flex justify-content-center pagination border border-dark bg-white col ml-0 mt-0 js-pagination overflow-auto pagination-bar">
        <li class="page-item"><a class="page-link js-previous" href="#">Anterior</a></li>
    </ul>
</nav>