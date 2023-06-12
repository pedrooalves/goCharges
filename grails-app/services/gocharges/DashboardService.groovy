package gocharges

import gocharges.payer.PayerRepository
import gocharges.payment.PaymentRepository
import gocharges.payment.enums.PaymentStatus
import grails.gorm.transactions.Transactional

@Transactional
class DashboardService {

    public Map info(Customer customer) {
        Map info = [:]
        info.payerCount = PayerRepository.query([customer: customer]).count()
        info.pendingPaymentCount = PaymentRepository.query([status: PaymentStatus.PENDING, customer: customer]).count()
        info.overduePaymentCount = PaymentRepository.query([status: PaymentStatus.OVERDUE, customer: customer]).count()
        info.receivedPaymentCount = PaymentRepository.query([status: PaymentStatus.RECEIVED, customer: customer]).count()

        return info
    }
}
