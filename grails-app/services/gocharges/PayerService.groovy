package gocharges

import gocharges.payer.adapter.PayerAdapter
import grails.gorm.transactions.Transactional

@Transactional
class PayerService {

    public Payer save(PayerAdapter adapter) throws RuntimeException {

        validateSave(adapter)

        Payer payer = new Payer()
        payer.name = adapter.name
        payer.email = adapter.email
        payer.mobilePhone = adapter.mobilePhone
        payer.cpfCnpj = adapter.cpfCnpj
        payer.address = adapter.address

        if(!payer.save(failOnError:true)){
            throw new RuntimeException("Erro inesperado")
        }

        return payer
    }

    public Payer delete(Long id) throws RuntimeException {
        Payer payer = Payer.get(id)

        if(!payer) {
            throw new RuntimeException("Pagador não encontrado")
        }

        payer.delete(failOnError: true)

        return payer
    }

    public list() {
        def payers = Payer.list()

        return payers
    }

    private void validateNotNull(PayerAdapter adapter) {
        if (adapter.email.isBlank() || adapter.name.isBlank() || adapter.mobilePhone.isBlank() ||
                adapter.cpfCnpj.isBlank() || adapter.address.isBlank()) {
            throw new RuntimeException("É preciso preencher todos os campos")
        }
    }

    private void validateSave(PayerAdapter adapter) throws RuntimeException {
        validateNotNull(adapter)

        Payer payer = Payer.findByEmail(adapter.email)

        if(payer != null) {
            throw new RuntimeException("Email já cadastrado")
        }

        payer = Payer.findByCpfCnpj(adapter.cpfCnpj)

        if(payer != null) {
            throw new RuntimeException("Cpf ou Cnpj já cadastrado")
        }

    }

    private void validateUpdate(String emailOriginal, PayerAdapter adapter) throws RuntimeException {
        validateNotNull(adapter)

        Payer payer = findById(emailOriginal)

        if(payer == null) {
            throw new RuntimeException("Pagador não encontrado")
        }
    }

    public Payer update(String emailOriginal, PayerAdapter adapter) throws RuntimeException {
        validateUpdate(emailOriginal, adapter)

        Payer payer = findById(emailOriginal)

        payer.name = adapter.name
        payer.email = adapter.email
        payer.mobilePhone = adapter.mobilePhone
        payer.address = adapter.address

        if(!payer.save(failOnError:true)){
            throw new RuntimeException("Não foi possível salvar")
        }

        return payer

    }
}
