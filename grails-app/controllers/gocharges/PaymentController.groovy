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
        PaymentAdapter paymentAdapter = new PaymentAdapter(params)
        paymentService.save(paymentAdapter, getCurrentCustomer())

        flash.message = "Cobrança criada com sucesso"
        flash.type = FlashMessageType.SUCCESS
        redirect(action: "index")
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
        PaymentAdapter adapter = new PaymentAdapter(params)
        Long id = Long.valueOf(params.id)
        paymentService.update(id, adapter, getCurrentCustomer())

        flash.message = "Cobrança alterada com sucesso"
        flash.type = FlashMessageType.SUCCESS
        redirect(action: "index")
    }

    def delete() {
        Long id = Long.valueOf(params.id)
        paymentService.delete(id, getCurrentCustomer())

        flash.message = "Cobrança removida com sucesso"
        flash.type = FlashMessageType.SUCCESS
        redirect(action: "index")
    }

    def confirmReceivedInCash() {
        Long id = Long.valueOf(params.id)
        paymentService.confirmReceivedInCash(id, getCurrentCustomer())

        flash.message = "Pagamento confirmado com sucesso"
        flash.type = FlashMessageType.SUCCESS
        redirect(action: "index")
    }

    def restore() {
        Long id = Long.valueOf(params.id)
        paymentService.restore(id, getCurrentCustomer())

        flash.message = "Cobrança restaurada com sucesso"
        flash.type = FlashMessageType.SUCCESS
        redirect(action: "index")
    }

    def show() {
        Payment payment = paymentService.getPayment(params, getCurrentCustomer())
        render(view: "show", model: [payment: payment])
    }
}