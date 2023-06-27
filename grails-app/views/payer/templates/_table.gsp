<table>
    <thead>
    <tr>
        <th class="h4" scope="col">Nome</th>
        <th class="h4" scope="col">E-mail</th>
        <th class="h4" scope="col">CPF / CNPJ</th>
        <th class="h4" scope="col">Celular</th>
        <th class="h4" scope="col">Endereço</th>
    </tr>
    </thead>
    <tbody>
    <g:each var="payer" in="${payerList}">
        <tr>
            <td>${payer.name}</td>
            <td>${payer.email}</td>
            <td>${payer.cpfCnpj}</td>
            <td>${payer.mobilePhone}</td>
            <td>${payer.address}</td>
        </tr>
    </g:each>
    </tbody>
</table>