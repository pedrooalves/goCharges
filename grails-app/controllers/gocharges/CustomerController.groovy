package gocharges

import gocharges.controller.base.BaseController
import gocharges.customer.CustomerAdapter
import gocharges.exception.BusinessException

class CustomerController extends BaseController {

    CustomerService customerService

    def index() {
        List<Customer> customerList = customerService.list()

        if (chainModel) {
            render(view: "index", model: [customers: customerList, validation: chainModel.validation])
        } else {
            render(view: "index", model: [customers: customerList])
        }
    }

    def create() {
        String userEmail = getCurrentCustomer().email
        render(view: "create", model: [userEmail: userEmail])
    }

    def save() {
        try {
            CustomerAdapter adapter = convertToAdapter(params)
            customerService.save(adapter)

            Map validation = [success: true, message: "Conta criada com sucesso!"]
            redirect(controller: "dashboard", action: "index")
        } catch (BusinessException exception) {
            Map validation = [success: false, message: exception.getMessage()]
            redirect(controller: "dashboard", action: "index")
        }
    }

    def edit() {
        Customer userCustomer = getCurrentCustomer()

        render(view: "edit", model: [customer: userCustomer])
    }

    def update() {
        try {
            CustomerAdapter adapter = convertToAdapter(params)

            customerService.update(adapter, getCurrentCustomer())

            Map validation = [success: true, message: "Conta alterada com sucesso!"]
            chain(action: "index", model: [validation: validation])
        } catch (BusinessException exception) {
            Map validation = [success: false, message: exception.getMessage()]
            chain(action: "index", model: [validation: validation])
        }
    }

    def delete() {
        Long id = Long.valueOf(params.id)
        customerService.delete(id)

        redirect(view: "index")
    }

    private CustomerAdapter convertToAdapter(Map params) {
        Map customerParams = params
        CustomerAdapter adapter = new CustomerAdapter(customerParams)

        return adapter
    }
}