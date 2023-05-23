package gocharges.payment.adapter

class PaymentAdapter {

    String name
    String email
    String mobilePhone
    String cpfCnpj
    String address

    public PaymentAdapter(Map params) {
        this.name = params.get("name")
        this.email = params.email
        this.mobilePhone = params.mobilePhone
        this.cpfCnpj  = params.cpfCnpj
        this.address = params.address
    }
}
