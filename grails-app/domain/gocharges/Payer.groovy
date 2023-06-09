package gocharges

import gocharges.domain.base.BaseEntity

class Payer extends BaseEntity {

    String name
    String email
    String mobilePhone
    String cpfCnpj
    String address

    Customer customer

    static constraints = {
        name(blank: false)
        email(email: true, blank: false, unique: 'customer')
        mobilePhone(blank: false)
        cpfCnpj(blank: false, unique: 'customer', size: 11..14)
        address(blank: false)
    }
}