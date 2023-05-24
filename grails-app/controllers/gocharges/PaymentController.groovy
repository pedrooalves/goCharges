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
            if (chainModel.showNewPaymentForm) {
                showNewPaymentForm = true
            }
            render(view: "index", model: [payments:payments, validation: validation, showNewPaymentForm: showNewPaymentForm])
        } else {
            render(view: "index", model: [payments:payments, showNewPaymentForm: showNewPaymentForm])
        }
    }

    public save() {
        try {
            PaymentAdapter paymentAdapter = new PaymentAdapter(params)
            Payment payment = paymentService.save(paymentAdapter)

            println(payment)

            Map validation = [success:true, message:"Cobrança criada com sucesso", type:"save"]
            chain(action: "index", model:[validation:validation])
        } catch(BusinessException e) {
            Map validation = [success:false, message:e.getMessage(), type:"save"]
            chain(action: "index", model: [validation:validation, showNewPaymentForm: true])
        }
    }

    public edit() {
        Long id = Long.parseLong(params.id)
        Payment payment = paymentService.findById(id)
        Payer payer = paymentService.findPayerById(payment.payer.id)

        render(view: "edit", model: [payment : payment, payer:payer])
    }

    public update() {
        try{
            PaymentAdapter adapter = new PaymentAdapter(params)
            Long id = Long.parseLong(params.id)

            paymentService.update(id, adapter)

            Map validation = [success:true, message:"Cobrança editada com sucesso", type:"update"]
            chain(action: "index", model:[validation:validation])
        }catch(BusinessException exception){
            Map validation = [success:false, message:exception.getMessage(), type:"save"]
            chain(action: "index", model: [validation:validation])
        }
    }

    public showForm() {
        chain(action: "index", model: [showNewPaymentForm: true])
    }
}
