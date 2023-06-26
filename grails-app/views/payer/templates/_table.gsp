<table>
    <thead>
    <tr>
        <th class="h4 text-center" scope="col">Nome</th>
        <th class="h4 text-center" scope="col">E-mail</th>
        <th class="h4 text-center" scope="col">CPF / CNPJ</th>
        <th class="h4 text-center" scope="col">Celular</th>
        <th class="h4 text-center" scope="col">Ações</th>
    </tr>
    </thead>
    <tbody>
    <g:each var="payer" in="${payerList}">
        <tr class="border border-dark">
            <td class="border border-dark">${payer.name}</td>
            <td class="border border-dark">${payer.email}</td>
            <td class="border border-dark">${payer.cpfCnpj}</td>
            <td class="border border-dark">${payer.mobilePhone}</td>
            <td class="row d-flex justify-content-center">
                <g:render template="/payer/templates/actions" model="${[payer: payer]}"/>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>