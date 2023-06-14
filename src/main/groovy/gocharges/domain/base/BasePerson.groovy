package gocharges.domain.base

import grails.gorm.dirty.checking.DirtyCheck
import shared.enums.State

@DirtyCheck
public abstract class BasePerson extends BaseEntity {

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
    State state

    static mapping = {
        tablePerHierarchy false
    }

    static constraints = {
        name(nullable: true, blank: false)
        email(email: true, nullable: true, blank: false, unique: true)
        mobilePhone(nullable: true, blank: false)
        cpfCnpj(nullable: true, blank: false)
        postalCode(nullable: true, blank: false)
        address(nullable: true, blank: false)
        addressNumber(nullable: true, blank: false)
        complement(nullable: true, blank: true)
        province(nullable: true, blank: false)
        city(nullable: true, blank: false)
        state(nullable: true, blank: false)
    }
}