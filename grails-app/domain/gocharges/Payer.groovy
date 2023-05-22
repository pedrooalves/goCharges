package gocharges

class Payer {

    String name
    String email
    String mobilePhone
    String cpfCnpj
    String address

    Date createdAt = new Date()

    static constraints = {
        name(blank:false)
        email(email:true, blank:false, unique:true)
        mobilePhone(blank:false)
        cpfCnpj(blank:false)
        address(blank:false)
    }
}