package gocharges

import gocharges.payment.PaymentRepository
import grails.gorm.transactions.Transactional

@Transactional
class PaymentReceiptService {

    public Map create(String publicId, Customer customer) {
        Map info = [:]
        info.customer = customer
        info.payment = PaymentRepository.query([publicId: publicId, customer: customer]).get()
        info.payer = info.payment.payer

        return info
    }
}
