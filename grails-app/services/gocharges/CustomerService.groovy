package gocharges

import gocharges.customer.CustomerAdapter
import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {

    Map register(CustomerAdapter customerAdapter) {
        Map validation = validateCustomer(customerAdapter)

        if(!validation.success) {
            return validation
        }

        Customer customer = new Customer()
        customer.name = customerAdapter.name;
        customer.email = customerAdapter.email;
        customer.mobilePhone = customerAdapter.mobilePhone;
        customer.cpfCnpj = customerAdapter.cpfCnpj;
        customer.address = customerAdapter.address;

        customer.save(failOnError: true)

        return validation
    }

    List<Customer> list() {
        List<Customer> customerList = Customer.list()

        return customerList;
    }

    Map validateCustomer(customerAdapter) {

        if(customerAdapter.name.isBlank() || customerAdapter.email.isBlank() || customerAdapter.mobilePhone.isBlank()
        || customerAdapter.cpfCnpj.isBlank() || customerAdapter.address.isBlank()) {
            return [success: false, message: "É preciso preencher todos os campos."]
        }

        Customer customer = Customer.findByEmail(customerAdapter.email)

        if(customer) {
            return [success: false, message: "Email em uso."]
        }

        return [success: true, message: "Conta criada com sucesso."]

    }
}
