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
                    <div class="row d-flex text-center rounded border">
                        <div class="pl-5 pt-2 pb-1">
                            <input class="mr-2 js-select-all" type="checkbox">
                            <span class="h4">Selecionar todas</span>
                        </div>
                    </div>
                    <g:each var="notification" in="${notificationList}">
                        <div class="d-flex row rounded border pl-5 pt-2 pb-1">
                            <input class="mr-2" type="checkbox">
                            <div class="d-flex flex-column ml-2 js-notification" data-url="/payment/show/${notification.payment.id}" style="cursor: pointer">
                                <span class="h5 font-weight-bold">
                                    <formatTagLib:notificationTypeTitle notificationType="${notification.notificationType}" payment="${notification.payment}"/>
                                </span>
                                <span class="h6">
                                    <formatTagLib:notificationTypeMessage notificationType="${notification.notificationType}" payment="${notification.payment}"/>
                                </span>
                            </div>
                        </div>
                    </g:each>
                </div>
            </div>
        </div>
    </div>
    <asset:javascript src="notification-controller.js" />
</body>
</html>