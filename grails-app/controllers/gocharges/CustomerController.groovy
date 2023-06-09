package gocharges

import gocharges.controller.base.BaseController
import gocharges.customer.CustomerAdapter
import shared.FlashMessageType

class CustomerController extends BaseController {

    CustomerService customerService

    def index() {
        List<Customer> customerList = customerService.list(params)

        render(view: "index", model: [customerList: customerList])
    }

    def show() {
        try{
            Long id = Long.valueOf(params.id)
            Customer customer = customerService.get([id: id])

            render(view: "show", model: [customer: customer])
        } catch (Exception exception) {
            exceptionHandler(exception)
            redirect(action: "index")
        }
    }

    def create() {
        Customer customer = getCurrentCustomer()
        render(view: "create", model: [customer: customer])
    }

    def edit() {
        Customer customer = getCurrentCustomer()

        render(view: "edit", model: [customer: customer])
    }

    def update() {
        try {
            CustomerAdapter adapter = convertToAdapter(params)

            customerService.update(getCurrentCustomer(), adapter)

            flash.message = "Conta alterada com sucesso"
            flash.type = FlashMessageType.SUCCESS

            redirect(controller: "dashboard", action: "index")
        } catch (Exception exception) {
            exceptionHandler(exception)
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