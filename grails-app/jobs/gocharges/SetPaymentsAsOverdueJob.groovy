package gocharges

class SetPaymentsAsOverdueJob {

    PaymentService paymentService

    static triggers = {
        cron name: "SetPaymentsAsOverdueJobTrigger", cronExpression: "0 0 0 1/1 * ? *"
    }

    def execute() {

        try {
            paymentService.setAsOverdue()
        } catch (Exception exception) {
            log.info("Ocorreu um erro ao executar a job SetPaymentsAsOverdueJob")
        }

    }
}
