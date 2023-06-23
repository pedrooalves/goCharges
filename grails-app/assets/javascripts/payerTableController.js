function PayerTableController() {
    var _this = this;
    _this.reference = $(".js-payer-index-container");
    _this.tableRow = _this.reference.find(".js-table-row");

    _this.init = function() {
        _this.setTableRow()
    }

    _this.setTableRow = function() {
        _this.tableRow.on("click", function() {
            window.open(this.getAttribute("data-url"), "_self")
        })
    }
}

$(document).ready(function() {
    var payerTableController = new PayerTableController();
    payerTableController.init();
});