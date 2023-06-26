package gocharges

import gocharges.controller.base.BaseController
import gocharges.customer.CustomerAdapter
import gocharges.customer.CustomerRepository
import shared.FlashMessageType

class CustomerController extends BaseController {

    CustomerService customerService

    def index() {
        List<Customer> customerList = customerService.list(params)

        render(view: "index", model: [customerList: customerList])
    }

    def create() {
        Customer customer = getCurrentCustomer()
        render(view: "create", model: [person: customer])
    }

    def edit() {
        Long id = Long.valueOf(params.id)
        Customer customer = CustomerRepository.query([id: id]).get()

        render(view: "edit", model: [customer: customer])
    }

    def updateByAdmin() {
        try {
            CustomerAdapter adapter = convertToAdapter(params)
            flash.message = "Conta alterada com sucesso"
            flash.type = FlashMessageType.SUCCESS

        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            redirect(controller: "customer", action: "index")
        }
    }

    def update() {
        try {
            CustomerAdapter adapter = convertToAdapter(params)

            customerService.update(getCurrentCustomer(), adapter)

            flash.message = "Conta alterada com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            redirect(controller: "dashboard", action: "index")
        }
    }

    def delete() {
        try {
            Long id = Long.valueOf(params.id)
            customerService.delete(id)

            flash.message = "Conta removida com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            redirect(view: "index")
        }
    }

    private CustomerAdapter convertToAdapter(Map params) {
        Map customerParams = params
        CustomerAdapter adapter = new CustomerAdapter(customerParams)

        return adapter
    }
}