package gocharges

import gocharges.exception.BusinessException
import gocharges.payer.adapter.PayerAdapter

class PayerController {

    PayerService payerService

    public index() {
        List<Payer> payers = payerService.list()
        Boolean showNewPayerForm = false

        if(chainModel) {
            Map validation = chainModel.validation
            if (chainModel.showNewPayerForm) {
                showNewPayerForm = true
            }
            render(view: "index", model: [payers:payers, validation: validation, showNewPayerForm: showNewPayerForm])
        } else {
            render(view: "index", model: [payers:payers, showNewPayerForm: showNewPayerForm])
        }
    }

    public save() {
        try {
            PayerAdapter payerAdapter = new PayerAdapter(params)
            Payer payer = payerService.save(payerAdapter)

            Map validation = [success:true, message:"Conta criada com sucesso", type:"save"]
            chain(action: "index", model:[validation:validation])
        } catch(BusinessException e) {
            Map validation = [success:false, message:e.getMessage(), type:"save"]
            chain(action: "index", model: [validation:validation, showNewPayerForm: true])
        }
    }


    public form() {
        return [:]
    }

    public delete() {
        try {
            Long id = Long.parseLong(params.id)
            payerService.delete(id)

            Map validation = [success:true, message:"Pagador excluído com sucesso", type:"delete"]
            chain(action: "index", model: [validation:validation])
        } catch(BusinessException e) {
            Map validation = [success:false, message:e.getMessage(), type:"delete"]
            chain(action: "index", model:[validation:validation])
        }
    }

    public update() {
        try {
            PayerAdapter adapter = new PayerAdapter(params)
            Long id = Long.parseLong(params.id)
            payerService.update(id, adapter)

            Map validation = [success:true, message:"Pagador salvo com sucesso", type:"update"]
            chain(action: "index", model: [validation:validation])
        } catch (BusinessException e) {
            Map validation = [success:false, message:e.getMessage(), type:"update"]
            chain(action: "index", model: [validation:validation])
        }
    }

    public showForm() {
        chain(action: "index", model: [showNewPayerForm: true])
    }

    public edit() {
        Long id = Long.parseLong(params.id)
        Payer payer = payerService.findById(id)

        if(chainModel) {
            Map validation = chainModel.validation
            render(view: "edit", model: [payer:payer, validation: validation])
        } else {
            render(view: "edit", model: [payer:payer])
        }
    }
}
