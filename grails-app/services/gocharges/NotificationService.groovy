package gocharges

import gocharges.notification.NotificationRepository
import grails.gorm.transactions.Transactional

@Transactional
class NotificationService {

    public List<Notification> list(Customer customer) {
        return NotificationRepository.query(customer: customer).list()
    }
}