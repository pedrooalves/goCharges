package gocharges

import gocharges.controller.base.BaseController

class PaymentReceiptController extends BaseController {

    PaymentService paymentService

    def index() {
        render(view: "index", model: [payment: paymentService.buildReceipt(params.publicId)])
    }
}
