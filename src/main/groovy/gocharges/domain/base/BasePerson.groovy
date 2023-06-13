package gocharges.domain.base

import grails.gorm.dirty.checking.DirtyCheck

@DirtyCheck
public abstract class BasePerson extends BaseEntity {

    String name
    String email
    String mobilePhone
    String cpfCnpj
    String address

    static mapping = {
        tablePerHierarchy false
    }
}
