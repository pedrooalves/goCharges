package gocharges

import gocharges.controller.base.BaseController
import gocharges.exception.BusinessException
import gocharges.payer.PayerRepository
import gocharges.payer.adapter.PayerAdapter

class PayerController extends BaseController {

    PayerService payerService

    public index() {
        List<Payer> payerList = payerService.list(params, getCurrentCustomer())

        render(view: "index", model: [payerList: payerList])
    }

    public create() {
        render(view: "create")
    }

    public save() {
        try {
            PayerAdapter payerAdapter = new PayerAdapter(params)
            payerService.save(payerAdapter, getCurrentCustomer())

            Map validation = [success: true, message: "Conta criada com sucesso", type: "save"]
            chain(action: "index", model: [validation: validation])
        } catch (BusinessException businessException) {
            Map validation = [success: false, message: businessException.getMessage(), type: "save"]
            chain(action: "index", model: [validation: validation, showNewPayerForm: true])
        }
    }

    public delete() {
        Long id = Long.valueOf(params.id)
        payerService.delete(id, getCurrentCustomer())

        Map validation = [success: true, message: "Pagador excluído com sucesso", type: "delete"]
        chain(view: "index", model: [validation: validation])
    }

    public update() {
        try {
            PayerAdapter adapter = new PayerAdapter(params)
            Long id = Long.valueOf(params.id)
            payerService.update(id, adapter, getCurrentCustomer())

            Map validation = [success: true, message: "Pagador salvo com sucesso", type: "update"]
            chain(action: "index", model: [validation: validation])
        } catch (BusinessException businessException) {
            Map validation = [success: false, message: businessException.getMessage(), type: "update"]
            chain(action: "index", model: [validation: validation])
        }
    }

    public edit() {
        Long id = Long.valueOf(params.id)
        Payer payer = PayerRepository.query([id: id, customer: getCurrentCustomer()]).get()

        render(view: "edit", model: [payer: payer])
    }
}
