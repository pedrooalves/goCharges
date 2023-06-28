function PaymentTableController() {
    _this = this;
    _this.reference = $(".js-body");
    _this.rowList = _this.reference.find(".js-row");

    _this.init = function() {
        _this.bindTableRow()
    }

    _this.bindTableRow = function() {
        _this.rowList.on("click", function() {
            window.open(this.getAttribute("data-url"), "_self")
        });
    }

    _this.disableTableOnClickEvent = function() {
        _this.rowList.off("click");
    }
}

$(document).ready(function(){
    var paymentTableController = new PaymentTableController();
    paymentTableController.init();
});