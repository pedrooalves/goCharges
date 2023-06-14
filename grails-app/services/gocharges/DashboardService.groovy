package gocharges

import gocharges.payer.PayerRepository
import gocharges.payment.PaymentRepository
import gocharges.payment.enums.PaymentStatus
import grails.gorm.transactions.Transactional

@Transactional
class DashboardService {

    public Map buildAccountInfo(Customer customer) {
        Map accountInfo = [:]
        accountInfo.overduePayerCount = PaymentRepository.query([status: PaymentStatus.OVERDUE, customer: customer, countDistinct: "payer"]).get()
        accountInfo.compliantPayerCount = PayerRepository.query([customer: customer]).count() - accountInfo.overduePayerCount

        accountInfo.pendingPaymentCount = PaymentRepository.query([status: PaymentStatus.PENDING, customer: customer]).count()
        accountInfo.overduePaymentCount = PaymentRepository.query([status: PaymentStatus.OVERDUE, customer: customer]).count()
        accountInfo.receivedPaymentCount = PaymentRepository.query([status: PaymentStatus.RECEIVED, customer: customer]).count()

        accountInfo.pendingRevenueAmount = PaymentRepository.query([status: PaymentStatus.PENDING, customer: customer]).property("value").sum()
        accountInfo.receivedRevenueAmount = PaymentRepository.query([status: PaymentStatus.RECEIVED, customer: customer]).property("value").sum()

        return accountInfo
    }
}
