package gocharges

class setPaymentsAsOverdueJob {

    PaymentService paymentService

    static triggers = {
        cron name: "setPaymentsAsOverdueJobTrigger", cronExpression: "0 0 0 1/1 * ? *"
    }

    def execute() {

        try{
            paymentService.setAsOverdue()
        }catch(Exception exception) {
            log.info("Ocorreu um erro ao executar a job PaymentOverdueJob")
        }

    }
}
