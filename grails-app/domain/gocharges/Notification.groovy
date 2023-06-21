package gocharges

import gocharges.domain.base.BaseEntity

class Notification extends BaseEntity {

    Customer customer
    Payment payment
    Boolean unread = true
}