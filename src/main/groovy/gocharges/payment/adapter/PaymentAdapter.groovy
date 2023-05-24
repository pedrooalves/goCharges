package gocharges.payment.adapter

import gocharges.exception.BusinessException
import gocharges.payment.enums.PaymentBillingType
import gocharges.payment.enums.PaymentStatus

import java.text.SimpleDateFormat

class PaymentAdapter {

    String payerCpfCnpj
    PaymentBillingType billingType
    PaymentStatus status
    Date dueDate
    BigDecimal value

    public PaymentAdapter(Map params) {
        validateParams(params)

        this.payerCpfCnpj = params.payerCpfCnpj
        this.billingType = PaymentBillingType.valueOf(params.billingType)
        this.dueDate  = new SimpleDateFormat("dd/MM/yyyy").parse(params.dueDate)
        this.value = new BigDecimal(params.value)
    }

    private void validateParams(Map params) {
        if (params.payerCpfCnpj.isBlank() || params.billingType.isBlank() || params.dueDate.isBlank() ||
                params.value.isBlank()) {
            throw new BusinessException("É preciso preencher todos os campos")
        }
    }
}
