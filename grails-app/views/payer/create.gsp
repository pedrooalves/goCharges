<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Novo Pagador</title>
</head>
<body>
    <div class="container my-8 d-flex justify-center w-25">
        <g:form class="card-body" name="payerForm" url="[controller: 'payer', action: 'save']">
            <h1 class="display-4">Novo Pagador</h1>

            <div class="form-group">
                <label>Nome</label> <input class="form-control" type="text" name="name" value=""/>
            </div>

            <div class="form-group">
                <label>Email</label> <input class="form-control" type="email" name="email" value="">
            </div>

            <div class="form-group">
                <label>Celular</label> <input class="form-control" type="text" name="mobilePhone" value=""/>
            </div>

            <div class="form-group">
                <label>CPF / CNPJ</label> <input class="form-control" type="text" name="cpfCnpj" value=""/>
            </div>

            <div class="form-group">
                <label>Endereço</label> <input class="form-control" type="text" name="address" value=""/>
            </div>

            <input class="btn btn-primary" type="submit" value="Cadastrar"/>
            <a class="text-decoration-none btn btn-primary" href="${createLink(controller:'payer', action:'index')}">
                Cancelar
            </a>
        </g:form>
    </div>
</body>
</html>