package gocharges.payment.adapter

import gocharges.payment.enums.PaymentStatus

class PaymentAdapter {

    String payerCpfCnpj
    String billingType
    PaymentStatus status
    String dueDate
    String value

    public PaymentAdapter(Map params) {
        this.payerCpfCnpj = params.payerCpfCnpj
        this.billingType = params.billingType
        this.dueDate  = params.dueDate
        this.value = params.value
    }
}
