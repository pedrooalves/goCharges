package gocharges

import gocharges.customer.CustomerAdapter
import gocharges.exception.BusinessException
import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {

    public Customer save(CustomerAdapter adapter) {
        validate(adapter)
        Customer customer = convertAdapterToDomain(adapter)

        customer.save(failOnError: true)
        return customer
    }

    public List<Customer> list() {
        List<Customer> customerList = Customer.list()

        return customerList
    }

    public void delete(Long id) {
        Customer customer = Customer.get(id)

        customer.delete()
    }

    public Customer update(CustomerAdapter adapter) {

    }

    private Customer convertAdapterToDomain(CustomerAdapter adapter) {
        Customer customer = new Customer()
        customer.name = adapter.name
        customer.email = adapter.email
        customer.mobilePhone = adapter.mobilePhone
        customer.cpfCnpj = adapter.cpfCnpj
        customer.address = adapter.address

        return customer
    }

    private void validate(adapter) {

        if(adapter.name.isBlank() || adapter.email.isBlank() || adapter.mobilePhone.isBlank()
        || adapter.cpfCnpj.isBlank() || adapter.address.isBlank()) {
            throw new BusinessException("É preciso preencher todos os campos!")
        }

        Customer emailValidator = Customer.findByEmail(adapter.email)

        if(emailValidator) {
            throw new BusinessException("Email em uso!")
        }

        Customer cpfCnpjValidator = Customer.findByCpfCnpj(adapter.cpfCnpj)

        if(cpfCnpjValidator) {
            throw new BusinessException("CPF / CNPJ em uso!")
        }
    }
}
