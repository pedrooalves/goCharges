package gocharges

import gocharges.auth.Role
import gocharges.auth.User
import gocharges.auth.UserRole
import gocharges.auth.role.RoleRepository
import gocharges.auth.user.UserRepository
import gocharges.auth.user.adapter.UserAdapter
import gocharges.auth.userrole.UserRoleRepository
import gocharges.exception.BusinessException
import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    public void save(UserAdapter adapter) {
        User user = new User()
        user.customer = new Customer()
        user.username = adapter.username
        user.password = adapter.password
        user.enabled = adapter.enabled
        user.accountExpired = adapter.accountExpired
        user.accountLocked = adapter.accountLocked
        user.passwordExpired = adapter.passwordExpired

        user.save(failOnError: true)

        Role role = RoleRepository.query([authority: "ROLE_USER"]).get()
        if (!role) {
            role = new Role()
            role.authority = "ROLE_USER"
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
        if (params.username.isBlank() || params.password.isBlank() || params.confirmPassword.isBlank()) {
            throw new BusinessException("É preciso preencher todos os campos")
        }

        if (UserRepository.query([username: params.username]).get()) {
            throw new BusinessException("E-mail já cadastrado")
        }

        if (!(params.password == params.confirmPassword)) {
            throw new BusinessException("As senhas precisam ser iguais")
        }
    }
}
