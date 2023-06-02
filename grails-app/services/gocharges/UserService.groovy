package gocharges

import gocharges.auth.Role
import gocharges.auth.User
import gocharges.auth.UserRole
import gocharges.auth.role.RoleRepository
import gocharges.auth.role.enums.RoleAuthority
import gocharges.auth.user.UserRepository
import gocharges.auth.user.adapter.UserAdapter
import gocharges.auth.userrole.UserRoleRepository
import gocharges.exception.BusinessException
import grails.gorm.transactions.Transactional
import shared.Utils

@Transactional
class UserService {

    public save(UserAdapter adapter) {
        User user = new User()
        user.username = adapter.username
        user.password = adapter.password
        user.enabled = adapter.enabled
        user.accountExpired = adapter.accountExpired
        user.accountLocked = adapter.accountLocked
        user.passwordExpired = adapter.passwordExpired

        user.save(failOnError: true)

        Role role = RoleRepository.query([authority: RoleAuthority.ROLE_USER]).get()
        if (!role) {
            role = new Role()
            role.authority = RoleAuthority.ROLE_USER
            role.save(failOnError: true)
        }

        UserRole userRole = UserRoleRepository.query([user: user, role: role]).get()
        if (!userRole) {
            userRole = new UserRole()
            userRole.user = user
            userRole.role = role
            userRole.save(failOnError: true)
        }
    }

    public static void validate(Map params) {
        if (!params.username) throw new BusinessException(Utils.getMessageProperty("default.null.message", "e-mail"))

        if (!params.password) throw new BusinessException(Utils.getMessageProperty("default.null.message", "senha"))

        if (UserRepository.query([username: params.username]).get()) throw new BusinessException(Utils.getMessageProperty("default.not.unique.message", "e-mail"))

        if (!(params.password == params.confirmPassword)) throw new BusinessException(Utils.getMessageProperty("default.password.doesnt.match.message", null))
    }
}
