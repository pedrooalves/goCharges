package gocharges

import gocharges.mail.MailTask
import grails.gorm.transactions.Transactional

@Transactional
class PaymentMessageService {

    def mailService

    public void sendMail(Map<String, Object> mailParams) {
        MailTask task = new MailTask(mailParams, this.mailService)
        Thread sendMail = new Thread(task)
        sendMail.start()
    }
}
