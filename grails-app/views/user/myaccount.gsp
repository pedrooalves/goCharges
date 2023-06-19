<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main" />
    <title>Minha Conta</title>
</head>
<body>
    <div class="container d-flex justify-content-start col-10 mb-3 main-container">
        <div class="col-12">
            <h1 class="display-4 mb-4">Minha conta</h1>
            <div class="border rounded col js-my-account">
                <div class="row d-flex justify-content-center rounded border">
                    <span class="h2">Informações</span>
                </div>
                <div class="d-flex justify-content-center mt-3">
                    <a class="update-tab border-right border-dark pr-3 nav-link js-link-tab active" href="#">Dados Cadastrais</a>
                    <a class="update-tab pl-3 nav-link js-link-tab" href="#">Senha</a>
                </div>
                <div class="js-form">
                    <g:render template="/templates/personForm" model="[formTitle: 'Dados Comerciais', controller: 'user', action: 'update']"/>
                </div>
            </div>
        </div>
    </div>
    <asset:javascript src="my-account-controller.js"/>
</body>
</html>