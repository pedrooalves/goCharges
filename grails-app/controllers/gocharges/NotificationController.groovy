package gocharges

import gocharges.controller.base.BaseController

class NotificationController extends BaseController {

    NotificationService notificationService

    def index() {
        List<Notification> notificationList = notificationService.list(getCurrentCustomer())
        render(view: "index", model: [notificationList: notificationList])
    }
}