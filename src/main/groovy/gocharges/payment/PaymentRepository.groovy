package gocharges.payment

import gocharges.Payment
import grails.gorm.DetachedCriteria

class PaymentRepository {

    public static DetachedCriteria<Payment> query(Map search) {
        DetachedCriteria<Payment> query = Payment.where {
            if(!Boolean.valueOf(search.includeDeleted)) {
                eq("deleted", false)
            }

            if(search.containsKey("id")) {
                eq("id", search.id)
            }

            if(search.containsKey("dueDate[le]")) {
                le("dueDate", search."dueDate[le]")
            }

            if(search.containsKey("status")) {
                eq("status", search.status)
            }
        }

        return query
    }
}