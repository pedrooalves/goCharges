package gocharges

import gocharges.customer.CustomerAdapter
import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {

    def register(CustomerAdapter customerAdapter) {
        def erro = validateCustomer(customerAdapter)

        if(!(erro === "sucesso")) {
            return println(erro)
        }

        Customer customer = customerAdapter.create()
        customer.save(failOnError: true)
    }

    def list() {
        def customerList = Customer.list()

        return customerList;
    }

    def validateCustomer(customerAdapter) {

        if(customerAdapter.name.isBlank() or customerAdapter.email.isBlank() or customerAdapter.email.isBlank()) {
            return "É preciso preencher todos os campos"
        }

        Customer customer = Customer.findByEmail(customerAdapter.email)

        if(customer) {
            return "Email em uso."
        }

        return "sucesso"

    }
}
