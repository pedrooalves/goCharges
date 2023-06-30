<div class="d-flex flex-column js-notification mr-2 js-read-${notification.isRead}" data-url="/payment/show/${notification.paymentId}" data-id="${notification.id}" style="cursor: pointer">
    <span class="h5">
        <formatTagLib:notificationTypeTitle notificationType="${notification.notificationType}" payment="${notification.payment}"/>
    </span>
    <span class="h6">
        <formatTagLib:notificationTypeMessage notificationType="${notification.notificationType}" payment="${notification.payment}"/>
    </span>
</div>