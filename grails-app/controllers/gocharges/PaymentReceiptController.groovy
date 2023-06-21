package gocharges

import gocharges.controller.base.BaseController

class PaymentReceiptController extends BaseController {

    PaymentService paymentService

    def index() {
        try {
            render(view: "index", model: [payment: paymentService.buildReceipt(params.publicId)])
        } catch (Exception exception) {
            exceptionHandler(exception)

            redirect(controller: "payment", action: "index")
        }
    }
}
