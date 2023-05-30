package gocharges

import gocharges.exception.BusinessException
import gocharges.payer.PayerRepository
import gocharges.payer.adapter.PayerAdapter
import shared.FlashMessageType

class PayerController {

    PayerService payerService

    public index() {
        List<Payer> payers = payerService.list()
        Boolean showNewPayerForm = false

        if(chainModel) {
            if (chainModel) {
                showNewPayerForm = true
            }
            render(view: "index", model: [payers:payers, showNewPayerForm: showNewPayerForm])
        } else {
            render(view: "index", model: [payers:payers, showNewPayerForm: showNewPayerForm])
        }
    }

    public save() {
        try {
            PayerAdapter payerAdapter = new PayerAdapter(params)
            payerService.save(payerAdapter)

            flash.message = "Pagador criado com sucesso!"
            flash.type = FlashMessageType.SUCCESS

        } catch(BusinessException e) {
            flash.message = e.getMessage()
            flash.type = FlashMessageType.ERROR

        } finally {
            chain(action: "index", model: [showNewPaymentForm: false])
        }
    }


    public form() {
        return [:]
    }

    public delete() {
        Long id = Long.parseLong(params.id)
        payerService.delete(id)

        flash.message = "Pagador deletado com sucesso!"
        flash.type = FlashMessageType.SUCCESS

        redirect(view: "index")
    }

    public update() {
        try {
            PayerAdapter adapter = new PayerAdapter(params)
            Long id = Long.parseLong(params.id)
            payerService.update(id, adapter)

            flash.message = "Pagador alterado com sucesso!"
            flash.type = FlashMessageType.SUCCESS

        } catch (BusinessException e) {
            flash.message = e.getMessage()
            flash.type = FlashMessageType.ERROR

        } finally {
            redirect(action: "index")
        }
    }

    public showForm() {
        chain(action: "index", model: [showNewPayerForm: true])
    }

    public edit() {
        Long id = Long.parseLong(params.id)
        Payer payer = PayerRepository.query([id: id]).get()

        render(view: "edit", model: [payer:payer])
    }
}
