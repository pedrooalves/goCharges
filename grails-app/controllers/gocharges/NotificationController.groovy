package gocharges

import gocharges.controller.base.BaseController
import grails.converters.JSON
import org.springframework.http.HttpStatus

class NotificationController extends BaseController {

    NotificationService notificationService

    def index() {
        List<Notification> notificationList = notificationService.list([unreadOnly: false], getCurrentCustomer())
        render(view: "index", model: [notificationList: notificationList])
    }

    public hasUnreadNotifications() {
        List<Notification> notificationList = notificationService.list([unreadOnly: true], getCurrentCustomer())
        render(notificationList as JSON, status: HttpStatus.OK)
    }

    public markAsRead() {
        try {
            println(params)
            notificationService.markAsRead(params.id, getCurrentCustomer())
            render([Message: "hello"] as JSON, status: HttpStatus.OK)
        } catch (Exception exception) {
            println(exception.getMessage())
        }
    }
}