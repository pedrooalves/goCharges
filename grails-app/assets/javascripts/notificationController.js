function NotificationController() {
    var _this = this;
    _this.reference = $(".js-notification-index-container");
    _this.selectAllCheckbox = _this.reference.find(".js-select-all");
    _this.notificationList = _this.reference.find(".js-notification");

    _this.init = function() {
        _this.notificationList.on("click", function() {
            window.open(this.getAttribute("data-url"), "_self")
        });
        _this.bindSelectAllCheckBox()
    };

    _this.bindSelectAllCheckBox = function() {
        _this.selectAllCheckbox.on("click", function() {
            var selected = this.checked;

            $(":checkbox").each(function() {
                this.checked = selected;
            });
        });
    };
}

$(document).ready(function() {
    var notificationController = new NotificationController();
    notificationController.init();
});