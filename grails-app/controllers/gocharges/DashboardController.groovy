package gocharges

import gocharges.auth.User
import gocharges.payment.enums.PaymentStatus
import grails.plugin.springsecurity.SpringSecurityService

class DashboardController {

    SpringSecurityService springSecurityService
    PayerService payerService
    PaymentService paymentService

    def index() {
        User currentUser = springSecurityService.loadCurrentUser()
        String userName = currentUser.customer.name

        if(!currentUser.customer.name) {
            redirect(controller: "customer", action: "create")
        }

        Integer payerCount = payerService.payerCount()

        Map paymentsByStatus = [:]
        paymentsByStatus.pending = paymentService.getPaymentsByStatus(PaymentStatus.PENDING)
        paymentsByStatus.overdue = paymentService.getPaymentsByStatus(PaymentStatus.OVERDUE)
        paymentsByStatus.received = paymentService.getPaymentsByStatus(PaymentStatus.RECEIVED)

        render(view: "index", model: [payerCount: payerCount, paymentsByStatus : paymentsByStatus, userName: userName])
    }
}
