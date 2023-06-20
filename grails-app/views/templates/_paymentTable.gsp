<table>
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
    <tbody>
        <g:each var="payment" in="${paymentList}">
            <tr class="border border-dark col">
                <td class="border border-dark">${payment.billingType.name}</td>
                <td class="border border-dark">${payment.value}</td>
                <td class="border border-dark"><formatTagLib:brazilDate date="${payment.dueDate}"/></td>
                <td class="border border-dark">${payment.status.name}</td>
                <td class="border border-dark">${payment.payer.name}</td>
                <td class="d-flex justify-content-center">
                    <g:render template="/templates/paymentActions" model="${[payment: payment]}"/>
                </td>
            </tr>
        </g:each>
    </tbody>
</table>