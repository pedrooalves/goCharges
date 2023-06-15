package gocharges.payment

import gocharges.Payment
import gocharges.payment.enums.PaymentStatus
import grails.gorm.DetachedCriteria

class PaymentRepository {

    public static DetachedCriteria<Payment> query(Map search) {
        DetachedCriteria<Payment> query = Payment.where {
            if (!Boolean.valueOf(search.ignoreCustomer) && !search.containsKey("customer")) {
                throw new RuntimeException("O atributo customer é obrigatório para executar a consulta.")
            }

            if (Boolean.valueOf(search.deletedOnly)) {
                eq("deleted", true)
            } else if (!Boolean.valueOf(search.includeDeleted)) {
                eq("deleted", false)
            }

            if (search.containsKey("id")) {
                eq("id", Long.valueOf(search.id))
            }

            if (search.containsKey("dueDate[le]")) {
                le("dueDate", search."dueDate[le]")
            }

            if (search.containsKey("customer")) {
                eq("customer", search.customer)
            }

            if (search.containsKey("status")) {
                eq("status", PaymentStatus.valueOf(search.status.toString()))
            }

            if (search.containsKey("publicId")) {
                eq("publicId", search.publicId)
            }

            if (search.containsKey("countDistinct")) {
                projections {
                    countDistinct(search.countDistinct)
                }
            }
        }

        return query
    }
}