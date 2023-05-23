package gocharges

class Payment {

    Payer payer
    String billingType
    String status = "PENDENTE"
    Date dueDate
    BigDecimal value

    static constraints = {
        billingType(inList:["BOLETO","DEBIT_CARD", "PIX"])
        status(inList: ["RECEBIDA","VENCIDA","PENDENTE"])
        dueDate(blank: false, nullable: false)
        value(blank:false, nullable: false)
    }
}
