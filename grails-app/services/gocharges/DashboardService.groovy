package gocharges

import gocharges.payer.PayerRepository
import gocharges.payment.PaymentRepository
import gocharges.payment.enums.PaymentStatus
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class DashboardService {

    SpringSecurityService springSecurityService

    public Map info() {
        Customer userCustomer = springSecurityService.getCurrentUser().customer

        Map info = [:]
        info.payerCount = PayerRepository.query([customer: userCustomer]).count()
        info.pendingPaymentCount = PaymentRepository.query([status: PaymentStatus.PENDING, customer: userCustomer]).count()
        info.overduePaymentCount = PaymentRepository.query([status: PaymentStatus.OVERDUE, customer: userCustomer]).count()
        info.receivedPaymentCount = PaymentRepository.query([status: PaymentStatus.RECEIVED, customer: userCustomer]).count()

        return info
    }
}
