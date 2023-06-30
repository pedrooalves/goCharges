package gocharges

import gocharges.notification.NotificationRepository
import gocharges.notification.NotificationType
import grails.gorm.transactions.Transactional
import shared.Utils

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

    public Notification markAsRead(Long id, Customer customer) {
        Notification notification = NotificationRepository.query([id: id, customer: customer]).get()
        notification.isRead = true
        notification.save(failOnError: true)

        return notification
    }

    public ArrayList buildNotification(Map params, Customer customer) {
        List<Notification> notificationList = list(params, customer)
        ArrayList formattedNotificationList = []

        for (Notification notification : notificationList) {
             Map formattedNotification  = [
                id: notification.id,
                paymentId: notification.payment.id,
                isRead: notification.isRead,
                title: Utils.getMessageProperty("NotificationType.title.${notification.notificationType.toString()}",null),
                message: Utils.getMessageProperty("NotificationType.message.${notification.notificationType.toString()}",
                        new String[] {notification.payment.payer.name, Utils.getCurrencyWithoutMonetarySimbol(notification.payment.value)}),
                date: Utils.getBrazilDateFormat(notification.dateCreated)
            ]

            formattedNotificationList.push(formattedNotification)
        }

        return formattedNotificationList
    }
}