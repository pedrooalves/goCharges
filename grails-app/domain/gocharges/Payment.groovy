package gocharges

import gocharges.domain.base.BaseEntity
import gocharges.payment.enums.PaymentBillingType
import gocharges.payment.enums.PaymentStatus

class Payment extends BaseEntity {

    Payer payer
    PaymentBillingType billingType
    PaymentStatus status = PaymentStatus.PENDING
    Date dueDate
    BigDecimal value

    static constraints = {
        billingType(blank:false)
        status(blank:false)
        dueDate(blank: false, nullable: false)
        value(blank:false, nullable: false)
    }
}
