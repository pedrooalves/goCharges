<table class="mb-0 js-table">
    <thead class="border border-dark">
        <tr>
            <th class="h4 text-center" scope="col">Nome</th>
            <th class="h4 text-center" scope="col">E-mail</th>
            <th class="h4 text-center" scope="col">CPF / CNPJ</th>
            <th class="h4 text-center" scope="col">Celular</th>
        </tr>
    </thead>
    <tbody class="js-body">
        <g:each var="customer" in="${customerList}">
            <tr class="border border-dark js-table-row" data-url="/customer/show/${customer.id}">
                <td class="border border-dark">${customer.name}</td>
                <td class="border border-dark">${customer.email}</td>
                <td class="border border-dark">${customer.cpfCnpj}</td>
                <td class="border border-dark">${customer.mobilePhone}</td>
            </tr>
        </g:each>
    </tbody>
</table>
<nav>
    <ul class="d-flex justify-content-center pagination border border-dark bg-white col ml-0 mt-0 js-pagination overflow-auto pagination-bar">
        <li class="page-item"><a class="page-link js-previous" href="#">Anterior</a></li>
    </ul>
</nav>