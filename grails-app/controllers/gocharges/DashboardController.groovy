package gocharges

import gocharges.auth.User
import gocharges.customer.enums.CustomerStatus
import grails.plugin.springsecurity.SpringSecurityService

class DashboardController {

    SpringSecurityService springSecurityService

    def index() {
        User currentUser = springSecurityService.loadCurrentUser()

        if (currentUser.customer.status == CustomerStatus.PENDING) {
            redirect(controller: "customer", action: "create")
        }

        render(view: "index")
    }
}
