package gocharges

import gocharges.controller.base.BaseController
import gocharges.exception.BusinessException
import gocharges.payment.PaymentRepository
import gocharges.payment.adapter.PaymentAdapter
import shared.FlashMessageType

class PaymentController extends BaseController {

    PaymentService paymentService

    public index() {
        List<Payment> payments = paymentService.list()
        Boolean showNewPaymentForm = false

        if (chainModel?.showNewPaymentForm) {
            showNewPaymentForm = chainModel.showNewPaymentForm
        }

        render(view: "index", model: [payments: payments, showNewPaymentForm: showNewPaymentForm])
    }

    public save() {
        try {
            PaymentAdapter paymentAdapter = new PaymentAdapter(params)
            Payment payment = paymentService.save(paymentAdapter, getCurrentCustomer())

            flash.message = "Cobrança criada com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessageType.ERROR
            log.info("PaymentController.save >> Erro em criar cobrança com os seguintes dados: ${params}")
        } finally {
            redirect(action: "index")
        }
    }

    public edit() {
        Long id = Long.valueOf(params.id)
        Payment payment = PaymentRepository.query([id: id]).get()

        render(view: "edit", model: [payment: payment])
    }

    public update() {
        try {
            PaymentAdapter adapter = new PaymentAdapter(params)
            Long id = Long.valueOf(params.id)

            paymentService.update(id, adapter, getCurrentCustomer())

            flash.message = "Cobrança alterada com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessageType.ERROR
            log.info("PaymentController.update >> Erro em atualizar cobrança com os seguintes dados: ${params}")
        } finally {
            redirect(action: "index")
        }
    }

    public showForm() {
        chain(action: "index", model: [showNewPaymentForm: true])
    }

    public delete() {
        try {
            Long id = Long.valueOf(params.id)
            paymentService.delete(id)

            flash.message = "Cobrança removida com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessageType.ERROR
            log.info("PaymentController.delete >> Erro em remover cobrança com o seguinte id: ${params.id}")
        } finally {
            redirect(view: "index")
        }
    }

    public confirm() {
        try {
            Long id = Long.valueOf(params.id)
            paymentService.confirm(id)

            flash.message = "Cobrança confirmada com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde."
            flash.type = FlashMessageType.ERROR
            log.info("PaymentController.confirm >> Erro em confirmar cobrança com o seguinte id: ${params.id}")
        } finally {
            redirect(action: "index")
        }
    }
}