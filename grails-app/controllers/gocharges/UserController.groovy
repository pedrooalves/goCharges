package gocharges

import gocharges.exception.BusinessException
import gocharges.auth.user.adapter.UserAdapter

class UserController {

    UserService userService

    public index() {

    }

    public login() {
        render(view: "login")
    }

    public signUp() {
        if (chainModel) {
            Map validation = chainModel.validation
            render(view: "signup", model: [validation: validation])
        } else {
            render(view: "signup")
        }
    }

    public save() {
        try {
            UserAdapter userAdapter = new UserAdapter(params)
            userService.save(userAdapter)

            Map validation = [success: true, message: "Conta criada com sucesso", type: "save"]
            chain(action: "signUp", model: [validation: validation])
        } catch (BusinessException businessException) {
            Map validation = [success: false, message: businessException.getMessage(), type: "save"]
            chain(action: "signUp", model: [validation: validation])
        }
    }
}
