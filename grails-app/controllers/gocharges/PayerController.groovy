package gocharges

import gocharges.exception.BusinessException
import gocharges.payer.PayerRepository
import gocharges.payer.adapter.PayerAdapter

class PayerController {

    PayerService payerService

    public index() {
        List<Payer> payers = payerService.list()

        if (chainModel) {
            Map validation = chainModel.validation
            render(view: "index", model: [payers: payers, validation: validation])
        } else {
            render(view: "index", model: [payers: payers])
        }
    }

    public create() {
        render(view: "create")
    }

    public save() {
        try {
            PayerAdapter payerAdapter = new PayerAdapter(params)
            payerService.save(payerAdapter)

            Map validation = [success: true, message: "Conta criada com sucesso", type: "save"]
            chain(action: "index", model: [validation: validation])
        } catch (BusinessException e) {
            Map validation = [success: false, message: e.getMessage(), type: "save"]
            chain(action: "index", model: [validation: validation])
        }
    }

    public delete() {
        Long id = Long.valueOf(params.id)
        payerService.delete(id)

        Map validation = [success: true, message: "Pagador excluído com sucesso", type: "delete"]
        chain(view: "index", model: [validation: validation])
    }

    public update() {
        try {
            PayerAdapter adapter = new PayerAdapter(params)
            Long id = Long.valueOf(params.id)
            payerService.update(id, adapter)

            Map validation = [success: true, message: "Pagador salvo com sucesso", type: "update"]
            chain(action: "index", model: [validation: validation])
        } catch (BusinessException e) {
            Map validation = [success: false, message: e.getMessage(), type: "update"]
            chain(action: "index", model: [validation: validation])
        }
    }

    public edit() {
        Long id = Long.valueOf(params.id)
        Payer payer = PayerRepository.query([id: id]).get()

        if (chainModel) {
            Map validation = chainModel.validation
            render(view: "edit", model: [payer: payer, validation: validation])
        } else {
            render(view: "edit", model: [payer: payer])
        }
    }
}
