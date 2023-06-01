package gocharges.mail

import gocharges.Payment

class MailTask implements Runnable {

    Payment payment
    Object mailService
    String mailBody
    String mailSubject

    MailTask(Map<String, Object> mailParams, Object mailService) {
        this.payment = mailParams.payment
        this.mailService = mailService
        this.mailBody = mailParams.mailBody.toString()
        this.mailSubject = mailParams.mailSubject
    }

    @Override
    void run() {

        String payerName = payment.payer.name

        mailService.sendMail {
            to payment.payer.email
            subject mailSubject
            html(view: "/emails/emailTemplate", model: [payerName: payerName, mailBody: mailBody, mailSubject: mailSubject])
        }
    }
}
