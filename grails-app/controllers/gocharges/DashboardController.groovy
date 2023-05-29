package gocharges

import gocharges.auth.User
import grails.plugin.springsecurity.SpringSecurityService

class DashboardController {

    SpringSecurityService springSecurityService

    def index() {

        User currentUser = springSecurityService.loadCurrentUser()


        if(!currentUser.customer.name) {
            redirect(controller: "customer", action: "create")
        }

        render(view: "index")
    }
}
