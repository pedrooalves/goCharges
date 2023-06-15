package gocharges

import gocharges.customer.enums.CustomerStatus
import gocharges.domain.base.BasePerson

class Customer extends BasePerson {

    CustomerStatus status = CustomerStatus.PENDING
}