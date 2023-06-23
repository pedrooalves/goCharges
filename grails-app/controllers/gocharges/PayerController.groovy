package gocharges

import gocharges.controller.base.BaseController
import gocharges.payer.PayerRepository
import gocharges.payer.adapter.PayerAdapter
import shared.FlashMessageType

class PayerController extends BaseController {

    PayerService payerService

    def index() {
        String deletedOnly = params.deletedOnly
        if (deletedOnly && !Boolean.valueOf(deletedOnly)) params.put("includeDeleted", true)

        List<Payer> payerList = payerService.list(params, getCurrentCustomer())

        render(view: "index", model: [payerList: payerList])
    }

    def create() {
        render(view: "create")
    }

    def save() {
        try {
            PayerAdapter payerAdapter = new PayerAdapter(params)
            payerService.save(payerAdapter, getCurrentCustomer())

            flash.message = "Pagador criado com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            redirect(action: "index")
        }
    }

    def delete() {
        try {
            Long id = Long.valueOf(params.id)
            payerService.delete(id, getCurrentCustomer())

            flash.message = "Pagador removido com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            redirect(action: "index")
        }
    }

    def update() {
        try {
            PayerAdapter adapter = new PayerAdapter(params)
            Long id = Long.valueOf(params.id)
            payerService.update(id, adapter, getCurrentCustomer())

            flash.message = "Pagador alterado com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            redirect(action: "index")
        }
    }

    def edit() {
        Long id = Long.valueOf(params.id)
        Payer payer = PayerRepository.query([id: id, customer: getCurrentCustomer()]).get()

        render(view: "edit", model: [payer: payer])
    }

    public restore() {
        try {
            Long id = Long.valueOf(params.id)
            payerService.restore(id, getCurrentCustomer())

            flash.message = "Pagador restaurado com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (Exception exception) {
            exceptionHandler(exception)
        } finally {
            redirect(action: "index")
        }
    }

    def show() {
        println(params)
        try{
            Long id = Long.valueOf(params.id)
            Payer payer = payerService.get([id: id, includeDeleted: true], getCurrentCustomer())

            render(view: "show", model: [payer: payer])
        } catch (Exception exception) {
            exceptionHandler(exception)
            redirect(action: "index")
        }
    }
}