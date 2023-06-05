package gocharges

import gocharges.exception.BusinessException
import gocharges.payment.PaymentRepository
import gocharges.payment.adapter.PaymentAdapter
import shared.FlashMessageType

class PaymentController {

    PaymentService paymentService

    public Map index() {
        List<Payment> payments = paymentService.list()
        Boolean showNewPaymentForm = false

        if (chainModel?.showNewPaymentForm) {
            showNewPaymentForm = chainModel.showNewPaymentForm
        }

        render(view: "index", model: [payments: payments, showNewPaymentForm: showNewPaymentForm])
    }

    public Map save() {
        try {
            PaymentAdapter paymentAdapter = new PaymentAdapter(params)
            Payment payment = paymentService.save(paymentAdapter)

            flash.message = "Cobrança criada com sucesso!"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } finally {
            redirect(action: "index")
        }
    }

    public Map edit() {
        Long id = Long.valueOf(params.id)
        Payment payment = PaymentRepository.query([id: id]).get()

        render(view: "edit", model: [payment: payment])
    }

    public Map update() {
        try {
            PaymentAdapter adapter = new PaymentAdapter(params)
            Long id = Long.valueOf(params.id)

            paymentService.update(id, adapter)

            flash.message = "Cobrança alterada com sucesso!"
            flash.type = FlashMessageType.SUCCESS

        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR

        } finally {
            redirect(action: "index")
        }
    }

    public Map showForm() {
        chain(action: "index", model: [showNewPaymentForm: true])
    }

    public Map delete() {
        Long id = Long.valueOf(params.id)
        paymentService.delete(id)

        flash.message = "Cobrança excluída com sucesso!"
        flash.type = FlashMessageType.SUCCESS

        redirect(view: "index")
    }
}