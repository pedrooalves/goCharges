package gocharges.payment.adapter

import gocharges.PaymentService
import gocharges.payment.enums.PaymentBillingType
import gocharges.payment.enums.PaymentStatus
import shared.CustomDateUtils

class PaymentAdapter {

    String payerCpfCnpj
    PaymentBillingType billingType
    PaymentStatus status
    Date dueDate
    BigDecimal value

    public PaymentAdapter(Map params) {
        PaymentService.validate(params)
        this.payerCpfCnpj = params.payerCpfCnpj
        this.billingType = PaymentBillingType.valueOf(params.billingType)
        this.dueDate = CustomDateUtils.fromString(params.dueDate)
        this.value = new BigDecimal(params.value)
    }
}