package gocharges.payment.enums

public enum PaymentStatus {

    PENDING("Pendente"),
    OVERDUE("Vencida"),
    RECEIVED("Recebida"),
    CONFIRMED("Confirmada")

    String name

    PaymentStatus(String name) {
        this.name = name
    }
}