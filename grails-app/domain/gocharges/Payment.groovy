package gocharges

import gocharges.payment.enums.BillingType
import gocharges.payment.enums.Status

class Payment {

    Payer payer
    BillingType billingType
    Status status = Status.valueOf("PENDENTE")
    Date dueDate
    BigDecimal value

    static constraints = {
        billingType(blank:false)
        status(blank:false)
        dueDate(blank: false, nullable: false)
        value(blank:false, nullable: false)
    }
}
