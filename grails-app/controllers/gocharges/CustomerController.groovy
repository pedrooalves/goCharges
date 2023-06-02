package gocharges

import gocharges.customer.CustomerAdapter
import gocharges.exception.BusinessException
import shared.FlashMessageType
import grails.plugin.springsecurity.SpringSecurityService

class CustomerController {

    SpringSecurityService springSecurityService
    CustomerService customerService

    def index() {
        List<Customer> customerList = customerService.list()

        render(view: "index", model: [customers: customerList])
    }

    def create() {
        String userEmail = springSecurityService.getCurrentUser().username
        render(view: "create", model: [userEmail: userEmail])
    }

    def save() {
        try {
            CustomerAdapter adapter = convertToAdapter(params)

            customerService.save(adapter)

            flash.message = "Conta criada com sucesso!"
            flash.type = FlashMessageType.SUCCESS

            redirect(controller: "dashboard", action: "index")
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR

            redirect(action: "create")
        }
    }

    def edit() {
        Customer userCustomer = springSecurityService.getCurrentUser().customer

        render(view: "edit", model: [customer: userCustomer])
    }

    def update() {
        try {
            CustomerAdapter adapter = convertToAdapter(params)
            customerService.update(adapter)

            flash.message = "Conta alterada com sucesso!"
            flash.type = FlashMessageType.SUCCESS

        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR

        } finally {
            redirect(action: "index")
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