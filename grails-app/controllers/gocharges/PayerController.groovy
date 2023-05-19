package gocharges

import gocharges.payer.adapter.PayerAdapter

class PayerController {

    PayerService payerService
    Map<String, String> registerError

    def index() {
        if(!registerError) {
            Map<String, String> error = new HashMap<>()
            error.put("message", null)
            render(view: "index", model : [error:error])
        } else {
            render(view: "index", model : [error:registerError])
        }


    }

    def register() {
        PayerAdapter payerAdapter = new PayerAdapter(params)
        Map<String, String> validation = payerService.register(payerAdapter)


        if(validation.success) {
            redirect(action: "listar")
        } else {
            registerError = validation
            redirect(action: "index?message" + validation.message)
        }
    }

    def listar() {
        def payers = payerService.listPayers()

        render(view: "list", model: [payers:payers])
    }
}
