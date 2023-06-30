<table class="mb-0 js-table">
    <thead>
        <tr class="border border-dark">
            <th class="h4 text-center" scope="col">Forma de Pagamento</th>
            <th class="h4 text-center" scope="col">Valor</th>
            <th class="h4 text-center" scope="col">Data de Vencimento</th>
            <th class="h4 text-center" scope="col">Situação</th>
            <th class="h4 text-center" scope="col">Pagador</th>
            <th class="h4 text-center" scope="col">Ações</th>
        </tr>
    </thead>
    <tbody class="js-body">
        <g:each var="payment" in="${paymentList}">
            <tr data-url="/payment/show/${payment.id}" class="border border-dark col js-table-row">
                <td class="border border-dark">${payment.billingType.name}</td>
                <td class="border border-dark">${payment.value}</td>
                <td class="border border-dark"><formatTagLib:brazilDate date="${payment.dueDate}"/></td>
                <td class="border border-dark">${payment.status.name}</td>
                <td class="border border-dark">${payment.payer.name}</td>
                <td class="payment-action-row">
                    <div class="d-flex flex-row justify-content-end col-6" >
                        <g:render template="/payment/templates/actions" model="${[payment: payment]}"/>
                    </div>
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