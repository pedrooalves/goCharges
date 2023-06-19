package gocharges

import gocharges.mail.MailTask
import grails.gorm.transactions.Transactional
import shared.Utils

@Transactional
class PaymentMessageService {

    def mailService

    def onSave(Payment payment) {
        String mailSubject = "Nova Cobrança"

        Map customerMailParams = [
                recipient: payment.customer.name,
                mailSubject: mailSubject,
                mailBody: "Cobrança criada no valor de R\$ ${Utils.getCurrencyWithoutMonetarySimbol(payment.value)} para ${payment.payer.name}",
        ]

        Map payerMailParams = [
                recipient: payment.payer.name,
                mailSubject: mailSubject,
                mailBody: "Uma cobrança no valor R\$ ${Utils.getCurrencyWithoutMonetarySimbol(payment.value)} foi criada em seu nome por ${payment.customer.name}",
        ]

        sendMail(customerMailParams)
        sendMail(payerMailParams)
    }

    public void sendMail(Map<String, Object> mailParams) {
        MailTask task = new MailTask(mailParams, this.mailService)
        Thread sendMail = new Thread(task)
        sendMail.start()
    }
}
