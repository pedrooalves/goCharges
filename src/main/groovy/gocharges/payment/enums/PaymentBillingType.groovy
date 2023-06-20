package gocharges.payment.enums

public enum PaymentBillingType {

    BANK_SLIP("Boleto"),
    CREDIT_CARD("Cartão de Crédito"),
    PIX("Pix"),
    CASH("Dinheiro")

    String name

    PaymentBillingType(String name) {
        this.name = name
    }
}