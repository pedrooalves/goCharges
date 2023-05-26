package gocharges

class PaymentOverdueJob {

    PaymentService paymentService

    static triggers = {
        cron name: 'cronTrigger', cronExpression: '0 0 0 1/1 * ? *'
    }

    def execute() {

        paymentService.setAsOverdue()

    }
}
