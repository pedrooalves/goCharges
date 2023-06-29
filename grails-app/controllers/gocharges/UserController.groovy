package gocharges

import gocharges.controller.base.BaseController
import gocharges.auth.user.adapter.UserAdapter
import shared.FlashMessageType

class UserController extends BaseController {

    UserService userService

    def index() {
    }

    def login() {
        render(view: "login")
    }

    def signUp() {
        render(view: "signup")
    }

    def save() {
        UserAdapter adapter = new UserAdapter(params)
        userService.save(adapter)

        flash.message = "Conta criada com sucesso."
        flash.type = FlashMessageType.SUCCESS
        redirect(action: "login")
    }

    def myAccount() {
        render(view: "myaccount", model: [customer: getCurrentCustomer()])
    }

    def changePassword() {
        UserAdapter adapter = new UserAdapter(params)
        String currentPassword = params.currentPassword
        userService.changePassword(getCurrentUser(), adapter, currentPassword)

        flash.message = "Senha alterada com sucesso"
        flash.type = FlashMessageType.SUCCESS
        redirect(action: "myAccount")
    }
}
