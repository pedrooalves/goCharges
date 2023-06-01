package gocharges

import gocharges.auth.User
import gocharges.exception.BusinessException
import gocharges.auth.user.adapter.UserAdapter
import grails.plugin.springsecurity.SpringSecurityService

class UserController {

    UserService userService
    SpringSecurityService springSecurityService

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

            Map validation = [success:true, message:"Conta criada com sucesso", type:"save"]
            chain(action: "signUp", model:[validation:validation])
        } catch(BusinessException e) {
            Map validation = [success:false, message:e.getMessage(), type:"save"]
            chain(action: "signUp", model: [validation:validation])
        }
    }

    public myAccount() {
        User user = springSecurityService.getCurrentUser()

        if(chainModel) {
            Map validation = chainModel.validation
            render(view: "myaccount", model: [user: user, validation: validation])
        } else {
            render(view: "myaccount", model: [user: user])
        }
    }

    public update() {
        try {
            UserAdapter adapter = new UserAdapter(params)
            Long id = Long.parseLong(params.id)
            userService.update(id, adapter)

            Map validation = [success: true, message: "Informações salvas com sucesso", type: "update"]
            chain(action: "myAccount", model: [validation: validation])
        } catch (BusinessException businessException) {
            Map validation = [success:false, message:businessException.getMessage(), type:"update"]
            chain(action: "myAccount", model: [validation:validation])
        }
    }
}
