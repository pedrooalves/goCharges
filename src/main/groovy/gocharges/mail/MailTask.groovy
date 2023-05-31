package gocharges.mail

import gocharges.Payer
import gocharges.PaymentController

class MailTask implements Runnable {

    private Payer payer
    Object mailService

    MailTask(Payer payer, Object mailService) {
        this.payer = payer
        this.mailService = mailService
    }

    @Override
    void run() {
        mailService.sendMail {
            to payer.email
            subject "Nova cobrança"
            text "Uma nova cobrança foi criada"
        }
    }
}
