package gocharges.payer.adapter

import gocharges.Payer

class PayerAdapter {

    String name
    String email
    String mobilePhone
    String cpfCnpj
    String address

    public PayerAdapter(Map params) {
        this.name = params.get("name");
        this.email = params.get("email")
        this.mobilePhone = params.get("mobilePhone")
        this.cpfCnpj  = params.get("cpfCnpj")
        this.address = params.get("address")
    }
}
