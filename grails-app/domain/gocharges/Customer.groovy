package gocharges


class Customer {

    String name
    String email
    String mobilePhone
    String cpfCnpj
    String address

    Date created_at = new Date();

    static constraints = {
        name(nullable: false)
        email(email: true, nullable: false, unique: true)
        mobilePhone(nullable: false)
        cpfCnpj(nullable: false)
        address(nullable: false)
    }
}
