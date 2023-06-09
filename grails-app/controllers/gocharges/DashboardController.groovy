package gocharges

import gocharges.controller.base.BaseController
import gocharges.customer.enums.CustomerStatus

class DashboardController extends BaseController {

    DashboardService dashboardService

    def index() {
        Customer customer = getCurrentCustomer()

        if (customer.status == CustomerStatus.PENDING) {
            redirect(controller: "customer", action: "create")
        }

        Map info = dashboardService.info(getCurrentCustomer())

        render(view: "index", model: [info: info, userName: customer.name])
    }
}
