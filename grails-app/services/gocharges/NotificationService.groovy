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

    public List<Notification> list(Map params, Customer customer) {
        return NotificationRepository.query(params + [customer: customer]).order("dateCreated", "desc").list()
    }

    public void overduePayment(Payment payment) {
        Notification notification = new Notification()
        notification.notificationType = NotificationType.OVERDUE
        notification.customer = payment.customer
        notification.payment = payment

        notification.save(failOnError: true)
    }

    public void markAsRead(String idStr, Customer customer) {
        Notification notification = NotificationRepository.query([id: idStr, customer: customer]).get()
        notification.isRead = true
        notification.save(failOnError: true)
    }
}