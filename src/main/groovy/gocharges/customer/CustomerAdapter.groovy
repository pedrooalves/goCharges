package gocharges.customer

import gocharges.Customer

class CustomerAdapter {

    String name;
    String email;
    String mobilePhone;
    String cpfCnpj;
    String address;

    CustomerAdapter(Map params) {
        this.name = params.name;
        this.email = params.email;
        this.mobilePhone = params.mobilePhone;
        this.cpfCnpj = params.cpfCnpj;
        this.address = params.address;
    }

    Customer create() {
        Customer customer = new Customer();

        customer.name = this.name;
        customer.email = this.email;
        customer.mobilePhone = this.mobilePhone;
        customer.cpfCnpj = this.cpfCnpj;
        customer.address = this.address;

        return customer
    }

}
