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
                    <g:each var="notification" in="${notificationList}">
                        <div class="d-flex row rounded border pl-3 pt-2 pb-1" >
                            <g:render template="/notification/templates/notification" model="${[notification: notification]}"/>
                        </div>
                    </g:each>
                </div>
            </div>
        </div>
    </div>
    <asset:javascript src="notificationController.js" />
</body>
</html>