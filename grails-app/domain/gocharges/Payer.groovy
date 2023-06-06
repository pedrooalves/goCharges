package gocharges

import gocharges.domain.base.BasePerson

class Payer extends BasePerson {

    Customer customer

    static constraints = {
        name(blank: false)
        email(email: true, blank: false, unique: 'customer')
        mobilePhone(blank: false)
        cpfCnpj(blank: false, unique: 'customer', size: 11..14)
        postalCode(nullable: false, blank: false)
        address(nullable: false, blank: false)
        addressNumber(nullable: false, blank: false)
        complement(nullable: true, blank: true)
        province(nullable: false, blank: false)
        city(nullable: false, blank: false)
        state(nullable: false, blank: false)
    }
}