package gocharges

import gocharges.exception.BusinessException
import gocharges.auth.user.adapter.UserAdapter
import shared.FlashMessageType

class UserController {

    UserService userService

    public index() {

    }

    public login() {
        render(view: "login")
    }

    public signUp() {
        render(view: "signup")
    }

    public save() {
        try {
            UserAdapter userAdapter = new UserAdapter(params)
            userService.save(userAdapter)

            flash.message = "Conta criada com sucesso."
            flash.type = FlashMessageType.SUCCESS

            redirect(action: "login")
        } catch(BusinessException e) {
            flash.message = e.getMessage()
            flash.type = FlashMessageType.ERROR

            redirect(action: "signUp")
        }
    }
}
