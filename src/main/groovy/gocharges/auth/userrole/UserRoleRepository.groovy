package gocharges.auth.userrole

import gocharges.auth.UserRole
import grails.gorm.DetachedCriteria

class UserRoleRepository {

    public static DetachedCriteria<UserRole> query(Map search) {
        DetachedCriteria<UserRole> query = UserRole.where {
            if (search.containsKey("user")) {
                eq("user", search.user)
            }

            if (search.containsKey("role")) {
                eq("role", search.role)
            }
        }

        return query
    }
}
