package gocharges.payment.adapter

import gocharges.payment.enums.PaymentBillingType
import gocharges.payment.enums.PaymentStatus

import java.text.SimpleDateFormat

class PaymentAdapter {

    String payerCpfCnpj
    PaymentBillingType billingType
    PaymentStatus status
    Date dueDate
    String value

    public PaymentAdapter(Map params) {
        this.payerCpfCnpj = params.payerCpfCnpj
        this.billingType = PaymentBillingType.valueOf(params.billingType)
        this.dueDate  = new SimpleDateFormat("dd/MM/yyyy").parse(params.dueDate)
        this.value = params.value
    }
}
