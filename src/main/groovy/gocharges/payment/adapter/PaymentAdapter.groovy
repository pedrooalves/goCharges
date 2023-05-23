package gocharges.payment.adapter

class PaymentAdapter {

    String payerCpfCnpj
    String billingType
    String status
    String dueDate
    String value

    public PaymentAdapter(Map params) {
        this.payerCpfCnpj = params.payerCpfCnpj
        this.billingType = params.billingType
        this.status = params.status
        this.dueDate  = params.dueDate
        this.value = params.value
    }
}
