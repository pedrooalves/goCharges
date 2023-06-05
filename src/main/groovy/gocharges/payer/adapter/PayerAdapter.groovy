package gocharges.payer.adapter

import gocharges.Payer

class PayerAdapter {

    String name
    String email
    String mobilePhone
    String cpfCnpj
    String postalCode
    String address
    String addressNumber
    String complement
    String province
    String city
    String state

    public PayerAdapter(Map params) {
        this.name = params.get("name")
        this.email = params.email
        this.mobilePhone = params.mobilePhone
        this.cpfCnpj = params.cpfCnpj
        this.postalCode = params.postalCode
        this.address = params.address
        this.addressNumber = params.addressNumber
        this.complement = params.complement
        this.province = params.province
        this.city = params.city
        this.state = params.state
    }
}