<div class="d-flex flex-column ml-2 js-notification" data-url="/payment/show/${notification.payment.id}"  style="cursor: pointer">
    <span class="h5 ${!notification.isRead ? 'font-weight-bold' : 'text-muted'}">
        <formatTagLib:notificationTypeTitle notificationType="${notification.notificationType}" payment="${notification.payment}"/>
    </span>
    <span class="h6 ${!notification.isRead ? 'font-weight-bold' : 'text-muted'}">
        <formatTagLib:notificationTypeMessage notificationType="${notification.notificationType}" payment="${notification.payment}"/>
    </span>
</div>