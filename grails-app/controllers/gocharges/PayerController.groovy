package gocharges

import gocharges.controller.base.BaseController
import gocharges.exception.BusinessException
import gocharges.payer.PayerRepository
import gocharges.payer.adapter.PayerAdapter
import shared.FlashMessageType

class PayerController extends BaseController {

    PayerService payerService

    public index() {
        String deletedOnly = params.deletedOnly
        if (deletedOnly && !Boolean.valueOf(deletedOnly)) params.put("includeDeleted", true)

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
            redirect(action: "index")
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

    public edit() {
        Long id = Long.valueOf(params.id)
        Payer payer = PayerRepository.query([id: id, customer: getCurrentCustomer()]).get()

        render(view: "edit", model: [person: payer])
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
            redirect(action: "index")
        }
    }
}