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
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR

            redirect(action: "signup")
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessageType.ERROR
            log.info("Erro na execução da action Save do UserController com os seguintes dados: ${params}")

            redirect(action: "signup")
        }
    }
}
