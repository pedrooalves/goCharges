package gocharges.auth.user.adapter

import gocharges.UserService

class UserAdapter {

    String username
    String password
    String confirmPassword
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    public UserAdapter(Map params) {
        this.username = params.username
        this.password = params.password
        this.confirmPassword = params.confirmPassword
        this.accountExpired = false
        this.accountLocked = false
        this.passwordExpired = false
    }

    static constraints = {
        password(nullable: false, blank: false, password: true)
        username(nullable: false, blank: false, unique: true)
    }
}
