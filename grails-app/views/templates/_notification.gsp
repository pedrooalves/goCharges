<div class="d-flex flex-column ml-2 js-notification js-read-${notification.isRead}" data-url="/payment/show/${notification.payment.id}" data-id="${notification.id}"  style="cursor: pointer">
    <span class="h5">
        <formatTagLib:notificationTypeTitle notificationType="${notification.notificationType}" payment="${notification.payment}"/>
    </span>
    <span class="h6">
        <formatTagLib:notificationTypeMessage notificationType="${notification.notificationType}" payment="${notification.payment}"/>
    </span>
</div>