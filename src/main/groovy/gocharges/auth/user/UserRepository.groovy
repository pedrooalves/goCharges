package gocharges.auth.user

import gocharges.auth.User
import grails.gorm.DetachedCriteria

class UserRepository {

    public static DetachedCriteria<User> query(Map search) {
        DetachedCriteria<User> query = User.where {
            if (search.containsKey("username")) {
                eq("username", search.username)
            }
        }

        return query
    }
}
