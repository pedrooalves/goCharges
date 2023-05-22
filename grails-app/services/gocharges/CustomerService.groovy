package gocharges

import gocharges.customer.CustomerAdapter
import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {

    public Customer save(CustomerAdapter adapter) {

        try{
            validate(adapter)

            Customer customer = new Customer()
            customer.name = adapter.name
            customer.email = adapter.email
            customer.mobilePhone = adapter.mobilePhone
            customer.cpfCnpj = adapter.cpfCnpj
            customer.address = adapter.address

            customer.save(failOnError: true)

            return customer
        }catch(RuntimeException exception){
            throw exception
        }

    }

    public List<Customer> list() {
        List<Customer> customerList = Customer.list()

        return customerList;
    }

    private void validate(adapter) {
//        return [success: true, message: "Conta criada com sucesso."]

        if(adapter.name.isBlank() || adapter.email.isBlank() || adapter.mobilePhone.isBlank()
        || adapter.cpfCnpj.isBlank() || adapter.address.isBlank()) {
            throw new RuntimeException("É preciso preencher todos os campos!")
        }

        Customer customer = Customer.findByEmail(adapter.email)

        if(customer) {
            throw new RuntimeException("Email em uso!")
        }
    }
}
