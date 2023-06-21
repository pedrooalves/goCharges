package gocharges

import gocharges.controller.base.BaseController

class NotificationController extends BaseController {

    NotificationService notificationService

    def index() {
        List<Notification> notificationList = NotificationService.list(getCurrentCustomer())
        render(view: "index")
    }
}