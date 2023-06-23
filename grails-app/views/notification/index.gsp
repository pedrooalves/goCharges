<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <title>Notificações</title>
</head>
<body>
    <div class="container col-12 main-container js-notification-index-container" >
        <div class="card-body row">
            <div class="col 6">
                <h1 class="display-4 mb-4">Notificações</h1>
                <div class="border rounded col js-my-account">
                    <div class="row d-flex text-center justify-content-between rounded border">
                        <div class="pl-5 pt-2 pb-1">
                            <input class="mr-2 js-select-all" type="checkbox">
                            <span class="h4">Selecionar todas</span>
                        </div>
                        <button class="btn btn-outline-secondary mr-5 js-mark-as-read">Marcar</button>
                    </div>
                    <g:each var="notification" in="${notificationList}">
                        <div class="d-flex row rounded border pl-5 pt-2 pb-1" >
                            <input class="mr-2 js-checkbox" type="checkbox" value="${notification.id}">
                            <g:render template="/templates/notification" model="${[notification: notification]}"/>
                        </div>
                    </g:each>
                </div>
            </div>
        </div>
    </div>
    <asset:javascript src="notification-controller.js" />
</body>
</html>