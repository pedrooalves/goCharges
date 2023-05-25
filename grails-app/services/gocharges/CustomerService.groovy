package gocharges

import gocharges.customer.CustomerAdapter
import gocharges.customer.CustomerRepository
import gocharges.exception.BusinessException
import gocharges.validator.CpfCnpjValidator
import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {

    public Customer save(CustomerAdapter adapter) {
        validate(adapter)

        Customer customer = new Customer()
        customer.name = adapter.name
        customer.email = adapter.email
        customer.mobilePhone = adapter.mobilePhone
        customer.cpfCnpj = adapter.cpfCnpj
        customer.address = adapter.address

        customer.save(failOnError: true)
        return customer
    }

    public List<Customer> list() {
        return CustomerRepository.query([includeDeleted: false]).list()
    }

    public void delete(Long id) {
        Customer customer = CustomerRepository.query([id: id]).get()

        customer.deleted = true
        customer.save(failOnError: true)
    }

    public Customer update(Long id, CustomerAdapter adapter) {
        validateUpdate(id, adapter)

        Customer customer = CustomerRepository.query([id: id]).get()

        customer.name = adapter.name
        customer.email = adapter.email
        customer.mobilePhone = adapter.mobilePhone
        customer.address = adapter.address

        return customer
    }

    private void validate(CustomerAdapter adapter) {
        validateNotNull(adapter)

        Customer emailValidator = CustomerRepository.query([email: adapter.email, includeDeleted: true]).get()

        if(emailValidator) {
            throw new BusinessException("Email em uso!")
        }

        Customer cpfCnpjValidator = CustomerRepository.query([cpfCnpj: adapter.cpfCnpj, includeDeleted: true]).get()

        if(cpfCnpjValidator) {
            throw new BusinessException("CPF / CNPJ em uso!")
        }
    }

    private void validateUpdate(Long id, CustomerAdapter adapter) {
        validateNotNull(adapter)

        Customer customerById = CustomerRepository.query([id: id, includeDeleted: true]).get()
        Customer customerByEmail = CustomerRepository.query([email: adapter.email, includeDeleted: true]).get()

        if(!customerByEmail) {
            return
        }

        if(customerById != customerByEmail) {
            throw new BusinessException("Email em uso!")
        }
    }

    private void validateNotNull(CustomerAdapter adapter) {
        if(adapter.name.isBlank() || adapter.email.isBlank() || adapter.mobilePhone.isBlank()
                || adapter.cpfCnpj.isBlank() || adapter.address.isBlank()) {
            throw new BusinessException("É preciso preencher todos os campos!")
        }
    }
}
