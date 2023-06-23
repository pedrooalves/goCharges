package gocharges

import gocharges.controller.base.BaseController
import gocharges.payer.PayerRepository
import gocharges.payment.PaymentRepository
import gocharges.payment.adapter.PaymentAdapter
import shared.FlashMessageType

class PaymentController extends BaseController {

    PaymentService paymentService
    PayerService payerService

    def index() {
        if (params.deletedOnly) {
            params.put("includeDeleted", true)
        }

        Customer customer = getCurrentCustomer()
        List<Payment> paymentList = paymentService.list(params, customer)
        List<Payer> payerList = payerService.list([includeDeleted: true], customer)

        render(view: "index", model: [paymentList: paymentList, payerList: payerList])
    }

    def create() {
        List<Payer> payerList = PayerRepository.query([customer: getCurrentCustomer()]).list()

        render(view: "create", model: [payerList: payerList])
    }

    def save() {
        try {
            PaymentAdapter paymentAdapter = new PaymentAdapter(params)
            paymentService.save(paymentAdapter, getCurrentCustomer())

            flash.message = "Cobrança criada com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            String action = (flash.type == FlashMessageType.SUCCESS) ? "index" : "create"
            chain(action: action, model: [paramsId: params.id])
        }
    }

    def edit() {
        String paramsId = params.id ? params.id : chainModel?.paramsId
        Long id = Long.valueOf(paramsId)
        params.remove("id")
        List<Payer> payerList = payerService.list(params, getCurrentCustomer())
        Payment payment = PaymentRepository.query([id: id, customer: getCurrentCustomer()]).get()

        render(view: "edit", model: [payment: payment, payerList: payerList])
    }

    def update() {
        try {
            PaymentAdapter adapter = new PaymentAdapter(params)
            Long id = Long.valueOf(params.id)
            paymentService.update(id, adapter, getCurrentCustomer())

            flash.message = "Cobrança alterada com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            String action = (flash.type == FlashMessageType.SUCCESS) ? "index" : "edit"
            chain(action: action, model: [paramsId: params.id])
        }
    }

    def delete() {
        try {
            Long id = Long.valueOf(params.id)
            paymentService.delete(id, getCurrentCustomer())

            flash.message = "Cobrança removida com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            redirect(action: "index")
        }
    }

    def confirmReceivedInCash() {
        try {
            Long id = Long.valueOf(params.id)
            paymentService.confirmReceivedInCash(id, getCurrentCustomer())

            flash.message = "Pagamento confirmado com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            redirect(action: "index")
        }
    }

    def restore() {
        try {
            Long id = Long.valueOf(params.id)
            paymentService.restore(id, getCurrentCustomer())

            flash.message = "Cobrança restaurada com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            redirect(view: "index")
        }
    }
}