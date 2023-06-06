package gocharges

import gocharges.controller.base.BaseController

class PaymentReceiptController extends BaseController {

    PaymentReceiptService paymentReceiptService

    def index() {
        Map info = paymentReceiptService.create(params.publicId, getCurrentCustomer())

        render(view: "index", params: [publidId: params.publicId], model: [info: info])
    }
}
