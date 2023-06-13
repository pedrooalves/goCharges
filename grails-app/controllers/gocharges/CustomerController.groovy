package gocharges

import gocharges.controller.base.BaseController
import gocharges.customer.CustomerAdapter
import gocharges.exception.BusinessException
import shared.FlashMessageType

class CustomerController extends BaseController {

    CustomerService customerService

    def index() {
        List<Customer> customerList = customerService.list()

        render(view: "index", model: [customers: customerList])
    }

    def create() {
        String userEmail = getCurrentCustomer().email
        render(view: "create", model: [userEmail: userEmail])
    }

    def edit() {
        Customer customer = getCurrentCustomer()

        render(view: "edit", model: [customer: customer])
    }

    def update() {
        try {
            CustomerAdapter adapter = convertToAdapter(params)

            customerService.update(adapter, getCurrentCustomer())

            flash.message = "Conta alterada com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessageType.ERROR
            log.info("CustomerController.update >> Erro em atualizar customer com os seguintes dados: ${params}")
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
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessageType.ERROR
            log.info("CustomerController.delete >> Erro em remover customer com o seguinte id: ${params.id}")
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