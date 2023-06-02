package gocharges

import gocharges.auth.User
import gocharges.customer.enums.CustomerStatus
import grails.plugin.springsecurity.SpringSecurityService

class DashboardController {

    SpringSecurityService springSecurityService
    DashboardService dashboardService

    def index() {
        User currentUser = springSecurityService.getCurrentUser()
        String userName = currentUser.customer.name

        if (currentUser.customer.status == CustomerStatus.PENDING) {
            redirect(controller: "customer", action: "create")
        }

        Map info = dashboardService.info()

        render(view: "index", model: [info: info, userName: userName])
    }
}
