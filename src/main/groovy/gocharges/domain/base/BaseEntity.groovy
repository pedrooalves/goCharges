package gocharges.domain.base

import grails.gorm.dirty.checking.DirtyCheck

@DirtyCheck
public abstract class BaseEntity {

    Boolean deleted = false
    Date dateCreated = new Date()

    static mapping = {
        tablePerHierarchy false
    }
}
