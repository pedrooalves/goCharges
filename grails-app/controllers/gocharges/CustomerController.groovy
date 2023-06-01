package gocharges

import gocharges.customer.CustomerAdapter
import gocharges.exception.BusinessException
import grails.plugin.springsecurity.SpringSecurityService

class CustomerController {

    SpringSecurityService springSecurityService
    CustomerService customerService

    def index() {
        List<Customer> customerList = customerService.list()

        if(chainModel) {
            render(view:"index", model: [customers : customerList, validation: chainModel.validation])
        } else {
            render(view:"index", model: [customers : customerList])
        }
    }

    def create() {
        String userEmail = springSecurityService.getCurrentUser().username
        render(view: "create", model: [userEmail : userEmail])
    }

    def save() {
        try{
            CustomerAdapter adapter = convertToAdapter(params)

            customerService.save(adapter)

            Map validation = [success: true, message: "Conta criada com sucesso!"]
            redirect(controller: "dashboard", action: "index")
        }catch(BusinessException exception){
            Map validation = [success: false, message: exception.getMessage()]
            redirect(controller: "dashboard", action: "index")
        }
    }

    def edit() {
        Customer userCustomer = springSecurityService.getCurrentUser().customer

        render(view: "edit", model: [customer : userCustomer])
    }

    def update() {
        try{
            CustomerAdapter adapter = convertToAdapter(params)

            customerService.update(adapter)

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