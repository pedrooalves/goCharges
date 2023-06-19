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
import grails.plugin.springsecurity.SpringSecurityService
import shared.Utils

@Transactional
class UserService {

    SpringSecurityService springSecurityService
    CustomerService customerService

    public void save(UserAdapter adapter) {
        validateSave(adapter)

        User user = new User()
        user.username = adapter.username
        user.password = adapter.password
        user.enabled = adapter.enabled
        user.accountExpired = adapter.accountExpired
        user.accountLocked = adapter.accountLocked
        user.passwordExpired = adapter.passwordExpired

        user.customer = customerService.save(user.username)

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

    private void validateSave(UserAdapter adapter) {
        validateUsername(adapter)
        validatePassword(adapter)
        if (UserRepository.query([username: adapter.username]).get()) throw new BusinessException(Utils.getMessageProperty("default.not.unique.message", "e-mail"))
    }

    private void validateUsername(UserAdapter adapter) {
        if (!adapter.username) throw new BusinessException(Utils.getMessageProperty("default.null.message", "e-mail"))
    }

    private void validatePassword(UserAdapter adapter) {
        if (!adapter.password) throw new BusinessException(Utils.getMessageProperty("default.null.message", "senha"))
        if (adapter.password != adapter.confirmPassword) throw new BusinessException(Utils.getMessageProperty("default.password.doesnt.match.message", null))
    }

    private void validateUpdate(User user, UserAdapter adapter) {
        validateUsername(adapter)

        Boolean hasUserWithSameEmail = UserRepository.query([username: adapter.username, includeDeleted: true, "id[ne]": user.id])
                .get().asBoolean()
        if (hasUserWithSameEmail) throw new BusinessException("E-mail já em uso!")
    }

    public User update(User user, UserAdapter adapter) {
        validateUpdate(user, adapter)

        user.username = adapter.username

        return user.save(failOnError: true)
    }

    public User changePassword(User user, UserAdapter adapter, String currentPassword) {
        validatePassword(adapter)

        if (!springSecurityService.passwordEncoder.matches(currentPassword, user.password)) throw new BusinessException("Senha incorreta")

        user.password = adapter.password

        return user.save(failOnError: true)
    }
}
