package gocharges

import gocharges.customer.CustomerAdapter
import gocharges.customer.CustomerRepository
import gocharges.customer.enums.CustomerStatus
import gocharges.exception.BusinessException
import shared.CpfCnpjUtils
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class CustomerService {

    SpringSecurityService springSecurityService

    public Customer save(String email) {
        Long emailInUseId = CustomerRepository.query([email: email, includeDeleted: true]).property("id").get()
        if (emailInUseId) throw new BusinessException("E-mail já em uso.")

        Customer customer = new Customer()
        customer.email = email

        return customer
    }

    public void delete(Long id) {
        Customer customer = CustomerRepository.query([id: id]).get()

        if (!customer) throw new BusinessException("Cliente não encontrado")

        customer.deleted = true
        customer.save(failOnError: true)
    }

    private void validateNotNull(CustomerAdapter adapter) {
        if (adapter.name.isBlank() || adapter.mobilePhone.isBlank() || adapter.cpfCnpj.isBlank() || adapter.address.isBlank()) {
            throw new BusinessException("É preciso preencher todos os campos")
        }
    }

    private void validateEmailInUse(Long userCustomerId, String emailToValidate) {
        Long emailInUseId = CustomerRepository.query([email: emailToValidate, includeDeleted: true]).property("id").get()
        if (emailInUseId && emailInUseId != userCustomerId) throw new BusinessException("E-mail já em uso.")
    }

    private void validateCpfCnpjInUse(String cpfCnpjToValidate) {
        Long cpfCnpjInUseId = CustomerRepository.query([cpfCnpj: cpfCnpjToValidate, includeDeleted: true]).property("id").get()
        if (cpfCnpjInUseId) throw new BusinessException("CPF / CNPJ em uso.")
    }

    private void validateUpdate(Customer userCustomer, CustomerAdapter adapter) {
        validateNotNull(adapter)
        CpfCnpjUtils.validate(adapter.cpfCnpj)

        validateCpfCnpjInUse(adapter.cpfCnpj)

        validateEmailInUse(userCustomer.id, adapter.email)
    }

    public Customer update(CustomerAdapter adapter) {
        Customer userCustomer = springSecurityService.getCurrentUser().customer

        validateUpdate(userCustomer, adapter)

        userCustomer.name = adapter.name
        userCustomer.email = adapter.email
        userCustomer.cpfCnpj = adapter.cpfCnpj
        userCustomer.mobilePhone = adapter.mobilePhone
        userCustomer.address = adapter.address

        if (userCustomer.status == CustomerStatus.PENDING) {
            userCustomer.status = CustomerStatus.ACTIVE
        }

        userCustomer.save(failOnError: true)

        return userCustomer
    }

    public List<Customer> list() {
        return CustomerRepository.query([includeDeleted: false]).list()
    }
}
