package gocharges

import gocharges.domain.base.BaseEntity
import gocharges.notification.NotificationType

class Notification extends BaseEntity {

    NotificationType notificationType
    Customer customer
    Payment payment
    Boolean unread = true
}