package gocharges

import gocharges.auth.User
import gocharges.customer.CustomerAdapter
import gocharges.customer.CustomerRepository
import gocharges.exception.BusinessException
import gocharges.validator.CpfCnpjValidator
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class CustomerService {

    SpringSecurityService springSecurityService

    public void save(CustomerAdapter adapter) {
        validateSave(adapter)

        User currentUser = springSecurityService.loadCurrentUser()

        Customer customer = currentUser.customer
        customer.name = adapter.name
        customer.email = currentUser.username
        customer.mobilePhone = adapter.mobilePhone
        customer.cpfCnpj = adapter.cpfCnpj
        customer.address = adapter.address

        customer.save(failOnError: true)
    }

    public void delete(Long id) {
        Customer customer = CustomerRepository.query([id: id]).get()

        if (!customer) throw new BusinessException("Cliente não encontrado")

        customer.deleted = true
        customer.save(failOnError: true)
    }

    private void validateNotNull(CustomerAdapter adapter) {
        if (adapter.name.isBlank() || adapter.mobilePhone.isBlank()
                || adapter.cpfCnpj.isBlank() || adapter.address.isBlank()) {
            throw new BusinessException("É preciso preencher todos os campos")
        }
    }

    private void validateSave(CustomerAdapter adapter) {
        validateNotNull(adapter)
        CpfCnpjValidator.validate(adapter.cpfCnpj)

        Customer cpfCnpjExists = CustomerRepository.query([cpfCnpj: adapter.cpfCnpj, includeDeleted: true]).get()
        if (cpfCnpjExists) throw new BusinessException("CPF / CNPJ em uso")
    }

    private void validateUpdate(Long id, CustomerAdapter adapter) {
        validateNotNull(adapter)

        Customer Customer = CustomerRepository.query([id: id]).get()
        if (!Customer) throw new BusinessException("Pagador não encontrado")

        Boolean hasCustomerWithSameEmail = CustomerRepository.query([email: adapter.email, includeDeleted: true, "id[ne]": id]).get().asBoolean()
        if (hasCustomerWithSameEmail) throw new BusinessException("E-mail já em uso")
    }

    public Customer update(Long id, CustomerAdapter adapter) {
        validateUpdate(id, adapter)

        Customer customer = CustomerRepository.query([id: id]).get()

        customer.name = adapter.name
        customer.email = adapter.email
        customer.mobilePhone = adapter.mobilePhone
        customer.address = adapter.address

        customer.save(failOnError: true)

        return customer
    }

    public List<Customer> list() {
        return CustomerRepository.query([includeDeleted: false]).list()
    }
}
