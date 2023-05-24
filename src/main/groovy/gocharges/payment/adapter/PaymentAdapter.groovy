package gocharges.payment.adapter

import gocharges.payment.enums.PaymentBillingType
import gocharges.payment.enums.PaymentStatus

class PaymentAdapter {

    String payerCpfCnpj
    PaymentBillingType billingType
    PaymentStatus status
    String dueDate
    String value

    public PaymentAdapter(Map params) {
        this.payerCpfCnpj = params.payerCpfCnpj
        this.billingType = PaymentBillingType.valueOf(params.billingType)
        this.dueDate  = params.dueDate
        this.value = params.value
    }
}
