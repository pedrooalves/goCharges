package gocharges.adapter.base

import shared.Utils

public abstract class BasePersonAdapter {

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

    public BasePersonAdapter(Map params) {
        this.name = params.name
        this.email = params.email
        this.mobilePhone = Utils.removeNonNumeric(params.mobilePhone)
        this.cpfCnpj = Utils.removeNonNumeric(params.cpfCnpj)
        this.postalCode = Utils.removeNonNumeric(params.postalCode)
        this.address = params.address
        this.addressNumber = params.addressNumber
        this.complement = params.complement
        this.province = params.province
        this.city = params.city
        this.state = params.state
    }
}
