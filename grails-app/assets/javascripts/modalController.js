function ModalController() {
    var _this = this;
    _this.reference = $(".js-payment-list-container");
    _this.confirmReceivedInCashButton = _this.reference.find(".js-btn-confirm-received-in-cash");


    _this.init = function() {
        console.log(_this.confirmReceivedInCashButton)
        _this.setConfirmReceivedInCashModal()
        _this.setConfirmReceivedInCashButton()
    };

    _this.setConfirmReceivedInCashModal = function() {
        _this.reference.find(".js-confirm-received-in-cash-anchor").attr("href", `/payment/confirmReceivedInCash/${_this.confirmReceivedInCashButton.val()}`);
    };

    _this.setConfirmReceivedInCashButton = function() {
        _this.confirmReceivedInCashButton.attr("data-toggle", "modal").attr("data-target", "#receivedInCashModal");
    };
};

$(document).ready(function() {
    var modalController = new ModalController();
    modalController.init();
});