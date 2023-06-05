package gocharges

import gocharges.customer.enums.CustomerStatus
import gocharges.domain.base.BasePerson

class Customer extends BasePerson {

    CustomerStatus status = CustomerStatus.PENDING

    static constraints = {
        name(nullable: true, blank: false)
        email(email: true, nullable: true, blank: false, unique: true)
        mobilePhone(nullable: true, blank: false)
        cpfCnpj(nullable: true, blank: false)
        postalCode(nullable: true, blank: false)
        address(nullable: true, blank: false)
        addressNumber(nullable: true, blank: false)
        complement(nullable: true, blank: false)
        province(nullable: true, blank: false)
        city(nullable: true, blank: false)
        state(nullable: true, blank: false)
    }
}