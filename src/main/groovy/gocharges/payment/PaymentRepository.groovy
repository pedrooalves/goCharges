package gocharges.Payment

import gocharges.Payment
import grails.gorm.DetachedCriteria

class PaymentRepository {

    public static DetachedCriteria<Payment> query(Map search) {
        DetachedCriteria<Payment> query = Payment.where {

        }
        return query
    }
}