package gocharges.domain.base

public abstract class BaseEntity {

    Boolean deleted = false
    Date dateCreated = new Date()

    static mapping = {
        tablePerHierarchy false
    }
}
