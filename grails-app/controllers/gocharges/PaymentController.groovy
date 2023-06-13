package gocharges

import gocharges.controller.base.BaseController
import gocharges.exception.BusinessException
import gocharges.payer.PayerRepository
import gocharges.payment.PaymentRepository
import gocharges.payment.adapter.PaymentAdapter

class PaymentController extends BaseController {

    PaymentService paymentService

    public Map index() {
        List<Payment> payments = paymentService.list()

        if (chainModel) {
            Map validation = chainModel.validation
            render(view: "index", model: [payments: payments, validation: validation])
        } else {
            render(view: "index", model: [payments: payments])
        }
    }

    public Map create() {
        List<Payer> payerList = PayerRepository.query([customer: getCurrentCustomer()]).list()

        render(view: "create", model: [payerList: payerList])
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
        Payment payment = PaymentRepository.query([id: id]).get()

        render(view: "edit", model: [payment: payment])
    }

    public Map update() {
        try {
            PaymentAdapter adapter = new PaymentAdapter(params)
            Long id = Long.valueOf(params.id)

            paymentService.update(id, adapter)

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

    public Map delete() {
        try {
            Long id = Long.valueOf(params.id)
            paymentService.delete(id)

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