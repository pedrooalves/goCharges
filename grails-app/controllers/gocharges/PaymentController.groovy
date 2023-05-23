package gocharges

import gocharges.exception.BusinessException
import gocharges.payment.adapter.PaymentAdapter


class PaymentController {

    PaymentService paymentService

    public index() {
        List<Payment> payments = paymentService.list()
        
        if(chainModel) {
            println("Erro chegando na view")
            Map validation = chainModel.validation
            render(view: "index", model: [payments:payments, validation: validation])
        } else {
            render(view: "index", model: [payments:payments])
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
            chain(action: "index", model: [validation:validation])
        }
    }
}
