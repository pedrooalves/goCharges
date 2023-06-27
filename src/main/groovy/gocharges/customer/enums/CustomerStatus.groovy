package gocharges.customer.enums

public enum CustomerStatus {

    PENDING("Pendente"),
    ACTIVE("Ativo")

    String name

    CustomerStatus(String name) {
        this.name = name
    }
}
