package gocharges

import gocharges.customer.CustomerAdapter
import gocharges.customer.CustomerRepository
import gocharges.customer.enums.CustomerStatus
import gocharges.exception.BusinessException
import shared.CpfCnpjUtils
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import shared.Utils

@Transactional
class CustomerService {

    SpringSecurityService springSecurityService

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

    private void validateEmailInUse(Long userCustomerId, String emailToValidate) {
        Boolean emailInUse = CustomerRepository.query([email: emailToValidate, includeDeleted: true, "id[ne]": userCustomerId]).get().asBoolean()
        if (emailInUse) throw new BusinessException(Utils.getMessageProperty("default.not.unique.message", "e-mail"))
    }

    private void validateCpfCnpjInUse(String cpfCnpjToValidate) {
        Boolean cpfCnpjInUse = CustomerRepository.query([cpfCnpj: cpfCnpjToValidate, includeDeleted: true]).get().asBoolean()
        if (cpfCnpjInUse) throw new BusinessException(Utils.getMessageProperty("default.not.unique.message", "CPF/CNPJ"))
    }

    private void validateUpdate(Customer userCustomer, CustomerAdapter adapter) {
        validateNotNull(adapter)
        CpfCnpjUtils.validate(adapter.cpfCnpj)
        validateCpfCnpjInUse(adapter.cpfCnpj)
        validateEmailInUse(userCustomer.id, adapter.email)
    }

    public Customer update(CustomerAdapter adapter, Customer customer) {
        Customer userCustomer = customer

        validateUpdate(userCustomer, adapter)

        userCustomer.name = adapter.name
        userCustomer.email = adapter.email
        userCustomer.cpfCnpj = adapter.cpfCnpj
        userCustomer.mobilePhone = adapter.mobilePhone
        userCustomer.postalCode = adapter.postalCode
        userCustomer.address = adapter.address
        userCustomer.addressNumber = adapter.addressNumber
        userCustomer.complement = adapter.complement
        userCustomer.province = adapter.province
        userCustomer.city = adapter.city
        userCustomer.state = adapter.state

        if (userCustomer.status == CustomerStatus.PENDING) {
            userCustomer.status = CustomerStatus.ACTIVE
        }

        return userCustomer.save(failOnError: true)
    }

    public List<Customer> list() {
        return CustomerRepository.query([includeDeleted: false]).list()
    }
}