package gocharges.customer

class CustomerAdapter {

    String name
    String mobilePhone
    String cpfCnpj
    String address

    CustomerAdapter(Map params) {
        this.name = params.name
        this.mobilePhone = params.mobilePhone
        this.cpfCnpj = params.cpfCnpj
        this.address = params.address
    }

}
