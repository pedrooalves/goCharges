package gocharges

import gocharges.mail.MailTask
import grails.gorm.transactions.Transactional
import shared.Utils

@Transactional
class PaymentMessageService {

    def mailService

    public void onSave(Payment payment) {
        String mailSubject = "Nova Cobrança"

        Map customerMailParams = [
                recipientName: payment.customer.name,
                recipient: payment.customer.email,
                mailSubject: mailSubject,
                mailBody: "Cobrança criada no valor de R\$ ${Utils.getCurrencyWithoutMonetarySimbol(payment.value)} para ${payment.payer.name}"
        ]

        Map payerMailParams = [
                recipientName: payment.payer.name,
                recipient: payment.payer.email,
                mailSubject: mailSubject,
                mailBody: "Uma cobrança no valor R\$ ${Utils.getCurrencyWithoutMonetarySimbol(payment.value)} foi criada em seu nome por ${payment.customer.name}"
        ]

        buildMailTask(customerMailParams)
        buildMailTask(payerMailParams)
    }

    public void onReceivedInCash(Payment payment) {
        String mailSubject = "Pagamento realizado em dinheiro"

        Map customerMailParams = [
                recipientName: payment.customer.name,
                recipient: payment.customer.email,
                mailSubject: mailSubject,
                mailBody: "Você confirmou o recimento em dinheiro da cobrança ${payment.publicId} em ${Utils.getBrazilDateFormat(payment.paymentDate)}."
        ]

        Map payerMailParams = [
                recipientName: payment.payer.name,
                recipient: payment.payer.email,
                mailSubject: mailSubject,
                mailBody: "O pagamento em dinheiro da cobrança ${payment.publicId} foi realizado com sucesso."
        ]

        buildMailTask(customerMailParams)
        buildMailTask(payerMailParams)
    }

    public void onOverdue(Payment payment) {
        String mailSubject = "Cobrança vencida"

        Map customerMailParams = [
                recipientName: payment.customer.name,
                recipient: payment.customer.email,
                mailSubject: mailSubject,
                mailBody: "Sua cobrança ${payment.publicId} para ${payment.payer.name} está vencida."
        ]

        Map payerMailParams = [
                recipientName: payment.payer.name,
                recipient: payment.payer.email,
                mailSubject: mailSubject,
                mailBody: "A cobrança ${payment.publicId} criada em seu nome por ${payment.customer.name} está vencida"
        ]

        buildMailTask(customerMailParams)
        buildMailTask(payerMailParams)
    }

    public void onDelete(Payment payment) {
        String mailSubject = "Cobrança removida"

        Map customerMailParams = [
                recipientName: payment.customer.name,
                recipient: payment.customer.email,
                mailSubject: mailSubject,
                mailBody: "Você removeu a cobrança ${payment.publicId} criada para ${payment.payer.name}.",
        ]

        Map payerMailParams = [
                recipientName: payment.payer.name,
                recipient: payment.payer.email,
                mailSubject: mailSubject,
                mailBody: "A cobrança ${payment.publicId} criada em seu nome por ${payment.customer.name} foi removida."
        ]

        buildMailTask(customerMailParams)
        buildMailTask(payerMailParams)
    }

    private void buildMailTask(Map<String, Object> mailParams) {
        MailTask task = new MailTask(mailParams, this.mailService)
        Thread sendMail = new Thread(task)
        sendMail.start()
    }
}
