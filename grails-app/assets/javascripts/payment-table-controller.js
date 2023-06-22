function PaymentTableController() {
    _this = this;
    _this.reference = $(".js-body");
    _this.rowList = _this.reference.find(".js-row");

    _this.init = function() {
        _this.rowList.on("click", function() {
            window.open(this.getAttribute("data-url"), "_self")
        });
    }
}

$(document).ready(function(){
    var paymentTableController = new PaymentTableController();
    paymentTableController.init();
});