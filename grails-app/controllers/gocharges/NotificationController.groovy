package gocharges

import gocharges.controller.base.BaseController
import gocharges.notification.NotificationRepository
import grails.converters.JSON
import org.springframework.http.HttpStatus

class NotificationController extends BaseController {

    NotificationService notificationService

    def index() {
        ArrayList notificationList = notificationService.buildNotification(getCurrentCustomer())
        render(view: "index", model: [notificationList: notificationList])
    }

    def getLastUnreadNotificationList() {
        ArrayList notificationList = notificationService.buildNotification(getCurrentCustomer())
        render(notificationList as JSON, status: HttpStatus.OK)
    }

    def show() {
        Long id = Long.valueOf(params.id)
        Notification notification = notificationService.markAsRead(id, getCurrentCustomer())

        redirect(controller: "payment", action: "show", params: [id: notification.payment.id])
    }
}