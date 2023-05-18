package gocharges

class Payment {

    String billingType;
    String status;
    Date dueDate;
    BigDecimal value;

    static constraints = {
        billingType(inList:["BOLETO","DEBIT_CARD","PIX","DEPOSIT","TRANSFER"])
        status(inList: ["APROVADA","ATRASADA","PENDENTE"])
        dueDate(blank: false, nullable: false)
        value(blank:false, nullable: false)
    }
}
