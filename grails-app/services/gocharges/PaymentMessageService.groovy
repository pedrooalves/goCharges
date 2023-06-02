package gocharges

import gocharges.mail.MailTask
import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat

@Transactional
class PaymentMessageService {

    def mailService

    public void sendNewPaymentMessage(Payment payment, Customer customer) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy")
        String paymentDueDate = simpleDateFormat.format(payment.dueDate)
        String mailSubject = "Nova Cobrança"
        String mailBody = "Uma nova cobrança foi criada no seu nome por " + customer.name + ", no valor de R\$" + payment.value +
                ", com data de vencimento no dia " + paymentDueDate + " e a forma de pagamento escolhida foi " +
                payment.billingType.toString() + "."

        sendMailThread([payment: payment, mailBody: mailBody, mailSubject: mailSubject])
    }

    private void sendMailThread(Map<String, Object> mailParams) {
        MailTask task = new MailTask(mailParams, this.mailService)
        Thread sendMail = new Thread(task)
        sendMail.start()
    }
}
