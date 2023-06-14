package gocharges

import gocharges.controller.base.BaseController
import gocharges.exception.BusinessException

class PaymentReceiptController extends BaseController {

    PaymentService paymentService

    def index() {
        try {
            Payment payment = paymentService.getReceipt(params.publicId)

            render(view: "index", model: [payment: payment])
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessage.ERROR

            redirect(controller: "payment", action: "index")
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessage.ERROR
            log.info("Erro na execução da action index do PaymentReceiptController com o publicId: ${params.publicId}")

            redirect(controller: "payment", action: "index")
        }
    }
}