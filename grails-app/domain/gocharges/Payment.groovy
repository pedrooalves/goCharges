package gocharges

import gocharges.payment.enums.BillingType
import gocharges.payment.enums.PaymentStatus

class Payment {

    Payer payer
    BillingType billingType
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
