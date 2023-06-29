package gocharges

import gocharges.controller.base.BaseController
import grails.converters.JSON
import org.springframework.http.HttpStatus

class NotificationController extends BaseController {

    NotificationService notificationService

    def index() {
        ArrayList notificationList = notificationService.list([unreadOnly: false], getCurrentCustomer())
        render(view: "index", model: [notificationList: notificationList])
    }

    def getLastUnreadNotificationList() {
        ArrayList notificationList = notificationService.buildNotification(getCurrentCustomer())
        render(notificationList as JSON, status: HttpStatus.OK)
    }

    public markAsRead() {
        try {
            notificationService.markAsRead(params.id, getCurrentCustomer())
            render([Message: "Notification marked as read successfully"] as JSON, status: HttpStatus.OK)
        } catch (Exception exception) {
            println(exception.getMessage())
        }
    }
}