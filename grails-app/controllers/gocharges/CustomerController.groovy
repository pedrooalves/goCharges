package gocharges

import gocharges.customer.CustomerAdapter
import gocharges.customer.CustomerRepository
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
            CustomerAdapter adapter = convertToAdapter(params)
            Customer customer = customerService.save(adapter)

            Map validation = [success: true, message: "Conta criada com sucesso!"]
            chain(action: "index", model: [validation : validation])
        }catch(BusinessException exception){

            Map validation = [success: false, message: exception.getMessage()]
            chain(action: "index", model: [validation : validation])
        }
    }

    def edit() {
        Long id = Long.parseLong(params.id)
        Customer customer = CustomerRepository.query([id: id]).get()

        render(view: "edit", model: [customer:customer])
    }

    def update() {
        try{
            CustomerAdapter adapter = convertToAdapter(params)
            Long id = Long.parseLong(params.id)

            customerService.update(id, adapter)

            Map validation = [success: true, message: "Conta alterada com sucesso!"]
            chain(action: "index", model: [validation : validation])
        }catch(BusinessException exception) {
            Map validation = [success: false, message: exception.getMessage()]
            chain(action: "index", model: [validation : validation])
        }
    }

    def delete() {
        Long id = Long.parseLong(params.id)
        customerService.delete(id)

        redirect(view: "index")
    }

    private CustomerAdapter convertToAdapter(Map params) {
        Map customerParams = params
        CustomerAdapter adapter = new CustomerAdapter(customerParams)

        return adapter
    }
}