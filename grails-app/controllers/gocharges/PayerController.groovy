package gocharges

import gocharges.controller.base.BaseController
import gocharges.payer.PayerRepository
import gocharges.payer.adapter.PayerAdapter
import shared.FlashMessageType

class PayerController extends BaseController {

    PayerService payerService

    def index() {
        if (params.deletedOnly) params.put("includeDeleted", true)

        List<Payer> payerList = payerService.list(params, getCurrentCustomer())
        render(view: "index", model: [payerList: payerList])
    }

    def create() {
        render(view: "create")
    }

    def save() {
        PayerAdapter payerAdapter = new PayerAdapter(params)
        payerService.save(payerAdapter, getCurrentCustomer())

        flash.message = "Pagador criado com sucesso"
        flash.type = FlashMessageType.SUCCESS
        redirect(action: "index")
    }

    def delete() {
        Long id = Long.valueOf(params.id)
        payerService.delete(id, getCurrentCustomer())

        flash.message = "Pagador removido com sucesso"
        flash.type = FlashMessageType.SUCCESS
        redirect(action: "index")
    }

    def update() {
        PayerAdapter adapter = new PayerAdapter(params)
        Long id = Long.valueOf(params.id)
        payerService.update(id, adapter, getCurrentCustomer())

        flash.message = "Pagador alterado com sucesso"
        flash.type = FlashMessageType.SUCCESS
        redirect(action: "index")
    }

    def edit() {
        Long id = Long.valueOf(params.id)
        Payer payer = PayerRepository.query([id: id, customer: getCurrentCustomer()]).get()
        render(view: "edit", model: [payer: payer])
    }

    def restore() {
        Long id = Long.valueOf(params.id)
        payerService.restore(id, getCurrentCustomer())

        flash.message = "Pagador restaurado com sucesso"
        flash.type = FlashMessageType.SUCCESS
        redirect(action: "index")
    }
}