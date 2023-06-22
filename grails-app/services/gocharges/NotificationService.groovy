package gocharges

import gocharges.notification.NotificationRepository
import gocharges.notification.NotificationType
import grails.gorm.transactions.Transactional

@Transactional
class NotificationService {

    public void confirmPayment(Payment payment) {
        Notification notification = new Notification()
        notification.notificationType = NotificationType.PAID
        notification.customer = payment.customer
        notification.payment = payment

        notification.save(failOnError: true)
    }

    public List<Notification> list(Customer customer) {
        return NotificationRepository.query(customer: customer).list()
    }
}