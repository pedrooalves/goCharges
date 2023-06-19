package gocharges

import gocharges.controller.base.BaseController
import gocharges.customer.CustomerAdapter
import gocharges.exception.BusinessException
import gocharges.auth.user.adapter.UserAdapter
import shared.FlashMessageType

class UserController extends BaseController {

    UserService userService
    CustomerService customerService

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

    public myAccount() {
        render(view: "myaccount", model: [person: getCurrentCustomer()])
    }

    public update() {
        try {
            UserAdapter userAdapter = new UserAdapter([username: params.email])
            userService.update(getCurrentUser(), userAdapter)

            CustomerAdapter customerAdapter = new CustomerAdapter(params)
            customerService.update(getCurrentCustomer(), customerAdapter)

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
