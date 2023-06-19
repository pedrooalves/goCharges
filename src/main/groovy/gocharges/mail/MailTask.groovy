package gocharges.mail

class MailTask implements Runnable {

    Object mailService
    String mailRecipientName
    String mailRecipient
    String mailBody
    String mailSubject

    MailTask(Map<String, Object> mailParams, Object mailService) {
        this.mailService = mailService
        this.mailRecipientName = mailParams.recipientName
        this.mailRecipient = mailParams.recipient
        this.mailSubject = mailParams.mailSubject
        this.mailBody = mailParams.mailBody
    }

    @Override
    void run() {
        mailService.sendMail {
            to mailRecipient
            subject mailSubject
            html(view: "/emails/emailTemplate", model: [recipientName: mailRecipientName, mailBody: mailBody, mailSubject: mailSubject])
        }
    }
}
