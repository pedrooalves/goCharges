function TableController() {
    var _this = this;
    _this.reference = $(".js-container");
    _this.tableRow = _this.reference.find(".js-table-row");

    _this.init = function() {
        _this.bindTableRow();
    };

    _this.bindTableRow = function() {
        _this.tableRow.on("click", function() {
            window.open(this.getAttribute("data-url"), "_self")
        });
    };

    _this.unbindTableRow = function() {
        _this.tableRow.off("click");
    };
};

$(document).ready(function() {
    var tableController = new TableController();
    tableController.init();
});