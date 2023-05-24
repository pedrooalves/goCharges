package gocharges.payment.enums

public enum BillingType {

    BOLETO("BOLETO"),
    DEBIT_CARD("DEBIT_CARD"),
    PIX("PIX");

    private String billingType

    BillingType(String billingType) {
        this.billingType = billingType
    }

    public String getBillingType() {
        return this.billingType
    }
}