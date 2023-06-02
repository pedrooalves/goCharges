package gocharges.auth.user.adapter

import gocharges.UserService

class UserAdapter {

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    public UserAdapter(Map params) {
        UserService.validate(params)
        this.username = params.username
        this.password = params.password
        this.accountExpired = false
        this.accountLocked = false
        this.passwordExpired = false
    }

    static constraints = {
        password(nullable: false, blank: false, password: true)
        username(nullable: false, blank: false, unique: true)
    }
}
