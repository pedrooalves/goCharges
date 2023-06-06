package gocharges

import gocharges.payer.PayerRepository
import gocharges.payer.adapter.PayerAdapter
import gocharges.validator.CpfCnpjValidator
import grails.gorm.transactions.Transactional
import gocharges.exception.BusinessException
import shared.Utils

@Transactional
class PayerService {

    public Payer save(PayerAdapter adapter, Customer customer) {
        validateSave(adapter, customer)

        Payer payer = new Payer()
        payer.name = adapter.name
        payer.email = adapter.email
        payer.mobilePhone = adapter.mobilePhone
        payer.cpfCnpj = adapter.cpfCnpj
        payer.postalCode = adapter.postalCode
        payer.address = adapter.address
        payer.addressNumber = adapter.addressNumber
        payer.complement = adapter.complement
        payer.province = adapter.province
        payer.city = adapter.city
        payer.state = adapter.state
        payer.customer = customer

        return payer.save(failOnError: true)
    }

    public Payer delete(Long id, Customer customer) {
        Payer payer = PayerRepository.query([id: id, customer: customer]).get()

        if (!payer) throw new BusinessException(Utils.getMessageProperty("default.not.found.message", "Pagador"))

        payer.deleted = true
        return payer.save(failOnError: true)
    }

    private void validateNotNull(PayerAdapter adapter) {
        if (!adapter.name) throw new BusinessException(Utils.getMessageProperty("default.null.message", "nome"))
        if (!adapter.email) throw new BusinessException(Utils.getMessageProperty("default.null.message", "e-mail"))
        if (!adapter.mobilePhone) throw new BusinessException(Utils.getMessageProperty("default.null.message", "celular"))
        if (!adapter.cpfCnpj) throw new BusinessException(Utils.getMessageProperty("default.null.message", "CPF / CNPJ"))
        if (!adapter.postalCode) throw new BusinessException(Utils.getMessageProperty("default.null.message", "CEP"))
        if (!adapter.address) throw new BusinessException(Utils.getMessageProperty("default.null.message", "endereço"))
        if (!adapter.addressNumber) throw new BusinessException(Utils.getMessageProperty("default.null.message", "número"))
        if (!adapter.province) throw new BusinessException(Utils.getMessageProperty("default.null.message", "bairro"))
        if (!adapter.city) throw new BusinessException(Utils.getMessageProperty("default.null.message", "cidade"))
        if (!adapter.state) throw new BusinessException(Utils.getMessageProperty("default.null.message", "estado"))
    }

    private void validateEmailInUse(Map params) {
        Boolean payerExists = PayerRepository.query(params).get().asBoolean()
        if (payerExists) throw new BusinessException(Utils.getMessageProperty("default.not.unique.message", "e-mail"))
    }

    private void validateEmailInUseUpdate(Long id, PayerAdapter adapter, Customer customer) {
        validateEmailInUse([email: adapter.email, customer: customer, includeDeleted: true, "id[ne]": id])
    }

    private void validateEmailInUseSave(PayerAdapter adapter, Customer customer) {
        validateEmailInUse([email: adapter.email, customer: customer, includeDeleted: true])
    }

    private void validateCpfCnpjInUse(PayerAdapter adapter, Customer customer) {
        Boolean payerExists = PayerRepository.query([cpfCnpj: adapter.cpfCnpj, customer: customer, includeDeleted: true]).get().asBoolean()
        if (payerExists) throw new BusinessException(Utils.getMessageProperty("default.not.unique.message", "e-mail"))
    }

    private void validateSave(PayerAdapter adapter, Customer customer) {
        validateNotNull(adapter)
        CpfCnpjValidator.validate(adapter.cpfCnpj)
        validateCpfCnpjInUse(adapter, customer)
        validateEmailInUseSave(adapter, customer)

        if (adapter.email == customer.email) throw new BusinessException(Utils.getMessageProperty("default.not.own.data.message", "e-mail"))
        if (adapter.cpfCnpj == customer.cpfCnpj) throw new BusinessException(Utils.getMessageProperty("default.not.own.data.message", "CPF/CNPJ"))
    }

    private void validateUpdate(Long id, PayerAdapter adapter, Customer customer) {
        validateNotNull(adapter)
        validateEmailInUseUpdate(id, adapter, customer)

        Payer payer = PayerRepository.query([id: id, customer: customer]).get()
        if (!payer) throw new BusinessException(Utils.getMessageProperty("default.not.found.message", "Pagador"))
    }

    public Payer update(Long id, PayerAdapter adapter, Customer customer) {
        validateUpdate(id, adapter, customer)

        Payer payer = PayerRepository.query([id: id, customer: customer]).get()

        payer.name = adapter.name
        payer.email = adapter.email
        payer.mobilePhone = adapter.mobilePhone
        payer.postalCode = adapter.postalCode
        payer.address = adapter.address
        payer.addressNumber = adapter.addressNumber
        payer.complement = adapter.complement
        payer.province = adapter.province
        payer.city = adapter.city
        payer.state = adapter.state

        return payer.save(failOnError: true)
    }

    public List<Payer> list(Map params, Customer customer) {
        return PayerRepository.query(params + [customer: customer]).list()
    }
}
