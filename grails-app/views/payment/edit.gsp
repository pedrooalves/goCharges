<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Listagem de Payer</title>
</head>
<body>
    <div class="container d-flex justify-content-center mb-3">
        <div>
            <div class="card mb-3 mt-3 p-5 bg-gogreen text-center text-white">
                <h1 class="display-4">Editar Cobrança</h1>
            </div>

            <div class="card">
                <g:form class="card-body mb-3" name="payerForm" url="[controller: 'payment', action: 'update']">
                    <div class="form-group mb-3">
                        <label for="payer-select" class="mb-2 fw-bold">Pagador</label>
                        <select class="form-select" id="payer-select" name="payerId">
                            <option value="">Nenhum selecionado</option>
                            <g:each var="payer" in="${payerList}">
                                <option value="${payer.id}">${payer.name}</option>
                            </g:each>
                        </select><br/>
                    </div>

                    <div class="form-group mb-3">
                        <label for="billingType-select" class="mb-2">Tipo de Recebimento</label>
                        <select class="form-select" id="billingType-select" name="billingType">
                            <option value="">Nenhum selecionado</option>
                            <option value="BANK_SLIP">Boleto</option>
                            <option value="DEBIT_CARD">Cartão de Débito</option>
                            <option value="PIX">Pix</option>
                        </select>
                    </div>

                    <div class="form-group mb-3">
                        <label for="dueDate" class="mb-2">Data de Vencimento</label>
                        <input class="form-control" id="dueDate" type="date" name="dueDate"
                               value='<FormatTagLib:isoDateNotation date="${payment.dueDate}"/>'/><br>
                    </div>

                    <div class="form-group mb-3">
                        <label for="value" class="mb-2">Valor</label>
                        <input class="form-control" id="value" type="text" name="value" value="${payment.value}"/><br/>
                    </div>

                    <div class="navbar d-flex justify-content-space-between">
                        <a href="/payment"><input class="btn btn-outline-secondary" type="button"
                                                  name="buttonCancelar" value="Cancelar"/></a>
                        <button type="submit" name="id" value="${payment.id}" class="btn bg-gogreen text-white ml-3">
                            Editar
                        </button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</body>
</html>