function ToastController() {
    var _this = this;
    _this.reference = $(".js-main-container");
    _this.feedbackToast = _this.reference.find(".js-feedback-toast")

    _this.init = function() {
        _this.feedbackToast.toast('show')
    }

    _this.setFeedBackToast = function() {
        _this.feedbackToast.toast('show')
    }
}

$(document).ready(function() {
    var toastController = new ToastController();
    toastController.init();
});