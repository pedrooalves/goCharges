function ModalController() {
    var _this = this;
    _this.reference = $(".js-list-container");
    _this.openModalButton = _this.reference.find(".js-btn-open-modal");
    _this.confirmReceivedInCashButton = _this.reference.find(".js-btn-confirm-received-in-cash");
    _this.confirmDeleteButton = _this.reference.find(".js-btn-confirm-delete")

    var tableController = new TableController();

    _this.init = function() {
        _this.setModalOpenButton()
        _this.bindOpenModal()
        _this.bindModalHideButton()
        _this.handleModalClose()
    };

    _this.bindModalHideButton = function() {
        _this.reference.find(".js-btn-modal-hide").on("click", function() {
            _this.reference.find(".js-modal").modal("hide");
        })
    }

    _this.handleModalClose = function() {
        _this.reference.find(".js-modal").on("hide.bs.modal", function() {
            tableController.bindTableRow();
        })
    }

    _this.setModalOpenButton = function() {
        _this.confirmReceivedInCashButton.attr("data-toggle", "modal").attr("data-target", "#receivedInCashModal");
        _this.confirmDeleteButton.attr("data-toggle", "modal").attr("data-target", "#deleteModal");
    };

    _this.bindOpenModal = function() {
        _this.openModalButton.on("click", function() {
            tableController.unbindTableRow();
            $(this.getAttribute("data-target")).find(".js-btn-modal-value").val(this.value);
        });
    };
};

$(document).ready(function() {
    var modalController = new ModalController();
    modalController.init();
});