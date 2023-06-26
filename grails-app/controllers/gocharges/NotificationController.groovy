package gocharges

import gocharges.controller.base.BaseController
import grails.converters.JSON
import org.springframework.http.HttpStatus

class NotificationController extends BaseController {

    NotificationService notificationService

    def index() {
        ArrayList notificationList = notificationService.buildNotification([unreadOnly: false], getCurrentCustomer())
        render(view: "index", model: [notificationList: notificationList])
    }

    public hasUnreadNotifications() {
        ArrayList notificationList = notificationService.buildNotification([unreadOnly: true], getCurrentCustomer())
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