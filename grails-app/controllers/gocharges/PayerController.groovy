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

            flash.message = "Pagador criado com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessageType.ERROR
            log.info("PayerController.save >> Erro em criar pagador com os seguintes dados: ${params}")
        } finally {
            redirect(action: "index")
        }
    }


    public form() {
        return [:]
    }

    public delete() {
        try {
            Long id = Long.valueOf(params.id)
            payerService.delete(id, getCurrentCustomer())

            flash.message = "Pagador removido com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessageType.ERROR
            log.info("PayerController.delete >> Erro em remover pagador com o seguinte id: ${params.id}")
        } finally {
            redirect(view: "index")
        }
    }

    public update() {
        try {
            PayerAdapter adapter = new PayerAdapter(params)
            Long id = Long.valueOf(params.id)
            payerService.update(id, adapter, getCurrentCustomer())

            flash.message = "Pagador alterado com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessageType.ERROR
            log.info("PayerController.update >> Erro em atualizar pagador com os seguintes dados: ${params}")
        } finally {
            redirect(action: "index")
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

            flash.message = "Pagador restaurado com sucesso"
            flash.type = FlashMessageType.SUCCESS
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.type = FlashMessageType.ERROR
        } catch (Exception exception) {
            flash.message = "Erro inesperado, tente novamente mais tarde"
            flash.type = FlashMessageType.ERROR
            log.info("PayerController.restore >> Erro ao restaurar pagador com o seguinte id: ${params.id}")
        } finally {
            redirect(view: "index")
        }
    }
}