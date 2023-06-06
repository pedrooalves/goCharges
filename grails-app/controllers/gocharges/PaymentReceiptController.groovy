package gocharges

import gocharges.controller.base.BaseController
import gocharges.payment.PaymentRepository

class PaymentReceiptController extends BaseController {

    def index() {
        Payment payment = PaymentRepository.query([publicId: params.publicId, ignoreCustomer: true]).get()

        render(view: "index", model: [payment: payment])
    }
}
