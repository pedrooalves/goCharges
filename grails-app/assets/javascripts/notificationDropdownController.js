function NotificationDropdownController() {
    var _this = this;
    _this.reference = $(".js-main-container");
    _this.notification = _this.reference.find(".js-notification");
    _this.notificationDropdown = _this.reference.find(".js-dropdown");

    _this.init = function() {
    _this.getUserNotification();
    _this.bindNotificationContainer();
    _this.bindNotificationButton();
    _this.bindNotification();
    }

    _this.getUserNotification = function() {
        $.ajax({
            type: "GET",
            url: "/notification/hasUnreadNotifications",
            dataType: "json",
            success: (data) => {
                if (data.length < 1) {
                }

                _this.notification.attr("src", "/assets/bell-active.svg")

                for (const item of data) {
                    _this.setNotificationDropdownContent(item)
                }
                console.log("acabou de mandar")
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
    };

    _this.setNotificationDropdownContent = function(notificationItem) {
        _this.notificationDropdown.append(`
            <a class="text-decoration-none" href="/payment/show/${notificationItem.id}">
                <div class="d-flex flex-column px-2 mb-4 bg-white rounded-lg mx-2" style="cursor: pointer">
                    <span class="font-weight-bold text-dark" style="font-size: 1rem;">
                        ${notificationItem.title}
                    </span>
                    <span class="text-dark" style="font-size: 1rem;">
                        ${notificationItem.message}
                    </span>
                </div>
            </a>
        `)
    };
}

$(document).ready(function(){
    var notificationDropdownController = new NotificationDropdownController();
    notificationDropdownController.init();
});