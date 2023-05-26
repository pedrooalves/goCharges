package gocharges

import gocharges.payment.PaymentRepository
import gocharges.payment.enums.PaymentStatus
import grails.gorm.transactions.Transactional

@Transactional
class PaymentOverdueJob {

    static triggers = {
        cron name: 'cronTrigger', cronExpression: '0 55 13 1/1 * ? *'
    }

    def execute() {

        Date today = new Date()
        List<Payment> paymentsOverdue = PaymentRepository.query([today: today, status: PaymentStatus.PENDING]).list()

        for(Payment payment : paymentsOverdue) {
            PaymentService.setOverdue(payment)
        }

    }
}
