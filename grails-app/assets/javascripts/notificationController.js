function NotificationController() {
    var _this = this;
    _this.reference = $(".js-notification-index-container");
    _this.selectAllCheckbox = _this.reference.find(".js-select-all");
    _this.notificationList = _this.reference.find(".js-notification");

    _this.init = function() {
        _this.highlightNotifications();

        _this.notificationList.on("click", function() {
            $.post("/notification/markAsRead", {id: this.getAttribute("data-id")});
            window.open(this.getAttribute("data-url"), "_self");
        });
    };

    _this.highlightNotifications = function() {
        _this.reference.find(".js-read-false").find("span").addClass("font-weight-bold");
        _this.reference.find(".js-read-true").find("span").addClass("text-muted");
    }
}

$(document).ready(function() {
    var notificationController = new NotificationController();
    notificationController.init();
});