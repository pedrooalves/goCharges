package gocharges

import gocharges.payer.adapter.PayerAdapter
import grails.gorm.transactions.Transactional
import gocharges.exception.BusinessException

@Transactional
class PayerService {

    public Payer save(PayerAdapter adapter) {
        validateSave(adapter)

        Payer payer = new Payer()
        payer.name = adapter.name
        payer.email = adapter.email
        payer.mobilePhone = adapter.mobilePhone
        payer.cpfCnpj = adapter.cpfCnpj
        payer.address = adapter.address

        if(!payer.save(failOnError:true)){
            throw new BusinessException("Erro inesperado")
        }

        return payer
    }

    public Payer delete(Long id) {
        Payer payer = Payer.get(id)

        if (!payer) throw new BusinessException("Pagador não encontrado")

        payer.delete(failOnError: true)

        return payer
    }

    public list() {
        return Payer.list()
    }

    private void validateNotNull(PayerAdapter adapter) {
        if (adapter.email.isBlank() || adapter.name.isBlank() || adapter.mobilePhone.isBlank() ||
                adapter.cpfCnpj.isBlank() || adapter.address.isBlank()) {
            throw new BusinessException("É preciso preencher todos os campos")
        }
    }

    private void validateSave(PayerAdapter adapter)  {
        validateNotNull(adapter)

        Payer payer = Payer.findByEmail(adapter.email)
        if(payer) {
            throw new BusinessException("Email já cadastrado")
        }
    }

    private void validateUpdate(Long id, PayerAdapter adapter) {
        validateNotNull(adapter)

        Payer payer = findById(id)
        if (!payer) {
            throw new BusinessException("Pagador não encontrado")
        }

        payer = Payer.findByEmail(adapter.email)

        if (payer && payer.id != id) {
            throw new BusinessException("E-mail já em uso")
        }
    }

    public Payer update(Long id, PayerAdapter adapter) {
        validateUpdate(id, adapter)

        Payer payer = findById(id)

        payer.name = adapter.name
        payer.email = adapter.email
        payer.mobilePhone = adapter.mobilePhone
        payer.address = adapter.address

        if(!payer.save(failOnError:true)){
            throw new BusinessException("Não foi possível salvar")
        }

        return payer
    }

    public Payer findById(Long id) {
        return Payer.get(id)
    }
}
