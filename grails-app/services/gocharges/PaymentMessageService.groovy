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
                recipient: payment.customer.email,
                mailSubject: mailSubject,
                mailBody: "Cobrança criada no valor de R\$ ${Utils.getCurrencyWithoutMonetarySimbol(payment.value)} para ${payment.payer.name}",
        ]

        Map payerMailParams = [
                recipient: payment.payer.email,
                mailSubject: mailSubject,
                mailBody: "Uma cobrança no valor R\$ ${Utils.getCurrencyWithoutMonetarySimbol(payment.value)} foi criada em seu nome por ${payment.customer.name}",
        ]

        sendMail(customerMailParams, payerMailParams)
    }

    public void onReceivedInCash(Payment payment) {
        String mailSubject = "Pagamento realizado em dinheiro"

        Map customerMailParams = [
                recipient: payment.customer.email,
                mailSubject: mailSubject,
                mailBody: "Você confirmou o recimento em dinheiro da cobrança ${payment.publicId} em ${Utils.getBrazilDateFormat(payment.paymentDate)}.",
        ]

        Map payerMailParams = [
                recipient: payment.payer.email,
                mailSubject: mailSubject,
                mailBody: "O pagamento em dinheiro da cobrança ${payment.publicId} foi realizado com sucesso.",
        ]

        sendMail(customerMailParams, payerMailParams)
    }

    public void onOverdue(Payment payment) {
        String mailSubject = "Cobrança vencida"

        Map customerMailParams = [
                recipient: payment.customer.email,
                mailSubject: mailSubject,
                mailBody: "Sua cobrança ${payment.publicId} para ${payment.payer.name} está vencida.",
        ]

        Map payerMailParams = [
                recipient: payment.payer.email,
                mailSubject: mailSubject,
                mailBody: "A cobrança ${payment.publicId} criada em seu nome por ${payment.customer.name} está vencida"
        ]

        sendMail(customerMailParams, payerMailParams)
    }

    public void onDelete(Payment payment) {
        String mailSubject = "Cobrança removida"

        Map customerMailParams = [
                recipient: payment.customer.email,
                mailSubject: mailSubject,
                mailBody: "Você removeu a cobrança ${payment.publicId} criada para ${payment.payer.name}.",
        ]

        Map payerMailParams = [
                recipient: payment.payer.email,
                mailSubject: mailSubject,
                mailBody: "A cobrança ${payment.publicId} criada em seu nome por ${payment.customer.name} foi removida."
        ]

        sendMail(customerMailParams, payerMailParams)
    }

    public void sendMail(Map<String, Object> customerMailParams, Map<String, Object> payerMailParams) {
        MailTask customerTask = new MailTask(customerMailParams, this.mailService)
        MailTask payerTask = new MailTask(payerMailParams, this.mailService)
        Thread sendCustomerMail = new Thread(customerTask)
        Thread sendPayerMail = new Thread(payerTask)

        sendCustomerMail.start()
        sendPayerMail.start()
    }
}
