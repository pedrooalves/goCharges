<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main" />
    <title>Minha conta</title>
</head>
<body>
    <div class="container d-flex justify-content-center col mb-3 main-container">
        <div class="myaccount-div">
            <h1 class="display-4 mb-4">Minha conta</h1>
            <div class="border rounded col js-my-account">
                <div class="row d-flex justify-content-center rounded border">
                    <span class="h2">Informações</span>
                </div>
                <div class="d-flex justify-content-center mt-3">
                    <a class="update-tab border-right border-dark pr-3 nav-link js-link-tab active h5" data-type="person" href="#">Dados comerciais</a>
                    <a class="update-tab pl-3 nav-link js-link-tab h5" data-type="password" href="#">Senha</a>
                </div>
                <div class="d-flex justify-content-center">
                    <div class="create-form">
                        <div class="js-person js-set-hidden">
                            <g:render template="/customer/templates/update/form"/>
                        </div>
                        <div class="js-password js-set-hidden" style="display: none">
                            <div class="d-flex justify-content-center">
                                <h3>Para alterar a senha de acesso, informe sua senha atual e a nova senha.</h3>
                            </div>
                            <g:render template="/user/templates/changePassword"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <asset:javascript src="my-account-controller.js"/>
</body>
</html>