package gocharges

import gocharges.customer.CustomerAdapter
import gocharges.customer.CustomerRepository
import gocharges.customer.enums.CustomerStatus
import gocharges.exception.BusinessException
import shared.CpfCnpjUtils
import grails.gorm.transactions.Transactional
import shared.Utils
import shared.enums.State

@Transactional
class CustomerService {

    public Customer save(String email) {
        Long emailInUseId = CustomerRepository.query([email: email, includeDeleted: true]).property("id").get()
        if (emailInUseId) throw new BusinessException(Utils.getMessageProperty("default.not.unique.message", "e-mail"))

        Customer customer = new Customer()
        customer.email = email

        return customer
    }

    public void delete(Long id) {
        Customer customer = CustomerRepository.query([id: id]).get()

        if (!customer) throw new BusinessException(Utils.getMessageProperty("default.not.found.message", "Cliente"))

        customer.deleted = true
        customer.save(failOnError: true)
    }

    private void validateNotNull(CustomerAdapter adapter) {
        if (!adapter.name) throw new BusinessException(Utils.getMessageProperty("default.null.message", "nome"))
        if (!adapter.mobilePhone) throw new BusinessException(Utils.getMessageProperty("default.null.message", "celular"))
        if (!adapter.cpfCnpj) throw new BusinessException(Utils.getMessageProperty("default.null.message", "CPF / CNPJ"))
        if (!adapter.postalCode) throw new BusinessException(Utils.getMessageProperty("default.null.message", "CEP"))
        if (!adapter.address) throw new BusinessException(Utils.getMessageProperty("default.null.message", "endereço"))
        if (!adapter.addressNumber) throw new BusinessException(Utils.getMessageProperty("default.null.message", "número"))
        if (!adapter.province) throw new BusinessException(Utils.getMessageProperty("default.null.message", "bairro"))
        if (!adapter.city) throw new BusinessException(Utils.getMessageProperty("default.null.message", "cidade"))
        if (!adapter.state) throw new BusinessException(Utils.getMessageProperty("default.null.message", "estado"))
    }

    private void validateEmailInUse(Customer customer, CustomerAdapter adapter) {
        Boolean emailInUse = CustomerRepository.query([email: adapter.email, includeDeleted: true, "id[ne]": customer.id]).get().asBoolean()
        if (emailInUse) throw new BusinessException(Utils.getMessageProperty("default.not.unique.message", "e-mail"))
    }

    private void validateCpfCnpjInUse(Customer customer, CustomerAdapter adapter) {
        Boolean cpfCnpjInUse = CustomerRepository.query([cpfCnpj: adapter.cpfCnpj, includeDeleted: true, "id[ne]": customer.id]).get().asBoolean()
        if (cpfCnpjInUse) throw new BusinessException(Utils.getMessageProperty("default.not.unique.message", "CPF/CNPJ"))
    }

    private void validateUpdate(Customer customer, CustomerAdapter adapter) {
        validateNotNull(adapter)
        CpfCnpjUtils.validate(adapter.cpfCnpj)
        validateCpfCnpjInUse(customer, adapter)
        validateEmailInUse(customer, adapter)
    }

    public Customer update(Customer customer, CustomerAdapter adapter) {
        validateUpdate(customer, adapter)

        customer.name = adapter.name
        customer.cpfCnpj = adapter.cpfCnpj
        customer.mobilePhone = adapter.mobilePhone
        customer.postalCode = adapter.postalCode
        customer.address = adapter.address
        customer.addressNumber = adapter.addressNumber
        customer.complement = adapter.complement
        customer.province = adapter.province
        customer.city = adapter.city
        customer.state = State.valueOf(adapter.state)

        if (customer.status == CustomerStatus.PENDING) {
            customer.status = CustomerStatus.ACTIVE
        }

        return customer.save(failOnError: true)
    }

    public List<Customer> list(Map params) {
        return CustomerRepository.query(params).list()
    }
}