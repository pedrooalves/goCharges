package gocharges

import gocharges.customer.CustomerAdapter
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
        List<Customer> customerList = Customer.findAll {
            deleted == false
        }

        return customerList
    }

    public void delete(Long id) {
        Customer customer = Customer.get(id)

        customer.deleted = true
        customer.save(failOnError: true)
    }

    public Customer update(Long id, CustomerAdapter adapter) {
        validateUpdate(id, adapter)

        Customer customer = Customer.get(id)

        customer.name = adapter.name
        customer.email = adapter.email
        customer.mobilePhone = adapter.mobilePhone
        customer.address = adapter.address

        return customer
    }

    public Customer findById(Long id) {
        Customer customer = Customer.get(id)

        return customer
    }


    private void validate(CustomerAdapter adapter) {
        validateNotNull(adapter)

        CpfCnpjValidator.validate(adapter.cpfCnpj)

        Customer emailValidator = Customer.findByEmail(adapter.email)

        if(emailValidator) {
            throw new BusinessException("Email em uso!")
        }

        Customer cpfCnpjValidator = Customer.findByCpfCnpj(adapter.cpfCnpj)

        if(cpfCnpjValidator) {
            throw new BusinessException("CPF / CNPJ em uso!")
        }
    }

    private void validateUpdate(Long id, CustomerAdapter adapter) {
        validateNotNull(adapter)

        Customer customerById = Customer.get(id)
        Customer customerByEmail = Customer.findByEmail(adapter.email)

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
