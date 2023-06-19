package gocharges.mail

class MailTask implements Runnable {

    Object mailService
    String mailRecipient
    String mailBody
    String mailSubject

    MailTask(Map<String, Object> mailParams, Object mailService) {
        this.mailService = mailService
        this.mailRecipient = mailParams.recipient
        this.mailSubject = mailParams.mailSubject
        this.mailBody = mailParams.mailBody
    }

    @Override
    void run() {
        mailService.sendMail {
            to mailRecipient
            subject mailSubject
            html(view: "/emails/emailTemplate", model: [recipientEmail: mailRecipient, mailBody: mailBody, mailSubject: mailSubject])
        }
    }
}
