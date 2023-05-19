package gocharges

import gocharges.customer.CustomerAdapter

class CustomerController {

    CustomerService customerService

    def index() {

    }

    def register() {
        Map customerParams = params;

        CustomerAdapter customerAdapter = new CustomerAdapter(customerParams)
        Map validation = customerService.register(customerAdapter)


        render(view: "index", model: [validation : validation])
    }

    def list() {
        List<Customer> customerList = customerService.list()

        render(view:"list", model: [customers : customerList])
    }
}
