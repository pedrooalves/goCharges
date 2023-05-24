package gocharges

import gocharges.exception.BusinessException
import gocharges.payment.adapter.PaymentAdapter

class PaymentController {

    PaymentService paymentService

    public index() {
        List<Payment> payments = paymentService.list()
        Boolean showNewPaymentForm = false
        
        if(chainModel) {
            Map validation = chainModel.validation
            showNewPaymentForm = chainModel.showNewPaymentForm
            render(view: "index", model: [payments: payments, validation: validation, showNewPaymentForm: showNewPaymentForm])
        } else {
            render(view: "index", model: [payments: payments, showNewPaymentForm: showNewPaymentForm])
        }
    }

    public save() {
        try {
            PaymentAdapter paymentAdapter = new PaymentAdapter(params)
            Payment payment = paymentService.save(paymentAdapter)

            Map validation = [success: true, message: "Cobrança criada com sucesso", type: "save"]
            chain(action: "index", model: [validation: validation, showNewPaymentForm: false])
        } catch(BusinessException e) {
            Map validation = [success: false, message: e.getMessage(), type: "save"]
            chain(action: "index", model: [validation: validation, showNewPaymentForm: true])
        }
    }

    public edit() {
        Long id = Long.parseLong(params.id)
        Payment payment = paymentService.findById(id)

        render(view: "edit", model: [payment : payment])
    }

    public update() {
        try{
            PaymentAdapter adapter = new PaymentAdapter(params)
            Long id = Long.parseLong(params.id)

            paymentService.update(id, adapter)

            Map validation = [success:true, message:"Cobrança editada com sucesso", type:"update"]
            chain(action: "index", model: [validation: validation, showNewPaymentForm: false])
        }catch(BusinessException exception){
            Map validation = [success:false, message:exception.getMessage(), type:"update"]
            chain(action: "index", model: [validation: validation, showNewPaymentForm: false])
        }
    }

    public showForm() {
        chain(action: "index", model: [showNewPaymentForm: true])
    }

    public delete() {
        Long id = Long.parseLong(params.id)
        paymentService.delete(id)

        Map validation = [success:true, message:"Cobrança excluída com sucesso", type:"delete"]
        redirect(view: "index", model: [validation: validation])
    }
}
