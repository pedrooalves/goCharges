package gocharges.domain.base

import grails.gorm.dirty.checking.DirtyCheck

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
    String state

    static mapping = {
        tablePerHierarchy false
    }
}