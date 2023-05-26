package gocharges

import gocharges.payment.PaymentRepository

class PaymentOverdueJob {

    static triggers = {
        cron name: 'cronTrigger', cronExpression: '0 05 13 1/1 * ? *'
    }

    def execute() {

        Date today = new Date()
        List<Payment> paymentsOverdue = PaymentRepository.query([today: today]).list()

        for(Payment payment : paymentsOverdue) {
            PaymentService.setOverdue(payment.id)
        }

    }
}
