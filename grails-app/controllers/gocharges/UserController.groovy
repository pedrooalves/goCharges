package gocharges

import gocharges.auth.User
import gocharges.exception.BusinessException
import gocharges.auth.user.adapter.UserAdapter
import shared.FlashMessageType
import grails.plugin.springsecurity.SpringSecurityService

class UserController {

    UserService userService
    SpringSecurityService springSecurityService

    def index() {
    }

    def login() {
        render(view: "login")
    }

    def signUp() {
        render(view: "signup")
    }

    def save() {
        try {
            UserAdapter adapter = new UserAdapter(params)
            userService.save(adapter)

            flash.message = "Conta criada com sucesso."
            flash.type = FlashMessageType.SUCCESS

            redirect(action: "login")
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR

            redirect(action: "signUp")
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessageType.ERROR
            log.info("UserController.save >> Erro em criar user com os seguintes dados: ${params}")

            redirect(action: "signUp")
        }
    }

    def myAccount() {
        User user = springSecurityService.getCurrentUser()

        if (chainModel) {
            Map validation = chainModel.validation
            render(view: "myaccount", model: [user: user, validation: validation])
        } else {
            render(view: "myaccount", model: [user: user])
        }
    }

    def update() {
        try {
            UserAdapter adapter = new UserAdapter(params)
            Long id = Long.valueOf(params.id)
            String currentPassword = params.currentPassword
            userService.update(id, adapter, currentPassword)

            flash.message = "Informações salvas com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessageType.ERROR
            log.info("UserController.update >> Erro em atualizar user com os seguintes dados: ${params}")
        } finally {
            redirect(action: "myAccount")
        }
    }
}
