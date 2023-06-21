function ModalController() {
    var _this = this;
    _this.reference = $(".js-payment-list-container");
    _this.confirmReceivedInCashButton = _this.reference.find(".js-btn-confirm-received-in-cash");

    _this.init = function() {
        _this.setConfirmReceivedInCashButton()
        _this.setConfirmReceivedInCashModal()
    };

    _this.setConfirmReceivedInCashModal = function() {
        _this.confirmReceivedInCashButton.on("click", function() {
           _this.reference.find(".js-confirm-received-in-cash-modal-btn-value").val(this.value);
        });
    };

    _this.setConfirmReceivedInCashButton = function() {
        _this.confirmReceivedInCashButton.attr("data-toggle", "modal").attr("data-target", "#receivedInCashModal");
    };
};

$(document).ready(function() {
    var modalController = new ModalController();
    modalController.init();
});