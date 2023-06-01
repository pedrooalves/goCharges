package gocharges.mail

import gocharges.Customer
import gocharges.Payment

import java.text.SimpleDateFormat

class MailBuilder {

    public static Map<String, Object> buildNewPaymentMessage(Payment payment, Customer customer) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy")
        String paymentDueDate = simpleDateFormat.format(payment.dueDate)
        String mailSubject = "Nova Cobrança"
        String mailBody = "Uma nova cobrança foi criada no seu nome por " +  customer.name + ", no valor de R\$" + payment.value +
                ", com data de vencimento no dia " + paymentDueDate + " e a forma de pagamento escolhida foi " +
                payment.billingType.toString() + "."

        return [payment: payment, mailBody: mailBody, mailSubject: mailSubject]
    }
}
