package gocharges

import gocharges.customer.CustomerAdapter
import gocharges.exception.BusinessException

class CustomerController {

    CustomerService customerService

    def index() {
        List<Customer> customerList = customerService.list()

        if(chainModel) {
            render(view:"index", model: [customers : customerList, validation: chainModel.validation])
        } else {
            render(view:"index", model: [customers : customerList])
        }
    }

    def save() {
        try{
            CustomerAdapter customerAdapter = convertToAdapter(params)
            Customer customer = customerService.save(customerAdapter)

            Map validation = [success: true, message: "Conta criada com sucesso!"]
            chain(action: "index", model: [validation : validation])
        }catch(BusinessException exception){

            Map validation = [success: false, message: exception.getMessage()]
            chain(action: "index", model: [validation : validation])
        }
    }

    def edit() {

        render(view: "edit", model: [customer:customer])
    }

    def update() {
        CustomerAdapter customerAdapter = convertToAdapter(params)

        customerService.update(customerAdapter)

        redirect(view: "index")
    }

    def delete() {
        Long id = Long.parseLong(params.id)
        customerService.delete(id)

        redirect(view: "index")
    }

    private CustomerAdapter convertToAdapter(Map params) {
        Map customerParams = params
        CustomerAdapter customerAdapter = new CustomerAdapter(customerParams)

        return customerAdapter
    }
}
