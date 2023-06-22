<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <title>Notificações</title>
    <asset:javascript src="goCharges.js" />
</head>
<body>
    <div class="container col-12 main-container" >
        <div class="card-body row">
            <div class="col 6">
                <h1 class="display-4 mb-4">Notificações</h1>
                <div class="border rounded col js-my-account">
                    <div class="row d-flex text-center rounded border">
                        <span class="h4 pl-5 pt-2 pb-1">Selecionar todas</span>
                    </div>
                    <g:each var="notification" in="${notificationList}">
                        <div class="row d-flex text-center rounded border">
                            <span class="h4 pl-5 pt-2 pb-1">Título da Notificação</span>
                        </div>
                    </g:each>
                </div>
            </div>
        </div>
    </div>
</body>
</html>