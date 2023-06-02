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

@Transactional
class UserService {

    CustomerService customerService

    public void save(UserAdapter adapter) {
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

    public static void validate(Map params) {
        if (params.username.isBlank()) {
            throw new BusinessException("O campo e-mail é obrigatório")
        }

        if (params.password.isBlank()) {
            throw new BusinessException("O campo senha é obrigatório")
        }

        if (UserRepository.query([username: params.username]).get()) {
            throw new BusinessException("E-mail já cadastrado")
        }

        if (!(params.password == params.confirmPassword)) {
            throw new BusinessException("As senhas precisam ser iguais")
        }
    }
}
