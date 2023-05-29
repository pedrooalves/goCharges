package gocharges

import gocharges.domain.base.BaseEntity


class Customer extends BaseEntity {

    String name
    String email
    String mobilePhone
    String cpfCnpj
    String address

    static constraints = {
        name(nullable: true, blank: false)
        email(email: true, nullable: true, blank: false, unique: true)
        mobilePhone(nullable: true, blank: false)
        cpfCnpj(nullable: true, blank: false)
        address(nullable: true, blank: false)
    }
}
