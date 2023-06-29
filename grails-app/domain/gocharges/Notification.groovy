package gocharges

import gocharges.domain.base.BaseEntity
import gocharges.notification.NotificationType

class Notification extends BaseEntity {

    NotificationType notificationType
    Customer customer
    Payment payment
    Boolean isRead = false

    static constraints = {
        notificationType(nullable: false, blank:false)
        customer(nullable: false, blank:false)
        payment(nullable: false, blank:false)
    }
}