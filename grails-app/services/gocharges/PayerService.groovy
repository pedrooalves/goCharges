package gocharges

import gocharges.payer.PayerRepository
import gocharges.payer.adapter.PayerAdapter
import gocharges.validator.CpfCnpjValidator
import grails.gorm.transactions.Transactional
import gocharges.exception.BusinessException
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class PayerService {

    SpringSecurityService springSecurityService

    public Payer save(PayerAdapter adapter, Customer customer) {
        validateSave(adapter, customer)

        Payer payer = new Payer()
        payer.name = adapter.name
        payer.email = adapter.email
        payer.mobilePhone = adapter.mobilePhone
        payer.cpfCnpj = adapter.cpfCnpj
        payer.address = adapter.address
        payer.customer = customer

        return payer.save(failOnError: true)
    }

    public Payer delete(Long id, Customer customer) {
        Payer payer = PayerRepository.query([id: id, customer: customer]).get()

        if (!payer) throw new BusinessException("Pagador não encontrado")

        payer.deleted = true
        return payer.save(failOnError: true)
    }

    private void validateNotNull(PayerAdapter adapter) {
        if (adapter.name.isBlank()) throw new BusinessException("O campo nome é obrigatório")
        if (adapter.email.isBlank()) throw new BusinessException("O campo e-mail é obrigatório")
        if (adapter.mobilePhone.isBlank()) throw new BusinessException("O campo celular é obrigatório")
        if (adapter.cpfCnpj.isBlank()) throw new BusinessException("O campo CPF/CNPJ é obrigatório")
        if (adapter.address.isBlank()) throw new BusinessException("O campo endereço é obrigatório")
    }

    private void validateSave(PayerAdapter adapter, Customer customer) {
        validateNotNull(adapter)
        CpfCnpjValidator.validate(adapter.cpfCnpj)

        Payer payer = PayerRepository.query([email: adapter.email, customer: customer, includeDeleted: true]).get()
        if (payer) throw new BusinessException("Email já cadastrado")

        Payer cpfCnpjExists = PayerRepository.query([cpfCnpj: adapter.cpfCnpj, customer: customer, includeDeleted: true]).get()
        if (cpfCnpjExists) throw new BusinessException("CPF / CNPJ em uso")

        if (adapter.email == customer.email) throw new BusinessException("Você não pode cadastrar seu próprio e-mail")

        if (adapter.cpfCnpj == customer.cpfCnpj) throw new BusinessException("Você não pode cadastrar seu próprio CPF ou CNPJ")
    }

    private void validateUpdate(Long id, PayerAdapter adapter, Customer customer) {
        validateNotNull(adapter)

        Payer payer = PayerRepository.query([id: id, customer: customer]).get()
        if (!payer) throw new BusinessException("Pagador não encontrado")

        payer = PayerRepository.query([email: adapter.email, customer: customer, includeDeleted: true]).get()
        if (payer && payer.id != id) throw new BusinessException("E-mail já em uso")
    }

    public Payer update(Long id, PayerAdapter adapter, Customer customer) {
        validateUpdate(id, adapter, customer)

        Payer payer = PayerRepository.query([id: id, customer: customer]).get()

        payer.name = adapter.name
        payer.email = adapter.email
        payer.mobilePhone = adapter.mobilePhone
        payer.address = adapter.address

        return payer.save(failOnError: true)
    }

    public List<Payer> list(Map params, Customer customer) {
        return PayerRepository.query([customer: customer]).list()
    }
}
