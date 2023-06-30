<%@ page import="shared.enums.State"%>
<g:form class="card-body" name="customerForm" url="[controller: 'payer', action: 'save']">
    <h1 class="h1">Adicionar pagador</h1>

    <div class="form-group">
        <label>Nome</label>
        <input class="form-control js-name" type="text" name="name" placeholder="Ex: João da Silva"/>
    </div>

    <div class="form-group">
        <label>E-mail</label>
        <input class="form-control js-email" type="email" name="email" placeholder="Ex: joao.silva@email.com"/>
    </div>

    <div class="form-group">
        <label>Celular</label>
        <input class="form-control js-mobilePhone" type="text" name="mobilePhone" placeholder="(00) 00000-0000"/>
    </div>

    <div class="form-group">
        <label>CPF / CNPJ</label>
        <input class="form-control js-cpf-cnpj" type="text" name="cpfCnpj" placeholder="000.000.000-00"/>
    </div>

    <div class="js-postal-code-container">
        <h4 class="font-weight-bold mb-2">Endereço</h4>

        <div class="form-row mb-2">
            <div class="col">
                <label class="mb-1">CEP</label>
                <input class="form-control js-postal-code" type="text" pattern="\d{5}-?\d{3}" name="postalCode"
                       placeholder="00000-000"/>
            </div>
            <div class="d-flex align-items-end col">
                <a href="https://buscacepinter.correios.com.br/" target="_blank" class="btn btn-outline-dark"
                   style="text-decoration: none;"> Não sei meu CEP </a>
            </div>
        </div>

        <div class="form-row ml-1">
            <span class="form-row js-postal-code-warning text-danger"></span>
        </div>

        <div class="form-row mb-3">
            <div class="col">
                <label class="mb-1">Rua</label>
                <input class="form-control js-address js-set" type="text" name="address" placeholder="Ex: Rua Maria de Souza"/>
            </div>
            <div class="col">
                <label class="mb-1">Número</label>
                <input class="form-control js-address-number" type="text" name="addressNumber" placeholder="00000"/>
            </div>
        </div>

        <div class="form-row mb-3">
            <div class="col">
                <label class="mb-1">Complemento</label>
                <input class="form-control js-complement" type="text" name="complement" placeholder="Ex: Bloco 00, Apartamento 00"/>
            </div>
            <div class="col">
                <label class="mb-1">Bairro</label>
                <input class="form-control js-province js-set" type="text" name="province" placeholder="Ex: Jardim Campos Elíseos"/>
            </div>
        </div>

        <div class="form-row mb-3">
            <div class="col">
                <label class="mb-1 col">Cidade</label>
                <input class="form-control js-city js-set" type="text" name="city" placeholder="Ex: Salvador"/>
            </div>
            <div class="col">
                <label class="mb-1">Estado</label> <br/>
                <g:select class="form-select col js-state js-set" name="state" data-constraint="select" from="${State.values()}"
                          noSelection="${['': 'Selecione um estado']}" optionValue="name"/>
            </div>
        </div>
    </div>

    <div class="navbar d-flex justify-content-space-between">
        <a href="/dashboard" class="btn btn-outline-secondary" style="text-decoration: none" type="button">Cancelar</a>
        <button class="btn bg-gogreen text-white" type="submit">Cadastrar</button>
    </div>
</g:form>
<asset:javascript src="postalCodeUtils.js"/>
<asset:javascript src="personFormController.js"/>