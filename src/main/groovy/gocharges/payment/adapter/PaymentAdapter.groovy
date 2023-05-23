package gocharges.payment.adapter

class PaymentAdapter {

    String payerId
    String billingType
    String status
    String dueDate
    String value

    public PaymentAdapter(Map params) {
        this.payerId = params.payerId
        this.billingType = params.billingType
        this.status = params.status
        this.dueDate  = params.dueDate
        this.value = params.value
    }
}
