function MainController() {
    _this = this;
    _this.reference = $(".js-main-container");
    _this.notification = _this.reference.find(".js-bell");

    _this.init = function() {
        $.ajax({
            type: "POST",
            url: "/notification/hasUnreadNotifications",
            dataType: "json",
            success: (data) => {
                if (data.length > 0) {
                    _this.notification.attr("src", "/assets/bell-active.svg")
                }
            }
        });
    }
}

$(document).ready(function(){
    var mainController = new MainController();
    mainController.init();
});