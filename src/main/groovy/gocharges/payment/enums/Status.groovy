package gocharges.payment.enums

public enum Status {

    RECEBIDA("RECEBIDA"),
    VENCIDA("VENCIDA"),
    PENDENTE("PENDENTE");

    private String status

    Status(String status) {
        this.status = status
    }

    public String getStatus() {
        return this.status
    }
}