package gocharges

import gocharges.domain.base.BaseEntity


class Customer extends BaseEntity {

    String name
    String email
    String mobilePhone
    String cpfCnpj
    String address

    static constraints = {
        name(nullable: false)
        email(email: true, nullable: false, unique: true)
        mobilePhone(nullable: false)
        cpfCnpj(nullable: false)
        address(nullable: false)
    }
}
