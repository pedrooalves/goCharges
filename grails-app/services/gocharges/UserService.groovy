package gocharges

import gocharges.auth.Role
import gocharges.auth.User
import gocharges.auth.UserRole
import gocharges.auth.role.RoleRepository
import gocharges.auth.user.UserRepository
import gocharges.auth.user.adapter.UserAdapter
import gocharges.auth.userrole.UserRoleRepository
import gocharges.exception.BusinessException
import gocharges.payer.PayerRepository
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Transactional
class UserService {

    SpringSecurityService springSecurityService

    public save(UserAdapter adapter) {
        User user = new User()
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
        if(!userRole) {
            userRole = new UserRole()
            userRole.user = user
            userRole.role = role
            userRole.save(failOnError: true)
        }
    }

    public static void validate(Map params) {
        if((params.password.isBlank() && !params.containsKey("currentPassword")) || params.username.isBlank()) {
            throw new BusinessException("É preciso preencher todos os campos")
        }

        if(UserRepository.query([username: params.username]).get() && !params.containsKey("id")) {
            throw new BusinessException("E-mail já cadastrado")
        }

        if(!(params.password == params.confirmPassword)) {
            throw new BusinessException("As senhas precisam ser iguais")
        }
    }

    public void validateUpdate(Long id, UserAdapter adapter) {
        User user = UserRepository.query([id: id]).get()
        if (!user) throw new BusinessException("Usuário não encontrado.")

        user = UserRepository.query([username: adapter.username, includeDeleted: true]).get()
        if (user && user.id != id) throw new BusinessException("E-mail já em uso!")
    }

    public User update(Long id, UserAdapter adapter) {
        validateUpdate(id, adapter)

        User user = UserRepository.query([id: id]).get()
        if(!springSecurityService.passwordEncoder.matches(adapter.oldPassword, user.password)) {
            throw new BusinessException("Senha incorreta")
        }

        user.username = adapter.username
        user.password = adapter.password

        user.save(failOnError: true)

        return user
    }
}
