package gocharges

import gocharges.controller.base.BaseController
import gocharges.exception.BusinessException
import gocharges.mail.MailBuilder
import gocharges.mail.MailTask
import gocharges.payment.PaymentRepository
import gocharges.payment.adapter.PaymentAdapter

class PaymentController extends BaseController {

    PaymentService paymentService
    PayerService payerService
    def mailService

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
            Customer customer = getCurrentCustomer()
            PaymentAdapter paymentAdapter = new PaymentAdapter(params)
            Payment payment = paymentService.save(paymentAdapter, customer)

            Map<String, Object> mailParams = MailBuilder.buildNewPaymentMessage(payment, customer)
            sendMailThread(mailParams)

            Map validation = [success: true, message: "Cobrança criada com sucesso", type: "save"]
            chain(action: "index", model: [validation: validation, showNewPaymentForm: false])
        } catch (BusinessException businessException) {
            Map validation = [success: false, message: businessException.getMessage(), type: "save"]
            chain(action: "index", model: [validation: validation, showNewPaymentForm: true])
        }
    }

    public Map edit() {
        Long id = Long.parseLong(params.id)
        List<Payer> payerList = payerService.list(params, getCurrentCustomer())
        Payment payment = PaymentRepository.query([id: id, customer: getCurrentCustomer()]).get()

        render(view: "edit", model: [payment: payment, payerList: payerList])
    }

    public Map update() {
        try {
            PaymentAdapter adapter = new PaymentAdapter(params)
            Long id = Long.parseLong(params.id)

            paymentService.update(id, adapter, getCurrentCustomer())

            Map validation = [success: true, message: "Cobrança editada com sucesso", type: "update"]
            chain(action: "index", model: [validation: validation, showNewPaymentForm: false])
        } catch (BusinessException businessException) {
            Map validation = [success: false, message: businessException.getMessage(), type: "update"]
            chain(action: "index", model: [validation: validation, showNewPaymentForm: false])
        }
    }

    public Map showForm() {
        chain(action: "index", model: [showNewPaymentForm: true])
    }

    public Map delete() {
        Long id = Long.parseLong(params.id)
        paymentService.delete(id, getCurrentCustomer())

        Map validation = [success: true, message: "Cobrança excluída com sucesso", type: "delete"]
        redirect(view: "index", model: [validation: validation])
    }

    private void sendMailThread(Map<String, Object> mailParams) {
        MailTask task = new MailTask(mailParams, this.mailService)
        Thread sendMail = new Thread(task)
        sendMail.start()
    }
}