package gocharges.auth.role

import gocharges.auth.Role
import gocharges.auth.role.enums.RoleAuthority
import grails.gorm.DetachedCriteria

class RoleRepository {

    public static DetachedCriteria<Role> query(Map search) {
        DetachedCriteria<Role> query = Role.where {
            if(search.containsKey("authority")) {
                eq("authority", search.authority.toString())
            }
        }

        return query
    }
}
