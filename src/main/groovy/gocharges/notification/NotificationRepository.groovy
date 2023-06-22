package gocharges.notification

import gocharges.Notification
import grails.gorm.DetachedCriteria

class NotificationRepository {

    public static DetachedCriteria<Notification> query(Map search) {
        DetachedCriteria<Notification> query = Notification.where {
            if (!Boolean.valueOf(search.ignoreCustomer) && !search.containsKey("customer")) {
                throw new RuntimeException("O atributo customer é obrigatório para executar a consulta.")
            }

            if (search.containsKey("customer")) {
                eq("customer", search.customer)
            }

            if (search.containsKey("unread")) {
                eq("unread", Boolean.valueOf(search.unread))
            }
        }
        return query
    }
}
