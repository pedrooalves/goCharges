package gocharges

import gocharges.payer.adapter.PayerAdapter
import grails.gorm.transactions.Transactional

@Transactional
class PayerService {

    public Map<String, String> register(PayerAdapter payerAdapter) {
        Map<String, String> validated = validatePayer(payerAdapter)
        if (!validated.success) return validated

        Payer payer = new Payer()
        payer.name = payerAdapter.name
        payer.email = payerAdapter.email
        payer.mobilePhone = payerAdapter.mobilePhone
        payer.cpfCnpj = payerAdapter.cpfCnpj
        payer.address = payerAdapter.address

        if(!payer.save(failOnError: true)) return [success: false, message: "saveNotPossible"]

        return [success: true, message: "saved"]
    }

    public listPayers() {
        def payers = Payer.list()

        return payers
    }


    private Map<String, String> validatePayer(PayerAdapter payerAdapter) {
        if (payerAdapter.email.isBlank() || payerAdapter.name.isBlank() ||
            payerAdapter.mobilePhone.isBlank() || payerAdapter.cpfCnpj.isBlank() ||
            payerAdapter.address.isBlank()) {
            return [success: false, message:"blank"]
        }

        Payer payer = Payer.findByEmail(payerAdapter.email)

        if(payer != null) {
            return [success: false, message:"emailExists"]
        }

        return [success: true, message: "validated"]
    }
}
