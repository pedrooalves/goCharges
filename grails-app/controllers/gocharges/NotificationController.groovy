package gocharges

import gocharges.controller.base.BaseController
import grails.converters.JSON
import org.springframework.http.HttpStatus

class NotificationController extends BaseController {

    NotificationService notificationService

    def index() {
        ArrayList notificationList = notificationService.buildNotification(getCurrentCustomer())
        render(view: "index", model: [notificationList: notificationList])
    }

    public hasUnreadNotifications() {
        ArrayList notificationList = notificationService.buildNotification(getCurrentCustomer())
        render(notificationList as JSON, status: HttpStatus.OK)
    }
}