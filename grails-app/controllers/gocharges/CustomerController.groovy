package gocharges

import gocharges.customer.CustomerAdapter

class CustomerController {

    CustomerService customerService

    def index() {

    }

    def signin() {

    }

    def save() {

        try{
            Map customerParams = params

            CustomerAdapter customerAdapter = new CustomerAdapter(customerParams)
            Customer customer = customerService.save(customerAdapter)

            Map validation = [success: true, message: "Conta criada com sucesso!"]
            render(view: "index", model: [validation : validation])
        }catch(RuntimeException exception){

            Map validation = [success: false, message: exception.getMessage()]
            render(view: "index", model: [validation : validation])
        }
    }

    def list() {
        List<Customer> customerList = customerService.list()

        render(view:"list", model: [customers : customerList])
    }

}
