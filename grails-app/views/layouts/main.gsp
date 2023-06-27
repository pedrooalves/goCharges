<!DOCTYPE html>
<%@ page import="shared.FlashMessageType" %>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="GoCharges"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:javascript src="jquery-3.5.1.js"/>
    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="gocharges.css"/>

    <asset:javascript src="toastController.js"/>
    <g:layoutHead/>
</head>
<body class="js-main-container">
    <nav class="navbar sidebar-content navbar-expand-lg navbar-dark bg-dark navbar-static-top row" role="navigation">
        <div class="container-fluid justify-content-space-between">
            <p class="display-4 text-white text-logo">GoCharges</p>
            <sec:ifNotLoggedIn>
                <div>
                    <a class="btn btn-gogreen mr-3" href="/user/login">Login</a>
                    <a class="btn btn-gogreen mr-3" href="/user/signUp">Cadastrar</a>
                </div>
            </sec:ifNotLoggedIn>
            <sec:ifLoggedIn>
                <div>
                    <a href="/payer" class="link-gogreen ml-3">Pagadores</a>
                    <a href="/payment" class="link-gogreen ml-3">Cobranças</a>
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <a href="" class="link-gogreen ml-3">Clientes</a>
                    </sec:ifAnyGranted>
                </div>
                <div>
                    <a class="btn btn-gogreen mr-3" href="/user/myAccount">Minha Conta</a>
                    <a class="btn btn-gogreen mr-3" href='${request.contextPath}/logoff' method='POST'>Sair</a>
                </div>
            </sec:ifLoggedIn>
        </div>
    </nav>

    <g:if test="${flash?.message}">
        <g:render template="/toast/templates/feedbackToast"/>
    </g:if>

    <g:layoutBody/>

    <asset:javascript src="application.js"/>
</body>
</html>
