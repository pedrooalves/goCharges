function MainController() {
    _this = this;
    _this.reference = $(".js-main-container");
    _this.notification = _this.reference.find(".js-notification");
    _this.notificationDropdown = _this.reference.find(".js-dropdown");

    _this.init = function() {
    _this.getUserNotification();
    _this.bindNotificationContainer();
    _this.bindNotificationButton();
    }

    _this.getUserNotification = function() {
        $.ajax({
            type: "GET",
            url: "/notification/hasUnreadNotifications",
            dataType: "json",
            success: (data) => {
                if (data.length < 1) {
                    return
                }

                _this.notification.attr("src", "/assets/bell-active.svg")

                for (const item of data) {
                    _this.setNotificationDropdownContent(item)
                }
            }
        });
    };

    _this.bindNotificationContainer = function() {
        _this.notification.addClass("dropdown");
    };

    _this.bindNotificationButton = function() {
       var notificationButton = _this.reference.find(".js-btn-notification")
       notificationButton.attr("data-mdb-toggle", "dropdown");
       notificationButton.attr("aria-expanded", false);
    }

    _this.setNotificationDropdownContent = function(notificationItem) {
        _this.notificationDropdown.append(``)
    }
}

$(document).ready(function(){
    var mainController = new MainController();
    mainController.init();
});