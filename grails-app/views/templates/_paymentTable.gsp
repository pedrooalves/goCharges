<table>
    <thead>
        <tr>
            <th class="h4" scope="col">Forma de Pagamento</th>
            <th class="h4" scope="col">Valor</th>
            <th class="h4" scope="col">Data de Vencimento</th>
            <th class="h4" scope="col">Situação</th>
            <th class="h4" scope="col">Pagador</th>
        </tr>
    </thead>
    <tbody>
        <g:each var="payment" in="${paymentList}">
            <tr>
                <td>${payment.billingType.name}</td>
                <td>${payment.value}</td>
                <td><formatTagLib:brazilDate date="${payment.dueDate}"/></td>
                <td>${payment.status.name}</td>
                <td>${payment.payer.name}</td>
            </tr>
        </g:each>
    </tbody>
</table>