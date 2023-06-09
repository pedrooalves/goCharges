package gocharges

import gocharges.controller.base.BaseController
import gocharges.exception.BusinessException
import gocharges.payment.PaymentRepository
import gocharges.payment.adapter.PaymentAdapter
import shared.FlashMessageType

class PaymentController extends BaseController {

    PaymentService paymentService
    PayerService payerService

    public Map index() {
        Customer customer = getCurrentCustomer()
        List<Payment> paymentList = paymentService.list(params, customer)
        List<Payer> payerList = payerService.list(params, customer)
        Boolean showNewPaymentForm = false

        if (chainModel) {
            Map validation = chainModel.validation
            showNewPaymentForm = chainModel.showNewPaymentForm
            render(view: "index", model: [paymentList: paymentList, payerList: payerList, validation: validation, showNewPaymentForm: showNewPaymentForm])
        } else {
            render(view: "index", model: [paymentList: paymentList, payerList: payerList, showNewPaymentForm: showNewPaymentForm])
        }
    }

    public Map save() {
        try {
            PaymentAdapter paymentAdapter = new PaymentAdapter(params)
            Payment payment = paymentService.save(paymentAdapter, getCurrentCustomer())

            flash.message = "Cobrança criada com sucesso!"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde."
            flash.type = FlashMessageType.ERROR
            log.info("Erro na execução do método Save do PaymentController com os seguintes dados: ${params}")
        } finally {
            redirect(action: "index")
        }
    }

    public Map edit() {
        Long id = Long.valueOf(params.id)
        List<Payer> payerList = payerService.list(params, getCurrentCustomer())
        Payment payment = PaymentRepository.query([id: id, customer: getCurrentCustomer()]).get()

        render(view: "edit", model: [payment: payment, payerList: payerList])
    }

    public Map update() {
        try {
            PaymentAdapter adapter = new PaymentAdapter(params)
            Long id = Long.valueOf(params.id)

            paymentService.update(id, adapter, getCurrentCustomer())

            flash.message = "Cobrança editada com sucesso!"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde."
            flash.type = FlashMessageType.ERROR
            log.info("Erro na execução do método Update do PaymentController com o seguinte id: ${params.id}")
        } finally {
            redirect(action: "index")
        }
    }

    public Map showForm() {
        chain(action: "index", model: [showNewPaymentForm: true])
    }

    public Map delete() {
        try {
            Long id = Long.valueOf(params.id)
            paymentService.delete(id, getCurrentCustomer())

            flash.message = "Cobrança removida com sucesso!"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde."
            flash.type = FlashMessageType.ERROR
            log.info("Erro na execução do método Delete do PaymentController com o seguinte id: ${params.id}")
        } finally {
            redirect(action: "index")
        }
    }

    public Map confirm() {
        try {
            Long id = Long.valueOf(params.id)
            paymentService.confirm(id)

            flash.message = "Cobrança confirmada com sucesso!"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde."
            flash.type = FlashMessageType.ERROR
            log.info("Erro na execução do método Confirm do PaymentController com o seguinte id: ${params.id}")
        } finally {
            redirect(action: "index")
        }
    }
}