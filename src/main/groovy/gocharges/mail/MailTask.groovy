package gocharges.mail

import gocharges.Customer
import gocharges.Payment

import java.text.SimpleDateFormat

class MailTask implements Runnable {

    private Payment payment
    Object mailService
    String customerName

    MailTask(Payment payment, Object mailService, Customer customer) {
        this.payment = payment
        this.mailService = mailService
        this.customerName = customer.name
    }

    @Override
    void run() {

        String payerName = payment.payer.name
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy")
        String paymentDueDate = simpleDateFormat.format(payment.dueDate)
        String message = "Uma nova cobrança foi criada no seu nome por " +  customerName + ", no valor de R\$" + payment.value +
        ", com data de vencimento no dia " + paymentDueDate + " e a forma de pagamento escolhida foi " +
                payment.billingType.toString() + "."

        mailService.sendMail {
            println("enviando email")
            to payment.payer.email
            subject "Nova cobrança"
            html (view: "/emails/emailTemplate", model: [payerName: payerName, message: message])
        }
    }
}
