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
        <g:each var="customer" in="${customerList}">
            <tr class="border border-dark">
                <td class="border border-dark">${customer.name}</td>
                <td class="border border-dark">${customer.email}</td>
                <td class="border border-dark">${customer.cpfCnpj}</td>
                <td class="border border-dark">${customer.mobilePhone}</td>
                <td class="d-flex justify-content-center">
                    <g:render template="/customer/templates/actions" model="${[customer: customer]}"/>
                </td>
            </tr>
        </g:each>
    </tbody>
</table>