package gocharges

import gocharges.controller.base.BaseController
import gocharges.auth.user.adapter.UserAdapter
import shared.FlashMessageType
import grails.plugin.springsecurity.SpringSecurityService

class UserController extends BaseController {

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
        } catch (Exception exception) {
            exceptionHandler(exception)

            redirect(action: "signUp")
        }
    }

    def myAccount() {
        render(view: "myaccount", model: [user: getCurrentUser()])

    }

    def update() {
        try {
            UserAdapter adapter = new UserAdapter(params)
            Long id = Long.valueOf(params.id)
            String currentPassword = params.currentPassword
            userService.update(id, adapter, currentPassword)

            flash.message = "Informações salvas com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            redirect(action: "myAccount")
        }
    }
}
