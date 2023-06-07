package gocharges

import gocharges.controller.base.BaseController
import gocharges.customer.enums.CustomerStatus

class DashboardController extends BaseController {


    def index() {
        Customer userCustomer = getCurrentCustomer()

        if (userCustomer.status == CustomerStatus.PENDING) {
            redirect(controller: "customer", action: "create")
        }

        render(view: "index")
    }
}
