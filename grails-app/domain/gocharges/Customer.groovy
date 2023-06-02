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
        address(nullable: true, blank: false)
    }
}
