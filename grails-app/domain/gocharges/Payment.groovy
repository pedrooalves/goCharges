package gocharges

import gocharges.payment.enums.BillingType

class Payment {

    Payer payer
    BillingType billingType
    String status = "PENDENTE"
    Date dueDate
    BigDecimal value

    static constraints = {
        billingType(blank:false)
        status(inList: ["RECEBIDA","VENCIDA","PENDENTE"])
        dueDate(blank: false, nullable: false)
        value(blank:false, nullable: false)
    }
}
