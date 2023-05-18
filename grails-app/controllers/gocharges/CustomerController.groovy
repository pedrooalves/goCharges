package gocharges

import gocharges.customer.CustomerAdapter
import org.apache.tomcat.util.scan.NonClosingJarInputStream

class CustomerController {

    CustomerService customerService

    def index() {

    }

    def register() {

        CustomerAdapter customerAdapter = new CustomerAdapter(params)
        customerService.register(customerAdapter)

        redirect(action: "list")
    }

    def list() {
        def customerList = customerService.list()

        render(view:"list", model: [customers : customerList])
    }
}
