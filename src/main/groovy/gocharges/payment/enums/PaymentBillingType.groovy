package gocharges.payment.enums

public enum PaymentBillingType {

    BANK_SLIP("Boleto"),
    DEBIT_CARD("Cartão de Débito"),
    PIX("Pix")

    String name

    PaymentBillingType(String name) {
        this.name = name
    }
}