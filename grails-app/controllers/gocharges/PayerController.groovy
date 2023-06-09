package gocharges

import gocharges.controller.base.BaseController
import gocharges.exception.BusinessException
import gocharges.payer.PayerRepository
import gocharges.payer.adapter.PayerAdapter

class PayerController extends BaseController {

    PayerService payerService

    public index() {
        List<Payer> payers = payerService.list(params, getCurrentCustomer())
        Boolean showNewPayerForm = false

        if (chainModel) {
            Map validation = chainModel.validation
            if (chainModel.showNewPayerForm) {
                showNewPayerForm = true
            }
            render(view: "index", model: [payers: payers, validation: validation, showNewPayerForm: showNewPayerForm])
        } else {
            render(view: "index", model: [payers: payers, showNewPayerForm: showNewPayerForm])
        }
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
        } catch (Exception exception) {
            Map validation = [success: false, message: exception.getMessage(), type: "save"]
            chain(action: "index", model: [validation: validation])
        }
    }


    public form() {
        return [:]
    }

    public delete() {
        try {
            Long id = Long.valueOf(params.id)
            payerService.delete(id, getCurrentCustomer())

            Map validation = [success: true, message: "Pagador excluído com sucesso", type: "delete"]
            chain(view: "index", model: [validation: validation])
        } catch (BusinessException businessException) {
            Map validation = [success: false, message: businessException.getMessage(), type: "delete"]
            chain(action: "index", model: [validation: validation])
        } catch (Exception exception) {
            Map validation = [success: false, message: exception.getMessage(), type: "delete"]
            chain(action: "index", model: [validation: validation])
        }
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
        } catch (Exception exception) {
            Map validation = [success: false, message: exception.getMessage(), type: "update"]
            chain(action: "index", model: [validation: validation])
        }
    }

    public showForm() {
        chain(action: "index", model: [showNewPayerForm: true])
    }

    public edit() {
        Long id = Long.valueOf(params.id)
        Payer payer = PayerRepository.query([id: id, customer: getCurrentCustomer()]).get()

        if (chainModel) {
            Map validation = chainModel.validation
            render(view: "edit", model: [payer: payer, validation: validation])
        } else {
            render(view: "edit", model: [payer: payer])
        }
    }

    public restore() {
        try {
            Long id = Long.valueOf(params.id)
            payerService.restore(id, getCurrentCustomer())

            Map validation = [success: true, message: "Pagador restaurado com sucesso", type: "restore"]
            chain(action: "index", model: [validation: validation])
        } catch (BusinessException businessException) {
            Map validation = [success: false, message: exception.getMessage(), type: "restore"]
            chain(action: "index", model: [validation: validation])
        } catch (Exception exception) {
            Map validation = [success: false, message: exception.getMessage(), type: "restore"]
            chain(action: "index", model: [validation: validation])
        }
    }
}